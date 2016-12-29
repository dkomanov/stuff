package com.komanov.stringformat.jmh

import java.util.concurrent.TimeUnit

import com.komanov.stringformat.{InputArg, JavaFormats, ScalaFormats}
import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 2, jvmArgs = Array("-Xmx2G"))
@Measurement(iterations = 7, time = 3, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
abstract class BenchmarkBase

class ManyParamsBenchmark extends BenchmarkBase {

  @Param
  var arg: InputArg = InputArg.Tiny

  var nullObject: Object = null

  @Benchmark
  def javaConcat(): String = {
    JavaFormats.concat(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def scalaConcat(): String = {
    ScalaFormats.concat(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def stringFormat(): String = {
    JavaFormats.stringFormat(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def messageFormat(): String = {
    JavaFormats.messageFormat(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def slf4j(): String = {
    JavaFormats.slf4j(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def concatOptimized1(): String = {
    ScalaFormats.optimizedConcat1(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def concatOptimized2(): String = {
    ScalaFormats.optimizedConcat2(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def concatOptimizedMacros(): String = {
    ScalaFormats.optimizedConcatMacros(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def sInterpolator(): String = {
    ScalaFormats.sInterpolator(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def fInterpolator(): String = {
    ScalaFormats.fInterpolator(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def rawInterpolator(): String = {
    ScalaFormats.rawInterpolator(arg.value1, arg.value2, nullObject)
  }

  @Benchmark
  def sfiInterpolator(): String = {
    ScalaFormats.sfiInterpolator(arg.value1, arg.value2, nullObject)
  }

}
