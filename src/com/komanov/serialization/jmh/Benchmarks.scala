package com.komanov.serialization.jmh

import java.util.concurrent.TimeUnit

import com.komanov.serialization.converters._
import com.komanov.serialization.domain.{EventProcessor, Site, SiteEvent}
import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
abstract class BenchmarkBase(converter: MyConverter) {

  private val site1k = TestData.site1k
  private val site2k = TestData.site2k
  private val site4k = TestData.site4k
  private val site8k = TestData.site8k
  private val site64k = TestData.site64k

  private val site1kBytes = converter.toByteArray(site1k)
  private val site2kBytes = converter.toByteArray(site2k)
  private val site4kBytes = converter.toByteArray(site4k)
  private val site8kBytes = converter.toByteArray(site8k)
  private val site64kBytes = converter.toByteArray(site64k)

  private val events1kInput = createEventsInput(site1k)
  private val events2kInput = createEventsInput(site2k)
  private val events4kInput = createEventsInput(site4k)
  private val events8kInput = createEventsInput(site8k)
  private val events64kInput = createEventsInput(site64k)

  private val events1kOutput = createEventsOutput(site1k)
  private val events2kOutput = createEventsOutput(site2k)
  private val events4kOutput = createEventsOutput(site4k)
  private val events8kOutput = createEventsOutput(site8k)
  private val events64kOutput = createEventsOutput(site64k)

  @Benchmark
  def serialization_site_1k(): Array[Byte] = {
    converter.toByteArray(site1k)
  }

  @Benchmark
  def serialization_site_2k(): Array[Byte] = {
    converter.toByteArray(site2k)
  }

  @Benchmark
  def serialization_site_4k(): Array[Byte] = {
    converter.toByteArray(site4k)
  }

  @Benchmark
  def serialization_site_8k(): Array[Byte] = {
    converter.toByteArray(site8k)
  }

  @Benchmark
  def serialization_site_64k(): Array[Byte] = {
    converter.toByteArray(site64k)
  }

  @Benchmark
  def deserialization_site_1k(): Site = {
    converter.fromByteArray(site1kBytes)
  }

  @Benchmark
  def deserialization_site_2k(): Site = {
    converter.fromByteArray(site2kBytes)
  }

  @Benchmark
  def deserialization_site_4k(): Site = {
    converter.fromByteArray(site4kBytes)
  }

  @Benchmark
  def deserialization_site_8k(): Site = {
    converter.fromByteArray(site8kBytes)
  }

  @Benchmark
  def deserialization_site_64k(): Site = {
    converter.fromByteArray(site64kBytes)
  }

  @Benchmark
  def serialization_events_1k(): Any = {
    for (e <- events1kInput) {
      converter.toByteArray(e)
    }
  }

  @Benchmark
  def serialization_events_2k(): Any = {
    for (e <- events2kInput) {
      converter.toByteArray(e)
    }
  }

  @Benchmark
  def serialization_events_4k(): Any = {
    for (e <- events4kInput) {
      converter.toByteArray(e)
    }
  }

  @Benchmark
  def serialization_events_8k(): Any = {
    for (e <- events8kInput) {
      converter.toByteArray(e)
    }
  }

  @Benchmark
  def serialization_events_64k(): Any = {
    for (e <- events64kInput) {
      converter.toByteArray(e)
    }
  }

  @Benchmark
  def deserialization_events_1k(): Any = {
    for ((t, bytes) <- events1kOutput) {
      converter.siteEventFromByteArray(t, bytes)
    }
  }

  @Benchmark
  def deserialization_events_2k(): Any = {
    for ((t, bytes) <- events2kOutput) {
      converter.siteEventFromByteArray(t, bytes)
    }
  }

  @Benchmark
  def deserialization_events_4k(): Any = {
    for ((t, bytes) <- events4kOutput) {
      converter.siteEventFromByteArray(t, bytes)
    }
  }

  @Benchmark
  def deserialization_events_8k(): Any = {
    for ((t, bytes) <- events8kOutput) {
      converter.siteEventFromByteArray(t, bytes)
    }
  }

  @Benchmark
  def deserialization_events_64k(): Any = {
    for ((t, bytes) <- events64kOutput) {
      converter.siteEventFromByteArray(t, bytes)
    }
  }

  @Benchmark
  def both_site_1k(): Site = {
    converter.fromByteArray(converter.toByteArray(site1k))
  }

  @Benchmark
  def both_site_2k(): Site = {
    converter.fromByteArray(converter.toByteArray(site2k))
  }

  @Benchmark
  def both_site_4k(): Site = {
    converter.fromByteArray(converter.toByteArray(site4k))
  }

  @Benchmark
  def both_site_8k(): Site = {
    converter.fromByteArray(converter.toByteArray(site8k))
  }

  @Benchmark
  def both_site_64k(): Site = {
    converter.fromByteArray(converter.toByteArray(site64k))
  }

  @Benchmark
  def both_events_1k(): Any = {
    for (e <- events1kInput) {
      converter.siteEventFromByteArray(e.getClass, converter.toByteArray(e))
    }
  }

  @Benchmark
  def both_events_2k(): Any = {
    for (e <- events2kInput) {
      converter.siteEventFromByteArray(e.getClass, converter.toByteArray(e))
    }
  }

  @Benchmark
  def both_events_4k(): Any = {
    for (e <- events4kInput) {
      converter.siteEventFromByteArray(e.getClass, converter.toByteArray(e))
    }
  }

  @Benchmark
  def both_events_8k(): Any = {
    for (e <- events8kInput) {
      converter.siteEventFromByteArray(e.getClass, converter.toByteArray(e))
    }
  }

  @Benchmark
  def both_events_64k(): Any = {
    for (e <- events64kInput) {
      converter.siteEventFromByteArray(e.getClass, converter.toByteArray(e))
    }
  }

  private def createEventsInput(site: Site): Seq[SiteEvent] = {
    EventProcessor.unapply(site).map(_.event)
  }

  private def createEventsOutput(site: Site): Seq[(Class[_], Array[Byte])] = {
    EventProcessor.unapply(site).map(_.event).map(e => e.getClass -> converter.toByteArray(e))
  }

}

class JsonBenchmark extends BenchmarkBase(JsonConverter)

class ScalaPbBenchmark extends BenchmarkBase(ScalaPbConverter)

class JavaPbBenchmark extends BenchmarkBase(JavaPbConverter)

class JavaThriftBenchmark extends BenchmarkBase(JavaThriftConverter)

class ScroogeBenchmark extends BenchmarkBase(ScroogeConverter)

class JavaSerializationBenchmark extends BenchmarkBase(JavaSerializationConverter)

class PicklingBenchmark extends BenchmarkBase(PicklingConverter)

class BooPickleBenchmark extends BenchmarkBase(BoopickleConverter)

class ChillBenchmark extends BenchmarkBase(ChillConverter)
