package com.komanov.str.jmh

import com.komanov.str.jmh.RegionMatchesBenchmarks._
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Benchmarks on 2022-04-23.

==> jdk8.txt <==
Benchmark                                             Mode  Cnt   Score    Error  Units
RegionMatchesBenchmarks.begin_regionMatches           avgt    3  10.304 ±  2.645  ns/op
RegionMatchesBenchmarks.begin_startsWith              avgt    3   9.230 ±  1.078  ns/op
RegionMatchesBenchmarks.begin_substringEquals         avgt    3  16.614 ±  7.549  ns/op
RegionMatchesBenchmarks.end_endsWith                  avgt    3  15.824 ±  0.493  ns/op
RegionMatchesBenchmarks.end_regionMatches             avgt    3  17.419 ±  1.110  ns/op
RegionMatchesBenchmarks.end_substringEquals           avgt    3  17.658 ±  3.065  ns/op
RegionMatchesBenchmarks.middle2Sides_regionMatches    avgt    3  12.772 ±  1.425  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEquals  avgt    3  26.953 ± 16.167  ns/op
RegionMatchesBenchmarks.middle_regionMatches          avgt    3  13.539 ± 10.458  ns/op
RegionMatchesBenchmarks.middle_substringEquals        avgt    3  18.547 ± 13.792  ns/op

==> jdk11.txt <==
Benchmark                                             Mode  Cnt   Score    Error  Units
RegionMatchesBenchmarks.begin_regionMatches           avgt    3  19.805 ±  3.764  ns/op
RegionMatchesBenchmarks.begin_startsWith              avgt    3   9.563 ±  0.303  ns/op
RegionMatchesBenchmarks.begin_substringEquals         avgt    3  19.113 ± 39.820  ns/op
RegionMatchesBenchmarks.end_endsWith                  avgt    3  19.369 ± 73.462  ns/op
RegionMatchesBenchmarks.end_regionMatches             avgt    3  33.213 ± 42.291  ns/op
RegionMatchesBenchmarks.end_substringEquals           avgt    3  18.069 ± 22.753  ns/op
RegionMatchesBenchmarks.middle2Sides_regionMatches    avgt    3  27.385 ±  2.929  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEquals  avgt    3  27.966 ± 21.588  ns/op
RegionMatchesBenchmarks.middle_regionMatches          avgt    3  14.823 ±  1.400  ns/op
RegionMatchesBenchmarks.middle_substringEquals        avgt    3  18.743 ±  5.262  ns/op

==> jdk17.txt <==
Benchmark                                             Mode  Cnt   Score    Error  Units
RegionMatchesBenchmarks.begin_regionMatches           avgt    3  18.439 ±  1.304  ns/op
RegionMatchesBenchmarks.begin_startsWith              avgt    3  14.220 ± 62.512  ns/op
RegionMatchesBenchmarks.begin_substringEquals         avgt    3  15.628 ±  4.725  ns/op
RegionMatchesBenchmarks.end_endsWith                  avgt    3  18.340 ± 92.010  ns/op
RegionMatchesBenchmarks.end_regionMatches             avgt    3  27.380 ±  2.431  ns/op
RegionMatchesBenchmarks.end_substringEquals           avgt    3  16.799 ±  7.159  ns/op
RegionMatchesBenchmarks.middle2Sides_regionMatches    avgt    3  15.535 ±  0.747  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEquals  avgt    3  26.481 ± 19.558  ns/op
RegionMatchesBenchmarks.middle_regionMatches          avgt    3  15.239 ±  1.310  ns/op
RegionMatchesBenchmarks.middle_substringEquals        avgt    3  15.655 ±  7.562  ns/op
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
class RegionMatchesBenchmarks {

  @Setup // instead of separate test :D
  def check(): Unit = {
    require(begin_startsWith())
    require(begin_regionMatches())
    require(begin_substringEquals())
    require(middle_regionMatches())
    require(middle_substringEquals())
    require(middle2Sides_regionMatches())
    require(middle2Sides_substringEquals())
    require(end_endsWith())
    require(end_regionMatches())
    require(end_substringEquals())
  }

  @Benchmark
  def begin_startsWith(): Boolean =
    uri.startsWith(begin)

  @Benchmark
  def begin_regionMatches(): Boolean =
    uri.regionMatches(0, begin, 0, begin.length)

  @Benchmark
  def begin_substringEquals(): Boolean =
    uri.substring(0, begin.length) == begin

  @Benchmark
  def middle_regionMatches(): Boolean =
    uri.regionMatches(middleIndex, middle, 0, middle.length)

  @Benchmark
  def middle_substringEquals(): Boolean =
    uri.substring(middleIndex, middleIndex + middle.length) == middle

  @Benchmark
  def middle2Sides_regionMatches(): Boolean =
    uri.regionMatches(middleIndex, middle2Sides, 1, middle2Sides.length - 2)

  @Benchmark
  def middle2Sides_substringEquals(): Boolean =
    uri.substring(middleIndex, middleIndex + middle.length) == middle2Sides.substring(1, middle2Sides.length - 1)

  @Benchmark
  def end_endsWith(): Boolean =
    uri.endsWith(end)

  @Benchmark
  def end_regionMatches(): Boolean =
    uri.regionMatches(endIndex, end, 0, end.length)

  @Benchmark
  def end_substringEquals(): Boolean =
    uri.substring(endIndex) == end
}

object RegionMatchesBenchmarks {
  val uri = "/site/section/blog/2022-04-17/the-title-of-the-post"
  val begin = "/site/section"
  val middle = "2022-04-17"
  val middle2Sides = s"-$middle-"
  val middleIndex: Int = uri.indexOf(middle)
  val end = "/the-title-of-the-post"
  val endIndex: Int = uri.indexOf(end)
}
