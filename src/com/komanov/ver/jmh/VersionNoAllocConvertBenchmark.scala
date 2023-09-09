package com.komanov.ver.jmh

import com.komanov.ver.{Version, VersionNoAlloc}
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Benchmark                                                                        (encoded)  Mode  Cnt     Score      Error   Units
VersionNoAllocConvertBenchmark.generic                                                      avgt    5     1.185 ±    0.011   ns/op
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate                                       avgt    5     0.003 ±    0.018  MB/sec
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate.norm                                  avgt    5    ≈ 10⁻⁶               B/op
VersionNoAllocConvertBenchmark.generic:·gc.count                                            avgt    5       ≈ 0             counts
VersionNoAllocConvertBenchmark.generic                                               0.0.0  avgt    5     9.680 ±    1.561   ns/op
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate                                0.0.0  avgt    5  7888.932 ± 1255.535  MB/sec
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate.norm                           0.0.0  avgt    5    40.000 ±    0.001    B/op
VersionNoAllocConvertBenchmark.generic:·gc.count                                     0.0.0  avgt    5   184.000             counts
VersionNoAllocConvertBenchmark.generic:·gc.time                                      0.0.0  avgt    5   103.000                 ms
VersionNoAllocConvertBenchmark.generic                                               1.0.0  avgt    5     9.553 ±    0.663   ns/op
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate                                1.0.0  avgt    5  7984.353 ±  559.639  MB/sec
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate.norm                           1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionNoAllocConvertBenchmark.generic:·gc.count                                     1.0.0  avgt    5   186.000             counts
VersionNoAllocConvertBenchmark.generic:·gc.time                                      1.0.0  avgt    5    98.000                 ms
VersionNoAllocConvertBenchmark.generic                                         200.200.200  avgt    5     9.518 ±    1.484   ns/op
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate                          200.200.200  avgt    5  8033.103 ± 1099.181  MB/sec
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate.norm                     200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionNoAllocConvertBenchmark.generic:·gc.count                               200.200.200  avgt    5   177.000             counts
VersionNoAllocConvertBenchmark.generic:·gc.time                                200.200.200  avgt    5   101.000                 ms
VersionNoAllocConvertBenchmark.generic                                   10000.10000.10000  avgt    5     9.464 ±    1.175   ns/op
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate                    10000.10000.10000  avgt    5  8062.569 ±  972.745  MB/sec
VersionNoAllocConvertBenchmark.generic:·gc.alloc.rate.norm               10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionNoAllocConvertBenchmark.generic:·gc.count                         10000.10000.10000  avgt    5   185.000             counts
VersionNoAllocConvertBenchmark.generic:·gc.time                          10000.10000.10000  avgt    5   102.000                 ms
VersionNoAllocConvertBenchmark.inlined                                                      avgt    5     1.184 ±    0.007   ns/op
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate                                       avgt    5     0.003 ±    0.018  MB/sec
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate.norm                                  avgt    5    ≈ 10⁻⁶               B/op
VersionNoAllocConvertBenchmark.inlined:·gc.count                                            avgt    5       ≈ 0             counts
VersionNoAllocConvertBenchmark.inlined                                               0.0.0  avgt    5     9.422 ±    0.908   ns/op
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate                                0.0.0  avgt    5  8097.275 ±  762.730  MB/sec
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate.norm                           0.0.0  avgt    5    40.000 ±    0.001    B/op
VersionNoAllocConvertBenchmark.inlined:·gc.count                                     0.0.0  avgt    5   191.000             counts
VersionNoAllocConvertBenchmark.inlined:·gc.time                                      0.0.0  avgt    5   102.000                 ms
VersionNoAllocConvertBenchmark.inlined                                               1.0.0  avgt    5     9.465 ±    0.516   ns/op
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate                                1.0.0  avgt    5  8056.660 ±  439.698  MB/sec
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate.norm                           1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionNoAllocConvertBenchmark.inlined:·gc.count                                     1.0.0  avgt    5   181.000             counts
VersionNoAllocConvertBenchmark.inlined:·gc.time                                      1.0.0  avgt    5   102.000                 ms
VersionNoAllocConvertBenchmark.inlined                                         200.200.200  avgt    5     9.478 ±    1.095   ns/op
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate                          200.200.200  avgt    5  8051.818 ±  893.401  MB/sec
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate.norm                     200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionNoAllocConvertBenchmark.inlined:·gc.count                               200.200.200  avgt    5   180.000             counts
VersionNoAllocConvertBenchmark.inlined:·gc.time                                200.200.200  avgt    5    98.000                 ms
VersionNoAllocConvertBenchmark.inlined                                   10000.10000.10000  avgt    5     9.637 ±    2.172   ns/op
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate                    10000.10000.10000  avgt    5  7934.092 ± 1669.421  MB/sec
VersionNoAllocConvertBenchmark.inlined:·gc.alloc.rate.norm               10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionNoAllocConvertBenchmark.inlined:·gc.count                         10000.10000.10000  avgt    5   179.000             counts
VersionNoAllocConvertBenchmark.inlined:·gc.time                          10000.10000.10000  avgt    5    96.000                 ms
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx1G"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
class VersionNoAllocConvertBenchmark {

  @Param(value = Array(
    "",
    "0.0.0",
    "1.0.0",
    "200.200.200",
    "10000.10000.10000",
  ))
  var encoded: String = _

  private var version: Option[Version] = _

  @Setup
  def init(): Unit = {
    version = Version.parseOptimized4(encoded)
  }

  @Benchmark
  def generic =
    VersionNoAlloc.toVersionGeneric(VersionNoAlloc.fromVersionGeneric(version))

  @Benchmark
  def inlined =
    VersionNoAlloc.toVersionInlined(VersionNoAlloc.fromVersionInlined(version))
}
