package com.komanov.nativeaccess.jmh

import com.komanov.nativeaccess._
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Results for openjdk-17:
<code>
Benchmark                    Mode  Cnt     Score      Error  Units
NativeBenchmarks.bridj       avgt    5  2426.297 ± 1066.761  ns/op
NativeBenchmarks.javaCpp     avgt    5   741.889 ±   19.378  ns/op
NativeBenchmarks.javaMxBean  avgt    5  1833.716 ±   40.433  ns/op
NativeBenchmarks.jna         avgt    5  2611.944 ±  241.992  ns/op
NativeBenchmarks.jnaDirect   avgt    5   994.327 ±   51.901  ns/op
NativeBenchmarks.jni         avgt    5   763.334 ±   26.880  ns/op
NativeBenchmarks.jniEmpty    avgt    5    22.771 ±    1.305  ns/op
NativeBenchmarks.jnr         avgt    5   777.021 ±   23.250  ns/op
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
