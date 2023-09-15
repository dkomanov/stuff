package com.komanov.lang.jmh

import com.komanov.jmh.BenchmarkBase
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

/*
Benchmark                                            (iterations)  Mode  Cnt         Score        Error   Units
LoopBenchmark.javaBackwardLoop                              10000  avgt    5      7063.623 ±     52.235   ns/op
LoopBenchmark.javaBackwardLoop:·gc.alloc.rate               10000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.javaBackwardLoop:·gc.alloc.rate.norm          10000  avgt    5         0.011 ±      0.066    B/op
LoopBenchmark.javaBackwardLoop:·gc.count                    10000  avgt    5           ≈ 0               counts
LoopBenchmark.javaBackwardLoop                             100000  avgt    5     70940.027 ±    340.538   ns/op
LoopBenchmark.javaBackwardLoop:·gc.alloc.rate              100000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.javaBackwardLoop:·gc.alloc.rate.norm         100000  avgt    5         0.111 ±      0.655    B/op
LoopBenchmark.javaBackwardLoop:·gc.count                   100000  avgt    5           ≈ 0               counts
LoopBenchmark.javaBackwardLoop                            1000000  avgt    5    709612.659 ±  18302.720   ns/op
LoopBenchmark.javaBackwardLoop:·gc.alloc.rate             1000000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.javaBackwardLoop:·gc.alloc.rate.norm        1000000  avgt    5         1.106 ±      6.538    B/op
LoopBenchmark.javaBackwardLoop:·gc.count                  1000000  avgt    5           ≈ 0               counts
LoopBenchmark.javaBackwardLoop                           10000000  avgt    5   7075639.941 ±  95324.609   ns/op
LoopBenchmark.javaBackwardLoop:·gc.alloc.rate            10000000  avgt    5         0.003 ±      0.017  MB/sec
LoopBenchmark.javaBackwardLoop:·gc.alloc.rate.norm       10000000  avgt    5        11.012 ±     65.066    B/op
LoopBenchmark.javaBackwardLoop:·gc.count                 10000000  avgt    5           ≈ 0               counts
LoopBenchmark.javaForwardLoop                               10000  avgt    5      9289.703 ±    215.550   ns/op
LoopBenchmark.javaForwardLoop:·gc.alloc.rate                10000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.javaForwardLoop:·gc.alloc.rate.norm           10000  avgt    5         0.014 ±      0.086    B/op
LoopBenchmark.javaForwardLoop:·gc.count                     10000  avgt    5           ≈ 0               counts
LoopBenchmark.javaForwardLoop                              100000  avgt    5     93085.670 ±   2866.549   ns/op
LoopBenchmark.javaForwardLoop:·gc.alloc.rate               100000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.javaForwardLoop:·gc.alloc.rate.norm          100000  avgt    5         0.145 ±      0.867    B/op
LoopBenchmark.javaForwardLoop:·gc.count                    100000  avgt    5           ≈ 0               counts
LoopBenchmark.javaForwardLoop                             1000000  avgt    5    927851.604 ±   9187.665   ns/op
LoopBenchmark.javaForwardLoop:·gc.alloc.rate              1000000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.javaForwardLoop:·gc.alloc.rate.norm         1000000  avgt    5         1.441 ±      8.548    B/op
LoopBenchmark.javaForwardLoop:·gc.count                   1000000  avgt    5           ≈ 0               counts
LoopBenchmark.javaForwardLoop                            10000000  avgt    5   9278958.615 ±  52078.569   ns/op
LoopBenchmark.javaForwardLoop:·gc.alloc.rate             10000000  avgt    5         0.003 ±      0.017  MB/sec
LoopBenchmark.javaForwardLoop:·gc.alloc.rate.norm        10000000  avgt    5        14.312 ±     84.681    B/op
LoopBenchmark.javaForwardLoop:·gc.count                  10000000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaBackwardLoop                             10000  avgt    5      7065.796 ±    137.950   ns/op
LoopBenchmark.scalaBackwardLoop:·gc.alloc.rate              10000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaBackwardLoop:·gc.alloc.rate.norm         10000  avgt    5         0.011 ±      0.065    B/op
LoopBenchmark.scalaBackwardLoop:·gc.count                   10000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaBackwardLoop                            100000  avgt    5     70462.927 ±    208.678   ns/op
LoopBenchmark.scalaBackwardLoop:·gc.alloc.rate             100000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaBackwardLoop:·gc.alloc.rate.norm        100000  avgt    5         0.111 ±      0.649    B/op
LoopBenchmark.scalaBackwardLoop:·gc.count                  100000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaBackwardLoop                           1000000  avgt    5    708072.985 ±   9544.278   ns/op
LoopBenchmark.scalaBackwardLoop:·gc.alloc.rate            1000000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaBackwardLoop:·gc.alloc.rate.norm       1000000  avgt    5         1.105 ±      6.492    B/op
LoopBenchmark.scalaBackwardLoop:·gc.count                 1000000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaBackwardLoop                          10000000  avgt    5   7068418.282 ± 118655.732   ns/op
LoopBenchmark.scalaBackwardLoop:·gc.alloc.rate           10000000  avgt    5         0.003 ±      0.017  MB/sec
LoopBenchmark.scalaBackwardLoop:·gc.alloc.rate.norm      10000000  avgt    5        11.012 ±     65.381    B/op
LoopBenchmark.scalaBackwardLoop:·gc.count                10000000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaForwardLoop                              10000  avgt    5      9276.051 ±     59.432   ns/op
LoopBenchmark.scalaForwardLoop:·gc.alloc.rate               10000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaForwardLoop:·gc.alloc.rate.norm          10000  avgt    5         0.015 ±      0.086    B/op
LoopBenchmark.scalaForwardLoop:·gc.count                    10000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaForwardLoop                             100000  avgt    5     92674.215 ±    324.339   ns/op
LoopBenchmark.scalaForwardLoop:·gc.alloc.rate              100000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaForwardLoop:·gc.alloc.rate.norm         100000  avgt    5         0.145 ±      0.857    B/op
LoopBenchmark.scalaForwardLoop:·gc.count                   100000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaForwardLoop                            1000000  avgt    5    925501.894 ±   3091.872   ns/op
LoopBenchmark.scalaForwardLoop:·gc.alloc.rate             1000000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaForwardLoop:·gc.alloc.rate.norm        1000000  avgt    5         1.444 ±      8.547    B/op
LoopBenchmark.scalaForwardLoop:·gc.count                  1000000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaForwardLoop                           10000000  avgt    5   9271605.994 ±  98751.688   ns/op
LoopBenchmark.scalaForwardLoop:·gc.alloc.rate            10000000  avgt    5         0.003 ±      0.017  MB/sec
LoopBenchmark.scalaForwardLoop:·gc.alloc.rate.norm       10000000  avgt    5        14.252 ±     84.440    B/op
LoopBenchmark.scalaForwardLoop:·gc.count                 10000000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaRangeLoop                                10000  avgt    5      6686.168 ±    263.443   ns/op
LoopBenchmark.scalaRangeLoop:·gc.alloc.rate                 10000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaRangeLoop:·gc.alloc.rate.norm            10000  avgt    5         0.010 ±      0.062    B/op
LoopBenchmark.scalaRangeLoop:·gc.count                      10000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaRangeLoop                               100000  avgt    5     67144.078 ±   1657.667   ns/op
LoopBenchmark.scalaRangeLoop:·gc.alloc.rate                100000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaRangeLoop:·gc.alloc.rate.norm           100000  avgt    5         0.104 ±      0.614    B/op
LoopBenchmark.scalaRangeLoop:·gc.count                     100000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaRangeLoop                              1000000  avgt    5    667787.186 ±  17535.650   ns/op
LoopBenchmark.scalaRangeLoop:·gc.alloc.rate               1000000  avgt    5         0.003 ±      0.018  MB/sec
LoopBenchmark.scalaRangeLoop:·gc.alloc.rate.norm          1000000  avgt    5         1.032 ±      6.076    B/op
LoopBenchmark.scalaRangeLoop:·gc.count                    1000000  avgt    5           ≈ 0               counts
LoopBenchmark.scalaRangeLoop                             10000000  avgt    5  12471426.541 ± 939652.494   ns/op
LoopBenchmark.scalaRangeLoop:·gc.alloc.rate              10000000  avgt    5         0.016 ±      0.017  MB/sec
LoopBenchmark.scalaRangeLoop:·gc.alloc.rate.norm         10000000  avgt    5       107.239 ±    113.999    B/op
LoopBenchmark.scalaRangeLoop:·gc.count                   10000000  avgt    5           ≈ 0               counts
  */
