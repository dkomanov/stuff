package com.komanov.javaenum.jmh

import java.util.concurrent.TimeUnit

import com.komanov.javaenum.SimpleEnum;

import org.openjdk.jmh.annotations._

/*
openjdk-11:

Benchmark                             (idStr)  Mode  Cnt    Score   Error  Units
SimpleEnumBenchmark.arrayCopyForeach        4  avgt    2    5.019          ns/op
SimpleEnumBenchmark.arrayCopyForeach        5  avgt    2    5.498          ns/op
SimpleEnumBenchmark.arrayCopyForeach      all  avgt    2   24.281          ns/op
SimpleEnumBenchmark.arrayForeach            4  avgt    2    6.325          ns/op
SimpleEnumBenchmark.arrayForeach            5  avgt    2    6.328          ns/op
SimpleEnumBenchmark.arrayForeach          all  avgt    2   30.196          ns/op
SimpleEnumBenchmark.arrayStream             4  avgt    2   49.221          ns/op
SimpleEnumBenchmark.arrayStream             5  avgt    2   46.662          ns/op
SimpleEnumBenchmark.arrayStream           all  avgt    2  287.349          ns/op
SimpleEnumBenchmark.byIndex                 4  avgt    2    2.246          ns/op
SimpleEnumBenchmark.byIndex                 5  avgt    2    1.997          ns/op
SimpleEnumBenchmark.byIndex               all  avgt    2   12.728          ns/op
SimpleEnumBenchmark.byIndexOverCopy         4  avgt    2    1.748          ns/op
SimpleEnumBenchmark.byIndexOverCopy         5  avgt    2    1.997          ns/op
SimpleEnumBenchmark.byIndexOverCopy       all  avgt    2   12.543          ns/op
SimpleEnumBenchmark.reverseLookup           4  avgt    2    3.745          ns/op
SimpleEnumBenchmark.reverseLookup           5  avgt    2    3.762          ns/op
SimpleEnumBenchmark.reverseLookup         all  avgt    2   30.061          ns/op

openjdk-17:
Benchmark                             (idStr)  Mode  Cnt    Score   Error  Units
SimpleEnumBenchmark.arrayCopyForeach        4  avgt    2    4.166          ns/op
SimpleEnumBenchmark.arrayCopyForeach        5  avgt    2    5.069          ns/op
SimpleEnumBenchmark.arrayCopyForeach      all  avgt    2   18.716          ns/op
SimpleEnumBenchmark.arrayForeach            4  avgt    2    5.988          ns/op
SimpleEnumBenchmark.arrayForeach            5  avgt    2    5.779          ns/op
SimpleEnumBenchmark.arrayForeach          all  avgt    2   31.722          ns/op
SimpleEnumBenchmark.arrayStream             4  avgt    2   46.414          ns/op
SimpleEnumBenchmark.arrayStream             5  avgt    2   42.656          ns/op
SimpleEnumBenchmark.arrayStream           all  avgt    2  293.065          ns/op
SimpleEnumBenchmark.byIndex                 4  avgt    2    2.008          ns/op
SimpleEnumBenchmark.byIndex                 5  avgt    2    1.748          ns/op
SimpleEnumBenchmark.byIndex               all  avgt    2   11.002          ns/op
SimpleEnumBenchmark.byIndexOverCopy         4  avgt    2    1.504          ns/op
SimpleEnumBenchmark.byIndexOverCopy         5  avgt    2    1.540          ns/op
SimpleEnumBenchmark.byIndexOverCopy       all  avgt    2   11.516          ns/op
SimpleEnumBenchmark.reverseLookup           4  avgt    2    3.140          ns/op
SimpleEnumBenchmark.reverseLookup           5  avgt    2    2.746          ns/op
SimpleEnumBenchmark.reverseLookup         all  avgt    2   24.527          ns/op
*/
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
class SimpleEnumBenchmark {

  @Param(Array("1", "2", "3", "4", "5", "all"))
  var idStr = ""
  var ids = Seq(0)

  @Setup
  def test: Unit = {
    ids = if (idStr == "all") Seq(0, 1, 2, 3, 4, 5) else Seq(idStr.toInt)
    require(SimpleEnum.FIRST == SimpleEnum.fromInt(1));
    ids.foreach { id =>
      require(SimpleEnum.fromInt(id) == SimpleEnum.fromIntStream(id));
      require(SimpleEnum.fromInt(id) == SimpleEnum.fromIntLoop(id));
      require(SimpleEnum.fromInt(id) == SimpleEnum.fromIntLoopOverCopy(id));
      require(SimpleEnum.fromInt(id) == SimpleEnum.fromIndex(id));
      require(SimpleEnum.fromInt(id) == SimpleEnum.fromIndexOverCopy(id));
    }
  }

  @Benchmark
  def reverseLookup = {
    ids.foreach { id => SimpleEnum.fromInt(id) }
  }

  @Benchmark
  def arrayStream = {
    ids.foreach { id => SimpleEnum.fromIntStream(id) }
  }

  @Benchmark
  def arrayForeach = {
    ids.foreach { id => SimpleEnum.fromIntLoop(id) }
  }

  @Benchmark
  def arrayCopyForeach = {
    ids.foreach { id => SimpleEnum.fromIntLoopOverCopy(id) }
  }

  @Benchmark
  def byIndex = {
    ids.foreach { id => SimpleEnum.fromIndex(id) }
  }

  @Benchmark
  def byIndexLocalCopy = {
    ids.foreach { id => SimpleEnum.fromIndexOverLocalCopy(id) }
  }

  @Benchmark
  def byIndexOverCopy = {
    ids.foreach { id => SimpleEnum.fromIndexOverCopy(id) }
  }

  @Benchmark
  def bySwitch = {
    ids.foreach { id => SimpleEnum.fromSwitch(id) }
  }

}
