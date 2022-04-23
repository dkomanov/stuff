package com.komanov.str.jmh

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._
import RegionMatchesBenchmarks._

import java.util
import scala.util.Random

/*
In my blog post I omit "misses", because it has basically the same performance.

Benchmark                                                 Mode  Cnt   Score   Error  Units
RegionMatchesBenchmarks.begin_regionMatchesHit            avgt    3  17.360 ± 4.994  ns/op
RegionMatchesBenchmarks.begin_regionMatchesMiss           avgt    3  17.831 ± 5.630  ns/op
RegionMatchesBenchmarks.begin_startsWithHit               avgt    3   9.057 ± 1.467  ns/op
RegionMatchesBenchmarks.begin_startsWithMiss              avgt    3   8.914 ± 0.982  ns/op
RegionMatchesBenchmarks.begin_substringEqualsHit          avgt    3  14.261 ± 1.629  ns/op
RegionMatchesBenchmarks.begin_substringEqualsMiss         avgt    3  14.239 ± 2.423  ns/op
RegionMatchesBenchmarks.end_endsWithHit                   avgt    3  13.825 ± 0.222  ns/op
RegionMatchesBenchmarks.end_endsWithMiss                  avgt    3  14.086 ± 8.813  ns/op
RegionMatchesBenchmarks.end_regionMatchesHit              avgt    3  30.767 ± 2.473  ns/op
RegionMatchesBenchmarks.end_regionMatchesMiss             avgt    3  27.112 ± 1.620  ns/op
RegionMatchesBenchmarks.end_substringEqualsHit            avgt    3  14.802 ± 4.790  ns/op
RegionMatchesBenchmarks.end_substringEqualsMiss           avgt    3  14.497 ± 1.054  ns/op
RegionMatchesBenchmarks.middle2Sides_regionMatchesHit     avgt    3  16.447 ± 4.702  ns/op
RegionMatchesBenchmarks.middle2Sides_regionMatchesMiss    avgt    3  15.358 ± 1.033  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEqualsHit   avgt    3  23.763 ± 7.375  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEqualsMiss  avgt    3  23.284 ± 3.485  ns/op
RegionMatchesBenchmarks.middle_regionMatchesHit           avgt    3  15.296 ± 3.549  ns/op
RegionMatchesBenchmarks.middle_regionMatchesMiss          avgt    3  15.155 ± 2.140  ns/op
RegionMatchesBenchmarks.middle_substringEqualsHit         avgt    3  13.883 ± 2.026  ns/op
RegionMatchesBenchmarks.middle_substringEqualsMiss        avgt    3  13.854 ± 0.906  ns/op

Benchmark                                              Mode  Cnt   Score   Error  Units
RegionMatchesBenchmarks.begin_regionMatches            avgt    3  17.360 ± 4.994  ns/op
RegionMatchesBenchmarks.begin_startsWith               avgt    3   9.057 ± 1.467  ns/op
RegionMatchesBenchmarks.begin_substringEquals          avgt    3  14.261 ± 1.629  ns/op

Benchmark                                              Mode  Cnt   Score   Error  Units
RegionMatchesBenchmarks.end_endsWith                   avgt    3  13.825 ± 0.222  ns/op
RegionMatchesBenchmarks.end_regionMatches              avgt    3  30.767 ± 2.473  ns/op
RegionMatchesBenchmarks.end_substringEquals            avgt    3  14.802 ± 4.790  ns/op

Benchmark                                              Mode  Cnt   Score   Error  Units
RegionMatchesBenchmarks.middle_regionMatches           avgt    3  15.296 ± 3.549  ns/op
RegionMatchesBenchmarks.middle_substringEquals         avgt    3  13.883 ± 2.026  ns/op

Benchmark                                              Mode  Cnt   Score   Error  Units
RegionMatchesBenchmarks.middle2Sides_regionMatches     avgt    3  16.447 ± 4.702  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEquals   avgt    3  23.763 ± 7.375  ns/op
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(1)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
class RegionMatchesBenchmarks {

  @Setup // instead of separate test :D
  def check(): Unit = {
    require(begin_startsWithHit())
    require(!begin_startsWithMiss())
    require(begin_regionMatchesHit())
    require(!begin_regionMatchesMiss())
    require(begin_substringEqualsHit())
    require(!begin_substringEqualsMiss())
    require(middle_regionMatchesHit())
    require(!middle_regionMatchesMiss())
    require(middle_substringEqualsHit())
    require(!middle_substringEqualsMiss())
    require(middle2Sides_regionMatchesHit())
    require(!middle2Sides_regionMatchesMiss())
    require(middle2Sides_substringEqualsHit())
    require(!middle2Sides_substringEqualsMiss())
    require(end_endsWithHit())
    require(!end_endsWithMiss())
    require(end_regionMatchesHit())
    require(!end_regionMatchesMiss())
    require(end_substringEqualsHit())
    require(!end_substringEqualsMiss())
  }

  @Benchmark
  def begin_startsWithHit(): Boolean =
    uri.startsWith(beginHit)

  @Benchmark
  def begin_startsWithMiss(): Boolean =
    uri.startsWith(beginMiss)

  @Benchmark
  def begin_regionMatchesHit(): Boolean =
    uri.regionMatches(0, beginHit, 0, beginHit.length)

  @Benchmark
  def begin_regionMatchesMiss(): Boolean =
    uri.regionMatches(0, beginMiss, 0, beginMiss.length)

  @Benchmark
  def begin_substringEqualsHit(): Boolean =
    uri.substring(0, beginHit.length) == beginHit

  @Benchmark
  def begin_substringEqualsMiss(): Boolean =
    uri.substring(0, beginMiss.length) == beginMiss

  @Benchmark
  def middle_regionMatchesHit(): Boolean =
    uri.regionMatches(middleIndex, middleHit, 0, middleHit.length)

  @Benchmark
  def middle_regionMatchesMiss(): Boolean =
    uri.regionMatches(middleIndex, middleMiss, 0, middleMiss.length)

  @Benchmark
  def middle_substringEqualsHit(): Boolean =
    uri.substring(middleIndex, middleIndex + middleHit.length) == middleHit

  @Benchmark
  def middle_substringEqualsMiss(): Boolean =
    uri.substring(middleIndex, middleIndex + middleMiss.length) == middleMiss

  @Benchmark
  def middle2Sides_regionMatchesHit(): Boolean =
    uri.regionMatches(middleIndex, middleHit2Sides, 1, middleHit2Sides.length - 2)

  @Benchmark
  def middle2Sides_regionMatchesMiss(): Boolean =
    uri.regionMatches(middleIndex, middleMiss2Sides, 1, middleMiss2Sides.length - 2)

  @Benchmark
  def middle2Sides_substringEqualsHit(): Boolean =
    uri.substring(middleIndex, middleIndex + middleHit.length) == middleHit2Sides.substring(1, middleHit2Sides.length - 1)

  @Benchmark
  def middle2Sides_substringEqualsMiss(): Boolean =
    uri.substring(middleIndex, middleIndex + middleMiss.length) == middleMiss2Sides.substring(1, middleMiss2Sides.length - 1)

  @Benchmark
  def end_endsWithHit(): Boolean =
    uri.endsWith(endHit)

  @Benchmark
  def end_endsWithMiss(): Boolean =
    uri.endsWith(endMiss)

  @Benchmark
  def end_regionMatchesHit(): Boolean =
    uri.regionMatches(endIndex, endHit, 0, endHit.length)

  @Benchmark
  def end_regionMatchesMiss(): Boolean =
    uri.regionMatches(endIndex, endMiss, 0, endMiss.length)

  @Benchmark
  def end_substringEqualsHit(): Boolean =
    uri.substring(endIndex) == endHit

  @Benchmark
  def end_substringEqualsMiss(): Boolean =
    uri.substring(endIndex) == endMiss
}

object RegionMatchesBenchmarks {
  val uri = "/site/section/blog/2022-04-17/the-title-of-the-post"
  val beginHit = "/site/section"
  val beginMiss = "/site/sectioW"
  val middleHit = "2022-04-17"
  val middleHit2Sides = s"-$middleHit-"
  val middleMiss = "2022-04-19"
  val middleMiss2Sides = s"-$middleMiss-"
  val middleIndex: Int = uri.indexOf(middleHit)
  val endHit = "/the-title-of-the-post"
  val endMiss = "/the-title-of-the-posW"
  val endIndex: Int = uri.indexOf(endHit)
}
