package com.komanov.uuid

import java.util.UUID

object UuidScala2Utils {

  def fromStringFast(s: String): UUID = {
    val component1EndIndex = indexOfHyphen(s, 0)
    val component2EndIndex = indexOfHyphen(s, component1EndIndex + 1)
    val component3EndIndex = indexOfHyphen(s, component2EndIndex + 1)
    val component4EndIndex = indexOfHyphen(s, component3EndIndex + 1)
    require(s.indexOf('-', component4EndIndex + 1) == -1, s"Too much hyphens in a string: $s")

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
    require(index != -1, s"Expected 4 hyphens (-) in a string: $s")
    index
  }

  private def parseHex(s: String, from: Int, to: Int): Long = {
    if (to <= from) {
      throw new NumberFormatException(s"An empty component found in $s")
    }
    if (to - from > 16) {
      throw new NumberFormatException(s"Too long component found in $s: ${s.substring(from, to)}")
    }

    var i = from
    var result = 0L

    while (i < to) {
      val ch = s.charAt(i)
      i += 1
      val digit = Character.digit(ch, 16)
      if (digit < 0) {
        if (i == from && ch == '+') {
          // allow the first + sign for backward compatibility with UUID.fromString
        } else {
          throw new NumberFormatException(s"Unknown character $ch in $s")
        }
      } else {
        result = result * 16 + digit
      }
    }

    result
  }
}
