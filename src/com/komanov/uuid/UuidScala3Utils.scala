package com.komanov.uuid

import java.util.UUID

import scala.annotation.tailrec

object UuidScala3Utils {

  def fromStringFast(s: String): UUID = {
    val component1EndIndex = indexOfHyphen(s, 0)
    val component2EndIndex = indexOfHyphen(s, component1EndIndex + 1)
    val component3EndIndex = indexOfHyphen(s, component2EndIndex + 1)
    val component4EndIndex = indexOfHyphen(s, component3EndIndex + 1)
    if (s.indexOf('-', component4EndIndex + 1) != -1) {
      throw new IllegalArgumentException(s"Too much hyphens in a string: $s")
    }

    // This is a copy-paste from UUID.fromString implementation
    var mostSigBits: Long = parseHex(s, 0, component1EndIndex)
    mostSigBits <<= 16
    mostSigBits |= parseHex(s, component1EndIndex + 1, component2EndIndex)
    mostSigBits <<= 16
    mostSigBits |= parseHex(s, component2EndIndex + 1, component3EndIndex)

    var leastSigBits: Long = parseHex(s, component3EndIndex + 1, component4EndIndex)
    leastSigBits <<= 48
    leastSigBits |= parseHex(s, component4EndIndex + 1, s.length)

    new UUID(mostSigBits, leastSigBits)
  }

  private def indexOfHyphen(s: String, from: Int): Int = {
    val index = s.indexOf('-', from)
    if (index == -1) {
      throw new IllegalArgumentException(s"Expected 4 hyphens (-) in a string: $s")
    }
    index
  }

  private def parseHex(s: String, from: Int, to: Int): Long = {
    if (to <= from) {
      throw new NumberFormatException(s"An empty component found in $s")
    }
    if (to - from > 16) {
      throw new NumberFormatException(s"Too long component found in $s: ${s.substring(from, to)}")
    }

    parseHexRec(s, from, from, to, 0L)
  }

  @tailrec
  private def parseHexRec(s: String, i: Int, from: Int, to: Int, prevResult: Long): Long = {
    if (i < to) {
      val ch = s.charAt(i)
      val digit = Character.digit(ch, 16)
      if (digit >= 0) {
        parseHexRec(s, i + 1, from, to, prevResult * 16 + digit)
      } else if (i == from && ch == '+') {
        parseHexRec(s, i + 1, from, to, prevResult)
      } else {
        throw new NumberFormatException(s"Unknown character $ch in $s")
      }
    } else {
      prevResult
    }
  }
}
