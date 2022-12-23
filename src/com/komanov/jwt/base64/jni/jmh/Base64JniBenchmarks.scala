package com.komanov.jwt.base64.jni.jmh

import com.komanov.jwt.base64.Base64Helper.Jdk
import com.komanov.jwt.base64.jmh.Base64BenchmarkBase
import com.komanov.jwt.base64.jni.{NativeBazel, NativeCargo}
import org.openjdk.jmh.annotations._

/*
Results for openjdk-17:
<code>
Benchmark                                                       (dataset)  (length)  Mode  Cnt       Score       Error  Units
Base64JniBenchmarks.jdk_url_decode                                  fixed         1  avgt    5      32.261 ±     0.058  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed        10  avgt    5      52.165 ±     1.660  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed        50  avgt    5     133.914 ±     1.108  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed       100  avgt    5     232.112 ±     3.565  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed       500  avgt    5     973.504 ±     6.623  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed      1000  avgt    5    1932.961 ±    83.537  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed     10000  avgt    5   19530.268 ±   590.507  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random         1  avgt    5      32.594 ±     0.197  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random        10  avgt    5      53.476 ±     0.201  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random        50  avgt    5     129.308 ±     0.361  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random       100  avgt    5     233.406 ±    11.803  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random       500  avgt    5     971.815 ±     5.610  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random      1000  avgt    5    1913.533 ±    12.935  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random     10000  avgt    5   19459.235 ±   181.375  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed         1  avgt    5      23.037 ±     1.780  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed        10  avgt    5      46.815 ±     0.433  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed        50  avgt    5      86.023 ±     1.268  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed       100  avgt    5     128.376 ±     1.148  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed       500  avgt    5     442.988 ±    10.537  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed      1000  avgt    5     880.180 ±     2.357  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed     10000  avgt    5    9283.541 ±    68.965  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random         1  avgt    5      22.928 ±     1.229  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random        10  avgt    5      47.112 ±     0.548  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random        50  avgt    5      86.129 ±     1.649  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random       100  avgt    5     128.576 ±     3.784  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random       500  avgt    5     450.760 ±    29.946  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random      1000  avgt    5     862.301 ±    22.035  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random     10000  avgt    5    9312.859 ±   242.959  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed         1  avgt    5     454.530 ±    26.697  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed        10  avgt    5     420.099 ±     2.144  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed        50  avgt    5     503.044 ±    20.493  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed       100  avgt    5     662.213 ±    30.668  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed       500  avgt    5    1114.723 ±    23.251  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed      1000  avgt    5    1778.687 ±    32.655  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed     10000  avgt    5   12403.311 ±    99.469  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random         1  avgt    5     446.120 ±     4.142  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random        10  avgt    5     429.525 ±    12.082  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random        50  avgt    5     502.191 ±     5.772  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random       100  avgt    5     638.453 ±    12.977  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random       500  avgt    5    1118.850 ±    99.399  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random      1000  avgt    5    1790.715 ±    25.318  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random     10000  avgt    5   12541.171 ±   119.797  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed         1  avgt    5     428.765 ±     3.398  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed        10  avgt    5     400.632 ±     6.850  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed        50  avgt    5     468.572 ±     2.471  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed       100  avgt    5     538.772 ±     4.381  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed       500  avgt    5    1024.248 ±    13.106  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed      1000  avgt    5    1773.033 ±    32.938  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed     10000  avgt    5   12355.233 ±   620.308  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random         1  avgt    5     426.145 ±    27.477  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random        10  avgt    5     396.828 ±     2.666  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random        50  avgt    5     469.415 ±    20.159  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random       100  avgt    5     539.739 ±     7.723  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random       500  avgt    5    1004.027 ±     3.840  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random      1000  avgt    5    1783.655 ±    17.368  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random     10000  avgt    5   12281.377 ±    93.112  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed         1  avgt    5     390.491 ±    14.498  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed        10  avgt    5     366.070 ±    12.426  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed        50  avgt    5     439.038 ±    13.036  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed       100  avgt    5     505.095 ±    44.893  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed       500  avgt    5     994.965 ±   108.734  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed      1000  avgt    5    1744.814 ±    16.542  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed     10000  avgt    5   12252.848 ±    40.396  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random         1  avgt    5     387.354 ±     3.880  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random        10  avgt    5     362.981 ±     3.549  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random        50  avgt    5     441.415 ±     7.705  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random       100  avgt    5     505.534 ±     7.165  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random       500  avgt    5     975.426 ±     6.288  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random      1000  avgt    5    1762.588 ±    86.949  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random     10000  avgt    5   12316.667 ±   337.053  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed         1  avgt    5     344.372 ±     2.253  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed        10  avgt    5     320.277 ±     3.662  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed        50  avgt    5     392.219 ±     1.930  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed       100  avgt    5     455.117 ±     3.990  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed       500  avgt    5     904.545 ±     6.124  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed      1000  avgt    5    1569.830 ±    33.294  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed     10000  avgt    5   11942.370 ±   330.573  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random         1  avgt    5     349.994 ±    13.169  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random        10  avgt    5     324.653 ±     9.799  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random        50  avgt    5     395.382 ±    31.618  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random       100  avgt    5     453.441 ±     6.119  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random       500  avgt    5     897.328 ±     7.969  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random      1000  avgt    5    1599.359 ±     6.107  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random     10000  avgt    5   11918.281 ±   490.235  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed         1  avgt    5     367.476 ±     8.220  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed        10  avgt    5     352.637 ±    32.295  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed        50  avgt    5     433.632 ±     4.454  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed       100  avgt    5     506.076 ±     5.490  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed       500  avgt    5     962.083 ±    13.080  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed      1000  avgt    5    1636.471 ±    62.446  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed     10000  avgt    5   12318.419 ±   283.668  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random         1  avgt    5     330.075 ±     1.929  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random        10  avgt    5     357.699 ±     2.172  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random        50  avgt    5     430.467 ±     2.689  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random       100  avgt    5     507.583 ±     9.173  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random       500  avgt    5     973.726 ±     9.386  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random      1000  avgt    5    1644.775 ±    27.943  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random     10000  avgt    5   12212.385 ±   449.465  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed         1  avgt    5     234.192 ±    10.096  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed        10  avgt    5     254.985 ±     2.551  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed        50  avgt    5     327.848 ±     2.083  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed       100  avgt    5     378.639 ±     3.030  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed       500  avgt    5     833.686 ±     4.446  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed      1000  avgt    5    1545.809 ±    83.812  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed     10000  avgt    5   12322.464 ±    91.313  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random         1  avgt    5     232.714 ±     2.724  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random        10  avgt    5     261.132 ±    13.458  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random        50  avgt    5     329.503 ±    15.979  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random       100  avgt    5     380.451 ±     2.481  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random       500  avgt    5     837.131 ±     5.838  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random      1000  avgt    5    1524.587 ±    32.731  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random     10000  avgt    5   12316.025 ±   136.161  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed         1  avgt    5     144.330 ±     4.680  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed        10  avgt    5     178.244 ±    11.446  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed        50  avgt    5     241.677 ±    12.836  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed       100  avgt    5     292.331 ±     4.535  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed       500  avgt    5     744.310 ±     7.786  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed      1000  avgt    5    1452.657 ±    11.201  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed     10000  avgt    5   12071.756 ±   134.344  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random         1  avgt    5     150.012 ±     9.683  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random        10  avgt    5     176.600 ±    15.447  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random        50  avgt    5     277.359 ±     2.272  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random       100  avgt    5     309.063 ±    31.053  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random       500  avgt    5     739.162 ±    21.191  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random      1000  avgt    5    1431.783 ±    33.189  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random     10000  avgt    5   12045.525 ±   114.660  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed         1  avgt    5      64.040 ±     2.548  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed        10  avgt    5      87.030 ±     1.703  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed        50  avgt    5     132.581 ±     1.932  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed       100  avgt    5     182.863 ±    26.261  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed       500  avgt    5     625.604 ±     5.139  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed      1000  avgt    5    1221.930 ±    56.972  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed     10000  avgt    5   11841.998 ±    60.984  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random         1  avgt    5      63.132 ±     2.971  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random        10  avgt    5      87.888 ±     7.038  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random        50  avgt    5     132.972 ±     2.132  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random       100  avgt    5     181.868 ±     6.629  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random       500  avgt    5     624.731 ±     7.767  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random      1000  avgt    5    1206.813 ±     4.861  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random     10000  avgt    5   11801.534 ±    64.915  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                              fixed         1  avgt    5     114.930 ±     3.911  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                              fixed        10  avgt    5     279.449 ±    20.065  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                              fixed        50  avgt    5    1566.206 ±    96.158  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                              fixed       100  avgt    5    4107.847 ±   145.453  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                              fixed       500  avgt    5   19555.857 ±   154.444  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                              fixed      1000  avgt    5   39469.318 ±   421.431  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                              fixed     10000  avgt    5  397122.613 ±  2593.429  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                             random         1  avgt    5     115.965 ±    10.709  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                             random        10  avgt    5     281.700 ±     6.911  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                             random        50  avgt    5    1554.298 ±   114.298  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                             random       100  avgt    5    4092.271 ±    70.038  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                             random       500  avgt    5   19641.757 ±   574.298  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                             random      1000  avgt    5   39441.071 ±   324.889  ns/op
Base64JniBenchmarks.jni_url_decodeSimd                             random     10000  avgt    5  400173.405 ± 11496.442  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                         fixed         1  avgt    5      49.197 ±     1.177  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                         fixed        10  avgt    5      67.712 ±     1.641  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                         fixed        50  avgt    5      89.372 ±     5.070  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                         fixed       100  avgt    5      76.619 ±     1.512  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                         fixed       500  avgt    5     184.856 ±     2.380  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                         fixed      1000  avgt    5     319.438 ±    33.017  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                         fixed     10000  avgt    5    2918.582 ±   196.564  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                        random         1  avgt    5      50.469 ±     4.149  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                        random        10  avgt    5      65.548 ±     0.259  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                        random        50  avgt    5      93.683 ±    10.326  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                        random       100  avgt    5      75.759 ±     3.700  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                        random       500  avgt    5     192.118 ±     6.622  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                        random      1000  avgt    5     327.114 ±     6.697  ns/op
Base64JniBenchmarks.jni_url_decodeSimdCargo                        random     10000  avgt    5    2920.524 ±   193.974  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                       fixed         1  avgt    5     109.806 ±     3.517  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                       fixed        10  avgt    5     272.347 ±    20.422  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                       fixed        50  avgt    5    1551.219 ±    46.443  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                       fixed       100  avgt    5    4070.267 ±   113.942  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                       fixed       500  avgt    5   19574.552 ±   536.950  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                       fixed      1000  avgt    5   39531.097 ±   408.037  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                       fixed     10000  avgt    5  395839.911 ±  4616.368  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                      random         1  avgt    5     107.526 ±     5.520  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                      random        10  avgt    5     273.450 ±     6.034  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                      random        50  avgt    5    1549.060 ±    65.423  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                      random       100  avgt    5    4087.268 ±   128.412  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                      random       500  avgt    5   19497.635 ±   187.474  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                      random      1000  avgt    5   39765.371 ±  1019.321  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlace                      random     10000  avgt    5  395549.927 ±  6722.694  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                  fixed         1  avgt    5      48.601 ±     1.692  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                  fixed        10  avgt    5      66.460 ±     3.357  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                  fixed        50  avgt    5      92.001 ±     5.124  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                  fixed       100  avgt    5      73.074 ±     0.807  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                  fixed       500  avgt    5     180.799 ±     4.512  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                  fixed      1000  avgt    5     320.122 ±    11.479  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                  fixed     10000  avgt    5    2784.938 ±   269.968  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                 random         1  avgt    5      48.242 ±     0.676  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                 random        10  avgt    5      66.204 ±     0.352  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                 random        50  avgt    5      88.577 ±     8.227  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                 random       100  avgt    5      78.472 ±     6.894  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                 random       500  avgt    5     186.582 ±    25.163  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                 random      1000  avgt    5     322.187 ±    23.043  ns/op
Base64JniBenchmarks.jni_url_decodeSimdInPlaceCargo                 random     10000  avgt    5    2829.625 ±    56.895  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed         1  avgt    5     424.422 ±     4.873  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed        10  avgt    5     447.712 ±     2.746  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed        50  avgt    5     498.343 ±     3.719  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed       100  avgt    5     631.960 ±    33.949  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed       500  avgt    5    1147.806 ±   103.770  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed      1000  avgt    5    1715.975 ±    33.943  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed     10000  avgt    5   12155.027 ±   583.773  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random         1  avgt    5     420.413 ±     8.235  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random        10  avgt    5     447.518 ±     4.604  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random        50  avgt    5     499.953 ±    29.256  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random       100  avgt    5     639.992 ±    30.074  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random       500  avgt    5    1121.859 ±    55.810  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random      1000  avgt    5    1708.309 ±    33.660  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random     10000  avgt    5   12122.330 ±   705.585  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed         1  avgt    5     138.777 ±     2.880  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed        10  avgt    5     154.755 ±     5.305  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed        50  avgt    5     200.572 ±     1.514  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed       100  avgt    5     261.184 ±    25.134  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed       500  avgt    5     663.636 ±    13.381  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed      1000  avgt    5    1187.472 ±    18.666  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed     10000  avgt    5   10950.509 ±   124.232  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random         1  avgt    5     141.584 ±     3.001  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random        10  avgt    5     154.187 ±     9.696  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random        50  avgt    5     200.225 ±     0.584  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random       100  avgt    5     249.369 ±     1.040  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random       500  avgt    5     660.338 ±     6.917  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random      1000  avgt    5    1200.775 ±    29.985  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random     10000  avgt    5   11020.396 ±    45.316  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed         1  avgt    5     228.684 ±     4.324  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed        10  avgt    5     239.960 ±     2.063  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed        50  avgt    5     282.513 ±    11.284  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed       100  avgt    5     331.724 ±     3.783  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed       500  avgt    5     758.932 ±    47.533  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed      1000  avgt    5    1365.988 ±    15.100  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed     10000  avgt    5   11161.777 ±   431.406  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random         1  avgt    5     226.501 ±     6.043  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random        10  avgt    5     237.988 ±     7.082  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random        50  avgt    5     285.630 ±     7.243  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random       100  avgt    5     329.706 ±     4.974  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random       500  avgt    5     747.513 ±     1.763  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random      1000  avgt    5    1381.883 ±   101.582  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random     10000  avgt    5   11158.376 ±   172.654  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed         1  avgt    5      53.666 ±     5.688  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed        10  avgt    5      68.561 ±     2.251  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed        50  avgt    5     106.012 ±     0.822  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed       100  avgt    5     154.400 ±     0.285  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed       500  avgt    5     564.555 ±    23.165  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed      1000  avgt    5    1093.562 ±    76.345  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed     10000  avgt    5   10861.027 ±   116.176  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random         1  avgt    5      53.516 ±     1.312  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random        10  avgt    5      68.057 ±     3.774  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random        50  avgt    5     107.562 ±     1.505  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random       100  avgt    5     158.820 ±    15.218  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random       500  avgt    5     563.498 ±    24.504  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random      1000  avgt    5    1103.164 ±    23.687  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random     10000  avgt    5   10847.752 ±   506.848  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                              fixed         1  avgt    5     103.444 ±     2.744  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                              fixed        10  avgt    5     246.380 ±     6.211  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                              fixed        50  avgt    5    2681.018 ±    19.537  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                              fixed       100  avgt    5    4849.947 ±    27.293  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                              fixed       500  avgt    5   21258.712 ±   265.047  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                              fixed      1000  avgt    5   43005.502 ±   540.343  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                              fixed     10000  avgt    5  434290.270 ± 10660.781  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                             random         1  avgt    5     102.762 ±     0.723  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                             random        10  avgt    5     251.492 ±    14.917  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                             random        50  avgt    5    2695.396 ±    30.704  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                             random       100  avgt    5    4860.528 ±    49.617  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                             random       500  avgt    5   21220.794 ±   209.600  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                             random      1000  avgt    5   43058.294 ±   368.501  ns/op
Base64JniBenchmarks.jni_url_encodeSimd                             random     10000  avgt    5  433833.381 ±  6013.767  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                         fixed         1  avgt    5      49.246 ±     0.631  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                         fixed        10  avgt    5      63.358 ±     2.616  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                         fixed        50  avgt    5      75.605 ±     0.550  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                         fixed       100  avgt    5      94.242 ±     5.802  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                         fixed       500  avgt    5     191.247 ±    15.015  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                         fixed      1000  avgt    5     354.659 ±    12.747  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                         fixed     10000  avgt    5    3367.096 ±   199.407  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                        random         1  avgt    5      50.196 ±     0.773  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                        random        10  avgt    5      65.727 ±     3.752  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                        random        50  avgt    5      77.011 ±     6.237  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                        random       100  avgt    5      93.155 ±     3.892  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                        random       500  avgt    5     187.286 ±    10.837  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                        random      1000  avgt    5     354.627 ±     8.237  ns/op
Base64JniBenchmarks.jni_url_encodeSimdCargo                        random     10000  avgt    5    3336.829 ±   154.967  ns/op
</code>
 */
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
class Base64JniBenchmarks extends Base64BenchmarkBase {
  @Setup
  def init(): Unit = {
    NativeCargo.INSTANCE.toString // force print linkage error if any
  }

