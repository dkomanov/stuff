package com.komanov.jwt.base64.jni.jmh

import com.komanov.jwt.base64.Base64Helper.Jdk
import com.komanov.jwt.base64.jmh.Base64BenchmarkBase
import com.komanov.jwt.base64.jni.Native
import org.openjdk.jmh.annotations._

/*
Results for openjdk-17:
<code>
Benchmark                                                       (dataset)  (length)  Mode  Cnt      Score      Error  Units
Base64JniBenchmarks.jdk_url_decode                                  fixed         1  avgt    5     32.214 ±    0.110  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed        10  avgt    5     51.900 ±    0.361  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed        50  avgt    5    128.916 ±    1.362  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed       100  avgt    5    233.225 ±    1.741  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed       500  avgt    5    970.132 ±    6.761  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed      1000  avgt    5   1908.501 ±   14.231  ns/op
Base64JniBenchmarks.jdk_url_decode                                  fixed     10000  avgt    5  19375.404 ±   70.037  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random         1  avgt    5     32.463 ±    0.190  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random        10  avgt    5     51.186 ±    0.473  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random        50  avgt    5    133.427 ±    1.292  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random       100  avgt    5    236.511 ±   30.351  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random       500  avgt    5    970.265 ±    6.619  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random      1000  avgt    5   1911.495 ±   14.665  ns/op
Base64JniBenchmarks.jdk_url_decode                                 random     10000  avgt    5  19580.641 ±  210.049  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed         1  avgt    5     22.843 ±    0.512  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed        10  avgt    5     47.116 ±    3.077  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed        50  avgt    5     85.664 ±    0.620  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed       100  avgt    5    127.982 ±    0.941  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed       500  avgt    5    444.965 ±    2.526  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed      1000  avgt    5    857.990 ±   32.996  ns/op
Base64JniBenchmarks.jdk_url_encode                                  fixed     10000  avgt    5   9276.002 ±   99.343  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random         1  avgt    5     22.839 ±    0.706  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random        10  avgt    5     47.379 ±    0.777  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random        50  avgt    5     85.892 ±    4.135  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random       100  avgt    5    129.444 ±    6.738  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random       500  avgt    5    459.354 ±   10.968  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random      1000  avgt    5    857.044 ±    6.097  ns/op
Base64JniBenchmarks.jdk_url_encode                                 random     10000  avgt    5   9443.560 ± 1187.494  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed         1  avgt    5   1421.875 ±   39.718  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed        10  avgt    5   1396.002 ±   13.412  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed        50  avgt    5   1472.937 ±   25.846  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed       100  avgt    5   1611.511 ±   21.845  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed       500  avgt    5   2105.442 ±   71.710  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed      1000  avgt    5   2824.589 ±   27.786  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                           fixed     10000  avgt    5  13724.368 ±  890.567  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random         1  avgt    5   1418.234 ±   34.442  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random        10  avgt    5   1389.047 ±   21.503  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random        50  avgt    5   1505.353 ±  183.373  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random       100  avgt    5   1628.331 ±  149.699  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random       500  avgt    5   2128.075 ±   99.343  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random      1000  avgt    5   2853.394 ±  166.892  ns/op
Base64JniBenchmarks.jni_url_decodeConfig1                          random     10000  avgt    5  14013.628 ± 1611.898  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed         1  avgt    5   1253.755 ±  290.319  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed        10  avgt    5   1193.484 ±   46.208  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed        50  avgt    5   1260.209 ±   18.992  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed       100  avgt    5   1344.659 ±   28.179  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed       500  avgt    5   1850.107 ±   13.161  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed      1000  avgt    5   2657.447 ±   77.988  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                           fixed     10000  avgt    5  13533.866 ±  767.366  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random         1  avgt    5   1205.521 ±   32.960  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random        10  avgt    5   1188.488 ±    4.636  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random        50  avgt    5   1264.892 ±   11.228  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random       100  avgt    5   1331.542 ±   16.905  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random       500  avgt    5   1874.473 ±   21.669  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random      1000  avgt    5   2696.291 ±  117.142  ns/op
Base64JniBenchmarks.jni_url_decodeConfig2                          random     10000  avgt    5  13623.628 ± 1608.825  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed         1  avgt    5   1059.634 ±   12.712  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed        10  avgt    5   1052.568 ±   29.976  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed        50  avgt    5   1116.458 ±    4.285  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed       100  avgt    5   1188.923 ±   14.547  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed       500  avgt    5   1675.275 ±    9.949  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed      1000  avgt    5   2496.050 ±   18.643  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                           fixed     10000  avgt    5  13338.995 ±  860.659  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random         1  avgt    5   1059.527 ±   41.617  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random        10  avgt    5   1054.953 ±   83.941  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random        50  avgt    5   1128.353 ±   52.339  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random       100  avgt    5   1192.774 ±   25.857  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random       500  avgt    5   1697.966 ±  112.376  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random      1000  avgt    5   2500.232 ±   33.652  ns/op
Base64JniBenchmarks.jni_url_decodeConfig3                          random     10000  avgt    5  13368.857 ± 1262.066  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed         1  avgt    5   1012.215 ±   16.398  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed        10  avgt    5    990.682 ±    7.950  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed        50  avgt    5   1060.932 ±   11.684  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed       100  avgt    5   1131.172 ±    7.090  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed       500  avgt    5   1698.261 ±  282.791  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed      1000  avgt    5   2365.657 ±  109.854  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                           fixed     10000  avgt    5  13031.697 ± 1253.273  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random         1  avgt    5   1009.492 ±   17.554  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random        10  avgt    5   1001.844 ±   61.261  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random        50  avgt    5   1062.375 ±   51.272  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random       100  avgt    5   1130.821 ±   14.735  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random       500  avgt    5   1641.359 ±    8.949  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random      1000  avgt    5   2352.098 ±   40.612  ns/op
Base64JniBenchmarks.jni_url_decodeConfig4                          random     10000  avgt    5  13143.063 ± 1585.115  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed         1  avgt    5    995.373 ±   51.423  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed        10  avgt    5   1026.048 ±   15.892  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed        50  avgt    5   1107.265 ±   19.155  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed       100  avgt    5   1187.484 ±  135.172  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed       500  avgt    5   1668.824 ±   15.066  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed      1000  avgt    5   2348.692 ±   23.261  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                      fixed     10000  avgt    5  13699.250 ± 3337.114  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random         1  avgt    5   1087.488 ±  128.735  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random        10  avgt    5   1026.457 ±    8.804  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random        50  avgt    5   1108.032 ±    8.825  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random       100  avgt    5   1175.510 ±   13.135  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random       500  avgt    5   1667.006 ±   21.102  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random      1000  avgt    5   2339.747 ±  128.093  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1                     random     10000  avgt    5  13289.636 ±  198.718  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed         1  avgt    5    503.199 ±    4.200  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed        10  avgt    5    540.904 ±   32.037  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed        50  avgt    5    621.282 ±    4.169  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed       100  avgt    5    682.536 ±    8.625  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed       500  avgt    5   1175.699 ±   15.802  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed      1000  avgt    5   1866.021 ±   33.344  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed     10000  avgt    5  12654.853 ±  102.459  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random         1  avgt    5    503.138 ±    9.511  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random        10  avgt    5    544.367 ±   59.478  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random        50  avgt    5    627.325 ±   53.690  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random       100  avgt    5    685.232 ±   44.284  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random       500  avgt    5   1182.331 ±   24.468  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random      1000  avgt    5   1851.292 ±    5.834  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice1NoCache              random     10000  avgt    5  13064.211 ± 2097.880  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed         1  avgt    5    419.059 ±   32.052  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed        10  avgt    5    447.861 ±    4.437  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed        50  avgt    5    532.235 ±   15.577  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed       100  avgt    5    594.093 ±   46.193  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed       500  avgt    5   1070.889 ±    2.989  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed      1000  avgt    5   1764.648 ±   27.727  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed     10000  avgt    5  12654.136 ±  519.542  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random         1  avgt    5    418.031 ±   28.064  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random        10  avgt    5    448.225 ±    2.392  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random        50  avgt    5    528.539 ±    5.096  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random       100  avgt    5    597.270 ±    9.588  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random       500  avgt    5   1086.774 ±    7.463  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random      1000  avgt    5   1774.061 ±   30.810  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice2Cache                random     10000  avgt    5  12615.198 ±  153.717  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed         1  avgt    5     64.207 ±    1.804  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed        10  avgt    5     89.330 ±    0.296  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed        50  avgt    5    140.521 ±    4.930  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed       100  avgt    5    189.808 ±    7.059  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed       500  avgt    5    644.794 ±   39.852  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed      1000  avgt    5   1253.939 ±   14.479  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed     10000  avgt    5  11871.277 ±   81.139  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random         1  avgt    5     63.756 ±    0.996  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random        10  avgt    5     90.838 ±    3.691  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random        50  avgt    5    140.953 ±    4.015  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random       100  avgt    5    185.279 ±   13.958  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random       500  avgt    5    639.550 ±   24.446  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random      1000  avgt    5   1216.757 ±    5.321  ns/op
Base64JniBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput     random     10000  avgt    5  11897.309 ±  781.233  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed         1  avgt    5   1359.067 ±   38.890  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed        10  avgt    5   1401.468 ±   86.601  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed        50  avgt    5   1428.156 ±   14.849  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed       100  avgt    5   1583.153 ±   28.322  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed       500  avgt    5   2113.880 ±   41.033  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed      1000  avgt    5   2626.132 ±   35.315  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                            fixed     10000  avgt    5  13249.959 ±  379.802  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random         1  avgt    5   1366.436 ±   49.298  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random        10  avgt    5   1383.326 ±   28.968  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random        50  avgt    5   1425.227 ±    5.153  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random       100  avgt    5   1570.870 ±  109.613  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random       500  avgt    5   2054.975 ±   60.892  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random      1000  avgt    5   2625.729 ±   24.356  ns/op
Base64JniBenchmarks.jni_url_encodeConfig                           random     10000  avgt    5  13666.988 ± 2062.082  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed         1  avgt    5    398.113 ±   32.902  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed        10  avgt    5    421.606 ±   31.638  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed        50  avgt    5    461.226 ±    5.038  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed       100  avgt    5    526.172 ±   60.066  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed       500  avgt    5    949.099 ±    8.025  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed      1000  avgt    5   1465.699 ±  114.398  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed     10000  avgt    5  11697.531 ± 1780.788  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random         1  avgt    5    397.034 ±   14.834  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random        10  avgt    5    416.790 ±    6.985  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random        50  avgt    5    457.117 ±    7.441  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random       100  avgt    5    514.788 ±    2.560  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random       500  avgt    5    953.100 ±    4.886  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random      1000  avgt    5   1459.090 ±   11.537  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1Cache                random     10000  avgt    5  11470.392 ±  827.680  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed         1  avgt    5    479.459 ±    3.753  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed        10  avgt    5    505.040 ±    3.459  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed        50  avgt    5    542.925 ±    9.180  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed       100  avgt    5    604.470 ±    3.370  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed       500  avgt    5   1038.954 ±   34.210  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed      1000  avgt    5   1653.958 ±   83.549  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed     10000  avgt    5  11525.113 ±   49.852  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random         1  avgt    5    480.595 ±    3.552  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random        10  avgt    5    504.470 ±   19.311  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random        50  avgt    5    554.621 ±   46.806  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random       100  avgt    5    602.531 ±    2.405  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random       500  avgt    5   1040.586 ±  106.667  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random      1000  avgt    5   1683.423 ±   23.241  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice1NoCache              random     10000  avgt    5  11626.737 ±  370.674  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed         1  avgt    5     55.103 ±    0.996  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed        10  avgt    5     68.855 ±   10.460  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed        50  avgt    5    111.852 ±   10.872  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed       100  avgt    5    154.389 ±    1.802  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed       500  avgt    5    567.249 ±   65.807  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed      1000  avgt    5   1081.305 ±   10.426  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed     10000  avgt    5  10719.796 ±   70.856  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random         1  avgt    5     52.798 ±    0.659  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random        10  avgt    5     68.121 ±    0.601  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random        50  avgt    5    109.556 ±    7.808  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random       100  avgt    5    158.841 ±   13.866  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random       500  avgt    5    562.155 ±   21.524  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random      1000  avgt    5   1094.168 ±   44.838  ns/op
Base64JniBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput     random     10000  avgt    5  10966.633 ± 1632.895  ns/op
</code>
 */
