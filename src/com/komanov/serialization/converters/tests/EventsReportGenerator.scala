package com.komanov.serialization.converters

import java.io.{ByteArrayOutputStream, File}
import java.nio.file.{Files, StandardOpenOption}
import java.util.zip.GZIPOutputStream

import com.komanov.serialization.converters.IoUtils._
import com.komanov.serialization.domain.testdata.TestData

/*
Data Sizes (raw)
Converter,1k,ev 1k,2k,ev 2k,4k,ev 4k,8k,ev 8k,64k,ev 64k
JSON,1060,1277,2076,2499,4043,5119,8173,10961,65835,109539
Protobuf,554,578,1175,1192,1930,2076,3058,3604,27111,42455
Thrift,712,700,1441,1430,2499,2639,4315,4911,38289,57029
Serializable,2207,2716,3311,5078,4549,11538,6615,26228,43168,240267
Pickling,1681,1565,2989,3023,6000,6284,13034,13462,106901,128797
Boopickle,544,593,1130,1220,1855,2117,2882,3655,16290,42150
Chill,908,588,1695,1260,2507,2397,3644,3981,26259,47048
Data Sizes (gzip)
Converter,1k,ev 1k,2k,ev 2k,4k,ev 4k,8k,ev 8k,64k,ev 64k
JSON,682,1366,1137,2565,1630,5251,2677,11784,11719,116024
Protobuf,488,775,898,1463,1334,2710,2175,5552,9394,62763
Thrift,541,876,966,1669,1400,3189,2256,6673,9599,75044
Serializable,1185,2505,1738,4621,2221,9975,3046,23279,10356,216106
Pickling,692,1613,1145,2973,1621,5999,2577,13496,11057,130714
Boopickle,471,790,861,1492,1272,2748,2078,5600,9046,62455
Chill,599,795,1022,1544,1452,3073,2279,6075,9219,68228
Data Sizes
Converter,1k,ev 1k,2k,ev 2k,4k,ev 4k,8k,ev 8k,64k,ev 64k
JSON (rw),1060,1277,2076,2499,4043,5119,8173,10961,65835,109539
JSON (gz),682,1366,1137,2565,1630,5251,2677,11784,11719,116024
Protobuf (rw),554,578,1175,1192,1930,2076,3058,3604,27111,42455
Protobuf (gz),488,775,898,1463,1334,2710,2175,5552,9394,62763
Thrift (rw),712,700,1441,1430,2499,2639,4315,4911,38289,57029
Thrift (gz),541,876,966,1669,1400,3189,2256,6673,9599,75044
Serializable (rw),2207,2716,3311,5078,4549,11538,6615,26228,43168,240267
Serializable (gz),1185,2505,1738,4621,2221,9975,3046,23279,10356,216106
Pickling (rw),1681,1565,2989,3023,6000,6284,13034,13462,106901,128797
Pickling (gz),692,1613,1145,2973,1621,5999,2577,13496,11057,130714
Boopickle (rw),544,593,1130,1220,1855,2117,2882,3655,16290,42150
Boopickle (gz),471,790,861,1492,1272,2748,2078,5600,9046,62455
Chill (rw),908,588,1695,1260,2507,2397,3644,3981,26259,47048
Chill (gz),599,795,1022,1544,1452,3073,2279,6075,9219,68228
 */
object EventsReportGenerator extends App {

  val flush = false

  val dir = new File(new File(System.getProperty("user.home"), "123"), "events")
  require(!flush || dir.exists() || dir.mkdirs())

  val (raws, gzips, both) = (Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])], Seq.newBuilder[(String, Seq[Int])])

  for ((converterName, converter) <- Converters.all if converter ne ScroogeConverter if converter ne ScalaPbConverter) {
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
    both += (converterName + " (rw)") -> results.result().map(_._1)
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
