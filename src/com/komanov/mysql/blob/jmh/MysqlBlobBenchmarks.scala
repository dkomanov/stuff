package com.komanov.mysql.blob.jmh

import com.komanov.compression.jmh.InputData
import com.komanov.compression.{BlobCompressionRatio, CompressionAlgorithms, DeflatePlusSize}
import com.komanov.mysql.blob.Mysql
import org.openjdk.jmh.annotations._

import java.nio.file.Files
import java.sql.{Connection, DriverManager}
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(1)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
abstract class MysqlBlobBenchmarksBase {
  protected var conn: Connection = _

  protected def customSetup(blob: Array[Byte]): Unit

  protected def generateBlob(): Array[Byte]

  protected def lengthParam: Int

  @Setup
  def setup(): Unit = {
    conn = DriverManager.getConnection(Mysql.URL, Mysql.USER, Mysql.PASSWORD)
    val blob = generateBlob()
    customSetup(blob)
  }

  @TearDown
  def destroy(): Unit = {
    conn.close()
  }

  protected def insertInto(tableName: String, len: Int, blob: Array[Byte]): Unit = {
    val st = conn.prepareStatement(s"INSERT INTO $tableName (id, data) VALUES (?, ?) ON DUPLICATE KEY UPDATE data = ?")
    st.setInt(1, len)
    st.setBytes(2, blob)
    st.setBytes(3, blob)
    val rn = st.executeUpdate()
    require(rn > 0, s"returned $rn")
    st.close()
  }

  protected def selectData(tableName: String, field: String): Array[Byte] = {
    val st = conn.prepareStatement(s"SELECT $field FROM $tableName WHERE id = ?")
    st.setInt(1, lengthParam)
    require(st.execute())
    val rs = st.getResultSet
    require(rs.next())
    val result = rs.getBytes(1)
    rs.close()
    st.close()
    result
  }
}

trait StubDataBenchmark {
  self: MysqlBlobBenchmarksBase =>

  @Param(Array(
    "1024",
    "5120",
    "10240",
    "15360",
    "20480",
    "25600",
    "30720",
    "35840",
    "40960",
    "46080",
    "51200",
    "56320",
    "61440",
    "66560",
    "71680",
    "76800",
    "81920",
    "87040",
    "92160",
    "97280",
    "102400",
    "204800",
    "307200",
    "409600",
    "512000",
    "614400",
    "716800",
    "819200",
    "921600",
    "1048576",
    "1150976",
    "1253376",
    "1355776",
    "1458176",
    "1560576",
    "1662976",
    "1765376",
    "1867776",
    "1970176",
    "2097152",
    "2199552",
    "2301952",
    "2404352",
    "2506752",
    "2609152",
    "2711552",
    "2813952",
    "2916352",
    "3018752",
    "3145728",
    "3248128",
    "3350528",
    "3452928",
    "3555328",
    "3657728",
    "3760128",
    "3862528",
    "3964928",
    "4067328",
    "4194304",
    "4296704",
    "4399104",
    "4501504",
    "4603904",
    "4706304",
    "4808704",
    "4911104",
    "5013504",
    "5115904",
    "5242880",
  ))
  var length: Int = _
  @Param
  var compressionRatio: BlobCompressionRatio = _

  override protected def lengthParam: Int = length

  override def generateBlob(): Array[Byte] =
    compressionRatio.generateBlob(self.length)
}

trait RealDataBenchmark {
  self: MysqlBlobBenchmarksBase =>

  @Param(Array(
    //    "298",
    //    "319",
    //    "320",
    //    "326",
    //    "366",
    //    "372",
    //    "420",
    //    "459",
    //    "479",
    //    "531",
    //    "538",
    //    "562",
    //    "648",
    //    "665",
    //    "686",
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

  override protected def lengthParam: Int = length

  override def generateBlob(): Array[Byte] =
    InputData.values()
      .find(_.name.contains(length.toString))
      .map(id => Files.readAllBytes(id.path))
      .getOrElse(throw new IllegalStateException("unknown length"))
}

abstract class AlgorithmBenchmarkBase extends MysqlBlobBenchmarksBase {
  @Param
  var algorithm: CompressionAlgorithms = _

  override protected def customSetup(blob: Array[Byte]): Unit = {
    val encoded = algorithm.encode(blob)
    insertInto("uncompressed_blobs", lengthParam, encoded)
    require(java.util.Arrays.equals(fetch(new Counters()), blob))
  }

  @Benchmark
  def fetch(counters: Counters): Array[Byte] = {
    val r = selectData("uncompressed_blobs", "data")
    val decoded = algorithm.decode(r)
    counters.increment(r, decoded)
    decoded
  }
}

abstract class NotCompressedBenchmarkBase extends MysqlBlobBenchmarksBase {
  @Param(Array("uncompressed"))
  var algorithm: String = _

  override protected def customSetup(blob: Array[Byte]): Unit = {
    insertInto("uncompressed_blobs", lengthParam, blob)
    require(java.util.Arrays.equals(fetch(new Counters()), blob))
  }

  @Benchmark
  def fetch(counters: Counters): Array[Byte] = {
    val r = selectData("uncompressed_blobs", "data")
    counters.increment(r, r)
    r
  }
}

abstract class CompressedBenchmarkBase extends MysqlBlobBenchmarksBase {
  @Param(Array("auto_mysql"))
  var algorithm: String = _

  override protected def customSetup(blob: Array[Byte]): Unit = {
    insertInto("compressed_blobs", lengthParam, blob)
    require(java.util.Arrays.equals(fetch(new Counters()), blob))
  }

  @Benchmark
  def fetch(counters: Counters): Array[Byte] = {
    val r = selectData("compressed_blobs", "data")
    counters.increment(r, r)
    r
  }
}

abstract class UncompressBenchmarkBase extends MysqlBlobBenchmarksBase {
  @Param(Array("explicit_mysql"))
  var algorithm: String = _

  override protected def customSetup(blob: Array[Byte]): Unit = {
    val encoded = DeflatePlusSize.INSTANCE.encode(blob)
    insertInto("uncompressed_blobs", lengthParam, encoded)
    require(java.util.Arrays.equals(fetch(new Counters()), blob))
  }

  @Benchmark
  def fetch(counters: Counters): Array[Byte] = {
    val r = selectData("uncompressed_blobs", "UNCOMPRESS(data)")
    counters.increment(r, r)
    r
  }
}

@AuxCounters(AuxCounters.Type.EVENTS)
@State(Scope.Thread)
class Counters {
  var totalBytesFromMysql = 0L
  var totalBytesReturned = 0L

  def increment(fromMysql: Array[Byte], finalBytes: Array[Byte]): Unit = {
    totalBytesFromMysql += fromMysql.length
    totalBytesReturned += finalBytes.length
  }
}

class StubDataAlgorithmBenchmark extends AlgorithmBenchmarkBase with StubDataBenchmark

class RealDataAlgorithmBenchmark extends AlgorithmBenchmarkBase with RealDataBenchmark

class StubDataNotCompressedBenchmark extends NotCompressedBenchmarkBase with StubDataBenchmark

class RealDataNotCompressedBenchmark extends NotCompressedBenchmarkBase with RealDataBenchmark

class StubDataCompressedBenchmark extends CompressedBenchmarkBase with StubDataBenchmark

class RealDataCompressedBenchmark extends CompressedBenchmarkBase with RealDataBenchmark

class StubDataUncompressBenchmark extends UncompressBenchmarkBase with StubDataBenchmark

class RealDataUncompressBenchmark extends UncompressBenchmarkBase with RealDataBenchmark
