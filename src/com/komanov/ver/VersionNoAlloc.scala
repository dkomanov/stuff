package com.komanov.ver

import com.komanov.ver.Version.{MaxVersionSize, MyRegex}

import scala.util.Try

object VersionNoAlloc {
  def parseYolo(v: String): Long = Try {
    val numbers@Array(major, minor, fix) = v.split('.').map(_.toInt)
    if (numbers.exists(num => num < 0 || num > MaxVersionSize)) {
      throw new RuntimeException(v)
    }
    version(major, minor, fix)
  }.toOption.getOrElse(Invalid)

  def parseYoloNoTry(v: String): Long = {
    try {
      val numbers@Array(major, minor, fix) = v.split('.').map(_.toInt)
      if (numbers.exists(num => num < 0 || num > MaxVersionSize)) {
        throw new RuntimeException(v)
      }
      version(major, minor, fix)
    } catch {
      case _: Throwable => Invalid
    }
  }

  def parseYoloNoThrow(v: String): Long = Try {
    val numbers@Array(major, minor, fix) = v.split('.').map(_.toInt)
    if (numbers.exists(num => num < 0 || num > MaxVersionSize))
      Invalid
    else
      version(major, minor, fix)
  }.toOption.getOrElse(Invalid)

  def parseYoloNoThrowNoTry(v: String): Long = {
    try {
      val numbers@Array(major, minor, fix) = v.split('.').map(_.toInt)
      if (numbers.exists(num => num < 0 || num > MaxVersionSize))
        Invalid
      else
        version(major, minor, fix)
    } catch {
      case _: Throwable => Invalid
    }
  }

  def parseWithRegex(v: String): Long = {
    val m = MyRegex.matcher(v)
    if (m.matches()) {
      val major = m.group(1).toInt
      val minor = m.group(2).toInt
      val fix = m.group(3).toInt
      if (major > MaxVersionSize || minor > MaxVersionSize || fix > MaxVersionSize)
        Invalid
      else
        version(major, minor, fix)
    } else {
      Invalid
    }
  }

  def parseOptimized1(v: String): Long = {
    val index1 = v.indexOf('.')
    if (index1 == -1) {
      return Invalid
    }

    val index2 = v.indexOf('.', index1 + 1)
    if (index2 == -1) {
      return Invalid
    }

    if (v.indexOf('.', index2 + 1) != -1) {
      return Invalid
    }

    if (!isNumber3(v, 0, index1) || !isNumber3(v, index1 + 1, index2) || !isNumber3(v, index2 + 1, v.length)) {
      return Invalid
    }

    val majorStr = v.substring(0, index1)
    val minorStr = v.substring(index1 + 1, index2)
    val fixStr = v.substring(index2 + 1)

    val major = majorStr.toInt
    val minor = minorStr.toInt
    val fix = fixStr.toInt

    if (major > MaxVersionSize || minor > MaxVersionSize || fix > MaxVersionSize)
      Invalid
    else
      version(major, minor, fix)
  }

  def parseOptimized2(v: String): Long = {
    val index1 = v.indexOf('.')
    if (index1 == -1) {
      return Invalid
    }

    val index2 = v.indexOf('.', index1 + 1)
    if (index2 == -1) {
      return Invalid
    }

    if (v.indexOf('.', index2 + 1) != -1) {
      return Invalid
    }

    val len = v.length
    if (!isNumber3(v, 0, index1) || !isNumber3(v, index1 + 1, index2) || !isNumber3(v, index2 + 1, len)) {
      return Invalid
    }

    val major = Integer.parseUnsignedInt(v, 0, index1, 10)
    val minor = Integer.parseUnsignedInt(v, index1 + 1, index2, 10)
    val fix = Integer.parseUnsignedInt(v, index2 + 1, len, 10)

    if (major > MaxVersionSize || minor > MaxVersionSize || fix > MaxVersionSize)
      Invalid
    else
      version(major, minor, fix)
  }

  def parseOptimized3(v: String): Long = {
    var i = 0
    val len = v.length
    var major = -1
    var minor = -1
    var lastDotIndex = -1

    while (i < len) {
      v.charAt(i) match {
        case '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' =>
          if (i - (lastDotIndex + 1) > 5) {
            return Invalid
          }
        case '.' =>
          if (major == -1) {
            val parsed = ParseVersionNoAllocUtil.parseIntSafeEmpty(v, lastDotIndex + 1, i)
            if (parsed == -1 || parsed > MaxVersionSize) {
              return Invalid
            }
            major = parsed
          } else if (minor == -1) {
            val parsed = ParseVersionNoAllocUtil.parseIntSafeEmpty(v, lastDotIndex + 1, i)
            if (parsed == -1 || parsed > MaxVersionSize) {
              return Invalid
            }
            minor = parsed
          } else {
            return Invalid // 3rd dot
          }
          lastDotIndex = i
        case _ =>
          return Invalid
      }

      i += 1
    }

    if (minor != -1 && major != -1) {
      val parsed = ParseVersionNoAllocUtil.parseIntSafeEmpty(v, lastDotIndex + 1, len)
      if (parsed == -1 || parsed > MaxVersionSize) {
        return Invalid
      }
      version(major, minor, parsed)
    } else {
      Invalid
    }
  }

