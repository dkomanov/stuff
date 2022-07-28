package com.komanov.collection.jmh

import org.openjdk.jmh.annotations._

import java.util.UUID
import java.util.concurrent.TimeUnit
import scala.collection.JavaConverters._
import scala.util.Random

/**
Benchmark                        (size)  Mode  Cnt    Score   Error  Units
SetBenchmarks.javaSetFound         1000  avgt    2   98.608          ns/op
SetBenchmarks.javaSetFound        10000  avgt    2  187.030          ns/op
SetBenchmarks.javaSetFound       100000  avgt    2  130.318          ns/op
SetBenchmarks.javaSetFound      1000000  avgt    2  111.040          ns/op
SetBenchmarks.javaSetNotFound      1000  avgt    2  100.523          ns/op
SetBenchmarks.javaSetNotFound     10000  avgt    2   89.032          ns/op
SetBenchmarks.javaSetNotFound    100000  avgt    2  102.901          ns/op
SetBenchmarks.javaSetNotFound   1000000  avgt    2  103.385          ns/op
SetBenchmarks.scalaSetFound        1000  avgt    2  327.454          ns/op
SetBenchmarks.scalaSetFound       10000  avgt    2  341.051          ns/op
SetBenchmarks.scalaSetFound      100000  avgt    2  464.220          ns/op
SetBenchmarks.scalaSetFound     1000000  avgt    2  559.866          ns/op
SetBenchmarks.scalaSetNotFound     1000  avgt    2  196.838          ns/op
SetBenchmarks.scalaSetNotFound    10000  avgt    2  235.328          ns/op
SetBenchmarks.scalaSetNotFound   100000  avgt    2  335.779          ns/op
SetBenchmarks.scalaSetNotFound  1000000  avgt    2  393.320          ns/op
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
class SetBenchmarks {

  @Param(Array("1000", "10000", "100000", "1000000"))
  var size: Int = 0

  var javaSet: java.util.Set[UUID] = _
  var scalaSet: Set[UUID] = _
  var found: Seq[UUID] = _
  var notFound: Seq[UUID] = _

  @Setup
  def setup(): Unit = {
    val uuids = (1 to size).map(_ => UUID.randomUUID())
    javaSet = new java.util.HashSet(uuids.toSet.asJava)
    scalaSet = uuids.toSet
    found = (1 to 10).map(_ => uuids(Random.nextInt(uuids.size)))
    notFound = (1 to 10).map(_ => UUID.randomUUID)
  }

  @Benchmark
  def javaSetFound: Int = {
    var r = 0
    for (v <- found) {
      r += (if (javaSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def javaSetNotFound: Int = {
    var r = 0
    for (v <- notFound) {
      r += (if (javaSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def scalaSetFound: Int = {
    var r = 0
    for (v <- found) {
      r += (if (scalaSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def scalaSetNotFound: Int = {
    var r = 0
    for (v <- notFound) {
      r += (if (scalaSet.contains(v)) 1 else 0)
    }
    r
  }
}
