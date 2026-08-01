package com.komanov.metrics.jmh

import com.komanov.metrics._
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit
import scala.concurrent.duration._

/*
Benchmark                                              (rps)  Mode  Cnt    Score    Error  Units
SimulationBenchmark.arraySimulation                        1  avgt   10    0.943 ±  0.234  us/op
SimulationBenchmark.arraySimulation                       10  avgt   10    3.901 ±  0.249  us/op
SimulationBenchmark.arraySimulation                      100  avgt   10   34.057 ±  1.959  us/op
SimulationBenchmark.arraySimulation                     1000  avgt   10  346.856 ± 10.676  us/op
SimulationBenchmark.fixedArrayOfAtomicSimulation           1  avgt   10    2.218 ±  0.066  us/op
SimulationBenchmark.fixedArrayOfAtomicSimulation          10  avgt   10    3.723 ±  0.018  us/op
SimulationBenchmark.fixedArrayOfAtomicSimulation         100  avgt   10   10.687 ±  0.037  us/op
SimulationBenchmark.fixedArrayOfAtomicSimulation        1000  avgt   10   50.237 ±  0.896  us/op
SimulationBenchmark.fixedAtomicArraySimulation             1  avgt   10    1.419 ±  0.014  us/op
SimulationBenchmark.fixedAtomicArraySimulation            10  avgt   10    2.910 ±  0.008  us/op
SimulationBenchmark.fixedAtomicArraySimulation           100  avgt   10   11.806 ±  0.066  us/op
SimulationBenchmark.fixedAtomicArraySimulation          1000  avgt   10   87.006 ±  1.247  us/op
SimulationBenchmark.fixedThreadUnsafeInlineSimulation      1  avgt   10    0.589 ±  0.006  us/op
SimulationBenchmark.fixedThreadUnsafeInlineSimulation     10  avgt   10    1.598 ±  0.002  us/op
SimulationBenchmark.fixedThreadUnsafeInlineSimulation    100  avgt   10    8.439 ±  0.018  us/op
SimulationBenchmark.fixedThreadUnsafeInlineSimulation   1000  avgt   10   73.977 ±  0.143  us/op
SimulationBenchmark.fixedThreadUnsafeSimulation            1  avgt   10    0.588 ±  0.003  us/op
SimulationBenchmark.fixedThreadUnsafeSimulation           10  avgt   10    1.604 ±  0.001  us/op
SimulationBenchmark.fixedThreadUnsafeSimulation          100  avgt   10    9.104 ±  0.096  us/op
SimulationBenchmark.fixedThreadUnsafeSimulation         1000  avgt   10   80.674 ±  0.362  us/op
SimulationBenchmark.linkedListSimulation                   1  avgt   10    1.107 ±  0.005  us/op
SimulationBenchmark.linkedListSimulation                  10  avgt   10    7.791 ±  0.230  us/op
SimulationBenchmark.linkedListSimulation                 100  avgt   10   64.933 ±  1.344  us/op
SimulationBenchmark.linkedListSimulation                1000  avgt   10  733.204 ±  2.764  us/op
SingleOpBenchmark.arrayGetOnly                           N/A  avgt   10  145.194 ±  3.882  ns/op
SingleOpBenchmark.arraySingleUpdate                      N/A  avgt   10  331.770 ±  3.530  ns/op
SingleOpBenchmark.fixedArrayOfAtomicGetOnly              N/A  avgt   10    5.303 ±  0.027  ns/op
SingleOpBenchmark.fixedArrayOfAtomicSingleUpdate         N/A  avgt   10  175.474 ±  2.024  ns/op
SingleOpBenchmark.fixedAtomicArrayGetOnly                N/A  avgt   10    5.291 ±  0.011  ns/op
SingleOpBenchmark.fixedAtomicArraySingleUpdate           N/A  avgt   10  139.071 ±  1.662  ns/op
SingleOpBenchmark.fixedThreadUnsafeGetOnly               N/A  avgt   10    7.945 ±  0.024  ns/op
SingleOpBenchmark.fixedThreadUnsafeInlineGetOnly         N/A  avgt   10    5.298 ±  0.015  ns/op
SingleOpBenchmark.fixedThreadUnsafeInlineSingleUpdate    N/A  avgt   10  145.407 ±  3.305  ns/op
SingleOpBenchmark.fixedThreadUnsafeSingleUpdate          N/A  avgt   10  152.163 ±  9.188  ns/op
SingleOpBenchmark.linkedListGetOnly                      N/A  avgt   10  111.357 ±  0.644  ns/op
SingleOpBenchmark.linkedListSingleUpdate                 N/A  avgt   10  224.517 ±  2.137  ns/op
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
