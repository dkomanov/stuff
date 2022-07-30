package com.komanov.collection.jmh

import org.openjdk.jmh.annotations._

import java.util.UUID
import java.util.concurrent.TimeUnit
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.Random

/*
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
class SetMapJavaVsScalaBenchmarks {

  @Param(Array("1000", "10000", "100000", "1000000"))
  var size: Int = 0

  private var javaSet: java.util.Set[UUID] = _
  private var javaWrappedSet: mutable.Set[UUID] = _
  private var scalaSet: Set[UUID] = _
  private var scalaMutableSet: mutable.Set[UUID] = _
  private var javaMap: java.util.Map[UUID, Int] = _
  private var javaWrappedMap: mutable.Map[UUID, Int] = _
  private var scalaMap: Map[UUID, Int] = _
  private var scalaMutableMap: mutable.Map[UUID, Int] = _
  private var hitKeys: Seq[UUID] = _
  private var missKeys: Seq[UUID] = _

  @Setup
  def setup(): Unit = {
    val uuids = (1 to size).map(_ => UUID.randomUUID())
    val uuidsPlusIndex = uuids.zipWithIndex

    javaSet = new java.util.HashSet(uuids.asJava)
    javaWrappedSet = javaSet.asScala
    scalaSet = uuids.toSet
    scalaMutableSet = mutable.Set(uuids: _*)

    require(javaSet.size == size)

    scalaMap = uuidsPlusIndex.toMap
    javaMap = new java.util.HashMap(scalaMap.asJava)
    javaWrappedMap = javaMap.asScala
    scalaMutableMap = mutable.Map(uuidsPlusIndex: _*)

    hitKeys = (1 to 10).map(_ => uuids(Random.nextInt(uuids.size)))
    missKeys = (1 to 10).map(_ => UUID.randomUUID)
  }

  @Benchmark
  def javaSetHit: Int = {
    var r = 0
    for (v <- hitKeys) {
      r += (if (javaSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def javaSetMiss: Int = {
    var r = 0
    for (v <- missKeys) {
      r += (if (javaSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def javaWrappedSetHit: Int = {
    var r = 0
    for (v <- hitKeys) {
      r += (if (javaWrappedSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def javaWrappedSetMiss: Int = {
    var r = 0
    for (v <- missKeys) {
      r += (if (javaWrappedSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def scalaSetHit: Int = {
    var r = 0
    for (v <- hitKeys) {
      r += (if (scalaSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def scalaSetMiss: Int = {
    var r = 0
    for (v <- missKeys) {
      r += (if (scalaSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def scalaMutableSetHit: Int = {
    var r = 0
    for (v <- hitKeys) {
      r += (if (scalaMutableSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def scalaMutableSetMiss: Int = {
    var r = 0
    for (v <- missKeys) {
      r += (if (scalaMutableSet.contains(v)) 1 else 0)
    }
    r
  }

  @Benchmark
  def javaMapHit: Int = {
    var r = 0
    for (v <- hitKeys) {
      r += javaMap.getOrDefault(v, 0)
    }
    r
  }

  @Benchmark
  def javaMapMiss: Int = {
    var r = 0
    for (v <- missKeys) {
      r += javaMap.getOrDefault(v, 0)
    }
    r
  }

  @Benchmark
  def javaWrappedMapHit: Int = {
    var r = 0
    for (v <- hitKeys) {
      r += javaWrappedMap.getOrElse(v, 0)
    }
    r
  }

  @Benchmark
  def javaWrappedMapMiss: Int = {
    var r = 0
    for (v <- missKeys) {
      r += javaWrappedMap.getOrElse(v, 0)
    }
    r
  }

  @Benchmark
  def scalaMapHit: Int = {
    var r = 0
    for (v <- hitKeys) {
      r += scalaMap.getOrElse(v, 0)
    }
    r
  }

  @Benchmark
  def scalaMapMiss: Int = {
    var r = 0
    for (v <- missKeys) {
      r += scalaMap.getOrElse(v, 0)
    }
    r
  }

  @Benchmark
  def scalaMutableMapHit: Int = {
    var r = 0
    for (v <- hitKeys) {
      r += scalaMutableMap.getOrElse(v, 0)
    }
    r
  }

  @Benchmark
  def scalaMutableMapMiss: Int = {
    var r = 0
    for (v <- missKeys) {
      r += scalaMutableMap.getOrElse(v, 0)
    }
    r
  }
}
