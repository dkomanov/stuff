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
<code>
Benchmark                        (size)  Mode  Cnt      Score      Error  Units
Benchmarks.heapArrayHit            1000  avgt    3    307.359 ±    0.728  ns/op
Benchmarks.heapArrayHit           10000  avgt    3    424.029 ±    4.889  ns/op
Benchmarks.heapArrayHit          100000  avgt    3    554.394 ±    6.322  ns/op
Benchmarks.heapArrayHit         1000000  avgt    3    718.886 ±   12.699  ns/op
Benchmarks.heapArrayMiss           1000  avgt    3    334.974 ±    1.402  ns/op
Benchmarks.heapArrayMiss          10000  avgt    3    455.998 ±    7.447  ns/op
Benchmarks.heapArrayMiss         100000  avgt    3    579.426 ±    6.279  ns/op
Benchmarks.heapArrayMiss        1000000  avgt    3    727.404 ±   44.588  ns/op
Benchmarks.javaSetHit              1000  avgt    3    115.324 ±   26.040  ns/op
Benchmarks.javaSetHit             10000  avgt    3    122.736 ±    0.926  ns/op
Benchmarks.javaSetHit            100000  avgt    3     83.741 ±   13.965  ns/op
Benchmarks.javaSetHit           1000000  avgt    3    114.500 ±   29.786  ns/op
Benchmarks.javaSetMiss             1000  avgt    3     69.429 ±   10.341  ns/op
Benchmarks.javaSetMiss            10000  avgt    3     77.653 ±   17.087  ns/op
Benchmarks.javaSetMiss           100000  avgt    3     74.238 ±    8.242  ns/op
Benchmarks.javaSetMiss          1000000  avgt    3     77.487 ±   19.670  ns/op
Benchmarks.netty4CompositeHit      1000  avgt    3   8339.413 ± 2133.238  ns/op
Benchmarks.netty4CompositeHit     10000  avgt    3  12102.949 ±  842.799  ns/op
Benchmarks.netty4CompositeHit    100000  avgt    3  20355.848 ± 1083.110  ns/op
Benchmarks.netty4CompositeHit   1000000  avgt    3  10932.607 ±  242.434  ns/op
Benchmarks.netty4CompositeMiss     1000  avgt    3   8038.857 ± 8305.319  ns/op
Benchmarks.netty4CompositeMiss    10000  avgt    3  13625.994 ± 9300.203  ns/op
Benchmarks.netty4CompositeMiss   100000  avgt    3   8873.102 ± 7095.283  ns/op
Benchmarks.netty4CompositeMiss  1000000  avgt    3  12303.235 ± 4724.652  ns/op
Benchmarks.netty4SingleHit         1000  avgt    3    637.184 ±    9.828  ns/op
Benchmarks.netty4SingleHit        10000  avgt    3    773.328 ±    8.191  ns/op
Benchmarks.netty4SingleHit       100000  avgt    3   1041.523 ±  121.140  ns/op
Benchmarks.netty4SingleHit      1000000  avgt    3   1224.943 ±   18.558  ns/op
Benchmarks.netty4SingleMiss        1000  avgt    3    579.406 ±   16.573  ns/op
Benchmarks.netty4SingleMiss       10000  avgt    3    771.782 ±    8.333  ns/op
Benchmarks.netty4SingleMiss      100000  avgt    3    990.317 ±   11.605  ns/op
Benchmarks.netty4SingleMiss     1000000  avgt    3   1196.679 ±   10.093  ns/op
Benchmarks.netty5CompositeHit      1000  avgt    3   2752.809 ±  143.963  ns/op
Benchmarks.netty5CompositeHit     10000  avgt    3   5543.508 ±  539.825  ns/op
Benchmarks.netty5CompositeHit    100000  avgt    3   6504.562 ±  358.951  ns/op
Benchmarks.netty5CompositeHit   1000000  avgt    3  11536.318 ± 2624.800  ns/op
Benchmarks.netty5CompositeMiss     1000  avgt    3   2782.836 ±  274.454  ns/op
Benchmarks.netty5CompositeMiss    10000  avgt    3   4184.375 ±  626.011  ns/op
Benchmarks.netty5CompositeMiss   100000  avgt    3   5644.994 ±  123.795  ns/op
Benchmarks.netty5CompositeMiss  1000000  avgt    3   8609.538 ±  215.587  ns/op
Benchmarks.netty5SingleHit         1000  avgt    3    479.255 ±    3.540  ns/op
Benchmarks.netty5SingleHit        10000  avgt    3    702.154 ±    9.447  ns/op
Benchmarks.netty5SingleHit       100000  avgt    3    897.172 ±   74.461  ns/op
Benchmarks.netty5SingleHit      1000000  avgt    3   1081.787 ±   19.217  ns/op
Benchmarks.netty5SingleMiss        1000  avgt    3    501.996 ±   20.559  ns/op
Benchmarks.netty5SingleMiss       10000  avgt    3    667.884 ±    4.363  ns/op
Benchmarks.netty5SingleMiss      100000  avgt    3    862.942 ±    7.700  ns/op
Benchmarks.netty5SingleMiss     1000000  avgt    3   1091.997 ±    4.783  ns/op
Benchmarks.offHeapHit              1000  avgt    3    299.429 ±    1.107  ns/op
Benchmarks.offHeapHit             10000  avgt    3    402.442 ±    6.649  ns/op
Benchmarks.offHeapHit            100000  avgt    3    556.385 ±    8.425  ns/op
Benchmarks.offHeapHit           1000000  avgt    3    703.323 ±   37.659  ns/op
Benchmarks.offHeapMiss             1000  avgt    3    353.114 ±    5.803  ns/op
Benchmarks.offHeapMiss            10000  avgt    3    492.531 ±    0.978  ns/op
Benchmarks.offHeapMiss           100000  avgt    3    596.708 ±   15.274  ns/op
Benchmarks.offHeapMiss          1000000  avgt    3    772.442 ±    3.149  ns/op
Benchmarks.scalaSetHit             1000  avgt    3    251.332 ±    3.434  ns/op
Benchmarks.scalaSetHit            10000  avgt    3    338.365 ±    7.942  ns/op
Benchmarks.scalaSetHit           100000  avgt    3    455.671 ±   17.440  ns/op
Benchmarks.scalaSetHit          1000000  avgt    3    575.694 ±   25.463  ns/op
Benchmarks.scalaSetMiss            1000  avgt    3    187.328 ±    9.388  ns/op
Benchmarks.scalaSetMiss           10000  avgt    3    274.589 ±    3.211  ns/op
Benchmarks.scalaSetMiss          100000  avgt    3    352.340 ±    1.799  ns/op
Benchmarks.scalaSetMiss         1000000  avgt    3    439.608 ±   23.279  ns/op
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
  private var onHeap: ArrayBasedUuidSet = _
  private var netty4Single: Netty4UuidSet = _
  private var netty4Composite: Netty4UuidSet = _
  private var netty5Single: Netty5UuidSet = _
  private var netty5Composite: Netty5UuidSet = _

  private var hitKeys: java.util.List[UUID] = _
  private var missKeys: java.util.List[UUID] = _

  @Setup
  def setup(): Unit = {
    val uuids = (1 to size).map(_ => UUID.randomUUID())
    //val uuidsPlusIndex = uuids.zipWithIndex

    javaSet = new java.util.HashSet(uuids.asJava)
    require(uuids.size == javaSet.size)
    scalaSet = uuids.toSet
    offHeap = UuidSortedList.offHeap(uuids)
    onHeap = UuidSortedList.heap(uuids)
    netty4Single = UuidSortedList.netty4Single(uuids)
    netty4Composite = UuidSortedList.netty4Composite(uuids)
    netty5Single = UuidSortedList.netty5Single(uuids)
    netty5Composite = UuidSortedList.netty5Composite(uuids)

    hitKeys = IntStream.range(0, 10).mapToObj(_ => uuids(Random.nextInt(uuids.size))).collect(Collectors.toList[UUID])
    missKeys = IntStream.range(0, 10).mapToObj(_ => UUID.randomUUID).collect(Collectors.toList[UUID])
    require(JavaHelper.run(javaSet, missKeys) == 0)

    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == offHeap.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == onHeap.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == netty4Single.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == netty4Composite.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == netty5Single.contains(uuid)))
    hitKeys.forEach(uuid => require(javaSet.contains(uuid) == netty5Composite.contains(uuid)))

    missKeys.forEach(uuid => require(javaSet.contains(uuid) == offHeap.contains(uuid)))
    missKeys.forEach(uuid => require(javaSet.contains(uuid) == onHeap.contains(uuid)))
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
  def heapArrayHit: Int = JavaHelper.run(onHeap, hitKeys)

  @Benchmark
  def heapArrayMiss: Int = JavaHelper.run(onHeap, missKeys)

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
