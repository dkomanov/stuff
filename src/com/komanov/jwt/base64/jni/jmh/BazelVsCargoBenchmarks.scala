package com.komanov.jwt.base64.jni.jmh

import com.komanov.jwt.base64.jmh.Base64BenchmarkBase
import com.komanov.jwt.base64.jni.{Native, NativeCargo}
import org.openjdk.jmh.annotations._

/*
Results for openjdk-17:
<code>
Benchmark                                                          (dataset)  (length)  (lib)  Mode  Cnt       Score       Error  Units
BazelVsCargoBenchmarks.jni_url_decodeConfig1                           fixed     10000  BAZEL  avgt    5   13706.165 ±  6953.567  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfig1                           fixed     10000  CARGO  avgt    5   14079.295 ±  8994.308  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfig2                           fixed     10000  BAZEL  avgt    5   12739.203 ±   631.726  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfig2                           fixed     10000  CARGO  avgt    5   13153.104 ±  4228.683  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfig3                           fixed     10000  BAZEL  avgt    5   13914.896 ±  6535.043  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfig3                           fixed     10000  CARGO  avgt    5   12924.066 ±  2918.356  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfig4                           fixed     10000  BAZEL  avgt    5   13363.667 ± 10594.819  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfig4                           fixed     10000  CARGO  avgt    5   12091.834 ±   811.907  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfigSlice1                      fixed     10000  BAZEL  avgt    5   12528.092 ±  1412.882  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfigSlice1                      fixed     10000  CARGO  avgt    5   12318.424 ±   335.858  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed     10000  BAZEL  avgt    5   12497.226 ±   477.062  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfigSlice1NoCache               fixed     10000  CARGO  avgt    5   12286.717 ±   110.787  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed     10000  BAZEL  avgt    5   13409.079 ±  4408.745  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfigSlice2Cache                 fixed     10000  CARGO  avgt    5   12523.818 ±  1700.040  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed     10000  BAZEL  avgt    5   11995.509 ±   325.439  ns/op
BazelVsCargoBenchmarks.jni_url_decodeConfigSlice3CacheInputOutput      fixed     10000  CARGO  avgt    5   12064.699 ±   670.621  ns/op
BazelVsCargoBenchmarks.jni_url_decodeSimd                              fixed     10000  BAZEL  avgt    5  415488.110 ± 44594.204  ns/op
BazelVsCargoBenchmarks.jni_url_decodeSimd                              fixed     10000  CARGO  avgt    5    2996.776 ±    83.875  ns/op
BazelVsCargoBenchmarks.jni_url_decodeSimdInPlace                       fixed     10000  BAZEL  avgt    5  402227.590 ± 13706.905  ns/op
BazelVsCargoBenchmarks.jni_url_decodeSimdInPlace                       fixed     10000  CARGO  avgt    5    3056.402 ±  1267.584  ns/op
BazelVsCargoBenchmarks.jni_url_encodeConfig                            fixed     10000  BAZEL  avgt    5   12432.810 ±   651.918  ns/op
BazelVsCargoBenchmarks.jni_url_encodeConfig                            fixed     10000  CARGO  avgt    5   13312.855 ±  6948.412  ns/op
BazelVsCargoBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed     10000  BAZEL  avgt    5   11959.335 ±  4298.597  ns/op
BazelVsCargoBenchmarks.jni_url_encodeConfigSlice1Cache                 fixed     10000  CARGO  avgt    5   11094.204 ±   153.502  ns/op
BazelVsCargoBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed     10000  BAZEL  avgt    5   11368.845 ±   384.400  ns/op
BazelVsCargoBenchmarks.jni_url_encodeConfigSlice1NoCache               fixed     10000  CARGO  avgt    5   11362.071 ±   741.456  ns/op
BazelVsCargoBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed     10000  BAZEL  avgt    5   10930.500 ±   103.521  ns/op
BazelVsCargoBenchmarks.jni_url_encodeConfigSlice2CacheInputOutput      fixed     10000  CARGO  avgt    5   10939.165 ±   170.889  ns/op
BazelVsCargoBenchmarks.jni_url_encodeSimd                              fixed     10000  BAZEL  avgt    5  437293.580 ±  7689.046  ns/op
BazelVsCargoBenchmarks.jni_url_encodeSimd                              fixed     10000  CARGO  avgt    5    3402.558 ±   250.556  ns/op
</code>
 */
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
class BazelVsCargoBenchmarks extends Base64BenchmarkBase {

  @Param
  var lib: NativeLib = _

  private var provider: Native = _

  @Setup
  def init(): Unit = {
    NativeCargo.INSTANCE.toString
    provider = lib.getNative
  }

  @Benchmark
  def jni_url_encodeConfig: Array[Byte] = provider.encodeConfigUrlSafe(data)

  @Benchmark
  def jni_url_encodeConfigSlice1NoCache: Array[Byte] = provider.encodeConfigSlice1NoCache(data)

  @Benchmark
  def jni_url_encodeConfigSlice1Cache: Array[Byte] = provider.encodeConfigSlice1Cache(data)

  @Benchmark
  def jni_url_encodeConfigSlice2CacheInputOutput: Array[Byte] = provider.encodeConfigSlice2CacheInputOutput(data)

  @Benchmark
  def jni_url_decodeConfig1: Array[Byte] = provider.decodeConfigUrlSafe1(urlEncoded)

  @Benchmark
  def jni_url_decodeConfig2: Array[Byte] = provider.decodeConfigUrlSafe2(urlEncoded)

  @Benchmark
  def jni_url_decodeConfig3: Array[Byte] = provider.decodeConfigUrlSafe3(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfig4: Array[Byte] = provider.decodeConfigUrlSafe4(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfigSlice1: Array[Byte] = provider.decodeConfigSliceUrlSafe1(urlEncoded, urlEncoded.length)

  @Benchmark
  def jni_url_decodeConfigSlice1NoCache: Array[Byte] = provider.decodeConfigSliceUrlSafe2NoCache(urlEncoded)

  @Benchmark
  def jni_url_decodeConfigSlice2Cache: Array[Byte] = provider.decodeConfigSliceUrlSafe2Cache(urlEncoded)

  @Benchmark
  def jni_url_decodeConfigSlice3CacheInputOutput: Array[Byte] = provider.decodeConfigSliceUrlSafe3CacheInputOutput(urlEncoded)

  @Benchmark
  def jni_url_encodeSimd: Array[Byte] = provider.encodeSimd(data)

  @Benchmark
  def jni_url_decodeSimd: Array[Byte] = provider.decodeSimd(urlEncoded)

  @Benchmark
  def jni_url_decodeSimdInPlace: Array[Byte] = provider.decodeSimdInPlace(urlEncoded)
}