  @Benchmark
  def jdk_url_decode: Array[Byte] = Jdk.decodeUrlSafe(urlEncoded)

  @Benchmark
  def jdk_url_encode: Array[Byte] = Jdk.encodeUrlSafe(data)

  @Benchmark
  def jni_url_encodeConfig: Array[Byte] = NativeBazel.INSTANCE.encodeConfigUrlSafe(data)

  @Benchmark
  def jni_url_encodeConfigSlice1NoCache: Array[Byte] = NativeBazel.INSTANCE.encodeConfigSlice1NoCache(data)

  @Benchmark
  def jni_url_encodeConfigSlice1Cache: Array[Byte] = NativeBazel.INSTANCE.encodeConfigSlice1Cache(data)

  @Benchmark
  def jni_url_encodeConfigSlice2CacheInputOutput: Array[Byte] = NativeBazel.INSTANCE.encodeConfigSlice2CacheInputOutput(data)

  @Benchmark
  def jni_url_decodeConfig1: Array[Byte] = NativeBazel.INSTANCE.decodeConfigUrlSafe1(urlEncoded)

  @Benchmark
  def jni_url_decodeConfig2: Array[Byte] = NativeBazel.INSTANCE.decodeConfigUrlSafe2(urlEncoded)

  @Benchmark
  def jni_url_decodeConfig3: Array[Byte] = NativeBazel.INSTANCE.decodeConfigUrlSafe3(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfig4: Array[Byte] = NativeBazel.INSTANCE.decodeConfigUrlSafe4(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfigSlice1: Array[Byte] = NativeBazel.INSTANCE.decodeConfigSliceUrlSafe1(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfigSlice1NoCache: Array[Byte] = NativeBazel.INSTANCE.decodeConfigSliceUrlSafe2NoCache(urlEncoded)

  @Benchmark
  def jni_url_decodeConfigSlice2Cache: Array[Byte] = NativeBazel.INSTANCE.decodeConfigSliceUrlSafe2Cache(urlEncoded)

  @Benchmark
  def jni_url_decodeConfigSlice3CacheInputOutput: Array[Byte] = NativeBazel.INSTANCE.decodeConfigSliceUrlSafe3CacheInputOutput(urlEncoded)

  @Benchmark
  def jni_url_encodeSimd: Array[Byte] = NativeBazel.INSTANCE.encodeSimd(data)

  @Benchmark
  def jni_url_decodeSimd: Array[Byte] = NativeBazel.INSTANCE.decodeSimd(urlEncoded)

  @Benchmark
  def jni_url_decodeSimdInPlace: Array[Byte] = NativeBazel.INSTANCE.decodeSimdInPlace(urlEncoded)

  @Benchmark
  def jni_url_encodeSimdCargo: Array[Byte] = NativeCargo.INSTANCE.encodeSimd(data)

  @Benchmark
  def jni_url_decodeSimdCargo: Array[Byte] = NativeCargo.INSTANCE.decodeSimd(urlEncoded)

  @Benchmark
  def jni_url_decodeSimdInPlaceCargo: Array[Byte] = NativeCargo.INSTANCE.decodeSimdInPlace(urlEncoded)

  @Benchmark
  def nalim_url_encodeConfigSlice: Array[Byte] = NativeCargo.INSTANCE.encodeConfigSliceNalim(data)

  @Benchmark
  def nalim_url_decodeConfigSlice: Array[Byte] = NativeCargo.INSTANCE.decodeConfigSliceNalim(urlEncoded)

  @Benchmark
  def nalim_url_encodeSimd: Array[Byte] = NativeCargo.INSTANCE.encodeSimdNalim(data)

  @Benchmark
  def nalim_url_decodeSimd: Array[Byte] = NativeCargo.INSTANCE.decodeSimdNalim(urlEncoded)

  @Benchmark
  def nalim_url_decodeSimdInPlace: Array[Byte] = NativeCargo.INSTANCE.decodeSimdInPlaceNalim(urlEncoded)
}
