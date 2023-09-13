package com.komanov.ver.jmh

import com.komanov.ver.VersionNoAlloc
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Benchmark                                                                        (encoded)  Mode  Cnt     Score      Error   Units
VersionParseNoAllocBenchmark.optimized1                                                     avgt    5     2.078 ±    0.053   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                                      avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                                 avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                           avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                                200  avgt    5     5.507 ±    0.182   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                                 200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                            200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                      200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                               200.  avgt    5     6.454 ±    0.038   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                                200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                           200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                     200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                            200.200  avgt    5    13.522 ±    0.102   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                             200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                        200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                  200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                           200.200.  avgt    5    26.869 ±    0.082   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                            200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                       200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                 200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                          a.200.200  avgt    5    20.538 ±    0.624   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                           a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                      a.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                          200.a.200  avgt    5    30.787 ±    0.027   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                           200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                      200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                          200.200.a  avgt    5    34.647 ±    0.262   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                           200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                      200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                    200.200.200.200  avgt    5    21.235 ±    0.275   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                     200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                          200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                      200.200.99999  avgt    5   125.531 ±    5.213   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                       200.200.99999  avgt    5  2187.105 ±   88.747  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                  200.200.99999  avgt    5   144.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                            200.200.99999  avgt    5    75.000             counts
VersionParseNoAllocBenchmark.optimized1:·gc.time                             200.200.99999  avgt    5    34.000                 ms
VersionParseNoAllocBenchmark.optimized1                                       200.200.-200  avgt    5    40.374 ±    3.848   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                        200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                   200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                             200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized1                                              1.0.0  avgt    5    91.981 ±    3.188   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                               1.0.0  avgt    5  2984.888 ±  102.296  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                          1.0.0  avgt    5   144.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                                    1.0.0  avgt    5    89.000             counts
VersionParseNoAllocBenchmark.optimized1:·gc.time                                     1.0.0  avgt    5    42.000                 ms
VersionParseNoAllocBenchmark.optimized1                                        200.200.200  avgt    5   121.839 ±    0.384   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                         200.200.200  avgt    5  2252.957 ±    5.508  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                    200.200.200  avgt    5   144.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                              200.200.200  avgt    5    77.000             counts
VersionParseNoAllocBenchmark.optimized1:·gc.time                               200.200.200  avgt    5    38.000                 ms
VersionParseNoAllocBenchmark.optimized1                                    10000.9876.5432  avgt    5   133.951 ±    1.647   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                     10000.9876.5432  avgt    5  2049.434 ±   24.656  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm                10000.9876.5432  avgt    5   144.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                          10000.9876.5432  avgt    5    70.000             counts
VersionParseNoAllocBenchmark.optimized1:·gc.time                           10000.9876.5432  avgt    5    42.000                 ms
VersionParseNoAllocBenchmark.optimized1                                  10000.10000.10000  avgt    5   146.182 ±    0.199   ns/op
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate                   10000.10000.10000  avgt    5  1877.914 ±    1.686  MB/sec
VersionParseNoAllocBenchmark.optimized1:·gc.alloc.rate.norm              10000.10000.10000  avgt    5   144.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized1:·gc.count                        10000.10000.10000  avgt    5    65.000             counts
VersionParseNoAllocBenchmark.optimized1:·gc.time                         10000.10000.10000  avgt    5    33.000                 ms
VersionParseNoAllocBenchmark.optimized2                                                     avgt    5     2.069 ±    0.002   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                                      avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                                 avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                           avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                                200  avgt    5     5.515 ±    0.051   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                                 200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                            200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                      200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                               200.  avgt    5     6.466 ±    0.054   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                                200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                           200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                     200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                            200.200  avgt    5    13.799 ±    1.198   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                             200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                        200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                  200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                           200.200.  avgt    5    27.184 ±    1.267   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                            200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                       200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                 200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                          a.200.200  avgt    5    18.995 ±    0.130   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                           a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                      a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                          200.a.200  avgt    5    26.591 ±    0.089   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                           200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                      200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                          200.200.a  avgt    5    35.210 ±    0.226   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                           200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                      200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                    200.200.200.200  avgt    5    21.291 ±    0.182   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                     200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                          200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                      200.200.99999  avgt    5    91.141 ±    0.252   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                       200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                  200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                            200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                       200.200.-200  avgt    5    37.873 ±    1.391   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                        200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                   200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                             200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                              1.0.0  avgt    5    56.888 ±    0.280   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                               1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                          1.0.0  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                                    1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                        200.200.200  avgt    5    85.155 ±    0.099   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                         200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                    200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                              200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                    10000.9876.5432  avgt    5    99.134 ±    0.485   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                     10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm                10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                          10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized2                                  10000.10000.10000  avgt    5   112.207 ±    1.715   ns/op
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate                   10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized2:·gc.alloc.rate.norm              10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized2:·gc.count                        10000.10000.10000  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                                     avgt    5     2.102 ±    0.279   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                                      avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                                 avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                           avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                                200  avgt    5    11.352 ±    0.081   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                                 200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                            200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                      200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                               200.  avgt    5    26.152 ±    0.056   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                                200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                           200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                     200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                            200.200  avgt    5    32.111 ±    4.262   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                             200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                        200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                  200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                           200.200.  avgt    5    48.097 ±    1.229   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                            200.200.  avgt    5     0.003 ±    0.017  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                       200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                 200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                          a.200.200  avgt    5     2.367 ±    0.004   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                           a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                      a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                          200.a.200  avgt    5    27.972 ±    0.060   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                           200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                      200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                          200.200.a  avgt    5    67.198 ±    0.120   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                           200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                      200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                    200.200.200.200  avgt    5    58.917 ±    0.220   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                     200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                          200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                      200.200.99999  avgt    5    80.319 ±    0.423   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                       200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                  200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                            200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                       200.200.-200  avgt    5    57.910 ±    1.677   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                        200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                   200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                             200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                              1.0.0  avgt    5    41.423 ±    0.408   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                               1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                          1.0.0  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                                    1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                        200.200.200  avgt    5    67.851 ±    0.110   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                         200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                    200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                              200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                    10000.9876.5432  avgt    5    85.230 ±    0.297   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                     10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm                10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                          10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3                                  10000.10000.10000  avgt    5    99.666 ±    0.201   ns/op
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate                   10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3:·gc.alloc.rate.norm              10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3:·gc.count                        10000.10000.10000  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                                 avgt    5     2.070 ±    0.002   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                                  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                             avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                                       avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                            200  avgt    5    11.375 ±    0.081   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                             200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                        200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                                  200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                           200.  avgt    5    22.721 ±    0.129   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                            200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                       200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                                 200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                        200.200  avgt    5    26.879 ±    0.783   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                         200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                    200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                              200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                       200.200.  avgt    5    47.747 ±    0.176   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                        200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                   200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                             200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                      a.200.200  avgt    5     2.367 ±    0.012   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                       a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                  a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                            a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                      200.a.200  avgt    5    23.787 ±    0.018   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                       200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                  200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                            200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                      200.200.a  avgt    5    58.838 ±    1.986   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                       200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                  200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                            200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                200.200.200.200  avgt    5    55.017 ±    0.025   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                 200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm            200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                      200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                  200.200.99999  avgt    5    74.471 ±    6.674   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                   200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm              200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                        200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                   200.200.-200  avgt    5    57.940 ±    0.141   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                    200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm               200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                         200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                          1.0.0  avgt    5    43.781 ±    0.188   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                           1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                      1.0.0  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                                1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                    200.200.200  avgt    5    71.642 ±    1.876   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                     200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm                200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                          200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                                10000.9876.5432  avgt    5    89.196 ±   15.139   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate                 10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm            10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                      10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3Java                              10000.10000.10000  avgt    5    94.458 ±    0.260   ns/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate               10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3Java:·gc.alloc.rate.norm          10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3Java:·gc.count                    10000.10000.10000  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                                         avgt    5     2.072 ±    0.002   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                          avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                     avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                               avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                                    200  avgt    5    11.479 ±    1.379   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                     200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                          200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                                   200.  avgt    5    21.906 ±    0.023   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                    200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm               200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                         200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                                200.200  avgt    5    26.652 ±    0.847   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                 200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm            200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                      200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                               200.200.  avgt    5    48.597 ±    0.115   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm           200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                     200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                              a.200.200  avgt    5     2.957 ±    0.004   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate               a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm          a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                    a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                              200.a.200  avgt    5    22.914 ±    0.100   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate               200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm          200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                    200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                              200.200.a  avgt    5    53.121 ±    0.072   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate               200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm          200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                    200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                        200.200.200.200  avgt    5    53.133 ±    0.086   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate         200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm    200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count              200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                          200.200.99999  avgt    5    79.154 ±    1.886   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate           200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm      200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                           200.200.-200  avgt    5    48.918 ±    0.365   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate            200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm       200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                 200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                                  1.0.0  avgt    5    39.695 ±    0.232   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                   1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm              1.0.0  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                        1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                            200.200.200  avgt    5    67.558 ±    0.084   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate             200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm        200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count                  200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                        10000.9876.5432  avgt    5    88.800 ±    0.203   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate         10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm    10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count              10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch                      10000.10000.10000  avgt    5    99.079 ±    0.200   ns/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate       10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm  10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized3JavaNoSwitch:·gc.count            10000.10000.10000  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                                     avgt    5     2.071 ±    0.008   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                                      avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                                 avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                           avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                                200  avgt    5    15.916 ±    0.027   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                                 200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                            200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                      200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                               200.  avgt    5    19.596 ±    0.491   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                                200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                           200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                     200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                            200.200  avgt    5    29.198 ±    0.324   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                             200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                        200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                  200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                           200.200.  avgt    5    31.221 ±    0.047   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                            200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                       200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                 200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                          a.200.200  avgt    5     2.366 ±    0.007   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                           a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                      a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                          200.a.200  avgt    5    19.188 ±    0.029   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                           200.a.200  avgt    5     0.003 ±    0.017  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                      200.a.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                          200.200.a  avgt    5    29.814 ±    0.107   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                           200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                      200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                    200.200.200.200  avgt    5    40.875 ±    1.643   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                     200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                          200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                      200.200.99999  avgt    5    44.294 ±    0.192   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                       200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                  200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                            200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                       200.200.-200  avgt    5    29.743 ±    0.087   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                        200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                   200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                             200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                              1.0.0  avgt    5    21.942 ±    0.095   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                               1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                          1.0.0  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                                    1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                        200.200.200  avgt    5    40.734 ±    0.177   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                         200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                    200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                              200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                    10000.9876.5432  avgt    5    53.119 ±    2.166   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                     10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm                10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                          10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized4                                  10000.10000.10000  avgt    5    58.463 ±    0.969   ns/op
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate                   10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized4:·gc.alloc.rate.norm              10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized4:·gc.count                        10000.10000.10000  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                                     avgt    5     2.085 ±    0.010   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                                      avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                                 avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                           avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                                200  avgt    5    15.723 ±    0.104   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                                 200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                            200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                      200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                               200.  avgt    5    14.918 ±    0.475   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                                200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                           200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                     200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                            200.200  avgt    5    32.588 ±    0.069   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                             200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                        200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                  200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                           200.200.  avgt    5    27.855 ±    0.074   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                            200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                       200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                 200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                          a.200.200  avgt    5     3.286 ±    0.022   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                           a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                      a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                          200.a.200  avgt    5    18.425 ±    0.024   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                           200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                      200.a.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                          200.200.a  avgt    5    33.086 ±    0.036   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                           200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                      200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                    200.200.200.200  avgt    5    45.831 ±    2.280   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                     200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                          200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                      200.200.99999  avgt    5    49.636 ±    0.289   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                       200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                  200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                            200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                       200.200.-200  avgt    5    32.154 ±    0.033   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                        200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                   200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                             200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                              1.0.0  avgt    5    26.865 ±    0.033   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                               1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                          1.0.0  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                                    1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                        200.200.200  avgt    5    53.686 ±    0.064   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                         200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                    200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                              200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                    10000.9876.5432  avgt    5    69.791 ±    0.121   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                     10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm                10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                          10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized5                                  10000.10000.10000  avgt    5    76.314 ±    5.884   ns/op
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate                   10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized5:·gc.alloc.rate.norm              10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized5:·gc.count                        10000.10000.10000  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                                     avgt    5     2.073 ±    0.012   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                                      avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                                 avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                           avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                                200  avgt    5    12.618 ±    0.024   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                                 200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                            200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                      200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                               200.  avgt    5    15.261 ±    0.026   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                                200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                           200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                     200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                            200.200  avgt    5    23.331 ±    0.120   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                             200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                        200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                  200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                           200.200.  avgt    5    27.738 ±    0.079   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                            200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                       200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                 200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                          a.200.200  avgt    5     3.016 ±    0.302   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                           a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                      a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                          200.a.200  avgt    5    16.311 ±    0.047   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                           200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                      200.a.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                          200.200.a  avgt    5    29.615 ±    0.321   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                           200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                      200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                    200.200.200.200  avgt    5    33.916 ±    1.717   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                     200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                          200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                      200.200.99999  avgt    5    36.391 ±    0.029   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                       200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                  200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                            200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                       200.200.-200  avgt    5    29.201 ±    0.031   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                        200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                   200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                             200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                              1.0.0  avgt    5    20.232 ±    0.054   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                               1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                          1.0.0  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                    1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                        200.200.200  avgt    5    32.438 ±    0.220   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                         200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                    200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                              200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                    10000.9876.5432  avgt    5    42.025 ±    1.053   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                     10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                          10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                  10000.10000.10000  avgt    5    46.327 ±    0.065   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                   10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm              10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                        10000.10000.10000  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.regex                                                          avgt    5    65.708 ±    2.624   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                           avgt    5  6267.660 ±  244.543  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                                      avgt    5   216.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                                avgt    5   163.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                                 avgt    5    84.000                 ms
VersionParseNoAllocBenchmark.regex                                                     200  avgt    5   109.033 ±   15.035   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                      200  avgt    5  3780.322 ±  503.183  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                                 200  avgt    5   216.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                           200  avgt    5   130.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                            200  avgt    5    60.000                 ms
VersionParseNoAllocBenchmark.regex                                                    200.  avgt    5   164.153 ±    1.276   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                     200.  avgt    5  2508.765 ±   19.873  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                                200.  avgt    5   216.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                          200.  avgt    5    86.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                           200.  avgt    5    42.000                 ms
VersionParseNoAllocBenchmark.regex                                                 200.200  avgt    5   217.545 ±    2.609   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                  200.200  avgt    5  1892.866 ±   23.124  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                             200.200  avgt    5   216.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                       200.200  avgt    5    65.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                        200.200  avgt    5    39.000                 ms
VersionParseNoAllocBenchmark.regex                                                200.200.  avgt    5   250.088 ±    1.575   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                 200.200.  avgt    5  1646.572 ±   10.469  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                            200.200.  avgt    5   216.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                      200.200.  avgt    5    57.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                       200.200.  avgt    5    35.000                 ms
VersionParseNoAllocBenchmark.regex                                               a.200.200  avgt    5    48.092 ±    3.930   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                a.200.200  avgt    5  5710.582 ±  452.068  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                           a.200.200  avgt    5   144.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                     a.200.200  avgt    5   154.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                      a.200.200  avgt    5    78.000                 ms
VersionParseNoAllocBenchmark.regex                                               200.a.200  avgt    5   168.956 ±    0.796   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                200.a.200  avgt    5  2437.295 ±   10.643  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                           200.a.200  avgt    5   216.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                     200.a.200  avgt    5    83.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                      200.a.200  avgt    5    40.000                 ms
VersionParseNoAllocBenchmark.regex                                               200.200.a  avgt    5   249.539 ±   11.624   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                200.200.a  avgt    5  1650.449 ±   75.234  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                           200.200.a  avgt    5   216.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                     200.200.a  avgt    5    56.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                      200.200.a  avgt    5    38.000                 ms
VersionParseNoAllocBenchmark.regex                                         200.200.200.200  avgt    5   296.019 ±   16.801   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                          200.200.200.200  avgt    5  1391.417 ±   78.453  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                     200.200.200.200  avgt    5   216.000 ±    0.003    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                               200.200.200.200  avgt    5    48.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                200.200.200.200  avgt    5    29.000                 ms
VersionParseNoAllocBenchmark.regex                                           200.200.99999  avgt    5   366.381 ±    3.376   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                            200.200.99999  avgt    5  1873.249 ±   16.910  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                       200.200.99999  avgt    5   360.001 ±    0.003    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                 200.200.99999  avgt    5    65.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                  200.200.99999  avgt    5    29.000                 ms
VersionParseNoAllocBenchmark.regex                                            200.200.-200  avgt    5   250.741 ±    3.016   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                             200.200.-200  avgt    5  1642.348 ±   19.539  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                        200.200.-200  avgt    5   216.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                  200.200.-200  avgt    5    56.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                   200.200.-200  avgt    5    33.000                 ms
VersionParseNoAllocBenchmark.regex                                                   1.0.0  avgt    5   209.102 ±    1.170   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                                    1.0.0  avgt    5  3282.252 ±   16.695  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                               1.0.0  avgt    5   360.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                         1.0.0  avgt    5   113.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                          1.0.0  avgt    5    54.000                 ms
VersionParseNoAllocBenchmark.regex                                             200.200.200  avgt    5   323.566 ±   23.181   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                              200.200.200  avgt    5  2121.762 ±  149.658  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                         200.200.200  avgt    5   360.001 ±    0.003    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                                   200.200.200  avgt    5    73.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                    200.200.200  avgt    5    48.000                 ms
VersionParseNoAllocBenchmark.regex                                         10000.9876.5432  avgt    5   384.797 ±   16.769   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                          10000.9876.5432  avgt    5  1783.917 ±   76.215  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                     10000.9876.5432  avgt    5   360.001 ±    0.004    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                               10000.9876.5432  avgt    5    62.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                                10000.9876.5432  avgt    5    27.000                 ms
VersionParseNoAllocBenchmark.regex                                       10000.10000.10000  avgt    5   365.008 ±    2.079   ns/op
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate                        10000.10000.10000  avgt    5  1880.291 ±   12.446  MB/sec
VersionParseNoAllocBenchmark.regex:·gc.alloc.rate.norm                   10000.10000.10000  avgt    5   360.001 ±    0.003    B/op
VersionParseNoAllocBenchmark.regex:·gc.count                             10000.10000.10000  avgt    5    64.000             counts
VersionParseNoAllocBenchmark.regex:·gc.time                              10000.10000.10000  avgt    5    30.000                 ms
VersionParseNoAllocBenchmark.yolo                                                           avgt    5  2784.036 ±  370.316   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                            avgt    5   663.503 ±   87.529  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                                       avgt    5   968.004 ±    0.027    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                                 avgt    5    23.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                                  avgt    5    13.000                 ms
VersionParseNoAllocBenchmark.yolo                                                      200  avgt    5  1908.294 ±  279.221   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                       200  avgt    5   824.150 ±  123.328  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                                  200  avgt    5   824.003 ±    0.018    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                            200  avgt    5    29.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                             200  avgt    5    19.000                 ms
VersionParseNoAllocBenchmark.yolo                                                     200.  avgt    5  2019.703 ±  219.352   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                      200.  avgt    5   899.179 ±   95.271  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                                 200.  avgt    5   952.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                           200.  avgt    5    31.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                            200.  avgt    5    27.000                 ms
VersionParseNoAllocBenchmark.yolo                                                  200.200  avgt    5  2049.385 ±  212.714   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                   200.200  avgt    5   930.815 ±  100.314  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                              200.200  avgt    5  1000.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                        200.200  avgt    5    32.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                         200.200  avgt    5    22.000                 ms
VersionParseNoAllocBenchmark.yolo                                                 200.200.  avgt    5  2047.710 ±  203.242   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                  200.200.  avgt    5   931.513 ±   95.958  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                             200.200.  avgt    5  1000.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                       200.200.  avgt    5    32.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                        200.200.  avgt    5    19.000                 ms
VersionParseNoAllocBenchmark.yolo                                                a.200.200  avgt    5  3017.065 ±  203.912   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                 a.200.200  avgt    5   763.540 ±   52.917  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                            a.200.200  avgt    5  1208.005 ±    0.028    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                      a.200.200  avgt    5    26.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                       a.200.200  avgt    5    21.000                 ms
VersionParseNoAllocBenchmark.yolo                                                200.a.200  avgt    5  2922.599 ±  376.296   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                 200.a.200  avgt    5   788.727 ±  102.270  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                            200.a.200  avgt    5  1208.005 ±    0.028    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                      200.a.200  avgt    5    27.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                       200.a.200  avgt    5    14.000                 ms
VersionParseNoAllocBenchmark.yolo                                                200.200.a  avgt    5  3153.537 ±  111.127   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                 200.200.a  avgt    5   730.286 ±   26.164  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                            200.200.a  avgt    5  1208.005 ±    0.029    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                      200.200.a  avgt    5    25.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                       200.200.a  avgt    5    21.000                 ms
VersionParseNoAllocBenchmark.yolo                                          200.200.200.200  avgt    5  2146.266 ±  256.873   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                           200.200.200.200  avgt    5   988.545 ±  121.156  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                      200.200.200.200  avgt    5  1112.003 ±    0.020    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                200.200.200.200  avgt    5    34.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                 200.200.200.200  avgt    5    17.000                 ms
VersionParseNoAllocBenchmark.yolo                                            200.200.99999  avgt    5  2126.918 ±  255.297   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                             200.200.99999  avgt    5   975.992 ±  118.661  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                        200.200.99999  avgt    5  1088.003 ±    0.020    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                  200.200.99999  avgt    5    34.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                   200.200.99999  avgt    5    25.000                 ms
VersionParseNoAllocBenchmark.yolo                                             200.200.-200  avgt    5  2172.695 ±  206.898   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                              200.200.-200  avgt    5   969.070 ±   96.026  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                         200.200.-200  avgt    5  1104.003 ±    0.020    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                   200.200.-200  avgt    5    33.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                    200.200.-200  avgt    5    17.000                 ms
VersionParseNoAllocBenchmark.yolo                                                    1.0.0  avgt    5   164.106 ±   17.569   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                                     1.0.0  avgt    5  4184.806 ±  439.972  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                                1.0.0  avgt    5   360.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                          1.0.0  avgt    5   126.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                           1.0.0  avgt    5    61.000                 ms
VersionParseNoAllocBenchmark.yolo                                              200.200.200  avgt    5   191.586 ±   29.931   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                               200.200.200  avgt    5  3905.638 ±  585.688  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                          200.200.200  avgt    5   392.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                    200.200.200  avgt    5   117.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                     200.200.200  avgt    5    69.000                 ms
VersionParseNoAllocBenchmark.yolo                                          10000.9876.5432  avgt    5   202.874 ±    1.394   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                           10000.9876.5432  avgt    5  3683.408 ±   25.289  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                      10000.9876.5432  avgt    5   392.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                                10000.9876.5432  avgt    5   110.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                                 10000.9876.5432  avgt    5    58.000                 ms
VersionParseNoAllocBenchmark.yolo                                        10000.10000.10000  avgt    5   211.766 ±   19.489   ns/op
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate                         10000.10000.10000  avgt    5  3530.811 ±  313.982  MB/sec
VersionParseNoAllocBenchmark.yolo:·gc.alloc.rate.norm                    10000.10000.10000  avgt    5   392.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yolo:·gc.count                              10000.10000.10000  avgt    5   106.000             counts
VersionParseNoAllocBenchmark.yolo:·gc.time                               10000.10000.10000  avgt    5    73.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                                    avgt    5  2661.671 ±   67.889   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                                     avgt    5   693.405 ±   18.140  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                                avgt    5   968.004 ±    0.025    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                                          avgt    5    24.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                           avgt    5    12.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                               200  avgt    5  1873.548 ±  213.191   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                                200  avgt    5   839.069 ±   99.530  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                           200  avgt    5   824.003 ±    0.018    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                                     200  avgt    5    29.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                      200  avgt    5    15.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                              200.  avgt    5  1990.381 ±  210.208   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                               200.  avgt    5   912.422 ±  100.039  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                          200.  avgt    5   952.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                                    200.  avgt    5    31.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                     200.  avgt    5    16.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                           200.200  avgt    5  2035.276 ±  213.637   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                            200.200  avgt    5   937.255 ±  101.983  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                       200.200  avgt    5  1000.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                                 200.200  avgt    5    33.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                  200.200  avgt    5    21.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                          200.200.  avgt    5  2047.839 ±  199.892   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                           200.200.  avgt    5   931.432 ±   93.992  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                      200.200.  avgt    5  1000.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                                200.200.  avgt    5    32.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                 200.200.  avgt    5    19.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                         a.200.200  avgt    5  2932.028 ±  320.819   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                          a.200.200  avgt    5   785.965 ±   86.764  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                     a.200.200  avgt    5  1208.005 ±    0.027    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                               a.200.200  avgt    5    27.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                a.200.200  avgt    5    14.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                         200.a.200  avgt    5  2690.185 ±  278.769   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                          200.a.200  avgt    5   856.664 ±   90.030  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                     200.a.200  avgt    5  1208.004 ±    0.025    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                               200.a.200  avgt    5    29.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                200.a.200  avgt    5    18.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                         200.200.a  avgt    5  2894.858 ±  169.068   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                          200.200.a  avgt    5   795.733 ±   47.600  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                     200.200.a  avgt    5  1208.005 ±    0.027    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                               200.200.a  avgt    5    27.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                200.200.a  avgt    5    20.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                   200.200.200.200  avgt    5  2143.539 ±  213.183   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                    200.200.200.200  avgt    5   989.536 ±  102.028  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm               200.200.200.200  avgt    5  1112.003 ±    0.020    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                         200.200.200.200  avgt    5    34.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                          200.200.200.200  avgt    5    18.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                     200.200.99999  avgt    5   192.746 ±   17.568   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                      200.200.99999  avgt    5  3641.506 ±  321.212  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                 200.200.99999  avgt    5   368.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                           200.200.99999  avgt    5   110.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                            200.200.99999  avgt    5    56.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                      200.200.-200  avgt    5   194.569 ±    4.725   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                       200.200.-200  avgt    5  3762.870 ±   89.798  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                  200.200.-200  avgt    5   384.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                            200.200.-200  avgt    5   113.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                             200.200.-200  avgt    5    61.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                             1.0.0  avgt    5   164.030 ±   15.552   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                              1.0.0  avgt    5  4186.326 ±  399.908  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                         1.0.0  avgt    5   360.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                                   1.0.0  avgt    5   126.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                                    1.0.0  avgt    5    67.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                       200.200.200  avgt    5   187.397 ±    8.561   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                        200.200.200  avgt    5  3987.980 ±  177.857  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm                   200.200.200  avgt    5   392.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                             200.200.200  avgt    5   119.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                              200.200.200  avgt    5    63.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                   10000.9876.5432  avgt    5   208.430 ±   16.139   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                    10000.9876.5432  avgt    5  3586.705 ±  269.701  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm               10000.9876.5432  avgt    5   392.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                         10000.9876.5432  avgt    5   108.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                          10000.9876.5432  avgt    5    53.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrow                                 10000.10000.10000  avgt    5   207.407 ±    5.958   ns/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate                  10000.10000.10000  avgt    5  3603.447 ±  105.215  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.alloc.rate.norm             10000.10000.10000  avgt    5   392.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.count                       10000.10000.10000  avgt    5   108.000             counts
VersionParseNoAllocBenchmark.yoloNoThrow:·gc.time                        10000.10000.10000  avgt    5    53.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                               avgt    5  2299.758 ±  223.264   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                                avgt    5   802.887 ±   80.721  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                           avgt    5   968.004 ±    0.022    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                                     avgt    5    27.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                                      avgt    5    14.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                          200  avgt    5  1791.142 ±  209.337   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                           200  avgt    5   877.783 ±  107.284  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                      200  avgt    5   824.003 ±    0.017    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                                200  avgt    5    30.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                                 200  avgt    5    14.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                         200.  avgt    5  1900.542 ±  207.554   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                          200.  avgt    5   955.597 ±  108.644  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                     200.  avgt    5   952.003 ±    0.018    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                               200.  avgt    5    33.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                                200.  avgt    5    17.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                      200.200  avgt    5  1929.292 ±  210.368   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                       200.200  avgt    5   988.849 ±  112.330  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                  200.200  avgt    5  1000.003 ±    0.018    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                            200.200  avgt    5    34.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                             200.200  avgt    5    16.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                     200.200.  avgt    5  1969.628 ±  205.482   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                      200.200.  avgt    5   968.496 ±  105.221  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                 200.200.  avgt    5  1000.003 ±    0.018    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                           200.200.  avgt    5    33.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                            200.200.  avgt    5    25.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                    a.200.200  avgt    5  2781.986 ±  287.040   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                     a.200.200  avgt    5   828.341 ±   86.874  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                a.200.200  avgt    5  1208.004 ±    0.027    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                          a.200.200  avgt    5    29.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                           a.200.200  avgt    5    15.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                    200.a.200  avgt    5  2746.657 ±  179.963   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                     200.a.200  avgt    5   838.643 ±   56.125  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                200.a.200  avgt    5  1208.004 ±    0.026    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                          200.a.200  avgt    5    28.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                           200.a.200  avgt    5    19.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                    200.200.a  avgt    5  2558.973 ±  238.225   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                     200.200.a  avgt    5   900.435 ±   86.805  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                200.200.a  avgt    5  1208.004 ±    0.024    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                          200.200.a  avgt    5    31.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                           200.200.a  avgt    5    14.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                              200.200.200.200  avgt    5  2091.701 ±  294.955   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate               200.200.200.200  avgt    5  1014.569 ±  146.718  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm          200.200.200.200  avgt    5  1112.003 ±    0.020    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                    200.200.200.200  avgt    5    35.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                     200.200.200.200  avgt    5    21.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                200.200.99999  avgt    5   193.273 ±    1.807   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                 200.200.99999  avgt    5  3629.773 ±   37.312  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm            200.200.99999  avgt    5   368.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                      200.200.99999  avgt    5   125.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                       200.200.99999  avgt    5    58.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                 200.200.-200  avgt    5   198.641 ±   15.504   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                  200.200.-200  avgt    5  3687.103 ±  287.252  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm             200.200.-200  avgt    5   384.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                       200.200.-200  avgt    5   111.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                        200.200.-200  avgt    5    54.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                        1.0.0  avgt    5   157.756 ±    8.463   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                         1.0.0  avgt    5  4061.339 ±  217.952  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                    1.0.0  avgt    5   336.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                              1.0.0  avgt    5   121.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                               1.0.0  avgt    5    68.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                                  200.200.200  avgt    5   183.485 ±    0.491   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                   200.200.200  avgt    5  3823.779 ±   10.833  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm              200.200.200  avgt    5   368.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                        200.200.200  avgt    5   115.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                         200.200.200  avgt    5    52.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                              10000.9876.5432  avgt    5   209.547 ±    0.481   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate               10000.9876.5432  avgt    5  3348.167 ±    9.245  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm          10000.9876.5432  avgt    5   368.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                    10000.9876.5432  avgt    5   115.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                     10000.9876.5432  avgt    5    57.000                 ms
VersionParseNoAllocBenchmark.yoloNoThrowNoTry                            10000.10000.10000  avgt    5   209.737 ±    1.586   ns/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate             10000.10000.10000  avgt    5  3344.856 ±   25.238  MB/sec
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm        10000.10000.10000  avgt    5   368.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.count                  10000.10000.10000  avgt    5   115.000             counts
VersionParseNoAllocBenchmark.yoloNoThrowNoTry:·gc.time                   10000.10000.10000  avgt    5    59.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                                      avgt    5  2367.504 ±  189.284   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                                       avgt    5   779.742 ±   64.088  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                                  avgt    5   968.004 ±    0.022    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                            avgt    5    27.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                             avgt    5    16.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                                 200  avgt    5  1795.998 ±  248.105   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                                  200  avgt    5   875.643 ±  125.070  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                             200  avgt    5   824.003 ±    0.017    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                       200  avgt    5    31.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                        200  avgt    5    18.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                                200.  avgt    5  1913.697 ±  281.187   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                                 200.  avgt    5   949.502 ±  142.727  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                            200.  avgt    5   952.003 ±    0.018    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                      200.  avgt    5    33.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                       200.  avgt    5    17.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                             200.200  avgt    5  1933.508 ±  216.093   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                              200.200  avgt    5   986.592 ±  115.145  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                         200.200  avgt    5  1000.003 ±    0.018    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                   200.200  avgt    5    34.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                    200.200  avgt    5    18.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                            200.200.  avgt    5  1965.104 ±  213.788   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                             200.200.  avgt    5   970.837 ±  110.006  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                        200.200.  avgt    5  1000.003 ±    0.018    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                  200.200.  avgt    5    33.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                   200.200.  avgt    5    22.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                           a.200.200  avgt    5  2546.583 ±  257.598   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                            a.200.200  avgt    5   904.859 ±   92.508  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                       a.200.200  avgt    5  1208.004 ±    0.024    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                 a.200.200  avgt    5    31.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                  a.200.200  avgt    5    17.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                           200.a.200  avgt    5  2822.723 ±  201.522   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                            200.a.200  avgt    5   816.061 ±   59.854  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                       200.a.200  avgt    5  1208.004 ±    0.026    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                 200.a.200  avgt    5    28.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                  200.a.200  avgt    5    19.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                           200.200.a  avgt    5  2591.047 ±  208.895   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                            200.200.a  avgt    5   889.210 ±   73.806  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                       200.200.a  avgt    5  1208.004 ±    0.024    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                 200.200.a  avgt    5    31.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                  200.200.a  avgt    5    15.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                     200.200.200.200  avgt    5  2039.793 ±  203.268   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                      200.200.200.200  avgt    5  1039.952 ±  107.670  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                 200.200.200.200  avgt    5  1112.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                           200.200.200.200  avgt    5    36.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                            200.200.200.200  avgt    5    20.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                       200.200.99999  avgt    5  2069.164 ±  203.397   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                        200.200.99999  avgt    5  1002.996 ±  102.372  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                   200.200.99999  avgt    5  1088.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                             200.200.99999  avgt    5    34.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                              200.200.99999  avgt    5    26.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                        200.200.-200  avgt    5  2078.150 ±  201.873   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                         200.200.-200  avgt    5  1013.343 ±  101.977  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                    200.200.-200  avgt    5  1104.003 ±    0.019    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                              200.200.-200  avgt    5    35.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                               200.200.-200  avgt    5    19.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                               1.0.0  avgt    5   174.203 ±   18.900   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                                1.0.0  avgt    5  3679.604 ±  390.548  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                           1.0.0  avgt    5   336.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                                     1.0.0  avgt    5    99.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                      1.0.0  avgt    5    52.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                         200.200.200  avgt    5   188.471 ±   16.577   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                          200.200.200  avgt    5  3723.787 ±  318.050  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                     200.200.200  avgt    5   368.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                               200.200.200  avgt    5   111.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                                200.200.200  avgt    5    56.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                     10000.9876.5432  avgt    5   214.391 ±   55.180   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                      10000.9876.5432  avgt    5  3283.583 ±  803.994  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm                 10000.9876.5432  avgt    5   368.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                           10000.9876.5432  avgt    5    99.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                            10000.9876.5432  avgt    5    57.000                 ms
VersionParseNoAllocBenchmark.yoloNoTry                                   10000.10000.10000  avgt    5   214.282 ±    1.941   ns/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate                    10000.10000.10000  avgt    5  3274.250 ±   30.432  MB/sec
VersionParseNoAllocBenchmark.yoloNoTry:·gc.alloc.rate.norm               10000.10000.10000  avgt    5   368.000 ±    0.002    B/op
VersionParseNoAllocBenchmark.yoloNoTry:·gc.count                         10000.10000.10000  avgt    5    98.000             counts
VersionParseNoAllocBenchmark.yoloNoTry:·gc.time                          10000.10000.10000  avgt    5    50.000                 ms
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx1G"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
class VersionParseNoAllocBenchmark {

