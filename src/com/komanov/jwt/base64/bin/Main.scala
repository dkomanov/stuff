package com.komanov.jwt.base64.bin

import com.komanov.jwt.base64.Base64Helper

import java.nio.charset.StandardCharsets
import scala.util.Random

object Main extends App {
  printOfLength(1)
  printOfLength(10)
  printOfLength(50)
  printOfLength(100)
  printOfLength(500)
  printOfLength(1000)
  printOfLength(10000)


  def printOfLength(initialLength: Int): Unit = {
    var s = "="
    var len = initialLength
    while (s.endsWith("=")) {
      val payload = Random.alphanumeric.take(len).mkString
      val encoded = Base64Helper.Jdk.encode(payload.getBytes(StandardCharsets.US_ASCII))
      s = new String(encoded, StandardCharsets.US_ASCII)
      len += 1
    }
    println(s"$initialLength -> \"$s\", // ${len - 1}")
  }
}