  def parseOptimized3Java(v: String): Long =
    ParseVersionNoAllocUtil.parse(v)

  def parseOptimized3JavaNoSwitch(v: String): Long =
    ParseVersionNoAllocUtil.parseNoSwitch(v)

  def parseOptimized4(v: String): Long =
    ParseVersionNoAllocUtil.parseHardCore(v)

  def parseOptimized5(v: String): Long =
    ParseVersionNoAllocUtil.parseHardCore2(v)

  def parseOptimized6(v: String): Long =
    ParseVersionNoAllocUtil.parseHardCore3SingleLoop(v)

  def parseOptimized6Scala(v: String): Long = {
    val len = v.length

    var major = 0
    var minor = 0

    var current = 0
    var dots = 0
    var lastDotIndex = -1

    for (i <- 0 until len) {
      val ch = v.charAt(i)
      val digit = ch - 48
      if (digit == -2) {
        if (lastDotIndex + 1 == i) {
          return Invalid
        }
        if (current < 0 || current > MaxVersionSize) {
          return Invalid
        }

        dots match {
          case 0 =>
            major = current
            dots = 1

          case 1 =>
            minor = current
            dots = 2

          case _ =>
            return Invalid
        }
        lastDotIndex = i
        current = 0
      } else if (digit < 0 || digit > 9) {
        return Invalid
      } else { // overflow is possible!
        current = current * 10 + digit
      }
    }

    if (dots != 2 || lastDotIndex == len - 1)
      Invalid
    else if (current < 0 || current > MaxVersionSize)
      Invalid
    else
      version(major, minor, current)
  }

  private[ver] def isNumber(s: String, from: Int, to: Int): Boolean = {
    // length of MaxVersionSize
    val len = to - from
    if (len <= 0 || len > 5) {
      return false
    }

    var i = from
    while (i < to) {
      if (!Character.isDigit(s.charAt(i))) {
        return false
      }
      i += 1
    }
    true
  }

  private[ver] def isNumber2(s: String, from: Int, to: Int): Boolean = {
    // length of MaxVersionSize
    val len = to - from
    if (len <= 0 || len > 5) {
      return false
    }

    var i = from
    while (i < to) {
      val ch = s.charAt(i)
      if (ch < '0' || ch > '9') {
        return false
      }
      i += 1
    }
    true
  }

  private[ver] def isNumber3(s: String, from: Int, to: Int): Boolean = {
    // length of MaxVersionSize
    val len = to - from
    if (len <= 0 || len > 5) {
      return false
    }

    var i = from
    while (i < to) {
      val ch = s.codePointAt(i)
      val digit = ch - 48
      if (digit < 0 || digit >= 10) {
        return false
      }
      i += 1
    }
    true
  }

  private val BitsPerValue = MaxVersionSize.toBinaryString.length
  require(BitsPerValue * 3 + 1 <= 64, "not enough bits")
  private val InvalidBit = 1L << (3 * BitsPerValue + 1)
  private val ValueMask = (1 << BitsPerValue) - 1
  private val MajorBits = BitsPerValue * 2
  private val MinorBits = BitsPerValue

  private def longValue(v: Int) = (v & ValueMask).toLong

  private def intValue(v: Long, shift: Int) = ((v >> shift) & ValueMask).toInt

  val Invalid: Long = InvalidBit

  def version(major: Int, minor: Int, fix: Int): Long = {
    ((major & 16383).toLong << 28) | ((minor & 16383).toLong << 14) | (fix.toLong & 16383)
  }

  def toVersionGeneric(v: Long): Option[Version] = {
    if ((v & InvalidBit) == InvalidBit) {
      None
    } else {
      Some(Version(
        intValue(v, MajorBits),
        intValue(v, MinorBits),
        intValue(v, 0),
      ))
    }
  }

  def fromVersionGeneric(v: Option[Version]): Long = {
    v.fold(InvalidBit) { v =>
      (longValue(v.major) << MajorBits) | longValue(v.minor) << MinorBits | longValue(v.fix)
    }
  }

  def toVersionInlined(v: Long): Option[Version] = {
    if ((v & 8796093022208L) == 8796093022208L) {
      None
    } else {
      Some(Version(
        ((v >> 28) & 16383).toInt,
        ((v >> 14) & 16383).toInt,
        ((v >> 0) & 16383).toInt,
      ))
    }
  }

  def fromVersionInlined(v: Option[Version]): Long = {
    v.fold(8796093022208L) { v =>
      ((v.major & 16383).toLong << 28) | ((v.minor & 16383).toLong << 14) | (v.fix.toLong & 16383)
    }
  }
}
