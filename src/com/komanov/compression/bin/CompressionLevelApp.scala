package com.komanov.compression.bin

import com.komanov.compression.jmh.InputData
import com.komanov.compression.{BlobCompressionRatio, CompressionAlgorithms, Lengths}

import java.nio.file.Files

object CompressionLevelApp extends App {
  for (sz <- Lengths.JavaCompressionLengths) {
    println(s"\"$sz\",")
  }
  println()
  println()

  val list = CompressionAlgorithms.values().filter(!_.isOptimization)
  BlobCompressionRatio.values.foreach { ratio =>
    println()
    println(ratio)
    println(s"Length\t${list.mkString("\t")}")
    Lengths.JavaCompressionLengths.foreach { len =>
      val blob = ratio.generateBlob(len)
      println(s"$len\t${list.map(_.encode(blob).length).mkString("\t")}")
    }
  }

  println()
  println(s"Input\tLength\t${list.mkString("\t")}")
  InputData.values().map(input => input -> Files.readAllBytes(input.path)).sortBy(_._2.length).foreach { case (input, blob) =>
    println(s"$input\t${blob.length}\t${list.map(_.encode(blob).length).mkString("\t")}")
  }
}
