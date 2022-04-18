package com.komanov.stringformat.jmh

import com.komanov.stringformat.FastStringFactory
import org.openjdk.jmh.annotations.Benchmark

object NewStringBenchmarkData {
  val chars = new Array[Char](1006)
  val sb = new java.lang.StringBuilder(chars.length)
    .append(chars)
  val (bytes, coder) = (sb.toString.getBytes, 0.toByte)
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
    FastStringFactory.fastNewString(NewStringBenchmarkData.bytes, NewStringBenchmarkData.coder)
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
