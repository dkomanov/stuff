package com.komanov.stringformat.jmh

import com.komanov.stringformat.FastStringFactory
import org.openjdk.jmh.annotations.Benchmark

class StringBuilderNewStringBenchmark extends BenchmarkBase {

  @Benchmark
  def notOptimal: String = {
    new java.lang.StringBuilder(33)
      .append("0123456789")
      .append(1)
      .append("0123456789")
      .append(1)
      .append("0123456789")
      .append(1)
      .toString
  }

  @Benchmark
  def optimal: String = {
    val sb = new java.lang.StringBuilder(33)
      .append("0123456789")
      .append(1)
      .append("0123456789")
      .append(1)
      .append("0123456789")
      .append(1)
    FastStringFactory.fastNewString(sb)
  }

}
