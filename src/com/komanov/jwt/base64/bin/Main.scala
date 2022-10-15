package com.komanov.jwt.base64.bin

import com.komanov.jwt.base64.PayloadGenerator

object Main extends App {
  printOfLength(1)
  printOfLength(10)
  printOfLength(50)
  printOfLength(100)
  printOfLength(500)
  printOfLength(1000)
  printOfLength(10000)


  def printOfLength(len: Int): Unit = {
    val s = PayloadGenerator(len)
    println(s"$len -> \"$s\",")
  }
}
