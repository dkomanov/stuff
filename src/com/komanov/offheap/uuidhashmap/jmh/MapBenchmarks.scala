package com.komanov.offheap.uuidhashmap.jmh

import com.komanov.offheap.uuidhashmap.UuidToIntHashMap
import org.openjdk.jmh.annotations._

import java.util
import java.util.UUID
import java.util.concurrent.TimeUnit
import java.util.stream.{Collectors, IntStream}
import scala.jdk.CollectionConverters._

/*
openjdk-17

Benchmark                                 (size)  Mode  Cnt     Score     Error  Units
JavaHashMapBenchmark.baselineEmptyMap        100  avgt    3    24.212 ±   0.525  ns/op
JavaHashMapBenchmark.baselineEmptyMap       1000  avgt    3    26.601 ±   2.146  ns/op
JavaHashMapBenchmark.baselineEmptyMap      10000  avgt    3    26.861 ±   1.648  ns/op
JavaHashMapBenchmark.baselineEmptyMap     100000  avgt    3    39.591 ±   2.815  ns/op
JavaHashMapBenchmark.baselineEmptyMap    1000000  avgt    3    46.816 ±   1.266  ns/op
JavaHashMapBenchmark.javaMapHit              100  avgt    3    85.799 ±  21.267  ns/op
JavaHashMapBenchmark.javaMapHit             1000  avgt    3   104.415 ±  12.034  ns/op
JavaHashMapBenchmark.javaMapHit            10000  avgt    3   126.342 ±  13.965  ns/op
JavaHashMapBenchmark.javaMapHit           100000  avgt    3   355.278 ±  80.176  ns/op
JavaHashMapBenchmark.javaMapHit          1000000  avgt    3   676.491 ± 190.719  ns/op
JavaHashMapBenchmark.javaMapMiss             100  avgt    3    84.048 ±  67.895  ns/op
JavaHashMapBenchmark.javaMapMiss            1000  avgt    3    88.160 ±  26.518  ns/op
JavaHashMapBenchmark.javaMapMiss           10000  avgt    3    81.635 ±  18.126  ns/op
JavaHashMapBenchmark.javaMapMiss          100000  avgt    3    91.886 ±  30.287  ns/op
JavaHashMapBenchmark.javaMapMiss         1000000  avgt    3    90.686 ±  52.488  ns/op
OffHeapMapBenchmark.offHeapHit               100  avgt    3   132.562 ±   0.825  ns/op
OffHeapMapBenchmark.offHeapHit              1000  avgt    3   143.154 ±   1.298  ns/op
OffHeapMapBenchmark.offHeapHit             10000  avgt    3   283.964 ±  21.683  ns/op
OffHeapMapBenchmark.offHeapHit            100000  avgt    3   466.296 ±  18.823  ns/op
OffHeapMapBenchmark.offHeapHit           1000000  avgt    3   714.652 ±  39.263  ns/op
OffHeapMapBenchmark.offHeapMiss              100  avgt    3   104.409 ±   0.443  ns/op
OffHeapMapBenchmark.offHeapMiss             1000  avgt    3   114.769 ±   1.519  ns/op
OffHeapMapBenchmark.offHeapMiss            10000  avgt    3   106.352 ±   1.280  ns/op
OffHeapMapBenchmark.offHeapMiss           100000  avgt    3    97.465 ±   3.346  ns/op
OffHeapMapBenchmark.offHeapMiss          1000000  avgt    3   100.840 ±   1.301  ns/op
OnHeapMapBenchmark.onHeapHit                 100  avgt    3   104.522 ±   0.690  ns/op
OnHeapMapBenchmark.onHeapHit                1000  avgt    3   125.414 ±   2.978  ns/op
OnHeapMapBenchmark.onHeapHit               10000  avgt    3   256.635 ±  10.022  ns/op
OnHeapMapBenchmark.onHeapHit              100000  avgt    3   485.328 ±  53.055  ns/op
OnHeapMapBenchmark.onHeapHit             1000000  avgt    3   903.718 ±   8.689  ns/op
OnHeapMapBenchmark.onHeapMiss                100  avgt    3    97.728 ±   3.209  ns/op
OnHeapMapBenchmark.onHeapMiss               1000  avgt    3   104.252 ±   1.168  ns/op
OnHeapMapBenchmark.onHeapMiss              10000  avgt    3    96.590 ±   1.724  ns/op
OnHeapMapBenchmark.onHeapMiss             100000  avgt    3   113.137 ±   3.816  ns/op
OnHeapMapBenchmark.onHeapMiss            1000000  avgt    3   107.300 ±   1.114  ns/op
ScalaImmutableMapBenchmark.scalaMapHit       100  avgt    3   224.147 ±   2.866  ns/op
ScalaImmutableMapBenchmark.scalaMapHit      1000  avgt    3   304.349 ±   7.426  ns/op
ScalaImmutableMapBenchmark.scalaMapHit     10000  avgt    3   605.917 ±   5.969  ns/op
ScalaImmutableMapBenchmark.scalaMapHit    100000  avgt    3  1626.796 ± 116.276  ns/op
ScalaImmutableMapBenchmark.scalaMapHit   1000000  avgt    3  2588.303 ± 270.280  ns/op
ScalaImmutableMapBenchmark.scalaMapMiss      100  avgt    3   161.004 ±   2.162  ns/op
ScalaImmutableMapBenchmark.scalaMapMiss     1000  avgt    3   184.038 ±   1.953  ns/op
ScalaImmutableMapBenchmark.scalaMapMiss    10000  avgt    3   295.194 ±  21.110  ns/op
ScalaImmutableMapBenchmark.scalaMapMiss   100000  avgt    3   383.585 ±  63.340  ns/op
ScalaImmutableMapBenchmark.scalaMapMiss  1000000  avgt    3   509.092 ±  67.158  ns/op

Extra for off heap map with padding:
Benchmark                                    (size)  Mode  Cnt    Score   Error  Units
OffHeapMapBenchmark.offHeapHit                  100  avgt    2  132.173          ns/op
OffHeapMapBenchmark.offHeapHit               100000  avgt    2  446.956          ns/op
OffHeapMapBenchmark.offHeapHit              1000000  avgt    2  702.265          ns/op
OffHeapMapBenchmark.offHeapMiss                 100  avgt    2  110.888          ns/op
OffHeapMapBenchmark.offHeapMiss              100000  avgt    2  114.062          ns/op
OffHeapMapBenchmark.offHeapMiss             1000000  avgt    2  126.112          ns/op
OffHeapWithPaddingMapBenchmark.offHeapHit       100  avgt    2  128.395          ns/op
OffHeapWithPaddingMapBenchmark.offHeapHit    100000  avgt    2  444.711          ns/op
OffHeapWithPaddingMapBenchmark.offHeapHit   1000000  avgt    2  712.532          ns/op
OffHeapWithPaddingMapBenchmark.offHeapMiss      100  avgt    2   96.717          ns/op
OffHeapWithPaddingMapBenchmark.offHeapMiss   100000  avgt    2   91.884          ns/op
OffHeapWithPaddingMapBenchmark.offHeapMiss  1000000  avgt    2  111.771          ns/op
*/
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Measurement(iterations = 3, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 4, timeUnit = TimeUnit.SECONDS)
abstract class MapBenchmarkBase {

