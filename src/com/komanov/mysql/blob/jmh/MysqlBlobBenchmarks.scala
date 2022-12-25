package com.komanov.mysql.blob.jmh

import com.komanov.mysql.blob.{BlobCompressionRatio, Lz4Utils, Mysql}
import org.openjdk.jmh.annotations._

import java.sql.{Connection, DriverManager}
import java.util.concurrent.TimeUnit

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx2G"))
@Threads(1)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
class MysqlBlobBenchmarks {
  @Param(Array(
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
    "11264",
    "12288",
    "13312",
    "14336",
    "15360",
    "16384",
    "17408",
    "18432",
    "19456",
    "20480",
    "21504",
    "22528",
    "23552",
    "24576",
    "25600",
    "26624",
    "27648",
    "28672",
    "29696",
    "30720",
    "31744",
    "32768",
    "33792",
    "34816",
    "35840",
    "36864",
    "37888",
    "38912",
    "39936",
    "40960",
    "41984",
    "43008",
    "44032",
    "45056",
    "46080",
    "47104",
    "48128",
    "49152",
    "50176",
    "51200",
    "52224",
    "53248",
    "54272",
    "55296",
    "56320",
    "57344",
    "58368",
    "59392",
    "60416",
    "61440",
    "62464",
    "63488",
    "64512",
    "65536",
    "66560",
    "67584",
    "68608",
    "69632",
    "70656",
    "71680",
    "72704",
    "73728",
    "74752",
    "75776",
    "76800",
    "77824",
    "78848",
    "79872",
    "80896",
    "81920",
    "82944",
    "83968",
    "84992",
    "86016",
    "87040",
    "88064",
    "89088",
    "90112",
    "91136",
    "92160",
    "93184",
    "94208",
    "95232",
    "96256",
    "97280",
    "98304",
    "99328",
    "100352",
    "101376",
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

  private var conn: Connection = _

  @Setup
  def setup(): Unit = {
    conn = DriverManager.getConnection(Mysql.URL, Mysql.USER, Mysql.PASSWORD)

    val blob = compressionRatio.generateBlob(length)

    insertInto("uncompressed_blobs", length, blob)
    insertInto("compressed_blobs", length, blob)
    insertInto("app_compressed_blobs", length, Mysql.mysqlCompress(blob))
    insertInto("lz4_compressed_blobs", length, Lz4Utils.compress(blob))

    val bytes = uncompressed(new Counters)
    require(java.util.Arrays.equals(blob, bytes))
    require(java.util.Arrays.equals(bytes, compressed(new Counters)))
    require(java.util.Arrays.equals(bytes, app_compressed_in_java(new Counters)))
    require(java.util.Arrays.equals(bytes, app_compressed_in_mysql(new Counters)))
    require(java.util.Arrays.equals(bytes, lz4_compressed(new Counters)))
  }

  @TearDown
  def destroy(): Unit = {
    conn.close()
  }

  @Benchmark
  def uncompressed(counters: Counters): Array[Byte] = {
    val r = selectData("uncompressed_blobs", "data")
    counters.increment(r, r)
    r
  }

  @Benchmark
  def compressed(counters: Counters): Array[Byte] = {
    val r = selectData("compressed_blobs", "data")
    counters.increment(r, r)
    r
  }

  @Benchmark
  def app_compressed_in_java(counters: Counters): Array[Byte] = {
    val r = selectData("app_compressed_blobs", "data")
    val finalBytes = Mysql.mysqlDecompress(r)
    counters.increment(r, finalBytes)
    finalBytes
  }

  @Benchmark
  def app_compressed_in_mysql(counters: Counters): Array[Byte] = {
    val r = selectData("app_compressed_blobs", "UNCOMPRESS(data)")
    counters.increment(r, r)
    r
  }

  @Benchmark
  def lz4_compressed(counters: Counters): Array[Byte] = {
    val r = selectData("lz4_compressed_blobs", "data")
    val finalBytes = Lz4Utils.decompress(r)
    counters.increment(r, finalBytes)
    finalBytes
  }

  private def insertInto(tableName: String, len: Int, blob: Array[Byte]): Unit = {
    val st = conn.prepareStatement(s"INSERT INTO $tableName (id, data) VALUES (?, ?) ON DUPLICATE KEY UPDATE data = ?")
    st.setInt(1, len)
    st.setBytes(2, blob)
    st.setBytes(3, blob)
    val rn = st.executeUpdate()
    require(rn > 0, s"returned $rn")
    st.close()
  }

  private def selectData(tableName: String, field: String): Array[Byte] = {
    val st = conn.prepareStatement(s"SELECT $field FROM $tableName WHERE id = ?")
    st.setInt(1, length)
    require(st.execute())
    val rs = st.getResultSet
    require(rs.next())
    val result = rs.getBytes(1)
    rs.close()
    st.close()
    result
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
