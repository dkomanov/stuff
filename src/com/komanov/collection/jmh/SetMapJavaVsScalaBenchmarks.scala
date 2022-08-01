package com.komanov.collection.jmh

import org.openjdk.jmh.annotations._

import java.util.UUID
import java.util.concurrent.TimeUnit
import java.util.stream.{Collectors, IntStream}
import scala.collection.mutable
import scala.jdk.CollectionConverters._
import scala.util.Random

/*
openjdk-17, scala 2.13

Benchmark                                         (size)  Mode  Cnt    Score     Error  Units
SetMapJavaVsScalaBenchmarks.javaMapHit              1000  avgt    3   95.318 ±  53.363  ns/op
SetMapJavaVsScalaBenchmarks.javaMapHit             10000  avgt    3  109.439 ±  35.512  ns/op
SetMapJavaVsScalaBenchmarks.javaMapHit            100000  avgt    3  114.341 ±  29.184  ns/op
SetMapJavaVsScalaBenchmarks.javaMapHit           1000000  avgt    3   90.545 ±  19.529  ns/op
SetMapJavaVsScalaBenchmarks.javaMapMiss             1000  avgt    3   82.930 ±  23.468  ns/op
SetMapJavaVsScalaBenchmarks.javaMapMiss            10000  avgt    3   89.087 ±  39.302  ns/op
SetMapJavaVsScalaBenchmarks.javaMapMiss           100000  avgt    3   89.570 ±  18.720  ns/op
SetMapJavaVsScalaBenchmarks.javaMapMiss          1000000  avgt    3   92.031 ±  47.545  ns/op
SetMapJavaVsScalaBenchmarks.javaSetHit              1000  avgt    3   80.191 ±  11.223  ns/op
SetMapJavaVsScalaBenchmarks.javaSetHit             10000  avgt    3   86.387 ±  42.422  ns/op
SetMapJavaVsScalaBenchmarks.javaSetHit            100000  avgt    3   83.591 ±  15.767  ns/op
SetMapJavaVsScalaBenchmarks.javaSetHit           1000000  avgt    3   89.951 ±  17.596  ns/op
SetMapJavaVsScalaBenchmarks.javaSetMiss             1000  avgt    3   91.106 ±  20.903  ns/op
SetMapJavaVsScalaBenchmarks.javaSetMiss            10000  avgt    3   90.176 ±  22.625  ns/op
SetMapJavaVsScalaBenchmarks.javaSetMiss           100000  avgt    3   93.580 ±   4.822  ns/op
SetMapJavaVsScalaBenchmarks.javaSetMiss          1000000  avgt    3   96.194 ±  37.862  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapHit       1000  avgt    3   89.343 ±  19.059  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapHit      10000  avgt    3   85.877 ±  17.992  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapHit     100000  avgt    3   96.506 ±  17.052  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapHit    1000000  avgt    3  140.923 ±  67.170  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapMiss      1000  avgt    3  149.476 ±  30.819  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapMiss     10000  avgt    3  155.249 ±  27.231  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapMiss    100000  avgt    3  158.987 ±  25.757  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapMiss   1000000  avgt    3  111.668 ±  38.480  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetHit       1000  avgt    3   84.806 ±   2.271  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetHit      10000  avgt    3   92.755 ±  10.404  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetHit     100000  avgt    3   83.447 ±   5.710  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetHit    1000000  avgt    3   92.945 ±  42.114  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetMiss      1000  avgt    3   92.446 ±  17.896  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetMiss     10000  avgt    3  101.300 ±  21.129  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetMiss    100000  avgt    3   96.100 ±   7.376  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetMiss   1000000  avgt    3   99.698 ±  52.244  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapHit             1000  avgt    3  273.606 ±  28.148  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapHit            10000  avgt    3  320.009 ±  10.867  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapHit           100000  avgt    3  496.183 ±  56.322  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapHit          1000000  avgt    3  591.056 ± 147.294  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapMiss            1000  avgt    3  185.589 ±  29.002  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapMiss           10000  avgt    3  271.888 ±  24.015  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapMiss          100000  avgt    3  354.449 ±  82.990  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapMiss         1000000  avgt    3  463.019 ±  83.502  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapHit      1000  avgt    3   76.163 ±   0.656  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapHit     10000  avgt    3   80.117 ±   3.001  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapHit    100000  avgt    3   82.846 ±   1.224  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapHit   1000000  avgt    3   77.787 ±   3.743  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapMiss     1000  avgt    3   59.160 ±   6.160  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapMiss    10000  avgt    3   55.420 ±   0.958  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapMiss   100000  avgt    3   49.415 ±   0.637  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapMiss  1000000  avgt    3   52.119 ±   0.536  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetHit      1000  avgt    3   65.453 ±   1.760  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetHit     10000  avgt    3   77.620 ±  17.163  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetHit    100000  avgt    3   83.233 ±   0.761  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetHit   1000000  avgt    3   88.002 ±  14.134  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetMiss     1000  avgt    3   51.189 ±   0.222  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetMiss    10000  avgt    3   61.498 ±   0.819  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetMiss   100000  avgt    3   48.630 ±   0.127  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetMiss  1000000  avgt    3   57.713 ±   1.170  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetHit             1000  avgt    3  253.448 ±   4.359  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetHit            10000  avgt    3  333.881 ±  22.523  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetHit           100000  avgt    3  392.889 ±  17.035  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetHit          1000000  avgt    3  477.840 ±  65.137  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetMiss            1000  avgt    3  191.383 ±  30.768  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetMiss           10000  avgt    3  272.486 ±  41.934  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetMiss          100000  avgt    3  333.106 ±  42.113  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetMiss         1000000  avgt    3  414.700 ± 101.260  ns/op
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
  private var hitKeys: java.util.List[UUID] = _
  private var missKeys: java.util.List[UUID] = _

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

    hitKeys = IntStream.range(0, 10).mapToObj(_ => uuids(Random.nextInt(uuids.size))).collect(Collectors.toList[UUID])
    missKeys = IntStream.range(0, 10).mapToObj(_ => UUID.randomUUID).collect(Collectors.toList[UUID])
  }

  @Benchmark
  def javaSetHit: Int = JavaHelper.run(javaSet, hitKeys)

  @Benchmark
  def javaSetMiss: Int = JavaHelper.run(javaSet, missKeys)

  @Benchmark
  def javaWrappedSetHit: Int = JavaHelper.run(javaWrappedSet, hitKeys)

  @Benchmark
  def javaWrappedSetMiss: Int = JavaHelper.run(javaWrappedSet, missKeys)

  @Benchmark
  def scalaSetHit: Int = JavaHelper.run(scalaSet, hitKeys)

  @Benchmark
  def scalaSetMiss: Int = JavaHelper.run(scalaSet, missKeys)

  @Benchmark
  def scalaMutableSetHit: Int = JavaHelper.run(scalaMutableSet, hitKeys)

  @Benchmark
  def scalaMutableSetMiss: Int = JavaHelper.run(scalaMutableSet, missKeys)

  @Benchmark
  def javaMapHit: Int = JavaHelper.run(javaMap, hitKeys)

  @Benchmark
  def javaMapMiss: Int = JavaHelper.run(javaMap, missKeys)

  @Benchmark
  def javaWrappedMapHit: Int = JavaHelper.run(javaWrappedMap, hitKeys)

  @Benchmark
  def javaWrappedMapMiss: Int = JavaHelper.run(javaWrappedMap, missKeys)

  @Benchmark
  def scalaMapHit: Int = JavaHelper.run(scalaMap, hitKeys)

  @Benchmark
  def scalaMapMiss: Int = JavaHelper.run(scalaMap, missKeys)

  @Benchmark
  def scalaMutableMapHit: Int = JavaHelper.run(scalaMutableMap, hitKeys)

  @Benchmark
  def scalaMutableMapMiss: Int = JavaHelper.run(scalaMutableMap, missKeys)
}
