package com.komanov.future.jmh

import java.util.concurrent.{Executors, TimeUnit}

import com.komanov.future.{trampoline => trampolineExecutionContext, _}
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

  val N = 100

  val executor = Executors.newSingleThreadExecutor((r: Runnable) => {
    val thread = new Thread(r, "single")
    thread.setDaemon(true)
    thread
  })
  val singleThreadExecutionContext = ExecutionContext.fromExecutorService(executor)

  @Benchmark
  def direct(): Unit = {
    implicit def ec: ExecutionContext = directExecutionContext

    var future = Future.successful(0)
    for (_ <- 1 to N) {
      future = future.map(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == N)
  }

  @Benchmark
  def trampoline(): Unit = {
    implicit def ec: ExecutionContext = trampolineExecutionContext

    var future = Future.successful(0)
    for (_ <- 1 to N) {
      future = future.map(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == N)
  }

  @Benchmark
  def smart(): Unit = {
    import FutureMapBenchmark.Implicits.throwingEc

    var future = Future.successful(0)
    for (_ <- 1 to N) {
      future = future.smartMap(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == N)
  }

  @Benchmark
  def realSingleThread(): Unit = {
    implicit def ec: ExecutionContext = singleThreadExecutionContext

    var future = Future.successful(0)
    for (_ <- 1 to N) {
      future = future.map(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == N)
  }

  @Benchmark
  def realGlobal(): Unit = {
    import scala.concurrent.ExecutionContext.Implicits.global

    var future = Future.successful(0)
    for (_ <- 1 to N) {
      future = future.map(v => v + 1)
    }
    require(Await.result(future, 10.seconds) == N)
  }
}

object FutureMapBenchmark {
  object Implicits {
    implicit val throwingEc: ExecutionContext = ExecutionContext.fromExecutor((_: Runnable) => throw new IllegalStateException())
  }
}
