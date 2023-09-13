package com.komanov.ver.jmh

import com.komanov.ver.Version
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Benchmark                                                                        (encoded)  Mode  Cnt     Score      Error   Units
VersionParseBenchmark.optimized1                                                            avgt    5     2.071 ±    0.010   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                             avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                                        avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized1:·gc.count                                                  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                                       200  avgt    5     5.475 ±    0.136   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                        200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                                   200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized1:·gc.count                                             200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                                      200.  avgt    5     6.465 ±    0.061   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                       200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                                  200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized1:·gc.count                                            200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                                   200.200  avgt    5    13.368 ±    0.161   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                    200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                               200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized1:·gc.count                                         200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                                  200.200.  avgt    5    26.965 ±    0.632   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                   200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                              200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized1:·gc.count                                        200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                                 a.200.200  avgt    5    20.654 ±    1.261   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                  a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                             a.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized1:·gc.count                                       a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                                 200.a.200  avgt    5    32.104 ±    0.245   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                  200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                             200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized1:·gc.count                                       200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                                 200.200.a  avgt    5    34.619 ±    0.116   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                  200.200.a  avgt    5     0.003 ±    0.017  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                             200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized1:·gc.count                                       200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                           200.200.200.200  avgt    5    21.557 ±    2.067   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                            200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                       200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized1:·gc.count                                 200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                             200.200.99999  avgt    5   126.285 ±   11.317   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                              200.200.99999  avgt    5  2174.831 ±  189.462  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                         200.200.99999  avgt    5   144.000 ±    0.001    B/op
VersionParseBenchmark.optimized1:·gc.count                                   200.200.99999  avgt    5    74.000             counts
VersionParseBenchmark.optimized1:·gc.time                                    200.200.99999  avgt    5    33.000                 ms
VersionParseBenchmark.optimized1                                              200.200.-200  avgt    5    38.772 ±    0.429   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                               200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                          200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized1:·gc.count                                    200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized1                                                     1.0.0  avgt    5   100.512 ±    4.974   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                      1.0.0  avgt    5  3490.476 ±  170.516  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                                 1.0.0  avgt    5   184.000 ±    0.001    B/op
VersionParseBenchmark.optimized1:·gc.count                                           1.0.0  avgt    5   120.000             counts
VersionParseBenchmark.optimized1:·gc.time                                            1.0.0  avgt    5    62.000                 ms
VersionParseBenchmark.optimized1                                               200.200.200  avgt    5   126.358 ±    0.273   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                                200.200.200  avgt    5  2776.019 ±    6.507  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                           200.200.200  avgt    5   184.000 ±    0.001    B/op
VersionParseBenchmark.optimized1:·gc.count                                     200.200.200  avgt    5    95.000             counts
VersionParseBenchmark.optimized1:·gc.time                                      200.200.200  avgt    5    49.000                 ms
VersionParseBenchmark.optimized1                                           10000.9876.5432  avgt    5   141.785 ±    5.391   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                            10000.9876.5432  avgt    5  2474.327 ±   93.223  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                       10000.9876.5432  avgt    5   184.000 ±    0.001    B/op
VersionParseBenchmark.optimized1:·gc.count                                 10000.9876.5432  avgt    5    85.000             counts
VersionParseBenchmark.optimized1:·gc.time                                  10000.9876.5432  avgt    5    44.000                 ms
VersionParseBenchmark.optimized1                                         10000.10000.10000  avgt    5   156.005 ±    5.058   ns/op
VersionParseBenchmark.optimized1:·gc.alloc.rate                          10000.10000.10000  avgt    5  2248.645 ±   70.672  MB/sec
VersionParseBenchmark.optimized1:·gc.alloc.rate.norm                     10000.10000.10000  avgt    5   184.000 ±    0.001    B/op
VersionParseBenchmark.optimized1:·gc.count                               10000.10000.10000  avgt    5    77.000             counts
VersionParseBenchmark.optimized1:·gc.time                                10000.10000.10000  avgt    5    40.000                 ms
VersionParseBenchmark.optimized2                                                            avgt    5     2.070 ±    0.002   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                             avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                                        avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized2:·gc.count                                                  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                                       200  avgt    5     5.348 ±    0.178   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                        200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                                   200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized2:·gc.count                                             200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                                      200.  avgt    5     6.511 ±    0.067   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                       200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                                  200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized2:·gc.count                                            200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                                   200.200  avgt    5    13.464 ±    0.169   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                    200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                               200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized2:·gc.count                                         200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                                  200.200.  avgt    5    28.034 ±    2.882   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                   200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                              200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized2:·gc.count                                        200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                                 a.200.200  avgt    5    19.200 ±    0.783   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                  a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                             a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized2:·gc.count                                       a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                                 200.a.200  avgt    5    26.722 ±    0.148   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                  200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                             200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized2:·gc.count                                       200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                                 200.200.a  avgt    5    35.341 ±    0.199   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                  200.200.a  avgt    5     0.003 ±    0.017  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                             200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized2:·gc.count                                       200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                           200.200.200.200  avgt    5    21.354 ±    0.076   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                            200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                       200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized2:·gc.count                                 200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                             200.200.99999  avgt    5    89.174 ±    0.125   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                              200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                         200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized2:·gc.count                                   200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                              200.200.-200  avgt    5    37.935 ±    0.361   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                               200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                          200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized2:·gc.count                                    200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized2                                                     1.0.0  avgt    5    63.764 ±    4.887   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                      1.0.0  avgt    5  1196.403 ±   89.548  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                                 1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized2:·gc.count                                           1.0.0  avgt    5    41.000             counts
VersionParseBenchmark.optimized2:·gc.time                                            1.0.0  avgt    5    27.000                 ms
VersionParseBenchmark.optimized2                                               200.200.200  avgt    5    89.338 ±    0.200   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                                200.200.200  avgt    5   853.567 ±    2.153  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                           200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized2:·gc.count                                     200.200.200  avgt    5    30.000             counts
VersionParseBenchmark.optimized2:·gc.time                                      200.200.200  avgt    5    16.000                 ms
VersionParseBenchmark.optimized2                                           10000.9876.5432  avgt    5   104.107 ±    0.199   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                            10000.9876.5432  avgt    5   732.570 ±    1.461  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                       10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized2:·gc.count                                 10000.9876.5432  avgt    5    25.000             counts
VersionParseBenchmark.optimized2:·gc.time                                  10000.9876.5432  avgt    5    11.000                 ms
VersionParseBenchmark.optimized2                                         10000.10000.10000  avgt    5   115.824 ±    5.485   ns/op
VersionParseBenchmark.optimized2:·gc.alloc.rate                          10000.10000.10000  avgt    5   658.564 ±   29.777  MB/sec
VersionParseBenchmark.optimized2:·gc.alloc.rate.norm                     10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized2:·gc.count                               10000.10000.10000  avgt    5    23.000             counts
VersionParseBenchmark.optimized2:·gc.time                                10000.10000.10000  avgt    5    11.000                 ms
VersionParseBenchmark.optimized3                                                            avgt    5     2.071 ±    0.005   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                             avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                                        avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3:·gc.count                                                  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                                       200  avgt    5    11.343 ±    0.020   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                        200  avgt    5     0.003 ±    0.017  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                                   200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3:·gc.count                                             200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                                      200.  avgt    5    26.376 ±    2.378   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                       200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                                  200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3:·gc.count                                            200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                                   200.200  avgt    5    31.644 ±    0.089   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                    200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                               200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3:·gc.count                                         200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                                  200.200.  avgt    5    50.803 ±    1.549   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                   200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                              200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3:·gc.count                                        200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                                 a.200.200  avgt    5     2.365 ±    0.002   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                  a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                             a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3:·gc.count                                       a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                                 200.a.200  avgt    5    27.786 ±    0.059   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                  200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                             200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3:·gc.count                                       200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                                 200.200.a  avgt    5    67.093 ±    0.163   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                  200.200.a  avgt    5     0.003 ±    0.017  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                             200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3:·gc.count                                       200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                           200.200.200.200  avgt    5    56.248 ±    0.182   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                            200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                       200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3:·gc.count                                 200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                             200.200.99999  avgt    5    74.855 ±    0.197   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                              200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                         200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3:·gc.count                                   200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                              200.200.-200  avgt    5    57.894 ±    1.833   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                               200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                          200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3:·gc.count                                    200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3                                                     1.0.0  avgt    5    48.946 ±    1.607   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                      1.0.0  avgt    5  1558.046 ±   52.377  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                                 1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3:·gc.count                                           1.0.0  avgt    5    54.000             counts
VersionParseBenchmark.optimized3:·gc.time                                            1.0.0  avgt    5    37.000                 ms
VersionParseBenchmark.optimized3                                               200.200.200  avgt    5    81.379 ±    0.105   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                                200.200.200  avgt    5   937.011 ±    1.321  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                           200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3:·gc.count                                     200.200.200  avgt    5    32.000             counts
VersionParseBenchmark.optimized3:·gc.time                                      200.200.200  avgt    5    17.000                 ms
VersionParseBenchmark.optimized3                                           10000.9876.5432  avgt    5   108.024 ±    0.397   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                            10000.9876.5432  avgt    5   705.953 ±    2.711  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                       10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3:·gc.count                                 10000.9876.5432  avgt    5    25.000             counts
VersionParseBenchmark.optimized3:·gc.time                                  10000.9876.5432  avgt    5    19.000                 ms
VersionParseBenchmark.optimized3                                         10000.10000.10000  avgt    5   120.257 ±    0.252   ns/op
VersionParseBenchmark.optimized3:·gc.alloc.rate                          10000.10000.10000  avgt    5   634.136 ±    1.681  MB/sec
VersionParseBenchmark.optimized3:·gc.alloc.rate.norm                     10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3:·gc.count                               10000.10000.10000  avgt    5    22.000             counts
VersionParseBenchmark.optimized3:·gc.time                                10000.10000.10000  avgt    5    12.000                 ms
VersionParseBenchmark.optimized3Java                                                        avgt    5     2.072 ±    0.002   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                                         avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                                    avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                              avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                                   200  avgt    5    11.466 ±    0.828   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                                    200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                               200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                         200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                                  200.  avgt    5    22.010 ±    0.016   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                                   200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                              200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                        200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                               200.200  avgt    5    28.138 ±    0.215   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                                200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                           200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                     200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                              200.200.  avgt    5    50.150 ±    4.229   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                               200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                          200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                    200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                             a.200.200  avgt    5     2.367 ±    0.003   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                              a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                         a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                   a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                             200.a.200  avgt    5    23.785 ±    0.094   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                              200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                         200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                   200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                             200.200.a  avgt    5    58.824 ±    3.188   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                              200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                         200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                   200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                       200.200.200.200  avgt    5    56.381 ±    0.056   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                        200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                   200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3Java:·gc.count                             200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                         200.200.99999  avgt    5    71.824 ±    0.111   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                          200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                     200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3Java:·gc.count                               200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                          200.200.-200  avgt    5    59.065 ±    3.060   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                           200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                      200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3Java:·gc.count                                200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3Java                                                 1.0.0  avgt    5    45.064 ±    6.610   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                                  1.0.0  avgt    5  1694.083 ±  241.916  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                             1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3Java:·gc.count                                       1.0.0  avgt    5    58.000             counts
VersionParseBenchmark.optimized3Java:·gc.time                                        1.0.0  avgt    5    31.000                 ms
VersionParseBenchmark.optimized3Java                                           200.200.200  avgt    5    76.592 ±    5.190   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                            200.200.200  avgt    5   995.827 ±   66.749  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                       200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3Java:·gc.count                                 200.200.200  avgt    5    34.000             counts
VersionParseBenchmark.optimized3Java:·gc.time                                  200.200.200  avgt    5    21.000                 ms
VersionParseBenchmark.optimized3Java                                       10000.9876.5432  avgt    5    92.112 ±    0.592   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                        10000.9876.5432  avgt    5   827.892 ±    5.642  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                   10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3Java:·gc.count                             10000.9876.5432  avgt    5    29.000             counts
VersionParseBenchmark.optimized3Java:·gc.time                              10000.9876.5432  avgt    5    17.000                 ms
VersionParseBenchmark.optimized3Java                                     10000.10000.10000  avgt    5   107.327 ±    3.675   ns/op
VersionParseBenchmark.optimized3Java:·gc.alloc.rate                      10000.10000.10000  avgt    5   710.585 ±   24.346  MB/sec
VersionParseBenchmark.optimized3Java:·gc.alloc.rate.norm                 10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3Java:·gc.count                           10000.10000.10000  avgt    5    24.000             counts
VersionParseBenchmark.optimized3Java:·gc.time                            10000.10000.10000  avgt    5    12.000                 ms
VersionParseBenchmark.optimized3JavaNoSwitch                                                avgt    5     2.071 ±    0.014   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                                 avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                            avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                                      avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                           200  avgt    5    11.287 ±    0.023   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                            200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                       200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                                 200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                          200.  avgt    5    21.967 ±    0.141   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                           200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                      200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                                200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                       200.200  avgt    5    27.022 ±    2.066   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                        200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                   200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                             200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                      200.200.  avgt    5    49.605 ±    4.143   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                       200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                  200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                            200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                     a.200.200  avgt    5     2.955 ±    0.004   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                      a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                 a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                           a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                     200.a.200  avgt    5    23.027 ±    0.022   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                      200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                 200.a.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                           200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                     200.200.a  avgt    5    52.975 ±    0.266   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                      200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                 200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                           200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                               200.200.200.200  avgt    5    53.635 ±    0.563   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm           200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                     200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                 200.200.99999  avgt    5    77.098 ±    3.053   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                  200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm             200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                       200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                  200.200.-200  avgt    5    51.724 ±    0.137   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                   200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm              200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                        200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized3JavaNoSwitch                                         1.0.0  avgt    5    44.735 ±    0.415   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                          1.0.0  avgt    5  1704.649 ±   16.027  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm                     1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                               1.0.0  avgt    5    59.000             counts
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.time                                1.0.0  avgt    5    39.000                 ms
VersionParseBenchmark.optimized3JavaNoSwitch                                   200.200.200  avgt    5    74.762 ±    0.397   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                    200.200.200  avgt    5  1020.036 ±    5.151  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm               200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                         200.200.200  avgt    5    35.000             counts
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.time                          200.200.200  avgt    5    21.000                 ms
VersionParseBenchmark.optimized3JavaNoSwitch                               10000.9876.5432  avgt    5    87.180 ±    0.633   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate                10000.9876.5432  avgt    5   874.718 ±    6.063  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm           10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                     10000.9876.5432  avgt    5    30.000             counts
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.time                      10000.9876.5432  avgt    5    18.000                 ms
VersionParseBenchmark.optimized3JavaNoSwitch                             10000.10000.10000  avgt    5    97.115 ±    4.890   ns/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate              10000.10000.10000  avgt    5   785.267 ±   39.204  MB/sec
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.alloc.rate.norm         10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.count                   10000.10000.10000  avgt    5    27.000             counts
VersionParseBenchmark.optimized3JavaNoSwitch:·gc.time                    10000.10000.10000  avgt    5    15.000                 ms
VersionParseBenchmark.optimized4                                                            avgt    5     2.077 ±    0.057   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                             avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                                        avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized4:·gc.count                                                  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                                       200  avgt    5    15.907 ±    0.023   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                        200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                                   200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized4:·gc.count                                             200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                                      200.  avgt    5    19.479 ±    0.114   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                       200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                                  200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized4:·gc.count                                            200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                                   200.200  avgt    5    29.231 ±    0.526   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                    200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                               200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized4:·gc.count                                         200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                                  200.200.  avgt    5    31.280 ±    1.477   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                   200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                              200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized4:·gc.count                                        200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                                 a.200.200  avgt    5     2.367 ±    0.007   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                  a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                             a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized4:·gc.count                                       a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                                 200.a.200  avgt    5    19.451 ±    0.019   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                  200.a.200  avgt    5     0.003 ±    0.017  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                             200.a.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized4:·gc.count                                       200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                                 200.200.a  avgt    5    29.772 ±    0.044   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                  200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                             200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized4:·gc.count                                       200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                           200.200.200.200  avgt    5    41.765 ±    0.417   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                            200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                       200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized4:·gc.count                                 200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                             200.200.99999  avgt    5    44.693 ±    2.328   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                              200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                         200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized4:·gc.count                                   200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                              200.200.-200  avgt    5    29.783 ±    0.028   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                               200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                          200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized4:·gc.count                                    200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized4                                                     1.0.0  avgt    5    24.777 ±    3.074   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                      1.0.0  avgt    5  3080.317 ±  380.433  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                                 1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized4:·gc.count                                           1.0.0  avgt    5    92.000             counts
VersionParseBenchmark.optimized4:·gc.time                                            1.0.0  avgt    5    51.000                 ms
VersionParseBenchmark.optimized4                                               200.200.200  avgt    5    41.939 ±    0.180   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                                200.200.200  avgt    5  1818.296 ±    8.200  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                           200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized4:·gc.count                                     200.200.200  avgt    5    62.000             counts
VersionParseBenchmark.optimized4:·gc.time                                      200.200.200  avgt    5    38.000                 ms
VersionParseBenchmark.optimized4                                           10000.9876.5432  avgt    5    53.144 ±    0.239   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                            10000.9876.5432  avgt    5  1434.850 ±    6.116  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                       10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized4:·gc.count                                 10000.9876.5432  avgt    5    49.000             counts
VersionParseBenchmark.optimized4:·gc.time                                  10000.9876.5432  avgt    5    25.000                 ms
VersionParseBenchmark.optimized4                                         10000.10000.10000  avgt    5    60.060 ±    0.659   ns/op
VersionParseBenchmark.optimized4:·gc.alloc.rate                          10000.10000.10000  avgt    5  1269.685 ±   14.159  MB/sec
VersionParseBenchmark.optimized4:·gc.alloc.rate.norm                     10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized4:·gc.count                               10000.10000.10000  avgt    5    44.000             counts
VersionParseBenchmark.optimized4:·gc.time                                10000.10000.10000  avgt    5    22.000                 ms
VersionParseBenchmark.optimized5                                                            avgt    5     2.086 ±    0.016   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                             avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                                        avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized5:·gc.count                                                  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                                       200  avgt    5    15.617 ±    0.018   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                        200  avgt    5     0.003 ±    0.017  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                                   200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized5:·gc.count                                             200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                                      200.  avgt    5    14.762 ±    0.040   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                       200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                                  200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized5:·gc.count                                            200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                                   200.200  avgt    5    32.438 ±    0.021   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                    200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                               200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized5:·gc.count                                         200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                                  200.200.  avgt    5    28.348 ±    0.603   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                   200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                              200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized5:·gc.count                                        200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                                 a.200.200  avgt    5     3.279 ±    0.031   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                  a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                             a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized5:·gc.count                                       a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                                 200.a.200  avgt    5    18.443 ±    0.040   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                  200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                             200.a.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized5:·gc.count                                       200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                                 200.200.a  avgt    5    33.028 ±    0.084   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                  200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                             200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized5:·gc.count                                       200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                           200.200.200.200  avgt    5    45.129 ±    0.819   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                            200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                       200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized5:·gc.count                                 200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                             200.200.99999  avgt    5    50.081 ±    0.097   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                              200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                         200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized5:·gc.count                                   200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                              200.200.-200  avgt    5    33.155 ±    0.846   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                               200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                          200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized5:·gc.count                                    200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized5                                                     1.0.0  avgt    5    29.535 ±    0.347   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                      1.0.0  avgt    5  2582.116 ±   31.138  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                                 1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized5:·gc.count                                           1.0.0  avgt    5    89.000             counts
VersionParseBenchmark.optimized5:·gc.time                                            1.0.0  avgt    5    44.000                 ms
VersionParseBenchmark.optimized5                                               200.200.200  avgt    5    56.508 ±    0.759   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                                200.200.200  avgt    5  1349.527 ±   18.294  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                           200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized5:·gc.count                                     200.200.200  avgt    5    46.000             counts
VersionParseBenchmark.optimized5:·gc.time                                      200.200.200  avgt    5    31.000                 ms
VersionParseBenchmark.optimized5                                           10000.9876.5432  avgt    5    70.007 ±    0.215   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                            10000.9876.5432  avgt    5  1089.387 ±    3.241  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                       10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized5:·gc.count                                 10000.9876.5432  avgt    5    38.000             counts
VersionParseBenchmark.optimized5:·gc.time                                  10000.9876.5432  avgt    5    30.000                 ms
VersionParseBenchmark.optimized5                                         10000.10000.10000  avgt    5    76.329 ±    1.042   ns/op
VersionParseBenchmark.optimized5:·gc.alloc.rate                          10000.10000.10000  avgt    5   999.074 ±   13.332  MB/sec
VersionParseBenchmark.optimized5:·gc.alloc.rate.norm                     10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized5:·gc.count                               10000.10000.10000  avgt    5    35.000             counts
VersionParseBenchmark.optimized5:·gc.time                                10000.10000.10000  avgt    5    18.000                 ms
VersionParseBenchmark.optimized6                                                            avgt    5     2.070 ±    0.005   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                             avgt    5     0.003 ±    0.017  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                                        avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                                  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                       200  avgt    5    12.750 ±    1.336   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                        200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                                   200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                             200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                      200.  avgt    5    15.388 ±    0.493   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                       200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                                  200.  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                            200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                   200.200  avgt    5    23.277 ±    0.041   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                    200.200  avgt    5     0.003 ±    0.017  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                               200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                         200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                  200.200.  avgt    5    27.986 ±    1.126   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                   200.200.  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                              200.200.  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                        200.200.  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                 a.200.200  avgt    5     2.960 ±    0.014   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                  a.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                             a.200.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                       a.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                 200.a.200  avgt    5    16.329 ±    0.077   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                  200.a.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                             200.a.200  avgt    5    ≈ 10⁻⁵               B/op
VersionParseBenchmark.optimized6:·gc.count                                       200.a.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                 200.200.a  avgt    5    29.524 ±    0.052   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                  200.200.a  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                             200.200.a  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                       200.200.a  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                           200.200.200.200  avgt    5    33.956 ±    1.489   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                            200.200.200.200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                       200.200.200.200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                 200.200.200.200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                             200.200.99999  avgt    5    36.787 ±    0.183   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                              200.200.99999  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                         200.200.99999  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                   200.200.99999  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                              200.200.-200  avgt    5    28.695 ±    0.085   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                               200.200.-200  avgt    5     0.003 ±    0.018  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                          200.200.-200  avgt    5    ≈ 10⁻⁴               B/op
VersionParseBenchmark.optimized6:·gc.count                                    200.200.-200  avgt    5       ≈ 0             counts
VersionParseBenchmark.optimized6                                                     1.0.0  avgt    5    22.198 ±    0.051   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                      1.0.0  avgt    5  3435.703 ±    6.851  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                                 1.0.0  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6:·gc.count                                           1.0.0  avgt    5   102.000             counts
VersionParseBenchmark.optimized6:·gc.time                                            1.0.0  avgt    5    47.000                 ms
VersionParseBenchmark.optimized6                                               200.200.200  avgt    5    37.409 ±    1.147   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                                200.200.200  avgt    5  2038.530 ±   61.084  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                           200.200.200  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6:·gc.count                                     200.200.200  avgt    5    70.000             counts
VersionParseBenchmark.optimized6:·gc.time                                      200.200.200  avgt    5    35.000                 ms
VersionParseBenchmark.optimized6                                           10000.9876.5432  avgt    5    51.149 ±    0.341   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                            10000.9876.5432  avgt    5  1490.956 ±    9.401  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                       10000.9876.5432  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6:·gc.count                                 10000.9876.5432  avgt    5    52.000             counts
VersionParseBenchmark.optimized6:·gc.time                                  10000.9876.5432  avgt    5    29.000                 ms
VersionParseBenchmark.optimized6                                         10000.10000.10000  avgt    5    57.729 ±    1.709   ns/op
VersionParseBenchmark.optimized6:·gc.alloc.rate                          10000.10000.10000  avgt    5  1320.945 ±   39.008  MB/sec
VersionParseBenchmark.optimized6:·gc.alloc.rate.norm                     10000.10000.10000  avgt    5    40.000 ±    0.001    B/op
VersionParseBenchmark.optimized6:·gc.count                               10000.10000.10000  avgt    5    46.000             counts
VersionParseBenchmark.optimized6:·gc.time                                10000.10000.10000  avgt    5    24.000                 ms
VersionParseBenchmark.regex                                                                 avgt    5    67.718 ±   10.388   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                                  avgt    5  6088.088 ±  900.007  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                             avgt    5   216.000 ±    0.001    B/op
VersionParseBenchmark.regex:·gc.count                                                       avgt    5   157.000             counts
VersionParseBenchmark.regex:·gc.time                                                        avgt    5    83.000                 ms
VersionParseBenchmark.regex                                                            200  avgt    5   119.994 ±   14.811   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                             200  avgt    5  3434.360 ±  405.483  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                        200  avgt    5   216.000 ±    0.001    B/op
VersionParseBenchmark.regex:·gc.count                                                  200  avgt    5   118.000             counts
VersionParseBenchmark.regex:·gc.time                                                   200  avgt    5    72.000                 ms
VersionParseBenchmark.regex                                                           200.  avgt    5   158.710 ±   15.244   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                            200.  avgt    5  2596.068 ±  241.307  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                       200.  avgt    5   216.000 ±    0.001    B/op
VersionParseBenchmark.regex:·gc.count                                                 200.  avgt    5    89.000             counts
VersionParseBenchmark.regex:·gc.time                                                  200.  avgt    5    51.000                 ms
VersionParseBenchmark.regex                                                        200.200  avgt    5   213.821 ±    1.304   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                         200.200  avgt    5  1925.945 ±   12.251  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                    200.200  avgt    5   216.000 ±    0.002    B/op
VersionParseBenchmark.regex:·gc.count                                              200.200  avgt    5    66.000             counts
VersionParseBenchmark.regex:·gc.time                                               200.200  avgt    5    36.000                 ms
VersionParseBenchmark.regex                                                       200.200.  avgt    5   251.297 ±   17.063   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                        200.200.  avgt    5  1638.986 ±  110.673  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                   200.200.  avgt    5   216.000 ±    0.002    B/op
VersionParseBenchmark.regex:·gc.count                                             200.200.  avgt    5    56.000             counts
VersionParseBenchmark.regex:·gc.time                                              200.200.  avgt    5    29.000                 ms
VersionParseBenchmark.regex                                                      a.200.200  avgt    5    47.806 ±    0.281   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                       a.200.200  avgt    5  5742.682 ±   33.414  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                  a.200.200  avgt    5   144.000 ±    0.001    B/op
VersionParseBenchmark.regex:·gc.count                                            a.200.200  avgt    5   151.000             counts
VersionParseBenchmark.regex:·gc.time                                             a.200.200  avgt    5    74.000                 ms
VersionParseBenchmark.regex                                                      200.a.200  avgt    5   164.804 ±    2.106   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                       200.a.200  avgt    5  2498.539 ±   30.776  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                  200.a.200  avgt    5   216.000 ±    0.002    B/op
VersionParseBenchmark.regex:·gc.count                                            200.a.200  avgt    5    86.000             counts
VersionParseBenchmark.regex:·gc.time                                             200.a.200  avgt    5    57.000                 ms
VersionParseBenchmark.regex                                                      200.200.a  avgt    5   251.237 ±   13.064   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                       200.200.a  avgt    5  1639.436 ±   83.319  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                  200.200.a  avgt    5   216.000 ±    0.002    B/op
VersionParseBenchmark.regex:·gc.count                                            200.200.a  avgt    5    56.000             counts
VersionParseBenchmark.regex:·gc.time                                             200.200.a  avgt    5    29.000                 ms
VersionParseBenchmark.regex                                                200.200.200.200  avgt    5   309.310 ±   18.008   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                 200.200.200.200  avgt    5  1331.585 ±   76.856  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                            200.200.200.200  avgt    5   216.000 ±    0.003    B/op
VersionParseBenchmark.regex:·gc.count                                      200.200.200.200  avgt    5    46.000             counts
VersionParseBenchmark.regex:·gc.time                                       200.200.200.200  avgt    5    27.000                 ms
VersionParseBenchmark.regex                                                  200.200.99999  avgt    5   370.641 ±    1.860   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                   200.200.99999  avgt    5  1851.784 ±    8.882  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                              200.200.99999  avgt    5   360.001 ±    0.003    B/op
VersionParseBenchmark.regex:·gc.count                                        200.200.99999  avgt    5    63.000             counts
VersionParseBenchmark.regex:·gc.time                                         200.200.99999  avgt    5    32.000                 ms
VersionParseBenchmark.regex                                                   200.200.-200  avgt    5   259.873 ±    2.360   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                    200.200.-200  avgt    5  1584.601 ±   14.025  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                               200.200.-200  avgt    5   216.000 ±    0.002    B/op
VersionParseBenchmark.regex:·gc.count                                         200.200.-200  avgt    5    55.000             counts
VersionParseBenchmark.regex:·gc.time                                          200.200.-200  avgt    5    30.000                 ms
VersionParseBenchmark.regex                                                          1.0.0  avgt    5   216.829 ±    0.991   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                           1.0.0  avgt    5  3516.822 ±   17.813  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                      1.0.0  avgt    5   400.000 ±    0.002    B/op
VersionParseBenchmark.regex:·gc.count                                                1.0.0  avgt    5   121.000             counts
VersionParseBenchmark.regex:·gc.time                                                 1.0.0  avgt    5    78.000                 ms
VersionParseBenchmark.regex                                                    200.200.200  avgt    5   333.045 ±    3.117   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                     200.200.200  avgt    5  2289.711 ±   20.746  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                                200.200.200  avgt    5   400.001 ±    0.003    B/op
VersionParseBenchmark.regex:·gc.count                                          200.200.200  avgt    5    79.000             counts
VersionParseBenchmark.regex:·gc.time                                           200.200.200  avgt    5    44.000                 ms
VersionParseBenchmark.regex                                                10000.9876.5432  avgt    5   385.560 ±    0.924   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                                 10000.9876.5432  avgt    5  1977.899 ±    5.074  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                            10000.9876.5432  avgt    5   400.001 ±    0.004    B/op
VersionParseBenchmark.regex:·gc.count                                      10000.9876.5432  avgt    5    68.000             counts
VersionParseBenchmark.regex:·gc.time                                       10000.9876.5432  avgt    5    33.000                 ms
VersionParseBenchmark.regex                                              10000.10000.10000  avgt    5   384.005 ±    1.439   ns/op
VersionParseBenchmark.regex:·gc.alloc.rate                               10000.10000.10000  avgt    5  1985.913 ±    8.401  MB/sec
VersionParseBenchmark.regex:·gc.alloc.rate.norm                          10000.10000.10000  avgt    5   400.001 ±    0.004    B/op
VersionParseBenchmark.regex:·gc.count                                    10000.10000.10000  avgt    5    68.000             counts
VersionParseBenchmark.regex:·gc.time                                     10000.10000.10000  avgt    5    29.000                 ms
VersionParseBenchmark.yolo                                                                  avgt    5  2617.555 ±  188.037   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                                   avgt    5   705.272 ±   52.088  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                              avgt    5   968.004 ±    0.024    B/op
VersionParseBenchmark.yolo:·gc.count                                                        avgt    5    24.000             counts
VersionParseBenchmark.yolo:·gc.time                                                         avgt    5    14.000                 ms
VersionParseBenchmark.yolo                                                             200  avgt    5  1863.354 ±  258.834   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                              200  avgt    5   844.004 ±  121.044  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                         200  avgt    5   824.003 ±    0.017    B/op
VersionParseBenchmark.yolo:·gc.count                                                   200  avgt    5    29.000             counts
VersionParseBenchmark.yolo:·gc.time                                                    200  avgt    5    20.000                 ms
VersionParseBenchmark.yolo                                                            200.  avgt    5  1959.393 ±  195.330   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                             200.  avgt    5   926.803 ±   96.053  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                        200.  avgt    5   952.003 ±    0.018    B/op
VersionParseBenchmark.yolo:·gc.count                                                  200.  avgt    5    32.000             counts
VersionParseBenchmark.yolo:·gc.time                                                   200.  avgt    5    27.000                 ms
VersionParseBenchmark.yolo                                                         200.200  avgt    5  2010.600 ±  212.610   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                          200.200  avgt    5   948.794 ±  104.457  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                     200.200  avgt    5  1000.003 ±    0.019    B/op
VersionParseBenchmark.yolo:·gc.count                                               200.200  avgt    5    33.000             counts
VersionParseBenchmark.yolo:·gc.time                                                200.200  avgt    5    19.000                 ms
VersionParseBenchmark.yolo                                                        200.200.  avgt    5  2040.423 ±  256.024   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                         200.200.  avgt    5   935.180 ±  119.919  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                    200.200.  avgt    5  1000.003 ±    0.020    B/op
VersionParseBenchmark.yolo:·gc.count                                              200.200.  avgt    5    32.000             counts
VersionParseBenchmark.yolo:·gc.time                                               200.200.  avgt    5    16.000                 ms
VersionParseBenchmark.yolo                                                       a.200.200  avgt    5  3023.813 ±  166.541   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                        a.200.200  avgt    5   761.731 ±   43.088  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                   a.200.200  avgt    5  1208.005 ±    0.028    B/op
VersionParseBenchmark.yolo:·gc.count                                             a.200.200  avgt    5    26.000             counts
VersionParseBenchmark.yolo:·gc.time                                              a.200.200  avgt    5    18.000                 ms
VersionParseBenchmark.yolo                                                       200.a.200  avgt    5  2947.524 ±  213.664   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                        200.a.200  avgt    5   781.519 ±   58.172  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                   200.a.200  avgt    5  1208.005 ±    0.027    B/op
VersionParseBenchmark.yolo:·gc.count                                             200.a.200  avgt    5    26.000             counts
VersionParseBenchmark.yolo:·gc.time                                              200.a.200  avgt    5    14.000                 ms
VersionParseBenchmark.yolo                                                       200.200.a  avgt    5  2829.977 ±  279.243   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                        200.200.a  avgt    5   814.290 ±   81.145  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                   200.200.a  avgt    5  1208.004 ±    0.027    B/op
VersionParseBenchmark.yolo:·gc.count                                             200.200.a  avgt    5    28.000             counts
VersionParseBenchmark.yolo:·gc.time                                              200.200.a  avgt    5    16.000                 ms
VersionParseBenchmark.yolo                                                 200.200.200.200  avgt    5  2117.827 ±  203.813   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                  200.200.200.200  avgt    5  1001.620 ±   99.881  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                             200.200.200.200  avgt    5  1112.003 ±    0.020    B/op
VersionParseBenchmark.yolo:·gc.count                                       200.200.200.200  avgt    5    35.000             counts
VersionParseBenchmark.yolo:·gc.time                                        200.200.200.200  avgt    5    17.000                 ms
VersionParseBenchmark.yolo                                                   200.200.99999  avgt    5  2133.263 ±  218.761   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                    200.200.99999  avgt    5   972.888 ±  103.589  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                               200.200.99999  avgt    5  1088.003 ±    0.020    B/op
VersionParseBenchmark.yolo:·gc.count                                         200.200.99999  avgt    5    33.000             counts
VersionParseBenchmark.yolo:·gc.time                                          200.200.99999  avgt    5    25.000                 ms
VersionParseBenchmark.yolo                                                    200.200.-200  avgt    5  2140.690 ±  213.933   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                     200.200.-200  avgt    5   983.778 ±  102.172  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                200.200.-200  avgt    5  1104.003 ±    0.020    B/op
VersionParseBenchmark.yolo:·gc.count                                          200.200.-200  avgt    5    33.000             counts
VersionParseBenchmark.yolo:·gc.time                                           200.200.-200  avgt    5    15.000                 ms
VersionParseBenchmark.yolo                                                           1.0.0  avgt    5   163.856 ±   12.370   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                            1.0.0  avgt    5  4376.177 ±  320.780  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                       1.0.0  avgt    5   376.000 ±    0.001    B/op
VersionParseBenchmark.yolo:·gc.count                                                 1.0.0  avgt    5   131.000             counts
VersionParseBenchmark.yolo:·gc.time                                                  1.0.0  avgt    5    67.000                 ms
VersionParseBenchmark.yolo                                                     200.200.200  avgt    5   187.846 ±    8.626   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                      200.200.200  avgt    5  4141.384 ±  186.709  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                                 200.200.200  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yolo:·gc.count                                           200.200.200  avgt    5   124.000             counts
VersionParseBenchmark.yolo:·gc.time                                            200.200.200  avgt    5    58.000                 ms
VersionParseBenchmark.yolo                                                 10000.9876.5432  avgt    5   206.321 ±   10.128   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                  10000.9876.5432  avgt    5  3770.504 ±  182.124  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                             10000.9876.5432  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yolo:·gc.count                                       10000.9876.5432  avgt    5   113.000             counts
VersionParseBenchmark.yolo:·gc.time                                        10000.9876.5432  avgt    5    63.000                 ms
VersionParseBenchmark.yolo                                               10000.10000.10000  avgt    5   218.728 ±    2.026   ns/op
VersionParseBenchmark.yolo:·gc.alloc.rate                                10000.10000.10000  avgt    5  3556.082 ±   31.614  MB/sec
VersionParseBenchmark.yolo:·gc.alloc.rate.norm                           10000.10000.10000  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yolo:·gc.count                                     10000.10000.10000  avgt    5   107.000             counts
VersionParseBenchmark.yolo:·gc.time                                      10000.10000.10000  avgt    5    77.000                 ms
VersionParseBenchmark.yoloNoThrow                                                           avgt    5  2612.913 ±  160.674   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                            avgt    5   706.422 ±   44.403  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                                       avgt    5   968.004 ±    0.024    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                                 avgt    5    24.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                                  avgt    5    16.000                 ms
VersionParseBenchmark.yoloNoThrow                                                      200  avgt    5  1831.416 ±  227.361   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                       200  avgt    5   858.443 ±  111.597  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                                  200  avgt    5   824.003 ±    0.017    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                            200  avgt    5    29.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                             200  avgt    5    22.000                 ms
VersionParseBenchmark.yoloNoThrow                                                     200.  avgt    5  1968.034 ±  212.895   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                      200.  avgt    5   922.804 ±  104.138  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                                 200.  avgt    5   952.003 ±    0.018    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                           200.  avgt    5    32.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                            200.  avgt    5    17.000                 ms
VersionParseBenchmark.yoloNoThrow                                                  200.200  avgt    5  2015.140 ±  235.209   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                   200.200  avgt    5   946.813 ±  115.147  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                              200.200  avgt    5  1000.003 ±    0.019    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                        200.200  avgt    5    32.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                         200.200  avgt    5    16.000                 ms
VersionParseBenchmark.yoloNoThrow                                                 200.200.  avgt    5  2033.030 ±  206.809   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                  200.200.  avgt    5   938.312 ±   99.180  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                             200.200.  avgt    5  1000.003 ±    0.019    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                       200.200.  avgt    5    33.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                        200.200.  avgt    5    19.000                 ms
VersionParseBenchmark.yoloNoThrow                                                a.200.200  avgt    5  2913.601 ±  172.254   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                 a.200.200  avgt    5   790.532 ±   47.914  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                            a.200.200  avgt    5  1208.005 ±    0.027    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                      a.200.200  avgt    5    27.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                       a.200.200  avgt    5    13.000                 ms
VersionParseBenchmark.yoloNoThrow                                                200.a.200  avgt    5  3037.264 ±  242.542   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                 200.a.200  avgt    5   758.500 ±   61.053  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                            200.a.200  avgt    5  1208.005 ±    0.028    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                      200.a.200  avgt    5    26.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                       200.a.200  avgt    5    13.000                 ms
VersionParseBenchmark.yoloNoThrow                                                200.200.a  avgt    5  3156.323 ±  219.461   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                 200.200.a  avgt    5   729.837 ±   50.417  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                            200.200.a  avgt    5  1208.005 ±    0.029    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                      200.200.a  avgt    5    25.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                       200.200.a  avgt    5    13.000                 ms
VersionParseBenchmark.yoloNoThrow                                          200.200.200.200  avgt    5  2123.962 ±  215.598   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                           200.200.200.200  avgt    5   998.706 ±  105.342  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                      200.200.200.200  avgt    5  1112.003 ±    0.020    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                200.200.200.200  avgt    5    35.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                 200.200.200.200  avgt    5    19.000                 ms
VersionParseBenchmark.yoloNoThrow                                            200.200.99999  avgt    5   192.138 ±    1.144   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                             200.200.99999  avgt    5  3651.749 ±   22.508  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                        200.200.99999  avgt    5   368.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                  200.200.99999  avgt    5   110.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                   200.200.99999  avgt    5    55.000                 ms
VersionParseBenchmark.yoloNoThrow                                             200.200.-200  avgt    5   195.716 ±    2.138   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                              200.200.-200  avgt    5  3740.615 ±   43.009  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                         200.200.-200  avgt    5   384.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                   200.200.-200  avgt    5   112.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                    200.200.-200  avgt    5    58.000                 ms
VersionParseBenchmark.yoloNoThrow                                                    1.0.0  avgt    5   163.308 ±   10.761   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                                     1.0.0  avgt    5  4390.305 ±  286.708  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                                1.0.0  avgt    5   376.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                          1.0.0  avgt    5   132.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                           1.0.0  avgt    5    66.000                 ms
VersionParseBenchmark.yoloNoThrow                                              200.200.200  avgt    5   204.445 ±   13.913   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                               200.200.200  avgt    5  3805.221 ±  259.695  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                          200.200.200  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                    200.200.200  avgt    5   114.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                     200.200.200  avgt    5    65.000                 ms
VersionParseBenchmark.yoloNoThrow                                          10000.9876.5432  avgt    5   205.980 ±    1.730   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                           10000.9876.5432  avgt    5  3775.989 ±   30.446  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                      10000.9876.5432  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                                10000.9876.5432  avgt    5   114.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                                 10000.9876.5432  avgt    5    67.000                 ms
VersionParseBenchmark.yoloNoThrow                                        10000.10000.10000  avgt    5   209.066 ±    1.968   ns/op
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate                         10000.10000.10000  avgt    5  3720.561 ±   34.853  MB/sec
VersionParseBenchmark.yoloNoThrow:·gc.alloc.rate.norm                    10000.10000.10000  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrow:·gc.count                              10000.10000.10000  avgt    5   112.000             counts
VersionParseBenchmark.yoloNoThrow:·gc.time                               10000.10000.10000  avgt    5    54.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                                      avgt    5  2634.084 ±  100.189   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                                       avgt    5   700.682 ±   27.150  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                                  avgt    5   968.004 ±    0.025    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                            avgt    5    24.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                             avgt    5    13.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                                 200  avgt    5  1771.878 ±  225.386   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                                  200  avgt    5   887.417 ±  118.159  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                             200  avgt    5   824.003 ±    0.017    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                       200  avgt    5    30.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                        200  avgt    5    22.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                                200.  avgt    5  1912.557 ±  225.952   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                                 200.  avgt    5   949.722 ±  117.131  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                            200.  avgt    5   952.003 ±    0.018    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                      200.  avgt    5    32.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                       200.  avgt    5    20.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                             200.200  avgt    5  1907.980 ±  238.438   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                              200.200  avgt    5  1000.010 ±  126.883  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                         200.200  avgt    5  1000.003 ±    0.018    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                   200.200  avgt    5    35.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                    200.200  avgt    5    17.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                            200.200.  avgt    5  2001.499 ±   26.746   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                             200.200.  avgt    5   952.562 ±   12.947  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                        200.200.  avgt    5  1000.003 ±    0.019    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                  200.200.  avgt    5    33.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                   200.200.  avgt    5    18.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                           a.200.200  avgt    5  2808.985 ±  253.231   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                            a.200.200  avgt    5   820.248 ±   74.536  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                       a.200.200  avgt    5  1208.004 ±    0.026    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                 a.200.200  avgt    5    28.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                  a.200.200  avgt    5    20.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                           200.a.200  avgt    5  2812.029 ±  322.468   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                            200.a.200  avgt    5   819.621 ±   95.325  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                       200.a.200  avgt    5  1208.004 ±    0.026    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                 200.a.200  avgt    5    28.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                  200.a.200  avgt    5    15.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                           200.200.a  avgt    5  2810.153 ±  177.336   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                            200.200.a  avgt    5   819.749 ±   52.976  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                       200.200.a  avgt    5  1208.004 ±    0.026    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                 200.200.a  avgt    5    28.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                  200.200.a  avgt    5    21.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                     200.200.200.200  avgt    5  2037.505 ±  191.070   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                      200.200.200.200  avgt    5  1040.921 ±  101.240  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                 200.200.200.200  avgt    5  1112.003 ±    0.019    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                           200.200.200.200  avgt    5    36.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                            200.200.200.200  avgt    5    19.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                       200.200.99999  avgt    5   193.666 ±    1.154   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                        200.200.99999  avgt    5  3622.635 ±   22.046  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                   200.200.99999  avgt    5   368.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                             200.200.99999  avgt    5   109.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                              200.200.99999  avgt    5    68.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                        200.200.-200  avgt    5   194.691 ±    1.052   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                         200.200.-200  avgt    5  3760.131 ±   19.483  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                    200.200.-200  avgt    5   384.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                              200.200.-200  avgt    5   113.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                               200.200.-200  avgt    5    64.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                               1.0.0  avgt    5   166.721 ±    1.802   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                                1.0.0  avgt    5  4299.910 ±   45.259  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                           1.0.0  avgt    5   376.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                                     1.0.0  avgt    5   129.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                      1.0.0  avgt    5    67.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                         200.200.200  avgt    5   195.509 ±   10.275   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                          200.200.200  avgt    5  3979.274 ±  211.439  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                     200.200.200  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                               200.200.200  avgt    5   120.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                                200.200.200  avgt    5    70.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                     10000.9876.5432  avgt    5   212.145 ±   32.412   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                      10000.9876.5432  avgt    5  3671.075 ±  555.422  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm                 10000.9876.5432  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                           10000.9876.5432  avgt    5   110.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                            10000.9876.5432  avgt    5    54.000                 ms
VersionParseBenchmark.yoloNoThrowNoTry                                   10000.10000.10000  avgt    5   223.956 ±    0.996   ns/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate                    10000.10000.10000  avgt    5  3473.136 ±   15.322  MB/sec
VersionParseBenchmark.yoloNoThrowNoTry:·gc.alloc.rate.norm               10000.10000.10000  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoThrowNoTry:·gc.count                         10000.10000.10000  avgt    5   104.000             counts
VersionParseBenchmark.yoloNoThrowNoTry:·gc.time                          10000.10000.10000  avgt    5    53.000                 ms
VersionParseBenchmark.yoloNoTry                                                             avgt    5  2677.322 ±  275.570   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                              avgt    5   689.714 ±   71.705  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                                         avgt    5   968.004 ±    0.025    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                                   avgt    5    24.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                                    avgt    5    19.000                 ms
VersionParseBenchmark.yoloNoTry                                                        200  avgt    5  1773.514 ±  241.421   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                         200  avgt    5   886.648 ±  125.042  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                                    200  avgt    5   824.003 ±    0.017    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                              200  avgt    5    30.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                               200  avgt    5    17.000                 ms
VersionParseBenchmark.yoloNoTry                                                       200.  avgt    5  2132.151 ±  171.367   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                        200.  avgt    5   851.548 ±   70.523  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                                   200.  avgt    5   952.003 ±    0.020    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                             200.  avgt    5    29.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                              200.  avgt    5    23.000                 ms
VersionParseBenchmark.yoloNoTry                                                    200.200  avgt    5  1941.839 ±  210.070   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                     200.200  avgt    5   982.328 ±  109.131  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                                200.200  avgt    5  1000.003 ±    0.018    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                          200.200  avgt    5    34.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                           200.200  avgt    5    19.000                 ms
VersionParseBenchmark.yoloNoTry                                                   200.200.  avgt    5  1995.466 ±  218.482   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                    200.200.  avgt    5   955.974 ±  108.846  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                               200.200.  avgt    5  1000.003 ±    0.019    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                         200.200.  avgt    5    33.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                          200.200.  avgt    5    26.000                 ms
VersionParseBenchmark.yoloNoTry                                                  a.200.200  avgt    5  2773.175 ±  278.194   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                   a.200.200  avgt    5   830.911 ±   85.036  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                              a.200.200  avgt    5  1208.004 ±    0.026    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                        a.200.200  avgt    5    28.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                         a.200.200  avgt    5    15.000                 ms
VersionParseBenchmark.yoloNoTry                                                  200.a.200  avgt    5  2536.385 ±  175.330   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                   200.a.200  avgt    5   908.234 ±   64.160  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                              200.a.200  avgt    5  1208.004 ±    0.024    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                        200.a.200  avgt    5    31.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                         200.a.200  avgt    5    17.000                 ms
VersionParseBenchmark.yoloNoTry                                                  200.200.a  avgt    5  2866.893 ±  184.957   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                   200.200.a  avgt    5   803.446 ±   53.245  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                              200.200.a  avgt    5  1208.004 ±    0.027    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                        200.200.a  avgt    5    27.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                         200.200.a  avgt    5    15.000                 ms
VersionParseBenchmark.yoloNoTry                                            200.200.200.200  avgt    5  2044.639 ±  193.233   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                             200.200.200.200  avgt    5  1037.415 ±  101.625  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                        200.200.200.200  avgt    5  1112.003 ±    0.019    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                  200.200.200.200  avgt    5    36.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                   200.200.200.200  avgt    5    17.000                 ms
VersionParseBenchmark.yoloNoTry                                              200.200.99999  avgt    5  2090.509 ±   55.529   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                               200.200.99999  avgt    5   992.239 ±   26.668  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                          200.200.99999  avgt    5  1088.003 ±    0.019    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                    200.200.99999  avgt    5    34.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                     200.200.99999  avgt    5    22.000                 ms
VersionParseBenchmark.yoloNoTry                                               200.200.-200  avgt    5  2073.857 ±  204.163   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                200.200.-200  avgt    5  1015.379 ±  103.713  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                           200.200.-200  avgt    5  1104.003 ±    0.019    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                     200.200.-200  avgt    5    35.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                      200.200.-200  avgt    5    20.000                 ms
VersionParseBenchmark.yoloNoTry                                                      1.0.0  avgt    5   164.512 ±    5.407   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                       1.0.0  avgt    5  4357.848 ±  142.592  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                                  1.0.0  avgt    5   376.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                            1.0.0  avgt    5   130.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                             1.0.0  avgt    5    73.000                 ms
VersionParseBenchmark.yoloNoTry                                                200.200.200  avgt    5   188.999 ±    1.607   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                                 200.200.200  avgt    5  4115.570 ±   35.061  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                            200.200.200  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                      200.200.200  avgt    5   124.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                       200.200.200  avgt    5    61.000                 ms
VersionParseBenchmark.yoloNoTry                                            10000.9876.5432  avgt    5   213.424 ±   21.607   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                             10000.9876.5432  avgt    5  3646.632 ±  356.070  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                        10000.9876.5432  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                  10000.9876.5432  avgt    5   109.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                   10000.9876.5432  avgt    5    52.000                 ms
VersionParseBenchmark.yoloNoTry                                          10000.10000.10000  avgt    5   223.826 ±   25.393   ns/op
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate                           10000.10000.10000  avgt    5  3477.752 ±  387.160  MB/sec
VersionParseBenchmark.yoloNoTry:·gc.alloc.rate.norm                      10000.10000.10000  avgt    5   408.000 ±    0.002    B/op
VersionParseBenchmark.yoloNoTry:·gc.count                                10000.10000.10000  avgt    5   105.000             counts
VersionParseBenchmark.yoloNoTry:·gc.time                                 10000.10000.10000  avgt    5    58.000                 ms
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx1G"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
class VersionParseBenchmark {

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
  def yolo: Option[Version] = Version.parseYolo(encoded)

