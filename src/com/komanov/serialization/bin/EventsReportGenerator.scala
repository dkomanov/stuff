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
Java Thrift,561,592,1175,1217,1937,2137,3075,3729,26818,43577
Serializable,2592,2716,3717,5078,4989,11538,7191,26228,42607,240267
BooPickle,555,593,1165,1220,1917,2117,3047,3655,25769,42150
Chill,819,588,1606,1260,2414,2397,3519,3981,26088,47052
Jsoniter,1017,1277,2046,2499,3981,5119,7743,10961,64493,109539
Circe,1086,1208,2105,2375,4135,4875,8473,10397,67907,104390
uPickle,1126,1753,2246,3349,4523,6785,9165,14803,72435,144559
uPickle pooled,1126,1753,2246,3349,4523,6785,9165,14803,72435,144559
uPickle MsgPack,988,1633,1979,3114,3917,6250,7799,13588,60323,130872
Cap'n Proto,638,758,1316,1532,2218,2763,3735,5163,33625,59946
Cap'n Proto pooled,638,758,1316,1532,2218,2763,3735,5163,33625,59946
Data Sizes (gzip)
Converter,1k,ev 1k,2k,ev 2k,4k,ev 4k,8k,ev 8k,64k,ev 64k
JSON,681,1365,1136,2565,1634,5259,2671,11788,11668,116040
CBOR,635,1206,1080,2281,1529,4476,2412,9679,9929,99210
Smile,654,1276,1097,2412,1561,4752,2425,10357,9759,105790
Java PB,486,774,895,1463,1338,2710,2182,5564,9397,62730
Java Thrift,481,790,885,1490,1303,2773,2146,5691,9367,63869
Serializable,1299,2507,1861,4624,2338,9989,3160,23298,10336,216900
BooPickle,464,787,853,1486,1267,2744,2086,5608,9167,62422
Chill,558,795,983,1545,1412,3072,2213,6050,9065,68232
Jsoniter,658,1365,1124,2565,1624,5259,2648,11788,11600,116040
Circe,681,1320,1142,2492,1641,5131,2814,11469,12648,113302
uPickle,703,1770,1161,3280,1662,6635,2704,14940,11753,143373
uPickle pooled,703,1770,1161,3280,1662,6635,2704,14940,11753,143373
uPickle MsgPack,698,1733,1168,3208,1689,6492,2760,14668,11983,141504
Cap'n Proto,557,961,1004,1810,1543,3405,2473,7144,10278,80421
Cap'n Proto pooled,557,961,1004,1810,1543,3405,2473,7144,10278,80421
Data Sizes
Converter,1k,ev 1k,2k,ev 2k,4k,ev 4k,8k,ev 8k,64k,ev 64k
JSON (raw),1060,1277,2076,2499,4043,5119,8173,10961,65835,109539
JSON (gz),681,1365,1136,2565,1634,5259,2671,11788,11668,116040
CBOR (raw),877,1063,1737,2094,3246,4039,6120,8041,49689,84307
CBOR (gz),635,1206,1080,2281,1529,4476,2412,9679,9929,99210
Smile (raw),843,1127,1563,2210,2604,4293,4342,8759,34259,90206
Smile (gz),654,1276,1097,2412,1561,4752,2425,10357,9759,105790
Java PB (raw),554,578,1175,1192,1930,2076,3058,3604,27111,42455
Java PB (gz),486,774,895,1463,1338,2710,2182,5564,9397,62730
Java Thrift (raw),561,592,1175,1217,1937,2137,3075,3729,26818,43577
Java Thrift (gz),481,790,885,1490,1303,2773,2146,5691,9367,63869
Serializable (raw),2592,2716,3717,5078,4989,11538,7191,26228,42607,240267
Serializable (gz),1299,2507,1861,4624,2338,9989,3160,23298,10336,216900
BooPickle (raw),555,593,1165,1220,1917,2117,3047,3655,25769,42150
BooPickle (gz),464,787,853,1486,1267,2744,2086,5608,9167,62422
Chill (raw),819,588,1606,1260,2414,2397,3519,3981,26088,47052
Chill (gz),558,795,983,1545,1412,3072,2213,6050,9065,68232
Jsoniter (raw),1017,1277,2046,2499,3981,5119,7743,10961,64493,109539
Jsoniter (gz),658,1365,1124,2565,1624,5259,2648,11788,11600,116040
Circe (raw),1086,1208,2105,2375,4135,4875,8473,10397,67907,104390
Circe (gz),681,1320,1142,2492,1641,5131,2814,11469,12648,113302
uPickle (raw),1126,1753,2246,3349,4523,6785,9165,14803,72435,144559
uPickle (gz),703,1770,1161,3280,1662,6635,2704,14940,11753,143373
uPickle pooled (raw),1126,1753,2246,3349,4523,6785,9165,14803,72435,144559
uPickle pooled (gz),703,1770,1161,3280,1662,6635,2704,14940,11753,143373
uPickle MsgPack (raw),988,1633,1979,3114,3917,6250,7799,13588,60323,130872
uPickle MsgPack (gz),698,1733,1168,3208,1689,6492,2760,14668,11983,141504
Cap'n Proto (raw),638,758,1316,1532,2218,2763,3735,5163,33625,59946
Cap'n Proto (gz),557,961,1004,1810,1543,3405,2473,7144,10278,80421
Cap'n Proto pooled (raw),638,758,1316,1532,2218,2763,3735,5163,33625,59946
Cap'n Proto pooled (gz),557,961,1004,1810,1543,3405,2473,7144,10278,80421
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
