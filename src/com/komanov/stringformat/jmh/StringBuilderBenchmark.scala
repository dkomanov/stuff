package com.komanov.stringformat.jmh

import org.openjdk.jmh.annotations.Benchmark

class StringBuilderBenchmark extends BenchmarkBase {

  @Benchmark
  def javaStringBuilder: String = {
    new java.lang.StringBuilder()
      .append("abc")
      .append("def")
      .toString
  }

  @Benchmark
  def javaStringBuilder2: String = {
    new java.lang.StringBuilder()
      .append("string______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________495")
      .append("string______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________495")
      .toString
  }

  @Benchmark
  def scalaStringBuilder: String = {
    new scala.collection.mutable.StringBuilder()
      .append("abc")
      .append("def")
      .toString
  }

  @Benchmark
  def scalaStringBuilder2: String = {
    new scala.collection.mutable.StringBuilder()
      .append("string______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________495")
      .append("string______________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________495")
      .toString
  }

}
