package com.komanov.blockingqueue.jmh

import java.util
import java.util.concurrent._
import java.util.concurrent.atomic.AtomicInteger

import com.komanov.blockingqueue.jmh.SharedState._
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.Throughput))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS)
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

  private var blockingQueue: BlockingQueue[String] = _
  private var barrier: CountDownLatch = _

  @Setup(Level.Invocation)
  def setup(): Unit = {
    blockingQueue = queueType.create(capacity)
    barrier = new CountDownLatch(1)
    setupProducers()
    Thread.sleep(0)
  }

  @Benchmark
  def take(): Unit = {
    barrier.countDown()

    val blockingQueue = this.blockingQueue
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
    c.incrementInvocations()

    val list = new util.ArrayList[String](capacity)
    barrier.countDown()

    val blockingQueue = this.blockingQueue
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
    c.addSize(avg)
    c.addBlockedCount(blockedCount)
  }

  private def setupProducers(): Unit = {
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
  private var invocations = 0
  private var totalSize: Int = 0
  private var totalBlockedCount: Int = 0

  def getAverageSize = totalSize / invocations
  def getBlockedCount = totalBlockedCount / invocations

  def incrementInvocations(): Unit = {
    invocations += 1
  }

  def addSize(size: Int): Unit = {
    totalSize += size
  }

  def addBlockedCount(count: Int): Unit = {
    totalBlockedCount += count
  }
}
