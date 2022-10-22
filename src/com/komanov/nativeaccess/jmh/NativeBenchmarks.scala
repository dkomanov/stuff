package com.komanov.nativeaccess.jmh

import com.komanov.nativeaccess._
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Results for openjdk-17:
<code>
Benchmark                    Mode  Cnt     Score      Error  Units
NativeBenchmarks.bridj       avgt    5  2675.218 ± 1670.966  ns/op
NativeBenchmarks.javaCpp     avgt    5   798.700 ±   13.667  ns/op
NativeBenchmarks.javaMxBean  avgt    5  1963.496 ±  230.775  ns/op
NativeBenchmarks.jna         avgt    5  2653.977 ±   75.761  ns/op
NativeBenchmarks.jnaDirect   avgt    5  1068.356 ±   53.749  ns/op
NativeBenchmarks.jni         avgt    5   834.159 ±   40.013  ns/op
NativeBenchmarks.jniEmpty    avgt    5    22.249 ±    0.395  ns/op
NativeBenchmarks.jnr         avgt    5   826.690 ±   27.833  ns/op
</code>
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G", "-Djava.library.path=bazel-bin/rs/getloadavg:rs/getloadavg"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
class NativeBenchmarks {
  @Benchmark
  def bridj: Double = BridjHelper.getLoadAverage

  @Benchmark
  def javaCpp: Double = JavaCppHelper.getLoadAverage

  @Benchmark
  def javaMxBean: Double = PureJavaHelper.getLoadAverage

  @Benchmark
  def jna: Double = JnaHelper.getLoadAverage

  @Benchmark
  def jnaDirect: Double = JnaHelper.getLoadAverageDirect

  @Benchmark
  def jni: Double = JniHelper.getLoadAverage

  @Benchmark
  def jniEmpty: Int = JniHelper.emptyJniCall(1)

  @Benchmark
  def jnr: Double = JnrHelper.getLoadAverage
}
