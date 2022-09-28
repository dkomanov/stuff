package com.komanov.serialization.bin

import com.komanov.serialization.converters._
import com.komanov.serialization.domain.testdata.TestData
import com.komanov.serialization.io.IoUtils._

import java.io.{ByteArrayOutputStream, File}
import java.nio.file.{Files, StandardOpenOption}
import java.util.zip.GZIPOutputStream

/*
Data Sizes (raw)
Converter,1k,2k,4k,8k,64k
JSON,1060,2076,4043,8173,65835
CBOR,877,1737,3246,6120,49689
Smile,843,1563,2604,4342,34259
Java PB,554,1175,1930,3058,27111
Java Thrift,561,1175,1937,3077,26813
Serializable,2592,3717,4989,7191,42607
BooPickle,555,1165,1917,3047,25769
Chill,819,1606,2414,3519,26089
Circe,1086,2105,4135,8473,67907
uPickle,1126,2246,4523,9165,72435
uPickle MsgPack,988,1979,3917,7799,60323
Cap'n Proto,638,1316,2218,3734,33624
Data Sizes (gzip)
Converter,1k,2k,4k,8k,64k
JSON,679,1137,1633,2668,11671
CBOR,636,1074,1525,2408,9918
Smile,656,1099,1557,2427,9749
Java PB,488,894,1336,2181,9381
Java Thrift,485,883,1299,2144,9364
Serializable,1301,1859,2338,3164,10338
BooPickle,467,852,1266,2082,9159
Chill,562,986,1416,2221,9065
Circe,681,1142,1637,2802,12625
uPickle,701,1160,1659,2700,11752
uPickle MsgPack,698,1170,1684,2764,11988
Cap'n Proto,556,1004,1541,2463,10276
 */
object ReportGenerator extends App {

  val flush = false

  val dir = new File(System.getProperty("user.home"), "123")
  require(!flush || dir.exists() || dir.mkdirs())

  val (raws, gzips) = (Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])])

  for ((converterName, converter) <- Converters.all if !skip.containsKey(converter)) {
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
