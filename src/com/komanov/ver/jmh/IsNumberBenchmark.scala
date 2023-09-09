package com.komanov.ver.jmh

import com.komanov.ver.Version
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Benchmark                                                                        (encoded)  Mode  Cnt     Score      Error   Units
IsNumberBenchmark.isNumber                                                           1.0.0  avgt    5    17.833 ±    0.804   ns/op
IsNumberBenchmark.isNumber:·gc.alloc.rate                                            1.0.0  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber:·gc.alloc.rate.norm                                       1.0.0  avgt    5    ≈ 10⁻⁵               B/op
IsNumberBenchmark.isNumber:·gc.count                                                 1.0.0  avgt    5       ≈ 0             counts
IsNumberBenchmark.isNumber                                                     200.200.200  avgt    5    26.206 ±    0.463   ns/op
IsNumberBenchmark.isNumber:·gc.alloc.rate                                      200.200.200  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber:·gc.alloc.rate.norm                                 200.200.200  avgt    5    ≈ 10⁻⁴               B/op
IsNumberBenchmark.isNumber:·gc.count                                           200.200.200  avgt    5       ≈ 0             counts
IsNumberBenchmark.isNumber                                               10000.10000.10000  avgt    5    29.716 ±    0.062   ns/op
IsNumberBenchmark.isNumber:·gc.alloc.rate                                10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber:·gc.alloc.rate.norm                           10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
IsNumberBenchmark.isNumber:·gc.count                                     10000.10000.10000  avgt    5       ≈ 0             counts
IsNumberBenchmark.isNumber2                                                          1.0.0  avgt    5    17.942 ±    1.011   ns/op
IsNumberBenchmark.isNumber2:·gc.alloc.rate                                           1.0.0  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber2:·gc.alloc.rate.norm                                      1.0.0  avgt    5    ≈ 10⁻⁵               B/op
IsNumberBenchmark.isNumber2:·gc.count                                                1.0.0  avgt    5       ≈ 0             counts
IsNumberBenchmark.isNumber2                                                    200.200.200  avgt    5    26.153 ±    0.071   ns/op
IsNumberBenchmark.isNumber2:·gc.alloc.rate                                     200.200.200  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber2:·gc.alloc.rate.norm                                200.200.200  avgt    5    ≈ 10⁻⁴               B/op
IsNumberBenchmark.isNumber2:·gc.count                                          200.200.200  avgt    5       ≈ 0             counts
IsNumberBenchmark.isNumber2                                              10000.10000.10000  avgt    5    29.866 ±    0.216   ns/op
IsNumberBenchmark.isNumber2:·gc.alloc.rate                               10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber2:·gc.alloc.rate.norm                          10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
IsNumberBenchmark.isNumber2:·gc.count                                    10000.10000.10000  avgt    5       ≈ 0             counts
IsNumberBenchmark.isNumber3                                                          1.0.0  avgt    5    16.741 ±    0.362   ns/op
IsNumberBenchmark.isNumber3:·gc.alloc.rate                                           1.0.0  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber3:·gc.alloc.rate.norm                                      1.0.0  avgt    5    ≈ 10⁻⁵               B/op
IsNumberBenchmark.isNumber3:·gc.count                                                1.0.0  avgt    5       ≈ 0             counts
IsNumberBenchmark.isNumber3                                                    200.200.200  avgt    5    24.182 ±    0.898   ns/op
IsNumberBenchmark.isNumber3:·gc.alloc.rate                                     200.200.200  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber3:·gc.alloc.rate.norm                                200.200.200  avgt    5    ≈ 10⁻⁴               B/op
IsNumberBenchmark.isNumber3:·gc.count                                          200.200.200  avgt    5       ≈ 0             counts
IsNumberBenchmark.isNumber3                                              10000.10000.10000  avgt    5    27.623 ±    2.138   ns/op
IsNumberBenchmark.isNumber3:·gc.alloc.rate                               10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
IsNumberBenchmark.isNumber3:·gc.alloc.rate.norm                          10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
IsNumberBenchmark.isNumber3:·gc.count                                    10000.10000.10000  avgt    5       ≈ 0             counts
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx1G"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
class IsNumberBenchmark {

  @Param(value = Array(
    "1.0.0",
    "200.200.200",
    "10000.10000.10000",
  ))
  var encoded: String = _

  private var dotFirst: Int = -1
  private var dotSecond: Int = -1

  @Setup
  def init(): Unit = {
    dotFirst = encoded.indexOf('.')
    dotSecond = encoded.indexOf('.', dotFirst + 1)
    require(isNumber == 3)
    require(isNumber2 == 3)
  }

  @Benchmark
  def isNumber: Int = {
    val v1 = Version.isNumber(encoded, 0, dotFirst)
    val v2 = Version.isNumber(encoded, dotFirst + 1, dotSecond)
    val v3 = Version.isNumber(encoded, dotSecond + 1, encoded.length)
    toInt(v1) + toInt(v2) + toInt(v3)
  }

  @Benchmark
  def isNumber2: Int = {
    val v1 = Version.isNumber2(encoded, 0, dotFirst)
    val v2 = Version.isNumber2(encoded, dotFirst + 1, dotSecond)
    val v3 = Version.isNumber2(encoded, dotSecond + 1, encoded.length)
    toInt(v1) + toInt(v2) + toInt(v3)
  }

  @Benchmark
  def isNumber3: Int = {
    val v1 = Version.isNumber3(encoded, 0, dotFirst)
    val v2 = Version.isNumber3(encoded, dotFirst + 1, dotSecond)
    val v3 = Version.isNumber3(encoded, dotSecond + 1, encoded.length)
    toInt(v1) + toInt(v2) + toInt(v3)
  }

  private def toInt(v: Boolean) = if (v) 1 else 0
}
