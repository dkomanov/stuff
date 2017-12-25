package com.komanov.readlines.jmh

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

import com.komanov.readlines.{ReadLinesJavaStreams, ReadLinesUtils}
import org.openjdk.jmh.annotations._
import org.openjdk.jmh.infra.Blackhole

import scala.collection.JavaConverters._
import scala.util.Random

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(1)
@Measurement(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 10, timeUnit = TimeUnit.SECONDS)
class ReadLinesBenchmark {

  @Param(Array("1000"))
  var lineCount: Int = _
  @Param(Array("50"))
  var cpuTokens: Long = _

  private var path: Path = _
  private val lineLengths = List(0, 20, 50, 80, 120, 200, 300)

  @Setup
  def setup(): Unit = {
    path = Files.createTempFile(Paths.get("/tmp"), s"read-lines-$lineCount", "")
    Files.write(
      path,
      (1 to lineCount)
        .map(i => Random.alphanumeric.take(lineLengths(i % lineLengths.length)).mkString)
        .asJava,
      StandardCharsets.UTF_8
    )
  }

  @TearDown
  def cleanup(): Unit = {
    Files.deleteIfExists(path)
  }

  @Benchmark
  def baseline(): Unit = {
    for (_ <- 1 to lineCount) {
      processLine("")
    }
  }

  @Benchmark
  def filesReadLines(): Unit = {
    processLines(ReadLinesUtils.nioFiles(path))
  }

  @Benchmark
  def filesReadBytes(): Unit = {
    processLines(ReadLinesUtils.readBytesFirst(path))
  }

  @Benchmark
  def filesReadBytes2(): Unit = {
    processLines(ReadLinesUtils.readBytesFirstCustom(path))
  }

  @Benchmark
  def adHoc(): Unit = {
    ReadLinesUtils.adHoc(path, processLine)
  }

  @Benchmark
  def forEachInline(): Unit = {
    val stream = Files.newBufferedReader(path, StandardCharsets.UTF_8)
    var line: String = stream.readLine()
    while (line != null) {
      processLine(line)
      line = stream.readLine()
    }
    stream.close()
  }

  @Benchmark
  def forEachIndirect(): Unit = {
    ReadLinesUtils.forEachLine(path, processLine)
  }

  @Benchmark
  def forEachJava(): Unit = {
    ReadLinesJavaStreams.forEachJava(path, new Consumer[String] {
      override def accept(t: String): Unit = processLine(t)
    })
  }

  private def processLines(lines: java.util.List[String]): Unit = {
    val it = lines.iterator()
    while (it.hasNext) {
      processLine(it.next())
    }
  }

  private def processLine(line: String): Unit = {
    Blackhole.consumeCPU(cpuTokens)
  }
}
