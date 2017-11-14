package com.komanov.serialization.jmh

import java.util.concurrent.TimeUnit

import com.komanov.serialization.converters._
import com.komanov.serialization.domain.{EventProcessor, Site, SiteEvent}
import org.openjdk.jmh.annotations._

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
abstract class BenchmarkBase

@State(Scope.Benchmark)
class SiteBenchmark extends BenchmarkBase {

  @Param
  var inputType: InputType = _
  @Param
  var converterType: ConverterType = _

  private var converter: MyConverter = _
  private var input: Site = _
  private var output: Array[Byte] = _

  @Setup
  def setup(): Unit = {
    converter = converterType.converter
    input = inputType.site
    output = converter.toByteArray(input)
  }

  @Benchmark
  def serialization(): Array[Byte] = {
    converter.toByteArray(input)
  }

  @Benchmark
  def deserialization(): Site = {
    converter.fromByteArray(output)
  }

  @Benchmark
  def both(): Site = {
    converter.fromByteArray(converter.toByteArray(inputType.site))
  }
}

@State(Scope.Benchmark)
class EventBenchmark extends BenchmarkBase {

  @Param
  var inputType: InputType = _
  @Param
  var converterType: ConverterType = _

  private var converter: MyConverter = _
  private var input: Seq[SiteEvent] = _
  private var output: Seq[(Class[_], Array[Byte])] = _

  @Setup
  def setup(): Unit = {
    converter = converterType.converter
    input = EventProcessor.unapply(inputType.site).map(_.event)
    output = EventProcessor.unapply(inputType.site).map(_.event)
      .map(e => e.getClass -> converter.toByteArray(e))
  }

  @Benchmark
  def serialization(): Any = {
    for (e <- input) {
      converter.toByteArray(e)
    }
  }

  @Benchmark
  def deserialization(): Any = {
    for ((t, bytes) <- output) {
      converter.siteEventFromByteArray(t, bytes)
    }
  }

  @Benchmark
  def both(): Any = {
    for (e <- input) {
      converter.siteEventFromByteArray(e.getClass, converter.toByteArray(e))
    }
  }
}