  @Benchmark
  def yoloNoTry: Option[Version] = Version.parseYoloNoTry(encoded)

  @Benchmark
  def yoloNoThrow: Option[Version] = Version.parseYoloNoThrow(encoded)

  @Benchmark
  def yoloNoThrowNoTry: Option[Version] = Version.parseYoloNoThrowNoTry(encoded)

  @Benchmark
  def regex: Option[Version] = Version.parseWithRegex(encoded)

  @Benchmark
  def optimized1: Option[Version] = Version.parseOptimized1(encoded)

  @Benchmark
  def optimized2: Option[Version] = Version.parseOptimized2(encoded)

  @Benchmark
  def optimized3: Option[Version] = Version.parseOptimized3(encoded)

  @Benchmark
  def optimized3Java: Option[Version] = Version.parseOptimized3Java(encoded)

  @Benchmark
  def optimized3JavaNoSwitch: Option[Version] = Version.parseOptimized3JavaNoSwitch(encoded)

  @Benchmark
  def optimized4: Option[Version] = Version.parseOptimized4(encoded)

  @Benchmark
  def optimized5: Option[Version] = Version.parseOptimized5(encoded)

  @Benchmark
  def optimized6: Option[Version] = Version.parseOptimized6(encoded)

  @Benchmark
  def optimized6Scala: Option[Version] = Version.parseOptimized6Scala(encoded)
}
