package com.komanov.uuid

import java.util.UUID

object UuidScalaEpicUtils {

  def fromStringFast(s: String): UUID = {
    val component1EndIndex = 8
    val component2EndIndex = 13
    val component3EndIndex = 18
    val component4EndIndex = 23

    require(s.length == 36, s"Illegal UUID: $s")
    require(s.charAt(component1EndIndex) == '-' &&
      s.charAt(component2EndIndex) == '-' &&
      s.charAt(component3EndIndex) == '-' &&
      s.charAt(component4EndIndex) == '-', s"Expected 4 hyphens (-) in a string: $s")

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

  private def parseHex(s: String, from: Int, to: Int): Long = {
    var i = from
    var result = 0L

    while (i < to) {
      val ch = s.charAt(i)
      i += 1
      val digit = DigitResolver.digit(ch)
      if (digit < 0) {
        throw new NumberFormatException(s"Unknown character $ch in $s")
      }

      result = result * 16 + digit
    }

    result
  }
}
