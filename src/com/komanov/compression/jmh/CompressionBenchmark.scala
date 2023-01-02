package com.komanov.compression.jmh

import com.komanov.compression.{BlobCompressionRatio, CompressionAlgorithms}
import org.openjdk.jmh.annotations._

import java.nio.file.Files
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
class StubDataCompressionBenchmark {
  @Param(Array(
    "128",
    "512",
    "1024",
    "2048",
    "3072",
    "4096",
    "5120",
    "6144",
    "7168",
    "8192",
    "9216",
    "10240",
    "20480",
    "30720",
    "40960",
    "51200",
    "61440",
    "71680",
    "81920",
    "92160",
    "102400",
  ))
  var length: Int = _
  @Param
  var compressionRatio: BlobCompressionRatio = _
  @Param
  var algorithm: CompressionAlgorithms = _

  private var data: Array[Byte] = _
  private var encoded: Array[Byte] = _

  @Setup
  def setup(): Unit = {
    val blob = compressionRatio.generateBlob(length)

    data = blob
    encoded = algorithm.encode(blob)
  }

  @Benchmark
  def encode(counters: Counters): Array[Byte] = {
    val r = algorithm.encode(data)
    counters.increment(data, r)
    r
  }

  @Benchmark
  def decode(counters: Counters): Array[Byte] = {
    val r = algorithm.decode(encoded)
    counters.increment(encoded, r)
    r
  }
}

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(2)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
class RealDataCompressionBenchmark {
  @Param(Array(
    "298",
//    "319",
//    "320",
//    "326",
//    "366",
//    "372",
    "420",
//    "459",
//    "479",
    "531",
    "538",
//    "562",
//    "648",
//    "665",
    "686",
    "34011",
//    "35578",
    "42223",
    "51771",
//    "52928",
//    "59448",
//    "59617",
    "62830",
//    "67071",
//    "67872",
//    "68118",
//    "68230",
//    "79107",
    "81207",
//    "88114",
    "94417",
    "607930",
//    "668462",
    "751048",
//    "773419",
    "781196",
//    "791173",
    "866049",
    "904172",
//    "989390",
    "1075724",
    "1293080",
//    "1356567",
    "1448911",
    "1599048",
    "4072805",
    "4287156",
  ))
  var length: Int = _
  @Param
  var algorithm: CompressionAlgorithms = _

  private var input: InputData = _
  private var data: Array[Byte] = _
  private var encoded: Array[Byte] = _

  @Setup
  def setup(): Unit = {
    input = InputData.values().find(_.name.contains(length.toString)).get
    data = Files.readAllBytes(input.path)
    encoded = algorithm.encode(data)
  }

  @Benchmark
  def encode(counters: Counters): Array[Byte] = {
    val r = algorithm.encode(data)
    counters.increment(data, r)
    r
  }

  @Benchmark
  def decode(counters: Counters): Array[Byte] = {
    val r = algorithm.decode(encoded)
    counters.increment(encoded, r)
    r
  }
}

@AuxCounters(AuxCounters.Type.EVENTS)
@State(Scope.Thread)
class Counters {
  var totalInputBytes = 0L
  var totalOutputBytes = 0L

  def increment(input: Array[Byte], output: Array[Byte]): Unit = {
    totalInputBytes += input.length
    totalOutputBytes += output.length
  }
}
