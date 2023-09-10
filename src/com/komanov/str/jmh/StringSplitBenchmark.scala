package com.komanov.str.jmh

import org.apache.commons.lang3.{ArrayUtils, StringUtils}
import org.openjdk.jmh.annotations._

import java.util
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/*
Benchmark                                          (value)  Mode  Cnt    Score    Error  Units
StringSplitBenchmark.mySplit                             1  avgt    5   33.033 ±  2.601  ns/op
StringSplitBenchmark.mySplit                         1.0.0  avgt    5  125.612 ±  9.026  ns/op
StringSplitBenchmark.mySplit             10000.10000.10000  avgt    5  133.659 ±  7.935  ns/op
StringSplitBenchmark.splitByChar                         1  avgt    5   19.147 ±  1.608  ns/op
StringSplitBenchmark.splitByChar                     1.0.0  avgt    5  125.565 ±  8.426  ns/op
StringSplitBenchmark.splitByChar         10000.10000.10000  avgt    5  135.740 ± 19.065  ns/op
StringSplitBenchmark.splitByPattern                      1  avgt    5   77.722 ±  6.052  ns/op
StringSplitBenchmark.splitByPattern                  1.0.0  avgt    5  251.132 ± 18.542  ns/op
StringSplitBenchmark.splitByPattern      10000.10000.10000  avgt    5  270.035 ± 30.192  ns/op
StringSplitBenchmark.splitByString                       1  avgt    5   14.617 ±  1.261  ns/op
StringSplitBenchmark.splitByString                   1.0.0  avgt    5  118.713 ± 18.437  ns/op
StringSplitBenchmark.splitByString       10000.10000.10000  avgt    5  132.457 ± 41.634  ns/op
StringSplitBenchmark.splitByStringUtils                  1  avgt    5   34.844 ±  2.374  ns/op
StringSplitBenchmark.splitByStringUtils              1.0.0  avgt    5  131.032 ±  7.660  ns/op
StringSplitBenchmark.splitByStringUtils  10000.10000.10000  avgt    5  147.903 ±  9.213  ns/op
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
class StringSplitBenchmark {

  private val pattern = Pattern.compile("\\.")

  @Param(Array(
    "1",
    "1.0.0",
    "10000.10000.10000",
  ))
  var value: String = _

  @Setup
  def test(): Unit = {
    def toStr(v: Array[String]) = v.toSeq.map(v => if (v == null) "null" else s"\"$v\"").mkString("[", ", ", "]")

    def assertEquals(l: Array[String], r: Array[String]): Unit = {
      if (l.length != r.length || l.zip(r).exists(t => t._1 != t._2)) {
        throw new IllegalArgumentException(s"${toStr(l)} != ${toStr(r)}")
      }
    }
    assertEquals(splitByChar(), splitByString())
    assertEquals(splitByChar(), splitByPattern())
    assertEquals(splitByChar(), splitByStringUtils())
    assertEquals(splitByChar(), mySplit())
  }

  @Benchmark
  def splitByChar(): Array[String] =
    value.split('.')

  @Benchmark
  def splitByString(): Array[String] =
    value.split("\\.")

  @Benchmark
  def splitByPattern(): Array[String] =
    pattern.split(value)

  @Benchmark
  def splitByStringUtils(): Array[String] =
    StringUtils.split(value, '.')

  @Benchmark
  def mySplit(): Array[String] =
    StringSplitBenchmark.mySplit(value, '.')
}

object StringSplitBenchmark {
  def mySplit(value: String, ch: Char): Array[String] = {
    if (value.isEmpty) {
      return ArrayUtils.EMPTY_STRING_ARRAY
    }

    val result = new util.ArrayList[String]()
    var prev: Int = 0
    do {
      val index = value.indexOf(ch, prev)
      if (index == -1) {
        result.add(if (prev == 0) value else value.substring(prev))
      } else if (index != prev) { // do not add empty string
        result.add(value.substring(prev, index))
      }
      prev = index + 1
    } while (prev != 0)
    result.toArray(new Array[String](result.size))
  }
}