  @Param(value = Array(
    "",
    "200",
    "200.",
    "200.200",
    "200.200.",
    "a.200.200",
    "200.a.200",
    "200.200.a",
    "200.200.200.200",
    "200.200.99999",
    "200.200.-200",

    // correct ones
    "1.0.0",
    "200.200.200",
    "10000.9876.5432",
    "10000.10000.10000",
  ))
  var encoded: String = _

  @Benchmark
  def yolo: Long = VersionNoAlloc.parseYolo(encoded)

  @Benchmark
  def yoloNoTry: Long = VersionNoAlloc.parseYoloNoTry(encoded)

  @Benchmark
  def yoloNoThrow: Long = VersionNoAlloc.parseYoloNoThrow(encoded)

  @Benchmark
  def yoloNoThrowNoTry: Long = VersionNoAlloc.parseYoloNoThrowNoTry(encoded)

  @Benchmark
  def regex: Long = VersionNoAlloc.parseWithRegex(encoded)

  @Benchmark
  def optimized1: Long = VersionNoAlloc.parseOptimized1(encoded)

  @Benchmark
  def optimized2: Long = VersionNoAlloc.parseOptimized2(encoded)

  @Benchmark
  def optimized3: Long = VersionNoAlloc.parseOptimized3(encoded)

