package com.komanov.uuid

import java.lang.Long.parseLong
import java.util.UUID

object UuidScala1Utils {

  /**
    * A fast version of java.util.UUID#fromString. Less memory allocations (in JDK implementation there are 6 redundant
    * allocation: array allocation for split and 5 string concatenations "0x" + component[i]).
    */
  def fromStringFast(s: String): UUID = {
    val component1EndIndex = indexOfHyphen(s, 0)
    val component2EndIndex = indexOfHyphen(s, component1EndIndex + 1)
    val component3EndIndex = indexOfHyphen(s, component2EndIndex + 1)
    val component4EndIndex = indexOfHyphen(s, component3EndIndex + 1)
    require(s.indexOf('-', component4EndIndex + 1) == -1, s"Too much hyphens in a string: $s")

    // This is a copy-paste from UUID.fromString implementation
    var mostSigBits: Long = parseLong(s.substring(0, component1EndIndex), 16)
    mostSigBits <<= 16
    mostSigBits |= parseLong(s.substring(component1EndIndex + 1, component2EndIndex), 16)
    mostSigBits <<= 16
    mostSigBits |= parseLong(s.substring(component2EndIndex + 1, component3EndIndex), 16)

    var leastSigBits: Long = parseLong(s.substring(component3EndIndex + 1, component4EndIndex), 16)
    leastSigBits <<= 48
    leastSigBits |= parseLong(s.substring(component4EndIndex + 1), 16)

    new UUID(mostSigBits, leastSigBits)
  }

  private def indexOfHyphen(s: String, from: Int): Int = {
    val index = s.indexOf('-', from)
    require(index != -1, s"Expected 4 hyphens (-) in a string: $s")
    index
  }
}