@Fork(value = 1, jvmArgs = Array("-Xmx2G", "-Djava.library.path=bazel-bin/rs/base64:rs/base64"))
class Base64JniBenchmarks extends Base64BenchmarkBase {
  @Benchmark
  def jdk_url_decode: Array[Byte] = Jdk.decodeUrlSafe(urlEncoded)

  @Benchmark
  def jdk_url_encode: Array[Byte] = Jdk.encodeUrlSafe(data)

  @Benchmark
  def jni_url_encodeConfig: Array[Byte] = Native.encodeConfigUrlSafe(data)

  @Benchmark
  def jni_url_encodeConfigSlice1NoCache: Array[Byte] = Native.encodeConfigSlice1NoCache(data)

  @Benchmark
  def jni_url_encodeConfigSlice1Cache: Array[Byte] = Native.encodeConfigSlice1Cache(data)

  @Benchmark
  def jni_url_encodeConfigSlice2CacheInputOutput: Array[Byte] = Native.encodeConfigSlice2CacheInputOutput(data)

  @Benchmark
  def jni_url_decodeConfig1: Array[Byte] = Native.decodeConfigUrlSafe1(urlEncoded)

  @Benchmark
  def jni_url_decodeConfig2: Array[Byte] = Native.decodeConfigUrlSafe2(urlEncoded)

  @Benchmark
  def jni_url_decodeConfig3: Array[Byte] = Native.decodeConfigUrlSafe3(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfig4: Array[Byte] = Native.decodeConfigUrlSafe4(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfigSlice1: Array[Byte] = Native.decodeConfigSliceUrlSafe1(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfigSlice1NoCache: Array[Byte] = Native.decodeConfigSliceUrlSafe2NoCache(urlEncoded)

  @Benchmark
  def jni_url_decodeConfigSlice2Cache: Array[Byte] = Native.decodeConfigSliceUrlSafe2Cache(urlEncoded)

  @Benchmark
  def jni_url_decodeConfigSlice3CacheInputOutput: Array[Byte] = Native.decodeConfigSliceUrlSafe3CacheInputOutput(urlEncoded)
}
