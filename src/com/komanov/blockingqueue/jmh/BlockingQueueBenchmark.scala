package com.komanov.blockingqueue.jmh

import java.util
import java.util.concurrent._
import java.util.concurrent.atomic.AtomicInteger

import com.komanov.blockingqueue.jmh.SharedState._
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
class BlockingQueueBenchmark {

  @Param
  var queueType: QueueType = _
  @Param(Array("1", "2", "3", "4"))
  var producerThreadCount: Int = _
  @Param(Array("100000", "1000000"))
  var messagesPerProducer: Int = _
  @Param(Array("16", "128", "1024", "10240"))
  var capacity: Int = _
  @Param(Array("0", "10"))
  var producerTokens: Long = _
  @Param(Array("0", "10"))
  var consumerTokens: Long = _

  @Benchmark
  def take(): Unit = {
    val blockingQueue = queueType.create(capacity)

    startProducers(blockingQueue)

    val tokens = consumerTokens
    var stopCount = producerThreadCount
    while (stopCount > 0) {
      val v = blockingQueue.take()
      Blackhole.consumeCPU(tokens)
      if (v eq EndMarker) {
        stopCount -= 1
      }
    }
  }

  @Benchmark
  def drain(c: Counters): Unit = {
    val list = new util.ArrayList[String](capacity)
    val blockingQueue = new ArrayBlockingQueue[String](capacity)

    startProducers(blockingQueue)

    val tokens = consumerTokens
    var stopCount = producerThreadCount
    var iteration = 0
    var blockedCount = 0
    while (stopCount > 0) {
      blockingQueue.drainTo(list)
      iteration += 1
      if (list.size() == 0) {
        blockedCount += 1
        val v = blockingQueue.take()
        Blackhole.consumeCPU(tokens)
        if (v eq EndMarker) {
          stopCount -= 1
        }
      } else {
        list.forEach { v =>
          Blackhole.consumeCPU(tokens)
          if (v eq EndMarker) {
            stopCount -= 1
          }
        }
        list.clear()
      }
    }

    val avg = (producerThreadCount * messagesPerProducer) / iteration
    c.averageSize = if (c.averageSize == 0) avg else (avg + c.averageSize) / 2
    c.blockedCount = if (c.blockedCount == 0) blockedCount else (blockedCount + c.blockedCount) / 2
  }

  private def startProducers(blockingQueue: BlockingQueue[String]): Unit = {
    val barrier = new CountDownLatch(1)
    val tokens = producerTokens
    for (i <- 1 to producerThreadCount) {
      threadPool.submit(() => {
        val v = i.toString
        barrier.await()
        for (_ <- 1 to messagesPerProducer) {
          Blackhole.consumeCPU(tokens)
          blockingQueue.put(v)
        }
        blockingQueue.put(EndMarker)
        true
      })
    }
    barrier.countDown()
  }
}

object SharedState {
  val EndMarker = ""

  val threadPool: ExecutorService = Executors.newFixedThreadPool(4, new ThreadFactory {
    val count = new AtomicInteger()
    override def newThread(r: Runnable): Thread = {
      val t = new Thread(r, s"thread-pool-" + count.incrementAndGet())
      t.setDaemon(true)
      t
    }
  })
}

@AuxCounters(AuxCounters.Type.EVENTS)
@State(Scope.Thread)
class Counters {
  var averageSize: Int = 0
  var blockedCount: Int = 0
}
