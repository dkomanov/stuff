package com.komanov.serialization.converters

import java.io.{ByteArrayOutputStream, File}
import java.nio.file.{Files, StandardOpenOption}
import java.util.zip.GZIPOutputStream

import com.komanov.serialization.converters.IoUtils._
import com.komanov.serialization.domain.testdata.TestData

/*
Data Sizes (raw)
Converter,1k,2k,4k,8k,64k
JSON,1060,2076,4043,8173,65835
Protobuf,554,1175,1930,3058,27111
Thrift,712,1441,2499,4315,38289
Serializable,2207,3311,4549,6615,43168
Pickling,1681,2989,6000,13034,106901
Boopickle,544,1130,1855,2882,16290
Chill,908,1695,2507,3643,26261
Data Sizes (gzip)
Converter,1k,2k,4k,8k,64k
JSON,683,1136,1629,2681,11697
Protobuf,486,893,1329,2177,9391
Thrift,541,963,1400,2251,9612
Serializable,1186,1740,2223,3044,10366
Pickling,692,1144,1619,2579,11039
Boopickle,468,858,1271,2076,9044
Chill,601,1025,1455,2274,9193
 */
object ReportGenerator extends App {

  val flush = true

  val dir = new File(System.getProperty("user.home"), "123")
  require(!flush || dir.exists() || dir.mkdirs())

  val (raws, gzips) = (Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])])

  for ((converterName, converter) <- Converters.all if converter ne ScalaPbConverter if converter ne ScroogeConverter) {
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
