package com.komanov.nativeaccess.jmh

import com.komanov.nativeaccess._
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

/*
Results for openjdk-17:
<code>
Benchmark                      Mode  Cnt     Score      Error  Units
NativeBenchmarks.bridj         avgt    5  2682.311 ± 1938.336  ns/op
NativeBenchmarks.javaCpp       avgt    5   805.790 ±   19.053  ns/op
NativeBenchmarks.javaMxBean    avgt    5  1864.793 ±   15.666  ns/op
NativeBenchmarks.jna           avgt    5  2657.248 ±   50.673  ns/op
NativeBenchmarks.jnaDirect     avgt    5  1048.608 ±   59.695  ns/op
NativeBenchmarks.jni           avgt    5   809.720 ±   56.060  ns/op
NativeBenchmarks.jniEmpty      avgt    5    22.255 ±    0.220  ns/op
NativeBenchmarks.jnr           avgt    5   827.565 ±   51.496  ns/op
NativeBenchmarks.nalimDirect   avgt    5   703.535 ±   54.530  ns/op
NativeBenchmarks.nalimWrapper  avgt    5   716.342 ±   17.746  ns/op
</code>
 */
@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(
  value = 1,
  jvmArgs = Array("-Xmx2G", "-Djava.library.path=bazel-bin/rs/getloadavg:rs/getloadavg"),
  jvmArgsAppend = Array(
    "-XX:+UnlockExperimentalVMOptions",
    "-XX:+EnableJVMCI",
    "--add-exports", "jdk.internal.vm.ci/jdk.vm.ci.code=ALL-UNNAMED",
    "--add-exports", "jdk.internal.vm.ci/jdk.vm.ci.code.site=ALL-UNNAMED",
    "--add-exports", "jdk.internal.vm.ci/jdk.vm.ci.hotspot=ALL-UNNAMED",
    "--add-exports", "jdk.internal.vm.ci/jdk.vm.ci.meta=ALL-UNNAMED",
    "--add-exports", "jdk.internal.vm.ci/jdk.vm.ci.runtime=ALL-UNNAMED",
  ),
)
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

  @Benchmark
  def nalimDirect: Double = NalimHelper.getLoadAverageDirect

  @Benchmark
  def nalimWrapper: Double = NalimHelper.getLoadAverageViaWrapper
}