class LoopBenchmark extends BenchmarkBase {
  @Param(Array(
    "10000",
    "100000",
    "1000000",
    "10000000",
  ))
  var iterations = 0

  @Setup
  def test(b: Blackhole): Unit = {
    require(scalaForwardLoop(b) == scalaBackwardLoop(b))
    require(scalaForwardLoop(b) == scalaRangeLoop(b))
    require(scalaForwardLoop(b) == javaForwardLoop(b))
    require(scalaForwardLoop(b) == javaBackwardLoop(b))
  }

  @Benchmark
  def scalaForwardLoop(b: Blackhole): Long =
    ScalaLoopUtils.scalaForwardLoop(iterations, b)

  @Benchmark
  def scalaBackwardLoop(b: Blackhole): Long =
    ScalaLoopUtils.scalaBackwardLoop(iterations, b)

  @Benchmark
  def scalaRangeLoop(b: Blackhole): Long =
    ScalaLoopUtils.scalaRangeLoop(iterations, b)

  @Benchmark
  def javaForwardLoop(b: Blackhole): Long =
    JavaLoopUtils.javaForwardLoop(iterations, b)

  @Benchmark
  def javaBackwardLoop(b: Blackhole): Long =
    JavaLoopUtils.javaBackwardLoop(iterations, b)
}
