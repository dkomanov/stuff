package com.komanov.readlines.jmh

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

import com.komanov.readlines.{ReadUtf8Java, ReadUtf8Scala}
import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(1)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 7, time = 5, timeUnit = TimeUnit.SECONDS)
class ReadUtf8Benchmark {

  @Param(Array("0", "1", "2", "5", "10", "100", "10000"))
  var lineLength: Int = _
  @Param
  var inputType: InputType = _

  private var bytes: Array[Byte] = _

  @Setup
  def setup(): Unit = {
    val codePoints = inputType.getCodePoints(lineLength)
    bytes = new String(codePoints, 0, codePoints.length).getBytes(StandardCharsets.UTF_8)
  }

  @Benchmark
  def charset(): String = {
    StandardCharsets.UTF_8.decode(ByteBuffer.wrap(bytes)).toString
  }

  @Benchmark
  def localMethodsScala(): String = {
    ReadUtf8Scala.localMethods(bytes)
  }

  @Benchmark
  def localMethodsIndices(): String = {
    ReadUtf8Scala.localMethodsIndices(bytes)
  }

  @Benchmark
  def sequentialLoopScala(): String = {
    ReadUtf8Scala.sequentialLoop(bytes)
  }

  @Benchmark
  def changeIndexInsideLoopScala(): String = {
    ReadUtf8Scala.changeIndexInsideLoop(bytes)
  }

  @Benchmark
  def changeIndexInsideLoopByteMagicScala(): String = {
    ReadUtf8Scala.changeIndexInsideLoopByteMagic(bytes)
  }

  @Benchmark
  def sequentialLoopJava(): String = {
    ReadUtf8Java.sequentialLoop(bytes)
  }

  @Benchmark
  def changeIndexInsideLoopJava(): String = {
    ReadUtf8Java.changeIndexInsideLoop(bytes)
  }

  @Benchmark
  def changeIndexInsideLoopByteMagicJava(): String = {
    ReadUtf8Java.changeIndexInsideLoopByteMagic(bytes)
  }
}
