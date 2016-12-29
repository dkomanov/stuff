package com.komanov.stringformat.jmh

import com.komanov.stringformat.FastStringFactory
import org.openjdk.jmh.annotations.Benchmark

object NewStringBenchmarkData {
  val chars = new Array[Char](1006)
  val sb = new java.lang.StringBuilder(chars.length)
    .append(chars)
}

class NewStringBenchmark extends BenchmarkBase {

  @Benchmark
  def baseline: String = {
    ""
  }

  @Benchmark
  def newString: String = {
    new String(NewStringBenchmarkData.chars)
  }

  @Benchmark
  def fastString: String = {
    FastStringFactory.fastNewString(NewStringBenchmarkData.chars)
  }

  @Benchmark
  def sbToString: String = {
    NewStringBenchmarkData.sb.toString
  }

  @Benchmark
  def fastSb: String = {
    FastStringFactory.fastNewString(NewStringBenchmarkData.sb)
  }

}
