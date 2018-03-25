package com.komanov.stringformat.jmh

import org.openjdk.jmh.annotations.Benchmark

class EmptyStringBenchmark extends BenchmarkBase {

  @Benchmark
  def baseline: String = {
    ""
  }

  @Benchmark
  def sInterpolator: String = {
    s""
  }

  @Benchmark
  def sfiInterpolator: String = {
    import com.komanov.stringformat.macros.MacroConcat._
    sfi""
  }
}

class ConstStringBenchmark extends BenchmarkBase {

  @Benchmark
  def baseline: String = {
    "abc"
  }

  @Benchmark
  def sInterpolator: String = {
    s"abc"
  }

  @Benchmark
  def sfiInterpolator: String = {
    import com.komanov.stringformat.macros.MacroConcat._
    sfi"abc"
  }
}
