package com.komanov.str.jmh

import com.komanov.str.jmh.RegionMatchesBenchmarks._
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Benchmarks on 2022-04-23.

==> jdk8.txt <==
Benchmark                                             Mode  Cnt   Score    Error  Units
RegionMatchesBenchmarks.begin_regionMatches           avgt    3  13.967 ± 22.152  ns/op
RegionMatchesBenchmarks.begin_startsWith              avgt    3  13.692 ±  1.249  ns/op
RegionMatchesBenchmarks.begin_substringEquals         avgt    3  15.604 ±  3.514  ns/op
RegionMatchesBenchmarks.end_endsWith                  avgt    3  15.769 ± 14.764  ns/op
RegionMatchesBenchmarks.end_regionMatches             avgt    3  17.155 ± 17.123  ns/op
RegionMatchesBenchmarks.end_substringEquals           avgt    3  17.472 ±  2.146  ns/op
RegionMatchesBenchmarks.middle2Sides_regionMatches    avgt    3  18.261 ± 43.213  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEquals  avgt    3  25.975 ± 17.724  ns/op
RegionMatchesBenchmarks.middle_regionMatches          avgt    3  16.988 ±  0.189  ns/op
RegionMatchesBenchmarks.middle_substringEquals        avgt    3  18.663 ±  9.666  ns/op

==> jdk11.log.txt <==
Benchmark                                             Mode  Cnt   Score    Error  Units
RegionMatchesBenchmarks.begin_regionMatches           avgt    3  27.508 ± 25.079  ns/op
RegionMatchesBenchmarks.begin_startsWith              avgt    3  12.398 ±  0.304  ns/op
RegionMatchesBenchmarks.begin_substringEquals         avgt    3  20.012 ±  9.165  ns/op
RegionMatchesBenchmarks.end_endsWith                  avgt    3  15.306 ±  0.700  ns/op
RegionMatchesBenchmarks.end_regionMatches             avgt    3  26.858 ± 44.689  ns/op
RegionMatchesBenchmarks.end_substringEquals           avgt    3  16.702 ± 15.542  ns/op
RegionMatchesBenchmarks.middle2Sides_regionMatches    avgt    3  27.727 ± 33.234  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEquals  avgt    3  31.912 ± 30.710  ns/op
RegionMatchesBenchmarks.middle_regionMatches          avgt    3  26.697 ± 27.258  ns/op
RegionMatchesBenchmarks.middle_substringEquals        avgt    3  21.990 ±  9.910  ns/op

==> jdk17.log.txt <==
Benchmark                                             Mode  Cnt   Score     Error  Units
RegionMatchesBenchmarks.begin_regionMatches           avgt    3  33.613 ± 224.578  ns/op
RegionMatchesBenchmarks.begin_startsWith              avgt    3  12.834 ±  11.896  ns/op
RegionMatchesBenchmarks.begin_substringEquals         avgt    3  18.158 ±  30.074  ns/op
RegionMatchesBenchmarks.end_endsWith                  avgt    3  14.799 ±   2.148  ns/op
RegionMatchesBenchmarks.end_regionMatches             avgt    3  26.565 ±   1.306  ns/op
RegionMatchesBenchmarks.end_substringEquals           avgt    3  15.663 ±  11.616  ns/op
RegionMatchesBenchmarks.middle2Sides_regionMatches    avgt    3  46.804 ±   7.244  ns/op
RegionMatchesBenchmarks.middle2Sides_substringEquals  avgt    3  29.616 ±  46.455  ns/op
RegionMatchesBenchmarks.middle_regionMatches          avgt    3  26.995 ±  31.092  ns/op
RegionMatchesBenchmarks.middle_substringEquals        avgt    3  17.853 ±  30.165  ns/op

How to run:

$ bazel build //src/com/komanov/str/jmh:jmh_deploy.jar
$ /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java -jar bazel-bin/src/com/komanov/str/jmh/jmh_deploy.jar -rf json -rff jdk8.log.json | tee jdk8.txt && /usr/lib/jvm/java-11-openjdk-amd64/bin/java -jar bazel-bin/src/com/komanov/str/jmh/jmh_deploy.jar -rf json -rff jdk11.json | tee jdk11.log.txt && /usr/lib/jvm/java-17-openjdk-amd64/bin/java -jar bazel-bin/src/com/komanov/str/jmh/jmh_deploy.jar -rf json -rff jdk17.json | tee jdk17.log.txt
$ tail -n13 jdk*.txt
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
  val begin = "/site/section/blog/2"
  val middle = "/blog/2022-04-17/the"
  val middle2Sides = s"-$middle-"
  val middleIndex: Int = uri.indexOf(middle)
  val end = "he-title-of-the-post"
  val endIndex: Int = uri.indexOf(end)
}
