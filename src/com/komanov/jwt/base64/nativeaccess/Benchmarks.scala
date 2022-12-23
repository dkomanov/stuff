package com.komanov.jwt.base64.nativeaccess

import com.komanov.jwt.base64.Base64Helper.Jdk
import com.komanov.jwt.base64.jmh.Base64BenchmarkBase
import org.openjdk.jmh.annotations._

@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
class JdkBenchmark extends Base64BenchmarkBase {
  @Benchmark
  def jdk_url_decode: Array[Byte] = Jdk.decodeUrlSafe(urlEncoded)

  @Benchmark
  def jdk_url_encode: Array[Byte] = Jdk.encodeUrlSafe(data)
}

@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
class NativeBenchmarks extends Base64BenchmarkBase {
  @Param(Array("jni", "nalim", "panama-jdk17"))
  var accessor: String = _

  private var _accessor: NativeAccessor = _

  @Setup
  def init(): Unit = {
    _accessor = accessor match {
//      case "jni" =>
//      case "nalim" =>
      case "panama-jdk17" => PanamaJdk17NativeAccessor.INSTANCE
    }
  }

  @TearDown
  def destroy(): Unit = {
    _accessor.close()
  }

  @Benchmark
  def base64_encode: Array[Byte] = _accessor.base64_encode(data)

  @Benchmark
  def base64_decode: Array[Byte] = _accessor.base64_decode(urlEncoded)

  @Benchmark
  def simd_encode: Array[Byte] = _accessor.simd_encode(data)

  @Benchmark
  def simd_decode: Array[Byte] = _accessor.simd_decode(urlEncoded)
}
