package com.komanov.serialization.bin

import java.io.{ByteArrayOutputStream, File}
import java.nio.file.{Files, StandardOpenOption}
import java.util.zip.GZIPOutputStream

import com.komanov.serialization.converters.{Converters, ScalaPbConverter}
import com.komanov.serialization.io.IoUtils._
import com.komanov.serialization.domain.testdata.TestData

/*
Data Sizes (raw)
Converter,1k,2k,4k,8k,64k
JSON,1060,2076,4043,8173,65835
CBOR,877,1737,3246,6120,49689
Smile,843,1563,2604,4342,34259
Java PB,554,1175,1930,3058,27111
Java Thrift,561,1175,1937,3076,26807
Serializable,2592,3717,4989,7191,42607
BooPickle,555,1165,1917,3047,25769
Chill,819,1606,2414,3518,26089
Jsoniter,1017,2046,3981,7743,64493
Circe,1086,2105,4135,8473,67907
uPickle,1126,2246,4523,9165,72435
uPickle pooled,1126,2246,4523,9165,72435
uPickle MsgPack,988,1979,3917,7799,60323
Cap'n Proto,638,1316,2218,3733,33621
Cap'n Proto pooled,638,1316,2218,3733,33621
Data Sizes (gzip)
Converter,1k,2k,4k,8k,64k
JSON,681,1136,1637,2676,11706
CBOR,638,1079,1531,2412,9940
Smile,655,1104,1562,2419,9773
Java PB,489,898,1338,2181,9381
Java Thrift,483,887,1301,2147,9358
Serializable,1299,1859,2336,3167,10353
BooPickle,467,856,1268,2087,9155
Chill,560,984,1414,2215,9066
Jsoniter,658,1124,1625,2651,11639
Circe,680,1143,1644,2822,12666
uPickle,703,1160,1666,2709,11783
uPickle pooled,703,1160,1666,2709,11783
uPickle MsgPack,696,1169,1689,2753,12008
Cap'n Proto,559,1007,1544,2471,10281
Cap'n Proto pooled,559,1007,1544,2471,10281
 */
object ReportGenerator extends App {

  val flush = false

  val dir = new File(System.getProperty("user.home"), "123")
  require(!flush || dir.exists() || dir.mkdirs())

  val (raws, gzips) = (Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])])

  for ((converterName, converter) <- Converters.all if converter ne ScalaPbConverter) {
    val results = Seq.newBuilder[(Int, Int)]
    for ((name, site) <- TestData.sites) {
      val bytes = converter.toByteArray(site)
      val gzipLen = getGzipByteLength(bytes)

      results += bytes.length -> gzipLen

      if (flush) {
        val normalizedConverterName = converterName.toLowerCase().replace(" ", "-")
        Files.write(dir.toPath.resolve(s"site_${name}_$normalizedConverterName.bin"), bytes, StandardOpenOption.CREATE)
      }
    }

    raws += converterName -> results.result().map(_._1)
    gzips += converterName -> results.result().map(_._2)
  }

  println("Data Sizes (raw)")
  printHeaders
  printSizes(raws.result())

  println("Data Sizes (gzip)")
  printHeaders
  printSizes(gzips.result())

  private def printHeaders: Any = {
    println("Converter," + TestData.sites.map(_._1).mkString(","))
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
