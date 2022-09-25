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
Java Thrift,561,1175,1937,3076,26814
Serializable,2592,3717,4989,7191,42607
BooPickle,555,1165,1917,3047,25769
Chill,819,1606,2414,3519,26088
Jsoniter,1017,2046,3981,7743,64493
Circe,1086,2105,4135,8473,67907
uPickle,1126,2246,4523,9165,72435
uPickleMsgpack,988,1979,3917,7799,60323
Data Sizes (gzip)
Converter,1k,2k,4k,8k,64k
JSON,684,1139,1630,2671,11684
CBOR,634,1079,1527,2406,9925
Smile,654,1101,1562,2426,9769
Java PB,487,900,1337,2179,9389
Java Thrift,482,890,1298,2139,9355
Serializable,1299,1863,2337,3163,10347
BooPickle,466,859,1266,2079,9157
Chill,559,984,1408,2214,9067
Jsoniter,661,1127,1619,2647,11618
Circe,685,1145,1639,2814,12666
uPickle,707,1163,1657,2709,11771
uPickleMsgpack,701,1171,1685,2757,12005
 */
object ReportGenerator extends App {

  val flush = true

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