  @Param(Array("100", "1000", "10000", "100000", "1000000"))
  var size: Int = 0

  var javaMap: util.Map[UUID, Integer] = _
  var index = 0
  var hitKeys: util.List[util.List[UUID]] = _
  var missKeys: util.List[UUID] = _

  def init(): Unit = {
    val uuids = (1 to size).map(_ => UUID.randomUUID())
    javaMap = new util.HashMap(uuids.zipWithIndex.map(t => t._1 -> Int.box(t._2)).toMap.asJava)

    require(javaMap.size == size)

    hitKeys = new util.ArrayList(uuids.grouped(10).toSeq.map(list => new util.ArrayList(list.asJava)).asJava)
    index = 0
    missKeys = IntStream.range(0, 10).mapToObj(_ => UUID.randomUUID).collect(Collectors.toList[UUID])
  }
}

class JavaHashMapBenchmark extends MapBenchmarkBase {
  @Setup
  def setup(): Unit = {
    init()
  }

  @Benchmark
  def baselineEmptyMap: Int = {
    index += 1
    JavaHelper.run(util.Collections.emptyMap[UUID, Integer](), hitKeys.get(index % hitKeys.size))
  }

  @Benchmark
  def javaMapHit: Int = {
    index += 1
    JavaHelper.run(javaMap, hitKeys.get(index % hitKeys.size))
  }

  @Benchmark
  def javaMapMiss: Int = JavaHelper.run(javaMap, missKeys)
}

class ScalaImmutableMapBenchmark extends MapBenchmarkBase {
  private var scalaMap: Map[UUID, Integer] = _

  @Setup
  def setup(): Unit = {
    init()
    scalaMap = Map(javaMap.asScala.toSeq: _*)
  }

  @Benchmark
  def scalaMapHit: Int = {
    index += 1
    JavaHelper.run(scalaMap, hitKeys.get(index % hitKeys.size))
  }

  @Benchmark
  def scalaMapMiss: Int = JavaHelper.run(scalaMap, missKeys)
}

class OnHeapMapBenchmark extends MapBenchmarkBase {
  private var onHeapMap: UuidToIntHashMap = _

  @Setup
  def setup(): Unit = {
    init()
    onHeapMap = UuidToIntHashMap.heap(javaMap)
  }

  @Benchmark
  def onHeapHit: Int = {
    index += 1
    JavaHelper.run(onHeapMap, hitKeys.get(index % hitKeys.size))
  }

  @Benchmark
  def onHeapMiss: Int = JavaHelper.run(onHeapMap, missKeys)
}

class OffHeapMapBenchmark extends MapBenchmarkBase {
  private var offHeapMap: UuidToIntHashMap = _

  @Setup
  def setup(): Unit = {
    init()
    offHeapMap = UuidToIntHashMap.offHeap(javaMap)
  }

  @TearDown
  def destroy(): Unit = {
    offHeapMap.destroy()
  }

  @Benchmark
  def offHeapHit: Int = {
    index += 1
    JavaHelper.run(offHeapMap, hitKeys.get(index % hitKeys.size))
  }

  @Benchmark
  def offHeapMiss: Int = JavaHelper.run(offHeapMap, missKeys)
}

class OffHeapWithPaddingMapBenchmark extends MapBenchmarkBase {
  private var offHeapMap: UuidToIntHashMap = _

  @Setup
  def setup(): Unit = {
    init()
    offHeapMap = UuidToIntHashMap.offHeapWithPadding(javaMap)
  }

  @TearDown
  def destroy(): Unit = {
    offHeapMap.destroy()
  }

  @Benchmark
  def offHeapWithPaddingHit: Int = {
    index += 1
    JavaHelper.run(offHeapMap, hitKeys.get(index % hitKeys.size))
  }

  @Benchmark
  def offHeapWithPaddingMiss: Int = JavaHelper.run(offHeapMap, missKeys)
}