  @Benchmark
  def optimized3Java: Long = VersionNoAlloc.parseOptimized3Java(encoded)

  @Benchmark
  def optimized3JavaNoSwitch: Long = VersionNoAlloc.parseOptimized3JavaNoSwitch(encoded)

  @Benchmark
  def optimized4: Long = VersionNoAlloc.parseOptimized4(encoded)

  @Benchmark
  def optimized5: Long = VersionNoAlloc.parseOptimized5(encoded)

  @Benchmark
  def optimized6: Long = VersionNoAlloc.parseOptimized6(encoded)

  @Benchmark
  def optimized6Scala: Long = VersionNoAlloc.parseOptimized6Scala(encoded)
}

/*
Benchmark                                                                 (encoded)  Mode  Cnt     Score      Error   Units
VersionParseBenchmark.optimized6                                                     avgt    5     2.094 ±    0.080   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                      avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                                 avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                           avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                200  avgt    5    14.342 ±    3.177   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                 200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                            200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                      200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                               200.  avgt    5    16.047 ±    0.725   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                           200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                     200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                            200.200  avgt    5    24.023 ±    1.062   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                             200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                        200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                  200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                           200.200.  avgt    5    28.466 ±    0.904   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                            200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                       200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                 200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                          a.200.200  avgt    5     3.067 ±    0.152   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                           a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                      a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                          200.a.200  avgt    5    16.808 ±    0.724   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                           200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                      200.a.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                          200.200.a  avgt    5    30.338 ±    1.188   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                           200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                      200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                    200.200.200.200  avgt    5    35.715 ±    1.431   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                     200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                          200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                      200.200.99999  avgt    5    37.943 ±    1.183   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                       200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                  200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                            200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                       200.200.-200  avgt    5    29.643 ±    1.555   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                        200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                   200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                             200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                              1.0.0  avgt    5    22.995 ±    1.303   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                               1.0.0  avgt    5  3317.120 ±  188.961  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                          1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6:·gc.count                                    1.0.0  avgt    5    91.000             counts
VersionParseBenchmark.optimized6:·gc.time                                     1.0.0  avgt    5    51.000                 ms
VersionParseBenchmark.optimized6                                        200.200.200  avgt    5    38.147 ±    1.223   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                         200.200.200  avgt    5  1999.293 ±   62.924  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                    200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6:·gc.count                              200.200.200  avgt    5    69.000             counts
VersionParseBenchmark.optimized6:·gc.time                               200.200.200  avgt    5    34.000                 ms
VersionParseBenchmark.optimized6                                    10000.9876.5432  avgt    5    53.286 ±    0.848   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                     10000.9876.5432  avgt    5  1431.195 ±   22.027  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6:·gc.count                          10000.9876.5432  avgt    5    49.000             counts
VersionParseBenchmark.optimized6:·gc.time                           10000.9876.5432  avgt    5    28.000                 ms
VersionParseBenchmark.optimized6                                  10000.10000.10000  avgt    5    60.070 ±    3.753   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                   10000.10000.10000  avgt    5  1269.809 ±   79.389  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm              10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6:·gc.count                        10000.10000.10000  avgt    5    44.000             counts
VersionParseBenchmark.optimized6:·gc.time                         10000.10000.10000  avgt    5    23.000                 ms
VersionParseBenchmark.optimized6Scala                                                avgt    5     2.186 ±    0.291   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                                 avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                            avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6Scala:·gc.count                                      avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6Scala                                           200  avgt    5    11.686 ±    9.336   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                            200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                       200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6Scala:·gc.count                                 200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6Scala                                          200.  avgt    5    18.464 ±    3.432   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                           200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                      200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6Scala:·gc.count                                200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6Scala                                       200.200  avgt    5    27.653 ±    3.373   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                        200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                   200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6Scala:·gc.count                             200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6Scala                                      200.200.  avgt    5    30.054 ±    3.691   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                       200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                  200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6Scala:·gc.count                            200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6Scala                                     a.200.200  avgt    5    29.221 ±   12.069   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                      a.200.200  avgt    5  4212.774 ± 1578.632  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                 a.200.200  avgt    5    64.000 ±    0.001    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                           a.200.200  avgt    5   111.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                            a.200.200  avgt    5    79.000                 ms
VersionParseBenchmark.optimized6Scala                                     200.a.200  avgt    5    54.828 ±    2.636   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                      200.a.200  avgt    5  3896.671 ±  177.185  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                 200.a.200  avgt    5   112.000 ±    0.001    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                           200.a.200  avgt    5   117.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                            200.a.200  avgt    5    62.000                 ms
VersionParseBenchmark.optimized6Scala                                     200.200.a  avgt    5    75.869 ±   20.514   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                      200.200.a  avgt    5  2827.066 ±  758.184  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                 200.200.a  avgt    5   112.000 ±    0.001    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                           200.200.a  avgt    5    98.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                            200.200.a  avgt    5    55.000                 ms
VersionParseBenchmark.optimized6Scala                               200.200.200.200  avgt    5    80.682 ±    2.769   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                200.200.200.200  avgt    5  2647.065 ±   90.100  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm           200.200.200.200  avgt    5   112.000 ±    0.001    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                     200.200.200.200  avgt    5    91.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                      200.200.200.200  avgt    5    48.000                 ms
VersionParseBenchmark.optimized6Scala                                 200.200.99999  avgt    5    39.785 ±    4.243   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                  200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm             200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6Scala:·gc.count                       200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6Scala                                  200.200.-200  avgt    5    69.419 ±   13.048   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                   200.200.-200  avgt    5  3083.203 ±  574.359  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm              200.200.-200  avgt    5   112.000 ±    0.002    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                        200.200.-200  avgt    5   106.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                         200.200.-200  avgt    5    55.000                 ms
VersionParseBenchmark.optimized6Scala                                         1.0.0  avgt    5    24.780 ±    3.820   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                          1.0.0  avgt    5  3081.765 ±  489.186  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm                     1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                               1.0.0  avgt    5    92.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                                1.0.0  avgt    5    52.000                 ms
VersionParseBenchmark.optimized6Scala                                   200.200.200  avgt    5    39.085 ±    5.505   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                    200.200.200  avgt    5  1953.159 ±  263.227  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm               200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                         200.200.200  avgt    5    67.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                          200.200.200  avgt    5    35.000                 ms
VersionParseBenchmark.optimized6Scala                               10000.9876.5432  avgt    5    50.285 ±   10.446   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate                10000.9876.5432  avgt    5  1520.076 ±  302.523  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm           10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                     10000.9876.5432  avgt    5    52.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                      10000.9876.5432  avgt    5    26.000                 ms
VersionParseBenchmark.optimized6Scala                             10000.10000.10000  avgt    5    60.828 ±   27.522   ns/op
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate              10000.10000.10000  avgt    5  1266.738 ±  527.402  MB/sec
VersionParseBenchmark.optimized6Scala:·gc.alloc.rate.norm         10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6Scala:·gc.count                   10000.10000.10000  avgt    5    43.000             counts
VersionParseBenchmark.optimized6Scala:·gc.time                    10000.10000.10000  avgt    5    25.000                 ms
VersionParseNoAllocBenchmark.optimized6                                              avgt    5     2.170 ±    0.210   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                               avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                          avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                                    avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                         200  avgt    5    12.837 ±    0.886   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                          200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                     200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                               200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                        200.  avgt    5    16.306 ±    2.154   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                         200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                    200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                              200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                     200.200  avgt    5    24.488 ±    2.187   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                      200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                 200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                           200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                    200.200.  avgt    5    28.900 ±    3.222   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                     200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                          200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                   a.200.200  avgt    5     3.260 ±    0.714   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                    a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm               a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                         a.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                   200.a.200  avgt    5    17.408 ±    1.988   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                    200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm               200.a.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                         200.a.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                   200.200.a  avgt    5    34.270 ±   15.272   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                    200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm               200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                         200.200.a  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                             200.200.200.200  avgt    5    35.278 ±    2.077   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate              200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm         200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                   200.200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                               200.200.99999  avgt    5    37.836 ±    4.646   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm           200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                     200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                200.200.-200  avgt    5    30.266 ±    2.153   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                 200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm            200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                      200.200.-200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                       1.0.0  avgt    5    21.713 ±    2.534   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                        1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm                   1.0.0  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                             1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                                 200.200.200  avgt    5    34.549 ±    3.886   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate                  200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm             200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                       200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                             10000.9876.5432  avgt    5    43.605 ±    2.496   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate              10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm         10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                   10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6                           10000.10000.10000  avgt    5    48.535 ±    1.351   ns/op
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate            10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6:·gc.alloc.rate.norm       10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6:·gc.count                 10000.10000.10000  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                                         avgt    5     2.787 ±    2.294   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate                          avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm                     avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                               avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                                    200  avgt    5     9.520 ±    0.911   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate                     200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm                200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                          200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                                   200.  avgt    5    17.818 ±    1.221   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate                    200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm               200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                         200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                                200.200  avgt    5    25.614 ±    0.370   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate                 200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm            200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                      200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                               200.200.  avgt    5    28.800 ±    0.718   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate                200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm           200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                     200.200.  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                              a.200.200  avgt    5    25.086 ±    3.730   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate               a.200.200  avgt    5  5478.470 ±  818.357  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm          a.200.200  avgt    5    72.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                    a.200.200  avgt    5   188.000             counts
VersionParseNoAllocBenchmark.optimized6Scala:·gc.time                     a.200.200  avgt    5    94.000                 ms
VersionParseNoAllocBenchmark.optimized6Scala                              200.a.200  avgt    5    50.205 ±    5.659   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate               200.a.200  avgt    5  4560.092 ±  497.933  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm          200.a.200  avgt    5   120.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                    200.a.200  avgt    5   157.000             counts
VersionParseNoAllocBenchmark.optimized6Scala:·gc.time                     200.a.200  avgt    5    74.000                 ms
VersionParseNoAllocBenchmark.optimized6Scala                              200.200.a  avgt    5    67.515 ±   10.637   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate               200.200.a  avgt    5  3393.441 ±  518.004  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm          200.200.a  avgt    5   120.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                    200.200.a  avgt    5   116.000             counts
VersionParseNoAllocBenchmark.optimized6Scala:·gc.time                     200.200.a  avgt    5    55.000                 ms
VersionParseNoAllocBenchmark.optimized6Scala                        200.200.200.200  avgt    5    85.486 ±   21.295   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate         200.200.200.200  avgt    5  2685.662 ±  661.524  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm    200.200.200.200  avgt    5   120.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count              200.200.200.200  avgt    5    92.000             counts
VersionParseNoAllocBenchmark.optimized6Scala:·gc.time               200.200.200.200  avgt    5    51.000                 ms
VersionParseNoAllocBenchmark.optimized6Scala                          200.200.99999  avgt    5    37.121 ±    0.427   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate           200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm      200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                200.200.99999  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                           200.200.-200  avgt    5    67.954 ±    3.230   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate            200.200.-200  avgt    5  3367.372 ±  156.865  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm       200.200.-200  avgt    5   120.000 ±    0.001    B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                 200.200.-200  avgt    5   115.000             counts
VersionParseNoAllocBenchmark.optimized6Scala:·gc.time                  200.200.-200  avgt    5    58.000                 ms
VersionParseNoAllocBenchmark.optimized6Scala                                  1.0.0  avgt    5    20.101 ±    1.469   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate                   1.0.0  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm              1.0.0  avgt    5    ≈ 10⁻⁵               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                        1.0.0  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                            200.200.200  avgt    5    35.409 ±    1.381   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate             200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm        200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count                  200.200.200  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                        10000.9876.5432  avgt    5    44.355 ±    1.419   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate         10000.9876.5432  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm    10000.9876.5432  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count              10000.9876.5432  avgt    5       ≈ 0             counts
VersionParseNoAllocBenchmark.optimized6Scala                      10000.10000.10000  avgt    5    48.259 ±    1.484   ns/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate       10000.10000.10000  avgt    5     0.003 ±    0.018  MB/sec
VersionParseNoAllocBenchmark.optimized6Scala:·gc.alloc.rate.norm  10000.10000.10000  avgt    5    ≈ 10⁻⁴               B/op
VersionParseNoAllocBenchmark.optimized6Scala:·gc.count            10000.10000.10000  avgt    5       ≈ 0             counts
 */
