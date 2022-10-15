package com.komanov.jwt.base64.jmh

import com.komanov.jwt.base64.Base64Helper.{Commons, Jdk}
import org.openjdk.jmh.annotations._

/*
Results for openjdk-17:
<code>
Benchmark                          (dataset)  (length)  Mode  Cnt       Score      Error  Units
Base64Benchmarks.commonsDecode         fixed         1  avgt    5     992.125 ±  152.544  ns/op
Base64Benchmarks.commonsDecode         fixed        10  avgt    5    1064.405 ±   21.666  ns/op
Base64Benchmarks.commonsDecode         fixed        50  avgt    5    1483.646 ±   18.861  ns/op
Base64Benchmarks.commonsDecode         fixed       100  avgt    5    2034.970 ±   98.026  ns/op
Base64Benchmarks.commonsDecode         fixed       500  avgt    5    6566.262 ±  149.445  ns/op
Base64Benchmarks.commonsDecode         fixed      1000  avgt    5   11223.624 ±  400.052  ns/op
Base64Benchmarks.commonsDecode         fixed     10000  avgt    5   91408.153 ± 9128.746  ns/op
Base64Benchmarks.commonsDecode        random         1  avgt    5     981.295 ±   51.524  ns/op
Base64Benchmarks.commonsDecode        random        10  avgt    5    1067.720 ±   36.760  ns/op
Base64Benchmarks.commonsDecode        random        50  avgt    5    1487.465 ±   50.480  ns/op
Base64Benchmarks.commonsDecode        random       100  avgt    5    2062.426 ±  215.140  ns/op
Base64Benchmarks.commonsDecode        random       500  avgt    5    6644.135 ±  569.380  ns/op
Base64Benchmarks.commonsDecode        random      1000  avgt    5   11335.406 ± 1325.977  ns/op
Base64Benchmarks.commonsDecode        random     10000  avgt    5   94387.711 ±  563.326  ns/op
Base64Benchmarks.commonsEncode         fixed         1  avgt    5     968.670 ±   10.895  ns/op
Base64Benchmarks.commonsEncode         fixed        10  avgt    5    1000.739 ±   91.939  ns/op
Base64Benchmarks.commonsEncode         fixed        50  avgt    5    1322.488 ±    9.205  ns/op
Base64Benchmarks.commonsEncode         fixed       100  avgt    5    1805.992 ±   59.718  ns/op
Base64Benchmarks.commonsEncode         fixed       500  avgt    5    5717.774 ±   24.084  ns/op
Base64Benchmarks.commonsEncode         fixed      1000  avgt    5   11006.586 ±  972.550  ns/op
Base64Benchmarks.commonsEncode         fixed     10000  avgt    5   96677.707 ± 1211.912  ns/op
Base64Benchmarks.commonsEncode        random         1  avgt    5     971.163 ±   16.320  ns/op
Base64Benchmarks.commonsEncode        random        10  avgt    5     993.013 ±   18.646  ns/op
Base64Benchmarks.commonsEncode        random        50  avgt    5    1322.270 ±   35.981  ns/op
Base64Benchmarks.commonsEncode        random       100  avgt    5    1808.391 ±   66.187  ns/op
Base64Benchmarks.commonsEncode        random       500  avgt    5    5713.528 ±   41.206  ns/op
Base64Benchmarks.commonsEncode        random      1000  avgt    5   10813.070 ±  174.312  ns/op
Base64Benchmarks.commonsEncode        random     10000  avgt    5   98747.228 ± 1938.771  ns/op
Base64Benchmarks.commonsUrlDecode      fixed         1  avgt    5     969.446 ±    9.556  ns/op
Base64Benchmarks.commonsUrlDecode      fixed        10  avgt    5    1076.757 ±   58.182  ns/op
Base64Benchmarks.commonsUrlDecode      fixed        50  avgt    5    1553.080 ±   67.219  ns/op
Base64Benchmarks.commonsUrlDecode      fixed       100  avgt    5    2163.966 ±  246.457  ns/op
Base64Benchmarks.commonsUrlDecode      fixed       500  avgt    5    7068.632 ±  146.355  ns/op
Base64Benchmarks.commonsUrlDecode      fixed      1000  avgt    5   13848.420 ±  203.347  ns/op
Base64Benchmarks.commonsUrlDecode      fixed     10000  avgt    5   88657.131 ±  623.392  ns/op
Base64Benchmarks.commonsUrlDecode     random         1  avgt    5     970.079 ±   13.132  ns/op
Base64Benchmarks.commonsUrlDecode     random        10  avgt    5    1087.345 ±  113.021  ns/op
Base64Benchmarks.commonsUrlDecode     random        50  avgt    5    1546.197 ±   10.195  ns/op
Base64Benchmarks.commonsUrlDecode     random       100  avgt    5    2129.720 ±   24.387  ns/op
Base64Benchmarks.commonsUrlDecode     random       500  avgt    5    7234.292 ±   91.263  ns/op
Base64Benchmarks.commonsUrlDecode     random      1000  avgt    5   10279.399 ±  361.241  ns/op
Base64Benchmarks.commonsUrlDecode     random     10000  avgt    5   90083.696 ±  915.879  ns/op
Base64Benchmarks.commonsUrlEncode      fixed         1  avgt    5    1009.368 ±  205.687  ns/op
Base64Benchmarks.commonsUrlEncode      fixed        10  avgt    5     998.256 ±   42.311  ns/op
Base64Benchmarks.commonsUrlEncode      fixed        50  avgt    5    1334.147 ±   41.011  ns/op
Base64Benchmarks.commonsUrlEncode      fixed       100  avgt    5    1787.294 ±   71.825  ns/op
Base64Benchmarks.commonsUrlEncode      fixed       500  avgt    5    5684.001 ±  124.165  ns/op
Base64Benchmarks.commonsUrlEncode      fixed      1000  avgt    5   10939.912 ±  930.778  ns/op
Base64Benchmarks.commonsUrlEncode      fixed     10000  avgt    5  100431.500 ±  510.665  ns/op
Base64Benchmarks.commonsUrlEncode     random         1  avgt    5     968.008 ±    8.852  ns/op
Base64Benchmarks.commonsUrlEncode     random        10  avgt    5    1001.467 ±   10.849  ns/op
Base64Benchmarks.commonsUrlEncode     random        50  avgt    5    1351.891 ±  204.886  ns/op
Base64Benchmarks.commonsUrlEncode     random       100  avgt    5    1800.703 ±   81.127  ns/op
Base64Benchmarks.commonsUrlEncode     random       500  avgt    5    5788.779 ±  194.531  ns/op
Base64Benchmarks.commonsUrlEncode     random      1000  avgt    5   10840.678 ±  219.667  ns/op
Base64Benchmarks.commonsUrlEncode     random     10000  avgt    5  100258.096 ± 3312.008  ns/op
Base64Benchmarks.jdkDecode             fixed         1  avgt    5      34.998 ±    1.966  ns/op
Base64Benchmarks.jdkDecode             fixed        10  avgt    5      55.487 ±    0.377  ns/op
Base64Benchmarks.jdkDecode             fixed        50  avgt    5     133.106 ±   10.535  ns/op
Base64Benchmarks.jdkDecode             fixed       100  avgt    5     227.782 ±   14.273  ns/op
Base64Benchmarks.jdkDecode             fixed       500  avgt    5     893.495 ±   26.597  ns/op
Base64Benchmarks.jdkDecode             fixed      1000  avgt    5    1735.138 ±   14.209  ns/op
Base64Benchmarks.jdkDecode             fixed     10000  avgt    5   19194.075 ±   66.180  ns/op
Base64Benchmarks.jdkDecode            random         1  avgt    5      34.713 ±    0.228  ns/op
Base64Benchmarks.jdkDecode            random        10  avgt    5      57.374 ±    0.321  ns/op
Base64Benchmarks.jdkDecode            random        50  avgt    5     134.459 ±   13.576  ns/op
Base64Benchmarks.jdkDecode            random       100  avgt    5     227.978 ±    3.917  ns/op
Base64Benchmarks.jdkDecode            random       500  avgt    5     886.597 ±    5.536  ns/op
Base64Benchmarks.jdkDecode            random      1000  avgt    5    1729.704 ±   11.489  ns/op
Base64Benchmarks.jdkDecode            random     10000  avgt    5   17952.483 ±  807.170  ns/op
Base64Benchmarks.jdkEncode             fixed         1  avgt    5      21.941 ±    0.717  ns/op
Base64Benchmarks.jdkEncode             fixed        10  avgt    5      47.128 ±    0.178  ns/op
Base64Benchmarks.jdkEncode             fixed        50  avgt    5      83.983 ±    0.465  ns/op
Base64Benchmarks.jdkEncode             fixed       100  avgt    5     127.708 ±    7.983  ns/op
Base64Benchmarks.jdkEncode             fixed       500  avgt    5     462.496 ±   20.617  ns/op
Base64Benchmarks.jdkEncode             fixed      1000  avgt    5     872.161 ±    4.980  ns/op
Base64Benchmarks.jdkEncode             fixed     10000  avgt    5    9242.790 ±   56.612  ns/op
Base64Benchmarks.jdkEncode            random         1  avgt    5      22.156 ±    0.954  ns/op
Base64Benchmarks.jdkEncode            random        10  avgt    5      47.882 ±    3.297  ns/op
Base64Benchmarks.jdkEncode            random        50  avgt    5      85.530 ±    3.805  ns/op
Base64Benchmarks.jdkEncode            random       100  avgt    5     127.578 ±    1.765  ns/op
Base64Benchmarks.jdkEncode            random       500  avgt    5     452.962 ±   27.169  ns/op
Base64Benchmarks.jdkEncode            random      1000  avgt    5     873.836 ±   86.206  ns/op
Base64Benchmarks.jdkEncode            random     10000  avgt    5    9264.634 ±   62.622  ns/op
Base64Benchmarks.jdkUrlDecode          fixed         1  avgt    5      32.255 ±    0.224  ns/op
Base64Benchmarks.jdkUrlDecode          fixed        10  avgt    5      53.543 ±    0.750  ns/op
Base64Benchmarks.jdkUrlDecode          fixed        50  avgt    5     128.896 ±    1.437  ns/op
Base64Benchmarks.jdkUrlDecode          fixed       100  avgt    5     231.162 ±    1.275  ns/op
Base64Benchmarks.jdkUrlDecode          fixed       500  avgt    5     977.207 ±   31.177  ns/op
Base64Benchmarks.jdkUrlDecode          fixed      1000  avgt    5    1930.927 ±  114.872  ns/op
Base64Benchmarks.jdkUrlDecode          fixed     10000  avgt    5   19471.978 ±  258.044  ns/op
Base64Benchmarks.jdkUrlDecode         random         1  avgt    5      32.931 ±    1.427  ns/op
Base64Benchmarks.jdkUrlDecode         random        10  avgt    5      52.863 ±    1.306  ns/op
Base64Benchmarks.jdkUrlDecode         random        50  avgt    5     133.709 ±    2.893  ns/op
Base64Benchmarks.jdkUrlDecode         random       100  avgt    5     231.141 ±    1.301  ns/op
Base64Benchmarks.jdkUrlDecode         random       500  avgt    5     968.502 ±    5.006  ns/op
Base64Benchmarks.jdkUrlDecode         random      1000  avgt    5    1906.757 ±   17.683  ns/op
Base64Benchmarks.jdkUrlDecode         random     10000  avgt    5   19625.219 ±  429.358  ns/op
Base64Benchmarks.jdkUrlEncode          fixed         1  avgt    5      23.102 ±    2.271  ns/op
Base64Benchmarks.jdkUrlEncode          fixed        10  avgt    5      46.687 ±    0.971  ns/op
Base64Benchmarks.jdkUrlEncode          fixed        50  avgt    5      86.036 ±    2.410  ns/op
Base64Benchmarks.jdkUrlEncode          fixed       100  avgt    5     128.104 ±    0.624  ns/op
Base64Benchmarks.jdkUrlEncode          fixed       500  avgt    5     446.870 ±    3.196  ns/op
Base64Benchmarks.jdkUrlEncode          fixed      1000  avgt    5     872.363 ±   62.503  ns/op
Base64Benchmarks.jdkUrlEncode          fixed     10000  avgt    5    9246.288 ±   52.836  ns/op
Base64Benchmarks.jdkUrlEncode         random         1  avgt    5      24.556 ±    4.122  ns/op
Base64Benchmarks.jdkUrlEncode         random        10  avgt    5      46.690 ±    0.152  ns/op
Base64Benchmarks.jdkUrlEncode         random        50  avgt    5      85.574 ±    0.345  ns/op
Base64Benchmarks.jdkUrlEncode         random       100  avgt    5     127.489 ±    0.303  ns/op
Base64Benchmarks.jdkUrlEncode         random       500  avgt    5     450.609 ±    8.328  ns/op
Base64Benchmarks.jdkUrlEncode         random      1000  avgt    5     869.775 ±    7.331  ns/op
Base64Benchmarks.jdkUrlEncode         random     10000  avgt    5    9305.067 ±  334.893  ns/op
</code>
 */
class Base64Benchmarks extends Base64BenchmarkBase {
  @Benchmark
  def jdkDecode: Array[Byte] = Jdk.decode(encoded)

  @Benchmark
  def commonsDecode: Array[Byte] = Commons.decode(encoded)

  @Benchmark
  def jdkUrlDecode: Array[Byte] = Jdk.decodeUrlSafe(urlEncoded)

  @Benchmark
  def commonsUrlDecode: Array[Byte] = Commons.decodeUrlSafe(urlEncoded)

  @Benchmark
  def jdkEncode: Array[Byte] = Jdk.encode(data)

  @Benchmark
  def commonsEncode: Array[Byte] = Commons.encode(data)

  @Benchmark
  def jdkUrlEncode: Array[Byte] = Jdk.encodeUrlSafe(data)

  @Benchmark
  def commonsUrlEncode: Array[Byte] = Commons.encodeUrlSafe(data)
}
