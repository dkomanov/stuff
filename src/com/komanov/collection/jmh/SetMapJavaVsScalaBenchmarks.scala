package com.komanov.collection.jmh

import org.openjdk.jmh.annotations._

import java.util.UUID
import java.util.concurrent.TimeUnit
import java.util.stream.{Collectors, IntStream}
import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.util.Random

/*
openjdk-17, scala 2.13

Benchmark                                         (size)  Mode  Cnt    Score    Error  Units
SetMapJavaVsScalaBenchmarks.javaMapHit              1000  avgt    3   69.770 ± 19.038  ns/op
SetMapJavaVsScalaBenchmarks.javaMapHit             10000  avgt    3   82.177 ± 20.698  ns/op
SetMapJavaVsScalaBenchmarks.javaMapHit            100000  avgt    3   70.014 ± 15.682  ns/op
SetMapJavaVsScalaBenchmarks.javaMapHit           1000000  avgt    3   83.138 ± 12.338  ns/op
SetMapJavaVsScalaBenchmarks.javaMapMiss             1000  avgt    3   51.844 ±  0.943  ns/op
SetMapJavaVsScalaBenchmarks.javaMapMiss            10000  avgt    3   48.276 ±  0.425  ns/op
SetMapJavaVsScalaBenchmarks.javaMapMiss           100000  avgt    3   48.035 ±  2.777  ns/op
SetMapJavaVsScalaBenchmarks.javaMapMiss          1000000  avgt    3   47.262 ±  1.254  ns/op
SetMapJavaVsScalaBenchmarks.javaSetHit              1000  avgt    3   63.249 ±  0.134  ns/op
SetMapJavaVsScalaBenchmarks.javaSetHit             10000  avgt    3   57.679 ±  0.576  ns/op
SetMapJavaVsScalaBenchmarks.javaSetHit            100000  avgt    3   61.693 ±  0.317  ns/op
SetMapJavaVsScalaBenchmarks.javaSetHit           1000000  avgt    3   68.099 ± 23.567  ns/op
SetMapJavaVsScalaBenchmarks.javaSetMiss             1000  avgt    3   52.512 ±  0.533  ns/op
SetMapJavaVsScalaBenchmarks.javaSetMiss            10000  avgt    3   58.961 ±  6.630  ns/op
SetMapJavaVsScalaBenchmarks.javaSetMiss           100000  avgt    3   57.118 ±  0.640  ns/op
SetMapJavaVsScalaBenchmarks.javaSetMiss          1000000  avgt    3   55.691 ±  3.469  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapHit       1000  avgt    3   84.162 ±  0.530  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapHit      10000  avgt    3   92.049 ± 32.066  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapHit     100000  avgt    3   98.779 ±  3.900  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapHit    1000000  avgt    3   91.808 ± 21.236  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapMiss      1000  avgt    3   98.599 ±  6.601  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapMiss     10000  avgt    3  103.863 ±  0.720  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapMiss    100000  avgt    3   94.607 ± 36.463  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedMapMiss   1000000  avgt    3   97.531 ± 57.599  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetHit       1000  avgt    3   73.588 ±  0.571  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetHit      10000  avgt    3   71.278 ±  0.371  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetHit     100000  avgt    3   70.498 ±  7.583  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetHit    1000000  avgt    3   71.983 ± 19.224  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetMiss      1000  avgt    3   64.189 ±  1.095  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetMiss     10000  avgt    3   63.669 ±  1.100  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetMiss    100000  avgt    3   65.234 ±  0.698  ns/op
SetMapJavaVsScalaBenchmarks.javaWrappedSetMiss   1000000  avgt    3   62.436 ±  0.191  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapHit             1000  avgt    3  272.135 ± 13.338  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapHit            10000  avgt    3  355.651 ± 40.011  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapHit           100000  avgt    3  519.041 ±  9.469  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapHit          1000000  avgt    3  603.001 ± 91.917  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapMiss            1000  avgt    3  198.755 ± 11.578  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapMiss           10000  avgt    3  257.854 ±  7.071  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapMiss          100000  avgt    3  407.580 ±  2.240  ns/op
SetMapJavaVsScalaBenchmarks.scalaMapMiss         1000000  avgt    3  522.165 ±  8.261  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapHit      1000  avgt    3   76.938 ±  0.250  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapHit     10000  avgt    3   86.969 ± 20.157  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapHit    100000  avgt    3   91.331 ±  0.417  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapHit   1000000  avgt    3   90.308 ±  2.453  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapMiss     1000  avgt    3   52.402 ±  0.889  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapMiss    10000  avgt    3   45.881 ±  0.680  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapMiss   100000  avgt    3   47.598 ±  0.710  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableMapMiss  1000000  avgt    3   45.246 ±  2.381  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetHit      1000  avgt    3   66.387 ±  0.642  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetHit     10000  avgt    3   67.232 ±  0.417  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetHit    100000  avgt    3   71.104 ± 17.204  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetHit   1000000  avgt    3   68.068 ±  0.341  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetMiss     1000  avgt    3   46.297 ± 11.167  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetMiss    10000  avgt    3   48.549 ±  0.280  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetMiss   100000  avgt    3   45.661 ±  2.376  ns/op
SetMapJavaVsScalaBenchmarks.scalaMutableSetMiss  1000000  avgt    3   46.600 ±  0.426  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetHit             1000  avgt    3  267.368 ±  0.558  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetHit            10000  avgt    3  300.492 ± 14.763  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetHit           100000  avgt    3  380.558 ±  5.655  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetHit          1000000  avgt    3  493.270 ± 52.964  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetMiss            1000  avgt    3  177.311 ± 11.554  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetMiss           10000  avgt    3  263.542 ±  0.643  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetMiss          100000  avgt    3  324.326 ±  4.825  ns/op
SetMapJavaVsScalaBenchmarks.scalaSetMiss         1000000  avgt    3  430.694 ± 57.067  ns/op
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
