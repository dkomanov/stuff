package com.komanov.nativeaccess.jmh

import com.komanov.nativeaccess._
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Results for openjdk-17:
<code>
Benchmark                    Mode  Cnt     Score      Error  Units
NativeBenchmarks.bridj       avgt    5  2540.730 ± 1705.722  ns/op
NativeBenchmarks.javaCpp     avgt    5   769.480 ±   20.045  ns/op
NativeBenchmarks.javaMxBean  avgt    5  1893.771 ±   34.289  ns/op
NativeBenchmarks.jna         avgt    5  2601.693 ±   38.039  ns/op
NativeBenchmarks.jnaDirect   avgt    5  1026.631 ±   56.638  ns/op
NativeBenchmarks.jni         avgt    5   798.595 ±   36.098  ns/op
NativeBenchmarks.jnr         avgt    5   815.336 ±    1.678  ns/op
</code>
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
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
  def jnr: Double = JnrHelper.getLoadAverage
}
