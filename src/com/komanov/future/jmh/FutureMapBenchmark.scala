package com.komanov.future.jmh

import java.util.concurrent.{Executors, ThreadFactory, TimeUnit}

import com.komanov.future._
import org.openjdk.jmh.annotations._

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

@State(Scope.Thread)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 2, timeUnit = TimeUnit.SECONDS)
class FutureMapBenchmark {

  val executor = Executors.newSingleThreadExecutor(new ThreadFactory {
    override def newThread(r: Runnable) = {
      val thread = new Thread(r, "single")
      thread.setDaemon(true)
      thread
    }
  })
  val singleThreadExecutionContext = ExecutionContext.fromExecutorService(executor)

  @Benchmark
  def direct(): Unit = {
    implicit def ec: ExecutionContext = directExecutionContext

    var future = Future.successful(0)
    for (_ <- 1 to 100) {
      future = future.map(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == 100)
  }

  @Benchmark
  def smart(): Unit = {
    import FutureMapBenchmark.Implicits.throwingEc

    var future = Future.successful(0)
    for (_ <- 1 to 100) {
      future = future.smartMap(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == 100)
  }

  @Benchmark
  def realSingleThread(): Unit = {
    implicit def ec: ExecutionContext = singleThreadExecutionContext

    var future = Future.successful(0)
    for (_ <- 1 to 100) {
      future = future.map(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == 100)
  }

  @Benchmark
  def realGlobal(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    var future = Future.successful(0)
    for (_ <- 1 to 100) {
      future = future.map(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == 100)
  }
}

object FutureMapBenchmark {
  object Implicits {
    implicit val throwingEc: ExecutionContext = ExecutionContext.fromExecutor((_: Runnable) => throw new IllegalStateException())
  }
}
