package com.komanov.mysql.blob.bin

import com.komanov.compression.BlobCompressionRatio
import com.komanov.mysql.blob.{BlobGenerator, Lz4Utils, Mysql}

import scala.jdk.CollectionConverters._

object CompressionLevelApp extends App {
  def measured[T](f: => T): (T, Long) = {
    val start = System.nanoTime()
    val r = f
    val duration = System.nanoTime() - start
    (r, duration / 1000)
  }

  for (sz <- BlobGenerator.Lengths) {
    println(s"\"$sz\",")
  }

  println()
  println(s"Length\t${BlobCompressionRatio.values.mkString("\t\t\t\t\t\t")}")
  println(s"\t${BlobCompressionRatio.values.map(_ => "zlib size\tzlib ratio\tzlib duration\tlz4 size\tlz4 ratio\tlz4 duration").mkString("\t")}")
  for (sz <- BlobGenerator.Lengths) {
    val rr = BlobCompressionRatio.values.map { ratio =>
      val blob = ratio.generateBlob(sz)
      val (zlibed, zlibDuration) = measured(Mysql.mysqlCompress(blob))
      val (lz4ed, lz4Duration) = measured(Lz4Utils.compress(blob))

      def of(compressed: Array[Byte], duration: Long) = {
        Seq(
          compressed.length,
          String.format("%.2f", blob.length.toDouble / compressed.length.toDouble),
          duration,
        ).mkString("\t")
      }

      of(zlibed, zlibDuration) + "\t" + of(lz4ed, lz4Duration)
    }

    println(s"$sz\t${rr.mkString("\t")}")
  }
}
