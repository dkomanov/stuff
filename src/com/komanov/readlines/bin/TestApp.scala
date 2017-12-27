package com.komanov.readlines.bin

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

import com.komanov.readlines.ReadLinesUtils

object TestApp extends App {

  // List(41, df, 6771, 10400)
  val bytes = List(
    0x41,
    0xC3, 0x9F,
    0xE6, 0x9D, 0xB1,
    0xF0, 0x90, 0x90, 0x80
  )
    .map(_.toByte)
    .toArray

  val cs = StandardCharsets.UTF_8
  val s = cs.decode(ByteBuffer.wrap(bytes)).toString

  println(s)

  println(s.getBytes(cs).map(_.toInt.toHexString).toList)
  println(s.toCharArray.map(_.toInt.toHexString).toList)
  println(s.codePoints().toArray.map(_.toHexString).toList)

  ReadLinesUtils.decodeUtf8FromBytes(bytes, s => {
    println(s)
    println(s.codePoints().toArray.map(_.toHexString).toList)
  })

}
