package com.komanov.jwt.base64.jmh

import com.komanov.jwt.base64.Base64Helper.{Commons, Jdk}
import org.openjdk.jmh.annotations._

import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit
import scala.util.Random

/*
Results for openjdk-17:
<code>
Benchmark                          (dataset)  (length)  Mode  Cnt      Score       Error  Units
Base64Benchmarks.commonsDecode         fixed         1  avgt    3    978.934 ±   313.328  ns/op
Base64Benchmarks.commonsDecode         fixed        10  avgt    3   1057.615 ±    67.266  ns/op
Base64Benchmarks.commonsDecode         fixed        50  avgt    3   1492.180 ±   240.236  ns/op
Base64Benchmarks.commonsDecode         fixed       100  avgt    3   2045.546 ±   526.068  ns/op
Base64Benchmarks.commonsDecode         fixed       500  avgt    3   6640.114 ±  2219.096  ns/op
Base64Benchmarks.commonsDecode         fixed      1000  avgt    3  10961.130 ±    79.807  ns/op
Base64Benchmarks.commonsDecode         fixed     10000  avgt    3  89607.511 ± 13781.714  ns/op
Base64Benchmarks.commonsDecode        random         1  avgt    3    964.943 ±    30.717  ns/op
Base64Benchmarks.commonsDecode        random        10  avgt    3   1087.339 ±   602.954  ns/op
Base64Benchmarks.commonsDecode        random        50  avgt    3   1494.096 ±   399.370  ns/op
Base64Benchmarks.commonsDecode        random       100  avgt    3   2028.193 ±   333.284  ns/op
Base64Benchmarks.commonsDecode        random       500  avgt    3   6668.709 ±   634.802  ns/op
Base64Benchmarks.commonsDecode        random      1000  avgt    3  12463.031 ±  1075.591  ns/op
Base64Benchmarks.commonsDecode        random     10000  avgt    3  92630.561 ±  3603.710  ns/op
Base64Benchmarks.commonsEncode         fixed         1  avgt    3    973.535 ±   109.890  ns/op
Base64Benchmarks.commonsEncode         fixed        10  avgt    3    998.414 ±     8.990  ns/op
Base64Benchmarks.commonsEncode         fixed        50  avgt    3   1327.219 ±   299.718  ns/op
Base64Benchmarks.commonsEncode         fixed       100  avgt    3   1806.755 ±    16.189  ns/op
Base64Benchmarks.commonsEncode         fixed       500  avgt    3   5791.087 ±   684.923  ns/op
Base64Benchmarks.commonsEncode         fixed      1000  avgt    3  10803.237 ±   838.647  ns/op
Base64Benchmarks.commonsEncode         fixed     10000  avgt    3  97172.447 ±  3301.054  ns/op
Base64Benchmarks.commonsEncode        random         1  avgt    3    978.251 ±   190.750  ns/op
Base64Benchmarks.commonsEncode        random        10  avgt    3   1004.887 ±   239.611  ns/op
Base64Benchmarks.commonsEncode        random        50  avgt    3   1345.973 ±   261.147  ns/op
Base64Benchmarks.commonsEncode        random       100  avgt    3   1830.668 ±   218.258  ns/op
Base64Benchmarks.commonsEncode        random       500  avgt    3   5757.550 ±    50.332  ns/op
Base64Benchmarks.commonsEncode        random      1000  avgt    3  10966.530 ±  1841.185  ns/op
Base64Benchmarks.commonsEncode        random     10000  avgt    3  98104.504 ±  8566.876  ns/op
Base64Benchmarks.commonsUrlDecode      fixed         1  avgt    3    991.996 ±   536.078  ns/op
Base64Benchmarks.commonsUrlDecode      fixed        10  avgt    3   1077.480 ±   266.924  ns/op
Base64Benchmarks.commonsUrlDecode      fixed        50  avgt    3   1556.015 ±    12.292  ns/op
Base64Benchmarks.commonsUrlDecode      fixed       100  avgt    3   2152.856 ±   277.949  ns/op
Base64Benchmarks.commonsUrlDecode      fixed       500  avgt    3   7088.494 ±  1317.984  ns/op
Base64Benchmarks.commonsUrlDecode      fixed      1000  avgt    3  12935.699 ±   578.558  ns/op
Base64Benchmarks.commonsUrlDecode      fixed     10000  avgt    3  91976.960 ±  5141.394  ns/op
Base64Benchmarks.commonsUrlDecode     random         1  avgt    3    981.807 ±   130.381  ns/op
Base64Benchmarks.commonsUrlDecode     random        10  avgt    3   1098.697 ±   267.844  ns/op
Base64Benchmarks.commonsUrlDecode     random        50  avgt    3   1579.089 ±   318.105  ns/op
Base64Benchmarks.commonsUrlDecode     random       100  avgt    3   2154.481 ±   236.007  ns/op
Base64Benchmarks.commonsUrlDecode     random       500  avgt    3   7092.108 ±  1055.517  ns/op
Base64Benchmarks.commonsUrlDecode     random      1000  avgt    3  13772.693 ±    67.219  ns/op
Base64Benchmarks.commonsUrlDecode     random     10000  avgt    3  90815.948 ± 10652.350  ns/op
Base64Benchmarks.commonsUrlEncode      fixed         1  avgt    3    981.966 ±   134.758  ns/op
Base64Benchmarks.commonsUrlEncode      fixed        10  avgt    3   1008.847 ±    47.086  ns/op
Base64Benchmarks.commonsUrlEncode      fixed        50  avgt    3   1343.024 ±   222.449  ns/op
Base64Benchmarks.commonsUrlEncode      fixed       100  avgt    3   1809.845 ±   199.436  ns/op
Base64Benchmarks.commonsUrlEncode      fixed       500  avgt    3   5723.678 ±   427.664  ns/op
Base64Benchmarks.commonsUrlEncode      fixed      1000  avgt    3  10820.286 ±   622.582  ns/op
Base64Benchmarks.commonsUrlEncode      fixed     10000  avgt    3  96314.825 ±  9837.053  ns/op
Base64Benchmarks.commonsUrlEncode     random         1  avgt    3    977.591 ±   145.373  ns/op
Base64Benchmarks.commonsUrlEncode     random        10  avgt    3   1018.482 ±   347.236  ns/op
Base64Benchmarks.commonsUrlEncode     random        50  avgt    3   1339.435 ±   150.309  ns/op
Base64Benchmarks.commonsUrlEncode     random       100  avgt    3   1788.095 ±   334.793  ns/op
Base64Benchmarks.commonsUrlEncode     random       500  avgt    3   5745.912 ±   155.836  ns/op
Base64Benchmarks.commonsUrlEncode     random      1000  avgt    3  10872.286 ±   275.042  ns/op
Base64Benchmarks.commonsUrlEncode     random     10000  avgt    3  98647.122 ±  1482.012  ns/op
Base64Benchmarks.jdkDecode             fixed         1  avgt    3     35.170 ±     4.035  ns/op
Base64Benchmarks.jdkDecode             fixed        10  avgt    3     57.867 ±     7.547  ns/op
Base64Benchmarks.jdkDecode             fixed        50  avgt    3    131.725 ±     0.788  ns/op
Base64Benchmarks.jdkDecode             fixed       100  avgt    3    240.372 ±     5.957  ns/op
Base64Benchmarks.jdkDecode             fixed       500  avgt    3    972.937 ±    45.950  ns/op
Base64Benchmarks.jdkDecode             fixed      1000  avgt    3   1907.088 ±    40.050  ns/op
Base64Benchmarks.jdkDecode             fixed     10000  avgt    3  19132.958 ±   312.438  ns/op
Base64Benchmarks.jdkDecode            random         1  avgt    3     34.677 ±     0.660  ns/op
Base64Benchmarks.jdkDecode            random        10  avgt    3     59.096 ±     4.014  ns/op
Base64Benchmarks.jdkDecode            random        50  avgt    3    133.703 ±     0.989  ns/op
Base64Benchmarks.jdkDecode            random       100  avgt    3    227.291 ±     9.311  ns/op
Base64Benchmarks.jdkDecode            random       500  avgt    3    969.010 ±    32.302  ns/op
Base64Benchmarks.jdkDecode            random      1000  avgt    3   1908.534 ±   122.507  ns/op
Base64Benchmarks.jdkDecode            random     10000  avgt    3  19147.666 ±   236.876  ns/op
Base64Benchmarks.jdkEncode             fixed         1  avgt    3     21.882 ±     1.762  ns/op
Base64Benchmarks.jdkEncode             fixed        10  avgt    3     47.210 ±    10.754  ns/op
Base64Benchmarks.jdkEncode             fixed        50  avgt    3     85.381 ±    23.494  ns/op
Base64Benchmarks.jdkEncode             fixed       100  avgt    3    127.237 ±    12.903  ns/op
Base64Benchmarks.jdkEncode             fixed       500  avgt    3    459.061 ±    14.026  ns/op
Base64Benchmarks.jdkEncode             fixed      1000  avgt    3    882.443 ±   232.177  ns/op
Base64Benchmarks.jdkEncode             fixed     10000  avgt    3   9229.578 ±    59.660  ns/op
Base64Benchmarks.jdkEncode            random         1  avgt    3     22.075 ±     2.810  ns/op
Base64Benchmarks.jdkEncode            random        10  avgt    3     47.050 ±     1.231  ns/op
Base64Benchmarks.jdkEncode            random        50  avgt    3     84.576 ±    14.436  ns/op
Base64Benchmarks.jdkEncode            random       100  avgt    3    126.819 ±    12.870  ns/op
Base64Benchmarks.jdkEncode            random       500  avgt    3    468.435 ±   124.760  ns/op
Base64Benchmarks.jdkEncode            random      1000  avgt    3    898.655 ±   136.733  ns/op
Base64Benchmarks.jdkEncode            random     10000  avgt    3   9334.366 ±  3075.175  ns/op
Base64Benchmarks.jdkUrlDecode          fixed         1  avgt    3     32.116 ±     0.846  ns/op
Base64Benchmarks.jdkUrlDecode          fixed        10  avgt    3     54.811 ±     3.124  ns/op
Base64Benchmarks.jdkUrlDecode          fixed        50  avgt    3    134.292 ±    44.607  ns/op
Base64Benchmarks.jdkUrlDecode          fixed       100  avgt    3    232.542 ±    44.941  ns/op
Base64Benchmarks.jdkUrlDecode          fixed       500  avgt    3    968.003 ±     6.432  ns/op
Base64Benchmarks.jdkUrlDecode          fixed      1000  avgt    3   1915.903 ±   285.786  ns/op
Base64Benchmarks.jdkUrlDecode          fixed     10000  avgt    3  19360.464 ±   134.194  ns/op
Base64Benchmarks.jdkUrlDecode         random         1  avgt    3     32.358 ±     3.920  ns/op
Base64Benchmarks.jdkUrlDecode         random        10  avgt    3     52.513 ±     0.934  ns/op
Base64Benchmarks.jdkUrlDecode         random        50  avgt    3    133.631 ±    29.536  ns/op
Base64Benchmarks.jdkUrlDecode         random       100  avgt    3    233.093 ±     6.634  ns/op
Base64Benchmarks.jdkUrlDecode         random       500  avgt    3    970.021 ±    60.315  ns/op
Base64Benchmarks.jdkUrlDecode         random      1000  avgt    3   1908.279 ±    55.604  ns/op
Base64Benchmarks.jdkUrlDecode         random     10000  avgt    3  19401.101 ±  1152.412  ns/op
Base64Benchmarks.jdkUrlEncode          fixed         1  avgt    3     23.274 ±     6.096  ns/op
Base64Benchmarks.jdkUrlEncode          fixed        10  avgt    3     46.698 ±     2.679  ns/op
Base64Benchmarks.jdkUrlEncode          fixed        50  avgt    3     85.156 ±     1.406  ns/op
Base64Benchmarks.jdkUrlEncode          fixed       100  avgt    3    127.485 ±     0.723  ns/op
Base64Benchmarks.jdkUrlEncode          fixed       500  avgt    3    438.861 ±     2.262  ns/op
Base64Benchmarks.jdkUrlEncode          fixed      1000  avgt    3    862.860 ±   168.223  ns/op
Base64Benchmarks.jdkUrlEncode          fixed     10000  avgt    3   9255.009 ±   671.454  ns/op
Base64Benchmarks.jdkUrlEncode         random         1  avgt    3     22.743 ±     0.075  ns/op
Base64Benchmarks.jdkUrlEncode         random        10  avgt    3     47.154 ±     2.415  ns/op
Base64Benchmarks.jdkUrlEncode         random        50  avgt    3     86.349 ±    27.077  ns/op
Base64Benchmarks.jdkUrlEncode         random       100  avgt    3    128.167 ±    12.101  ns/op
Base64Benchmarks.jdkUrlEncode         random       500  avgt    3    445.131 ±    79.709  ns/op
Base64Benchmarks.jdkUrlEncode         random      1000  avgt    3    862.629 ±    68.835  ns/op
Base64Benchmarks.jdkUrlEncode         random     10000  avgt    3   9226.402 ±   339.902  ns/op
</code>
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
class Base64Benchmarks {

  @Param(Array("1", "10", "50", "100", "500", "1000", "10000"))
  var length: Int = 0
  @Param(Array("fixed", "random"))
  var dataset: String = _

  private var data: Array[Byte] = _
  private var encoded: Array[Byte] = _
  private var urlEncoded: Array[Byte] = _

  @Setup
  def setup(): Unit = {
    data = (if (dataset == "fixed") Base64Benchmarks.fixedValues(length) else Random.alphanumeric.take(length).mkString)
      .getBytes(StandardCharsets.US_ASCII)
    encoded = Jdk.encode(data)
    urlEncoded = Jdk.encodeUrlSafe(data)
  }

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

private object Base64Benchmarks {
  // Just randomly generated fixed values to avoid variations.
  val fixedValues: Map[Int, String] = Map(
    1 -> "q",
    10 -> "e97l7OjMlL",
    50 -> "XnebL7KZGlbduMmUODknrcn7CpPg6lCOOH95rwExw71PQPkQ0X",
    100 -> "arfFSxgxGIwSLA7kpUErlSl7jvaX3n17n2VhnrzwoeC9I8crQXHE5PMhi8tIhOkwYDHOz8wRoRJcrz1AdaWn6prTueIYNjXxVYvt",
    500 -> "bUPjmL6g0INlVCrqGThUJ9Kichn14tg9bWeVq0t91CWZG9Q2mG2qwD0ZpBOWsn0eKanONGrYU6grO23H5f0L22GkBuGoXYEzp6C7LrzWQ1nBDKnoFxo60SxdSdjqVhv4Zt1FYfw3OCRPptSWvoG6j1DiJLtrpOnPdEJ7byVcsT3WV9xpHlMpUFkixYRLPdLQNcmtWOCpZfPOFcbAEyShOqE0AKtcB4LvZkquH9IHvss95xJLri8XDIhkSd7DYlZ00nB72V0ePl3ieZs2tHyBxTyQmpPgoSokTs13C17BWabqklwvrcsklCH1SZb3zlxMNBVZ9ZgJ3WlJ7A0qjSFHINlccIqnT6CHSsvMtMPkGaTlm6R3R0to0h7eUxYb9NgbP2F3DqzjEpGUNpLzE7a3K0CheerbONwWh879xSnWMyS1S1HNJGXW7JtbGbJ5Lsn5qXTlr86PsgNdY5zqjqJukOiPYX44MdOr7hWl4xLdP2iYLP2sT6uN",
    1000 -> "d6kcqqCIX4KA3BI6gSl3afzEN0QDdp5lY8W9yDioGcrypdfMprUex8B67W5Y2EsorireaLgOK1kSbTnTiGdahmHpPuAvtiVQ9YwXQym0aAYDy8AqDdxTbKJXPcXBc8HlQiDQNwGhLwq9JsvPWko59L3ii81zzV4Se4dABWAAPNz3uPAD7r6qMmEOACSLTRZbAAfMOx7pgBXgLCvzFYwMHNYQhK2SixD0E2YVV6FaYUXmX6a5AxIcxx5bz6BoK7F4KNNWnSY2heDTyypXAW9gyxRdBgW5j2k3xUa50AgdwbRaoz2Re0mjjKYmz6x2LQCjrx86pF5o7H3Gv5EOIvWQ36yXdkH4uoGoqV07PBPdIIflpjNX0GcDeCmCai348ReU5ATvDUJq602JmxPAPleVvoM12GAmFOIbyPm3Shno2cQDduQNLPxOXzKWnBcSHeqPVSVOaCdv4lU6nXe44OcYQ8eTQFs4sKFZ4iHeFeOUPDlHseg49QZTVOgenfNi0zhqyJLNUNdUErlEn6aYER5nxuXjATykK0l3DrGBSwYbsxFXCpmcRaaIyEZcS06r4omj7CfqG1A9lXOUSZKEpncj3Ii7DACB4YPzxQ1oHJRQpQLJALk34bQPJ5AyTAd7xXZ9nxUEdpHdwqvEmsG0gCNWk395ilxIQrWYHfrUDGNZQ6VyLrdVEEBKTmnLTdv8D31CCMJNvXxksgw5otPSse0BZVxcw8EiRpEJS6hkGbMCtQsokNetK065FSVAXMcADw94ltKbDLLt9A8ke86VuJiMnIHMDzQEI0zBWPC1YofaaMBxOn5bz5Dv6JcpsPWi3KALdn45FX1YmEz7cJYFbmdHfvXjNYmYmA3zOhl3mzBh5ylh3jxXQjUUGXSqqthIlabaLMENpToHgPJgoQAAtIdm8xkMLNs3BdOD9u6mMrMJnX0xYaXKsYyzrLXY4gTcbcs9ZeiMHqXc4xMCmwdXBLungETdtfSECKoyMWoye6vE",
    10000 -> "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",
  )
}
