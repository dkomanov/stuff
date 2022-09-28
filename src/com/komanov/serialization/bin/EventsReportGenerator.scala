package com.komanov.serialization.bin

import java.io.{ByteArrayOutputStream, File}
import java.nio.file.{Files, StandardOpenOption}
import java.util.zip.GZIPOutputStream
import com.komanov.serialization.converters.{Converters, ScalaPbConverter}
import com.komanov.serialization.io.IoUtils._
import com.komanov.serialization.domain.testdata.TestData

import java.nio.ByteBuffer

/*
Data Sizes (raw)
Converter,1k,2k,4k,8k,64k
JSON,1277,2499,5119,10961,109539
CBOR,1063,2094,4039,8041,84307
Smile,1127,2210,4293,8759,90206
Java PB,578,1192,2076,3604,42455
Java Thrift,592,1217,2137,3729,43577
Serializable,2716,5078,11538,26228,240267
BooPickle,593,1220,2117,3655,42150
Chill,588,1260,2397,3981,47051
Circe,1208,2375,4875,10397,104390
uPickle,1753,3349,6785,14803,144559
uPickle MsgPack,1633,3114,6250,13588,130872
Cap'n Proto,758,1532,2763,5163,59944
Data Sizes (gzip)
Converter,1k,2k,4k,8k,64k
JSON,1362,2561,5257,11788,116017
CBOR,1206,2282,4479,9680,99234
Smile,1276,2412,4752,10357,105789
Java PB,772,1462,2714,5565,62762
Java Thrift,788,1489,2777,5692,63894
Serializable,2506,4623,9988,23305,216913
BooPickle,785,1485,2748,5609,62454
Chill,791,1549,3063,6067,68196
Circe,1320,2490,5133,11473,113261
uPickle,1770,3281,6645,14949,143337
uPickle MsgPack,1730,3210,6498,14680,141478
Cap'n Proto,959,1809,3404,7141,80446
Data Sizes (gzip together)
Converter,1k,2k,4k,8k,64k
JSON,751,1219,1810,2826,13070
CBOR,726,1186,1716,2585,11230
Smile,736,1216,1756,2645,11445
Java PB,501,874,1338,2093,10077
Java Thrift,503,877,1341,2101,10174
Serializable,1171,1749,2362,3244,12960
BooPickle,518,913,1378,2128,10123
Chill,503,914,1398,2165,10412
Circe,750,1218,1810,2823,13033
uPickle,776,1255,1856,2898,13284
uPickle MsgPack,808,1314,1919,2943,13139
Cap'n Proto,632,1075,1626,2474,11280
 */
object EventsReportGenerator extends App {

  val flush = false

  val dir = new File(new File(System.getProperty("user.home"), "123"), "events")
  require(!flush || dir.exists() || dir.mkdirs())

  val (raws, gzips, gzipTogether) = (Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])])

  def int2Array(v: Int): Array[Byte] =
    ByteBuffer.allocate(4).putInt(v).array()

  for ((converterName, converter) <- Converters.all if !skip.containsKey(converter)) {
    val results = Seq.newBuilder[(Int, Int, Int)]
    for ((name, _, events) <- TestData.all) {
      val eventsAndBytes = events.map(e => e -> converter.toByteArray(e.event))
      val eventsLen = eventsAndBytes.map(_._2.length).sum
      val eventsGzipLen = eventsAndBytes.map(_._2).map(getGzipByteLength).sum
      // Emulating this format: <number of events> (int) [<number of bytes> (int) <event bytes>] *
      val eventsGzipTogether = getGzipByteLength(eventsAndBytes.map(t => int2Array(t._2.length) ++ t._2).fold(int2Array(eventsAndBytes.length))(_ ++ _))

      val t = (eventsLen, eventsGzipLen, eventsGzipTogether)
      results += t

      if (flush) {
        val normalizedConverterName = converterName.toLowerCase().replace(" ", "-")
        for ((event, eventBytes) <- eventsAndBytes) {
          Files.write(dir.toPath.resolve(s"${name}_${normalizedConverterName}_${event.event.getClass.getSimpleName}.bin"), eventBytes, StandardOpenOption.CREATE)
        }
      }
    }

    raws += converterName -> results.result().map(_._1)
    gzips += converterName -> results.result().map(_._2)
    gzipTogether += converterName -> results.result().map(_._3)
  }

  println("Data Sizes (raw)")
  printHeaders
  printSizes(raws.result())

  println("Data Sizes (gzip)")
  printHeaders
  printSizes(gzips.result())

  println("Data Sizes (gzip together)")
  printHeaders
  printSizes(gzipTogether.result())

  private def printHeaders: Any = {
    println("Converter," + TestData.sites.map(t => t._1).mkString(","))
  }

  private def printSizes(all: Seq[(String, Seq[Int])]): Unit = {
    for ((name, list) <- all) {
      println(name + "," + list.mkString(","))
    }
  }

  private def getGzipByteLength(bytes: Array[Byte]): Int = {
    using(new ByteArrayOutputStream()) { baos =>
      using(new GZIPOutputStream(baos)) { os =>
        os.write(bytes)
      }
      baos.toByteArray.length
    }
  }

}
