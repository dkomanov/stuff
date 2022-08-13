package com.komanov.offheap.jmh

import com.komanov.collection.jmh.JavaHelper
import com.komanov.offheap._
import org.openjdk.jmh.annotations._

import java.util.UUID
import java.util.concurrent.TimeUnit
import java.util.stream.{Collectors, IntStream}
import scala.jdk.CollectionConverters._
import scala.util.Random

/*
Results for openjdk-17:
<code>
Benchmark                        (size)  Mode  Cnt      Score      Error  Units
Benchmarks.heapLongArrayHit        1000  avgt    3    329.587 ±    5.981  ns/op
Benchmarks.heapLongArrayHit       10000  avgt    3    429.440 ±    5.603  ns/op
Benchmarks.heapLongArrayHit      100000  avgt    3    533.398 ±   10.054  ns/op
Benchmarks.heapLongArrayHit     1000000  avgt    3    663.936 ±    7.215  ns/op
Benchmarks.heapLongArrayMiss       1000  avgt    3    339.291 ±   77.902  ns/op
Benchmarks.heapLongArrayMiss      10000  avgt    3    457.224 ±    9.073  ns/op
Benchmarks.heapLongArrayMiss     100000  avgt    3    581.088 ±    4.635  ns/op
Benchmarks.heapLongArrayMiss    1000000  avgt    3    747.666 ±   11.290  ns/op
Benchmarks.heapUuidArrayHit        1000  avgt    3    345.072 ±  109.409  ns/op
Benchmarks.heapUuidArrayHit       10000  avgt    3    399.456 ±   60.309  ns/op
Benchmarks.heapUuidArrayHit      100000  avgt    3    491.133 ±   10.220  ns/op
Benchmarks.heapUuidArrayHit     1000000  avgt    3    676.159 ±    9.577  ns/op
Benchmarks.heapUuidArrayMiss       1000  avgt    3    300.084 ±   38.316  ns/op
Benchmarks.heapUuidArrayMiss      10000  avgt    3    414.423 ±    4.290  ns/op
Benchmarks.heapUuidArrayMiss     100000  avgt    3    546.459 ±    3.655  ns/op
Benchmarks.heapUuidArrayMiss    1000000  avgt    3    658.861 ±    7.485  ns/op
Benchmarks.javaSetHit              1000  avgt    3    117.623 ±   61.923  ns/op
Benchmarks.javaSetHit             10000  avgt    3     70.986 ±    7.956  ns/op
Benchmarks.javaSetHit            100000  avgt    3    113.920 ±   26.587  ns/op
Benchmarks.javaSetHit           1000000  avgt    3    124.720 ±   46.156  ns/op
Benchmarks.javaSetMiss             1000  avgt    3     71.452 ±   16.567  ns/op
Benchmarks.javaSetMiss            10000  avgt    3     75.328 ±   16.873  ns/op
Benchmarks.javaSetMiss           100000  avgt    3     71.015 ±   11.967  ns/op
Benchmarks.javaSetMiss          1000000  avgt    3     70.430 ±   19.157  ns/op
Benchmarks.netty4CompositeHit      1000  avgt    3   6664.683 ±  657.729  ns/op
Benchmarks.netty4CompositeHit     10000  avgt    3  14990.911 ± 2726.566  ns/op
Benchmarks.netty4CompositeHit    100000  avgt    3  17607.337 ± 5261.042  ns/op
Benchmarks.netty4CompositeHit   1000000  avgt    3  29684.834 ± 2056.190  ns/op
Benchmarks.netty4CompositeMiss     1000  avgt    3   8684.727 ±  772.126  ns/op
Benchmarks.netty4CompositeMiss    10000  avgt    3  13196.846 ±  859.671  ns/op
Benchmarks.netty4CompositeMiss   100000  avgt    3   8689.853 ± 4036.934  ns/op
Benchmarks.netty4CompositeMiss  1000000  avgt    3  29296.415 ± 4261.821  ns/op
Benchmarks.netty4SingleHit         1000  avgt    3    571.676 ±    6.290  ns/op
Benchmarks.netty4SingleHit        10000  avgt    3    840.447 ±   23.810  ns/op
Benchmarks.netty4SingleHit       100000  avgt    3   1030.375 ±   22.350  ns/op
Benchmarks.netty4SingleHit      1000000  avgt    3   1215.569 ±   14.633  ns/op
Benchmarks.netty4SingleMiss        1000  avgt    3    580.041 ±    1.566  ns/op
Benchmarks.netty4SingleMiss       10000  avgt    3    768.853 ±   51.219  ns/op
Benchmarks.netty4SingleMiss      100000  avgt    3    996.713 ±   12.598  ns/op
Benchmarks.netty4SingleMiss     1000000  avgt    3   1201.959 ±   11.807  ns/op
Benchmarks.netty5CompositeHit      1000  avgt    3   3099.197 ±  316.196  ns/op
Benchmarks.netty5CompositeHit     10000  avgt    3   4742.918 ±  697.183  ns/op
Benchmarks.netty5CompositeHit    100000  avgt    3   6954.552 ± 3128.090  ns/op
Benchmarks.netty5CompositeHit   1000000  avgt    3  10825.640 ± 2147.798  ns/op
Benchmarks.netty5CompositeMiss     1000  avgt    3   2489.102 ±  419.009  ns/op
Benchmarks.netty5CompositeMiss    10000  avgt    3   3561.825 ±   18.620  ns/op
Benchmarks.netty5CompositeMiss   100000  avgt    3   5894.036 ±  213.559  ns/op
Benchmarks.netty5CompositeMiss  1000000  avgt    3  10020.871 ± 1350.435  ns/op
Benchmarks.netty5SingleHit         1000  avgt    3    517.126 ±    2.170  ns/op
Benchmarks.netty5SingleHit        10000  avgt    3    672.285 ±   52.326  ns/op
Benchmarks.netty5SingleHit       100000  avgt    3    887.227 ±   11.872  ns/op
Benchmarks.netty5SingleHit      1000000  avgt    3   1057.691 ±   46.495  ns/op
Benchmarks.netty5SingleMiss        1000  avgt    3    507.679 ±    3.845  ns/op
Benchmarks.netty5SingleMiss       10000  avgt    3    664.510 ±    3.443  ns/op
Benchmarks.netty5SingleMiss      100000  avgt    3    861.602 ±    5.151  ns/op
Benchmarks.netty5SingleMiss     1000000  avgt    3   1048.862 ±   19.391  ns/op
Benchmarks.offHeapHit              1000  avgt    3    310.583 ±   16.693  ns/op
Benchmarks.offHeapHit             10000  avgt    3    430.904 ±   16.690  ns/op
Benchmarks.offHeapHit            100000  avgt    3    590.544 ±   11.065  ns/op
Benchmarks.offHeapHit           1000000  avgt    3    698.077 ±    6.052  ns/op
Benchmarks.offHeapMiss             1000  avgt    3    359.933 ±    9.004  ns/op
Benchmarks.offHeapMiss            10000  avgt    3    478.635 ±   13.115  ns/op
Benchmarks.offHeapMiss           100000  avgt    3    607.679 ±    4.786  ns/op
Benchmarks.offHeapMiss          1000000  avgt    3    773.242 ±   14.705  ns/op
Benchmarks.scalaSetHit             1000  avgt    3    247.438 ±    4.116  ns/op
Benchmarks.scalaSetHit            10000  avgt    3    316.418 ±    7.615  ns/op
Benchmarks.scalaSetHit           100000  avgt    3    400.356 ±    1.613  ns/op
Benchmarks.scalaSetHit          1000000  avgt    3    529.193 ±   16.470  ns/op
Benchmarks.scalaSetMiss            1000  avgt    3    202.543 ±    2.552  ns/op
Benchmarks.scalaSetMiss           10000  avgt    3    272.906 ±   11.886  ns/op
Benchmarks.scalaSetMiss          100000  avgt    3    353.616 ±   54.538  ns/op
Benchmarks.scalaSetMiss         1000000  avgt    3    412.825 ±    5.841  ns/op
</code>
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
class Benchmarks {

  @Param(Array("1000", "10000", "100000", "1000000"))
  var size: Int = 0

  private var javaSet: java.util.Set[UUID] = _
  private var scalaSet: Set[UUID] = _
  private var offHeap: OffHeapBasedUuidSet = _
  private var onHeapLong: SortedLongArrayUuidSet = _
  private var onHeapUuid: SortedUuidArray = _
  private var netty4Single: Netty4UuidSet = _
  private var netty4Composite: Netty4UuidSet = _
  private var netty5Single: Netty5UuidSet = _
  private var netty5Composite: Netty5UuidSet = _

  private var hitKeys: java.util.List[UUID] = _
  private var missKeys: java.util.List[UUID] = _

  @Setup
  def setup(): Unit = {
    val uuids = (1 to size).map(_ => UUID.randomUUID())

    javaSet = new java.util.HashSet(uuids.asJava)
    require(uuids.size == javaSet.size)
    scalaSet = uuids.toSet
    offHeap = UuidSortedList.offHeap(uuids)
    onHeapLong = UuidSortedList.heapLong(uuids)
    onHeapUuid = UuidSortedList.heapUuid(uuids)
    netty4Single = UuidSortedList.netty4Single(uuids)
    netty4Composite = UuidSortedList.netty4Composite(uuids)
    netty5Single = UuidSortedList.netty5Single(uuids)
    netty5Composite = UuidSortedList.netty5Composite(uuids)

    hitKeys = IntStream.range(0, 10).mapToObj(_ => uuids(Random.nextInt(uuids.size))).collect(Collectors.toList[UUID])
    missKeys = IntStream.range(0, 10).mapToObj(_ => UUID.randomUUID).collect(Collectors.toList[UUID])
    require(JavaHelper.run(javaSet, missKeys) == 0)

    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == offHeap.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == onHeapLong.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == onHeapUuid.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == netty4Single.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == netty4Composite.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == netty5Single.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == netty5Composite.contains(uuid)))

    missKeys.forEach(uuid => require(javaSet.contains(uuid) == offHeap.contains(uuid)))
    missKeys.forEach(uuid => require(javaSet.contains(uuid) == onHeapLong.contains(uuid)))
    missKeys.forEach(uuid => require(javaSet.contains(uuid) == onHeapUuid.contains(uuid)))
    missKeys.forEach(uuid => require(javaSet.contains(uuid) == netty4Single.contains(uuid)))
    missKeys.forEach(uuid => require(javaSet.contains(uuid) == netty4Composite.contains(uuid)))
    missKeys.forEach(uuid => require(javaSet.contains(uuid) == netty5Single.contains(uuid)))
    missKeys.forEach(uuid => require(javaSet.contains(uuid) == netty5Composite.contains(uuid)))
  }

  @TearDown
  def destroy(): Unit = {
    offHeap.destroy()
    netty4Single.destroy()
    netty4Composite.destroy()
    netty5Single.destroy()
    netty5Composite.destroy()
  }

  @Benchmark
  def javaSetHit: Int = JavaHelper.run(javaSet, hitKeys)

  @Benchmark
  def javaSetMiss: Int = JavaHelper.run(javaSet, missKeys)

  @Benchmark
  def scalaSetHit: Int = JavaHelper.run(scalaSet, hitKeys)

  @Benchmark
  def scalaSetMiss: Int = JavaHelper.run(scalaSet, missKeys)

  @Benchmark
  def heapLongArrayHit: Int = JavaHelper.run(onHeapLong, hitKeys)

  @Benchmark
  def heapLongArrayMiss: Int = JavaHelper.run(onHeapLong, missKeys)

  @Benchmark
  def heapUuidArrayHit: Int = JavaHelper.run(onHeapUuid, hitKeys)

  @Benchmark
  def heapUuidArrayMiss: Int = JavaHelper.run(onHeapUuid, missKeys)

  @Benchmark
  def offHeapHit: Int = JavaHelper.run(offHeap, hitKeys)

  @Benchmark
  def offHeapMiss: Int = JavaHelper.run(offHeap, missKeys)

  @Benchmark
  def netty4SingleHit: Int = JavaHelper.run(netty4Single, hitKeys)

  @Benchmark
  def netty4SingleMiss: Int = JavaHelper.run(netty4Single, missKeys)

  @Benchmark
  def netty4CompositeHit: Int = JavaHelper.run(netty4Composite, hitKeys)

  @Benchmark
  def netty4CompositeMiss: Int = JavaHelper.run(netty4Composite, missKeys)

  @Benchmark
  def netty5SingleHit: Int = JavaHelper.run(netty5Single, hitKeys)

  @Benchmark
  def netty5SingleMiss: Int = JavaHelper.run(netty5Single, missKeys)

  @Benchmark
  def netty5CompositeHit: Int = JavaHelper.run(netty5Composite, hitKeys)

  @Benchmark
  def netty5CompositeMiss: Int = JavaHelper.run(netty5Composite, missKeys)
}
