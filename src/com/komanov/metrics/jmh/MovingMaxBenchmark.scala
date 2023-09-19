package com.komanov.metrics.jmh

import com.komanov.metrics._
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._

/*
  */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx1G"))
@Threads(2)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
abstract class MovingMaxBenchmarkBase {
  protected val clock = new FakeClock
  protected val increment: Long = 500.millis.toNanos

  protected val linkedListMovingMax = new SlidingTimeWindowMovingMax(clock, 1.minute)
  protected val arrayMovingMax = new SlidingTimeWindowArrayMovingMax(clock, 1.minute)
  protected val fixedThreadUnsafeMm = new ThreadUnsafeFixedMovingMax(clock, 1.minute, 1.second)
  protected val fixedThreadUnsafeInlineMm = new ThreadUnsafeInlineFixedMovingMax(clock, 1.minute, 1.second)
  protected val fixedArrayOfAtomicMm = new ArrayOfAtomicFixedMovingMax(clock, 1.minute, 1.second)
  protected val fixedAtomicArrayMm = new AtomicArrayFixedMovingMax(clock, 1.minute, 1.second)

  @Setup
  def init(): Unit = {
    require(linkedListSimulationImpl(1) == arraySimulationImpl(1))
    require(fixedThreadUnsafeSimulationImpl(1) == arraySimulationImpl(1))
    require(fixedThreadUnsafeInlineSimulationImpl(1) == arraySimulationImpl(1))
    require(fixedArrayOfAtomicSimulationImpl(1) == arraySimulationImpl(1))
    require(fixedAtomicArraySimulationImpl(1) == arraySimulationImpl(1))
  }

  private def simulate2MinutesOfUpdatesAndGet(mm: MovingMax, rps: Int): Long = {
    var i, j = 1
    while (i <= 120) {
      clock.moveForward(increment)
      while (j <= rps) {
        mm.update(i)
        j += 1
      }
      clock.moveForward(increment)
      i += 1
    }
    mm.getMaxInWindow
  }

  protected def linkedListSimulationImpl(rps: Int): Long =
    simulate2MinutesOfUpdatesAndGet(linkedListMovingMax, rps)

  protected def arraySimulationImpl(rps: Int): Long =
    simulate2MinutesOfUpdatesAndGet(arrayMovingMax, rps)

  protected def fixedThreadUnsafeSimulationImpl(rps: Int): Long =
    simulate2MinutesOfUpdatesAndGet(fixedThreadUnsafeMm, rps)

  protected def fixedThreadUnsafeInlineSimulationImpl(rps: Int): Long =
    simulate2MinutesOfUpdatesAndGet(fixedThreadUnsafeInlineMm, rps)

  protected def fixedArrayOfAtomicSimulationImpl(rps: Int): Long =
    simulate2MinutesOfUpdatesAndGet(fixedArrayOfAtomicMm, rps)

  protected def fixedAtomicArraySimulationImpl(rps: Int): Long =
    simulate2MinutesOfUpdatesAndGet(fixedAtomicArrayMm, rps)
}

class SingleOpBenchmark extends MovingMaxBenchmarkBase {
  @Benchmark
  def linkedListSingleUpdate(): Unit = {
    clock.moveForward(increment * 2)
    linkedListMovingMax.update(100500)
  }

  @Benchmark
  def linkedListGetOnly: Long = linkedListMovingMax.getMaxInWindow

  @Benchmark
  def arraySingleUpdate(): Unit = {
    clock.moveForward(increment * 2)
    arrayMovingMax.update(100500)
  }

  @Benchmark
  def arrayGetOnly: Long = arrayMovingMax.getMaxInWindow

  @Benchmark
  def fixedThreadUnsafeSingleUpdate(): Unit = {
    clock.moveForward(increment * 2)
    fixedThreadUnsafeMm.update(100500)
  }

  @Benchmark
  def fixedThreadUnsafeGetOnly: Long =
    fixedThreadUnsafeMm.getMaxInWindow

  @Benchmark
  def fixedThreadUnsafeInlineSingleUpdate(): Unit = {
    clock.moveForward(increment * 2)
    fixedThreadUnsafeInlineMm.update(100500)
  }

  @Benchmark
  def fixedThreadUnsafeInlineGetOnly: Long = fixedThreadUnsafeInlineMm.getMaxInWindow

  @Benchmark
  def fixedArrayOfAtomicSingleUpdate(): Unit = {
    clock.moveForward(increment * 2)
    fixedArrayOfAtomicMm.update(100500)
  }

  @Benchmark
  def fixedArrayOfAtomicGetOnly: Long = fixedArrayOfAtomicMm.getMaxInWindow

  @Benchmark
  def fixedAtomicArraySingleUpdate(): Unit = {
    clock.moveForward(increment * 2)
    fixedAtomicArrayMm.update(100500)
  }

  @Benchmark
  def fixedAtomicArrayGetOnly: Long = fixedAtomicArrayMm.getMaxInWindow
}

@OutputTimeUnit(TimeUnit.MICROSECONDS)
class SimulationBenchmark extends MovingMaxBenchmarkBase {
  @Param(Array(
    "1",
    "10",
    "100",
    "1000",
  ))
  var rps: Int = 0

  @Benchmark
  def linkedListSimulation: Long = linkedListSimulationImpl(rps)

  @Benchmark
  def arraySimulation: Long = arraySimulationImpl(rps)

  @Benchmark
  def fixedThreadUnsafeSimulation: Long = fixedThreadUnsafeSimulationImpl(rps)

  @Benchmark
  def fixedThreadUnsafeInlineSimulation: Long = fixedThreadUnsafeInlineSimulationImpl(rps)

  @Benchmark
  def fixedArrayOfAtomicSimulation: Long = fixedArrayOfAtomicSimulationImpl(rps)

  @Benchmark
  def fixedAtomicArraySimulation: Long = fixedAtomicArraySimulationImpl(rps)
}
