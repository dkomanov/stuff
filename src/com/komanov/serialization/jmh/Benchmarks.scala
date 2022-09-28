package com.komanov.serialization.jmh

import com.komanov.serialization.converters.ScalaPbConverter
import com.komanov.serialization.converters.api.{EventConverter, SiteConverter}
import com.komanov.serialization.domain.{EventProcessor, Site, SiteEvent}
import org.openjdk.jmh.annotations._

import java.util.concurrent.TimeUnit

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"), jvmArgsAppend = Array("-Dfile.encoding=UTF-8"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)
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

@State(Scope.Benchmark)
class SiteRawScalaPbBenchmark extends BenchmarkBase {

  import com.komanov.serialization.domain.protos.site._

  @Param
  var inputType: InputType = _
  @Param(Array("SCALA_PB_DIRECT"))
  var converterType: String = _

  private var input: SitePb = _
  private var output: Array[Byte] = _

  @Setup
  def setup(): Unit = {
    input = ScalaPbConverter.toProto(inputType.site)
    output = input.toByteArray
  }

  @Benchmark
  def serialization: Array[Byte] = input.toByteArray

  @Benchmark
  def deserialization: SitePb = SitePb.parseFrom(output)

  @Benchmark
  def both: SitePb = SitePb.parseFrom(input.toByteArray)
}

@State(Scope.Benchmark)
class EventRawScalaPbBenchmark extends BenchmarkBase {

  import com.komanov.serialization.domain.protos.events._

  @Param
  var inputType: InputType = _
  @Param(Array("SCALA_PB_DIRECT"))
  var converterType: String = _

  private var input: Seq[SiteEventPb] = _
  private var output: Seq[Array[Byte]] = _

  @Setup
  def setup(): Unit = {
    input = EventProcessor.unapply(inputType.site).map(_.event).map(ScalaPbConverter.toProto)
    output = input.map(_.toByteArray)
  }

  @Benchmark
  def serialization(): Any = {
    for (e <- input) {
      e.toByteArray
    }
  }

  @Benchmark
  def deserialization(): Any = {
    for (bytes <- output) {
      SiteEventPb.parseFrom(bytes)
    }
  }

  @Benchmark
  def both(): Any = {
    for (e <- input) {
      SiteEventPb.parseFrom(e.toByteArray)
    }
  }
}
