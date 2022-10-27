package com.komanov.jwt.base64.jni.jmh

import com.komanov.jwt.base64.jmh.Base64BenchmarkBase
import com.komanov.jwt.base64.jni.{DirectBuffer, NativeHelper}
import org.openjdk.jmh.annotations._

/*
Results for openjdk-17:
<code>
Benchmark                                       (dataset)  (length)  Mode  Cnt     Score     Error  Units
DirectBufferBenchmarks.from_decoded_to_encoded      fixed         1  avgt    5    22.500 ±   1.815  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded      fixed        10  avgt    5    23.615 ±   1.135  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded      fixed        50  avgt    5    27.520 ±   3.064  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded      fixed       100  avgt    5    31.529 ±   0.616  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded      fixed       500  avgt    5    93.917 ±  12.695  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded      fixed      1000  avgt    5   226.875 ± 131.001  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded      fixed     10000  avgt    5  2017.573 ± 100.586  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded     random         1  avgt    5    22.198 ±   0.313  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded     random        10  avgt    5    22.322 ±   0.412  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded     random        50  avgt    5    27.383 ±   1.200  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded     random       100  avgt    5    32.057 ±   3.017  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded     random       500  avgt    5    91.840 ±   7.396  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded     random      1000  avgt    5   200.071 ±   6.938  ns/op
DirectBufferBenchmarks.from_decoded_to_encoded     random     10000  avgt    5  2081.984 ± 263.843  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded      fixed         1  avgt    5    21.984 ±   0.209  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded      fixed        10  avgt    5    22.449 ±   2.086  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded      fixed        50  avgt    5    28.633 ±   0.609  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded      fixed       100  avgt    5    29.547 ±   3.860  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded      fixed       500  avgt    5    78.169 ±   5.227  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded      fixed      1000  avgt    5   153.605 ±  12.674  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded      fixed     10000  avgt    5  1664.824 ±  39.950  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded     random         1  avgt    5    22.153 ±   0.668  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded     random        10  avgt    5    22.269 ±   1.058  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded     random        50  avgt    5    29.047 ±   0.420  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded     random       100  avgt    5    29.352 ±   1.571  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded     random       500  avgt    5    79.049 ±   4.154  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded     random      1000  avgt    5   157.047 ±  17.761  ns/op
DirectBufferBenchmarks.from_encoded_to_decoded     random     10000  avgt    5  1709.716 ± 201.076  ns/op
</code>
 */
@State(Scope.Thread)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
class DirectBufferBenchmarks extends Base64BenchmarkBase {

  private var encodedAddress: Long = 0
  private var encodedSize: Int = 0
  private var decodedAddress: Long = 0
  private var decodedSize: Int = 0

  @Setup
  def init(): Unit = {
    encodedSize = encoded.length
    encodedAddress = DirectBuffer.getInputBuffer(encodedSize)
    NativeHelper.copyFromByteArray(encodedAddress, encoded, encodedSize)

    decodedSize = data.length
    decodedAddress = DirectBuffer.getOutputBuffer(decodedSize)
    NativeHelper.copyFromByteArray(decodedAddress, data, decodedSize)
  }

  @Benchmark
  def from_decoded_to_encoded: Array[Byte] = {
    NativeHelper.copyFromByteArray(decodedAddress, data, decodedSize)
    NativeHelper.toByteArray(encodedAddress, encodedSize)
  }

  @Benchmark
  def from_encoded_to_decoded: Array[Byte] = {
    NativeHelper.copyFromByteArray(encodedAddress, encoded, encodedSize)
    NativeHelper.toByteArray(decodedAddress, decodedSize)
  }
}
