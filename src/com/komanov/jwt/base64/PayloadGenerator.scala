package com.komanov.jwt.base64

import scala.util.Random

object PayloadGenerator {
  def apply(length: Int): String =
    Random.alphanumeric.take(length).mkString
}
