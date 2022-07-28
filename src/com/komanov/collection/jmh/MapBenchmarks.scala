package com.komanov.collection.jmh

import org.openjdk.jmh.annotations._

import java.util.UUID
import java.util.concurrent.TimeUnit
import scala.collection.JavaConverters._
import scala.util.Random

/**
Benchmark                        (size)  Mode  Cnt    Score     Error  Units
MapBenchmarks.javaMapFound         1000  avgt    3  120.262 ± 126.145  ns/op
MapBenchmarks.javaMapFound        10000  avgt    3  118.033 ±  31.656  ns/op
MapBenchmarks.javaMapFound       100000  avgt    3  254.128 ± 104.252  ns/op
MapBenchmarks.javaMapFound      1000000  avgt    3  266.485 ± 141.067  ns/op
MapBenchmarks.javaMapNotFound      1000  avgt    3  100.582 ± 100.295  ns/op
MapBenchmarks.javaMapNotFound     10000  avgt    3   98.656 ±  33.972  ns/op
MapBenchmarks.javaMapNotFound    100000  avgt    3  143.254 ±  18.523  ns/op
MapBenchmarks.javaMapNotFound   1000000  avgt    3  158.000 ± 630.484  ns/op
MapBenchmarks.scalaMapFound        1000  avgt    3  389.468 ± 347.330  ns/op
MapBenchmarks.scalaMapFound       10000  avgt    3  383.948 ±  89.538  ns/op
MapBenchmarks.scalaMapFound      100000  avgt    3  507.746 ± 190.892  ns/op
MapBenchmarks.scalaMapFound     1000000  avgt    3  600.665 ± 113.746  ns/op
MapBenchmarks.scalaMapNotFound     1000  avgt    3  188.967 ± 229.082  ns/op
MapBenchmarks.scalaMapNotFound    10000  avgt    3  213.400 ± 194.933  ns/op
MapBenchmarks.scalaMapNotFound   100000  avgt    3  334.455 ± 261.454  ns/op
MapBenchmarks.scalaMapNotFound  1000000  avgt    3  374.619 ±  97.913  ns/op
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
class MapBenchmarks {

  @Param(Array("1000", "10000", "100000", "1000000"))
  var size: Int = 0

  var javaMap: java.util.Map[UUID, Int] = _
  var scalaMap: Map[UUID, Int] = _
  var found: Seq[UUID] = _
  var notFound: Seq[UUID] = _

  @Setup
  def setup(): Unit = {
    val uuids = (1 to size).map(_ => UUID.randomUUID()).zipWithIndex
    javaMap = new java.util.HashMap(uuids.toMap.asJava)
    scalaMap = uuids.toMap
    found = (1 to 10).map(_ => uuids(Random.nextInt(uuids.size))._1)
    notFound = (1 to 10).map(_ => UUID.randomUUID)
  }

  @Benchmark
  def javaMapFound: Int = {
    var r = 0
    for (v <- found) {
      r += javaMap.getOrDefault(v, 0)
    }
    r
  }

  @Benchmark
  def javaMapNotFound: Int = {
    var r = 0
    for (v <- notFound) {
      r += javaMap.getOrDefault(v, 0)
    }
    r
  }

  @Benchmark
  def scalaMapFound: Int = {
    var r = 0
    for (v <- found) {
      r += scalaMap.getOrElse(v, 0)
    }
    r
  }

  @Benchmark
  def scalaMapNotFound: Int = {
    var r = 0
    for (v <- notFound) {
      r += scalaMap.getOrElse(v, 0)
    }
    r
  }
}
