package com.komanov.serialization.bin

import java.io.{ByteArrayOutputStream, File}
import java.nio.file.{Files, StandardOpenOption}
import java.util.zip.GZIPOutputStream

import com.komanov.serialization.converters.{Converters, ScalaPbConverter}
import com.komanov.serialization.io.IoUtils._
import com.komanov.serialization.domain.testdata.TestData

/*
Data Sizes (raw)
Converter,1k,ev 1k,2k,ev 2k,4k,ev 4k,8k,ev 8k,64k,ev 64k
JSON,1060,1277,2076,2499,4043,5119,8173,10961,65835,109539
CBOR,877,1063,1737,2094,3246,4039,6120,8041,49689,84307
Smile,843,1127,1563,2210,2604,4293,4342,8759,34259,90206
Java PB,554,578,1175,1192,1930,2076,3058,3604,27111,42455
Java Thrift,561,592,1175,1217,1937,2137,3073,3729,26813,43577
Serializable,2592,2716,3717,5078,4989,11538,7191,26228,42607,240267
BooPickle,555,593,1165,1220,1917,2117,3047,3655,25769,42150
Chill,819,588,1606,1260,2414,2397,3519,3981,26090,47054
Jsoniter,1017,1277,2046,2499,3981,5119,7743,10961,64493,109539
Circe,1086,1208,2105,2375,4135,4875,8473,10397,67907,104390
uPickle,1126,1753,2246,3349,4523,6785,9165,14803,72435,144559
uPickleMsgpack,988,1633,1979,3114,3917,6250,7799,13588,60323,130872
Data Sizes (gzip)
Converter,1k,ev 1k,2k,ev 2k,4k,ev 4k,8k,ev 8k,64k,ev 64k
JSON,679,1363,1135,2554,1635,5260,2673,11789,11700,115992
CBOR,630,1205,1074,2284,1531,4480,2410,9683,9934,99223
Smile,654,1276,1097,2412,1567,4752,2426,10357,9771,105789
Java PB,481,775,893,1465,1338,2716,2181,5576,9404,62765
Java Thrift,476,791,882,1492,1300,2779,2146,5703,9385,63903
Serializable,1295,2508,1857,4624,2339,9994,3169,23309,10340,216925
BooPickle,459,788,852,1488,1266,2750,2082,5620,9162,62455
Chill,559,795,982,1547,1411,3067,2217,6051,9082,68210
Jsoniter,656,1363,1124,2554,1623,5260,2648,11789,11632,115992
Circe,681,1318,1141,2487,1644,5133,2823,11472,12683,113259
uPickle,701,1771,1158,3279,1662,6635,2708,14943,11782,143298
uPickleMsgpack,696,1732,1169,3202,1689,6495,2760,14670,12008,141416
Data Sizes
Converter,1k,ev 1k,2k,ev 2k,4k,ev 4k,8k,ev 8k,64k,ev 64k
JSON (raw),1060,1277,2076,2499,4043,5119,8173,10961,65835,109539
JSON (gz),679,1363,1135,2554,1635,5260,2673,11789,11700,115992
CBOR (raw),877,1063,1737,2094,3246,4039,6120,8041,49689,84307
CBOR (gz),630,1205,1074,2284,1531,4480,2410,9683,9934,99223
Smile (raw),843,1127,1563,2210,2604,4293,4342,8759,34259,90206
Smile (gz),654,1276,1097,2412,1567,4752,2426,10357,9771,105789
Java PB (raw),554,578,1175,1192,1930,2076,3058,3604,27111,42455
Java PB (gz),481,775,893,1465,1338,2716,2181,5576,9404,62765
Java Thrift (raw),561,592,1175,1217,1937,2137,3073,3729,26813,43577
Java Thrift (gz),476,791,882,1492,1300,2779,2146,5703,9385,63903
Serializable (raw),2592,2716,3717,5078,4989,11538,7191,26228,42607,240267
Serializable (gz),1295,2508,1857,4624,2339,9994,3169,23309,10340,216925
BooPickle (raw),555,593,1165,1220,1917,2117,3047,3655,25769,42150
BooPickle (gz),459,788,852,1488,1266,2750,2082,5620,9162,62455
Chill (raw),819,588,1606,1260,2414,2397,3519,3981,26090,47054
Chill (gz),559,795,982,1547,1411,3067,2217,6051,9082,68210
Jsoniter (raw),1017,1277,2046,2499,3981,5119,7743,10961,64493,109539
Jsoniter (gz),656,1363,1124,2554,1623,5260,2648,11789,11632,115992
Circe (raw),1086,1208,2105,2375,4135,4875,8473,10397,67907,104390
Circe (gz),681,1318,1141,2487,1644,5133,2823,11472,12683,113259
uPickle (raw),1126,1753,2246,3349,4523,6785,9165,14803,72435,144559
uPickle (gz),701,1771,1158,3279,1662,6635,2708,14943,11782,143298
uPickleMsgpack (raw),988,1633,1979,3114,3917,6250,7799,13588,60323,130872
uPickleMsgpack (gz),696,1732,1169,3202,1689,6495,2760,14670,12008,141416
 */
object EventsReportGenerator extends App {

  val flush = false

  val dir = new File(new File(System.getProperty("user.home"), "123"), "events")
  require(!flush || dir.exists() || dir.mkdirs())

  val (raws, gzips, both) = (Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])])

  for ((converterName, converter) <- Converters.all if converter ne ScalaPbConverter) {
    val results = Seq.newBuilder[(Int, Int)]
    for ((name, site, events) <- TestData.all) {
      val bytes = converter.toByteArray(site)
      val gzipLen = getGzipByteLength(bytes)

      val eventsAndBytes = events.map(e => e -> converter.toByteArray(e.event))
      val eventsLen = eventsAndBytes.map(_._2.length).sum
      val eventsGzipLen = eventsAndBytes.map(_._2).map(getGzipByteLength).sum

      results += bytes.length -> gzipLen
      results += eventsLen -> eventsGzipLen

      if (flush) {
        val normalizedConverterName = converterName.toLowerCase().replace(" ", "-")
        Files.write(dir.getParentFile.toPath.resolve(s"site_${name}_$normalizedConverterName.bin"), bytes, StandardOpenOption.CREATE)
        for ((event, eventBytes) <- eventsAndBytes) {
          Files.write(dir.toPath.resolve(s"${name}_${normalizedConverterName}_${event.event.getClass.getSimpleName}.bin"), eventBytes, StandardOpenOption.CREATE)
        }
      }
    }

    raws += converterName -> results.result().map(_._1)
    gzips += converterName -> results.result().map(_._2)
    both += (converterName + " (raw)") -> results.result().map(_._1)
    both += (converterName + " (gz)") -> results.result().map(_._2)
  }

  println("Data Sizes (raw)")
  printHeaders
  printSizes(raws.result())

  println("Data Sizes (gzip)")
  printHeaders
  printSizes(gzips.result())

  println("Data Sizes")
  printHeaders
  printSizes(both.result())

  private def printHeaders: Any = {
    println("Converter," + TestData.sites.flatMap(t => Seq(t._1, "ev " + t._1)).mkString(","))
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
