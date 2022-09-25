package com.komanov.serialization.jmh

import com.komanov.serialization.converters.api.{EventConverter, SiteConverter}
import com.komanov.serialization.domain.{EventProcessor, Site, SiteEvent}
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"), jvmArgsAppend = Array("-Dfile.encoding=UTF-8"))
@Threads(2)
@Measurement(iterations = 5, time = 3, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
abstract class BenchmarkBase

@State(Scope.Thread) // Kryo modifies bytes during parsing, see: https://github.com/EsotericSoftware/kryo#threading
class SiteBenchmark extends BenchmarkBase {

  @Param
  var inputType: InputType = _
  @Param
  var converterType: ConverterType = _

  private var converter: SiteConverter = _
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

@State(Scope.Thread) // Kryo modifies bytes during parsing, see: https://github.com/EsotericSoftware/kryo#threading
class EventBenchmark extends BenchmarkBase {

  @Param
  var inputType: InputType = _
  @Param
  var converterType: ConverterType = _

  private var converter: EventConverter = _
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
