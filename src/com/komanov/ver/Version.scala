package com.komanov.ver

import java.util.regex.Pattern
import scala.util.Try

case class Version(major: Int, minor: Int, fix: Int) {
  override def toString: String = s"$major.$minor.$fix"
}

object Version {
  val MaxVersionSize = 10000
  private[ver] val MyRegex = {
    val len = MaxVersionSize.toString.length
    Pattern.compile("(\\d{1," + len + "})\\.(\\d{1," + len + "})\\.(\\d{1," + len + "})")
  }

  def parseYolo(v: String): Option[Version] = Try {
    val numbers@Array(major, minor, fix) = v.split('.').map(_.toInt)
    if (numbers.exists(num => num < 0 || num > MaxVersionSize)) {
      throw new RuntimeException(v)
    }
    Version(major, minor, fix)
  }.toOption

  def parseYoloNoTry(v: String): Option[Version] = {
    try {
      val numbers@Array(major, minor, fix) = v.split('.').map(_.toInt)
      if (numbers.exists(num => num < 0 || num > MaxVersionSize)) {
        throw new RuntimeException(v)
      }
      Some(Version(major, minor, fix))
    } catch {
      case _: Throwable => None
    }
  }

  def parseYoloNoThrow(v: String): Option[Version] = Try {
    val numbers@Array(major, minor, fix) = v.split('.').map(_.toInt)
    if (numbers.exists(num => num < 0 || num > MaxVersionSize))
      None
    else
      Some(Version(major, minor, fix))
  }.toOption.flatten

  def parseYoloNoThrowNoTry(v: String): Option[Version] = {
    try {
      val numbers@Array(major, minor, fix) = v.split('.').map(_.toInt)
      if (numbers.exists(num => num < 0 || num > MaxVersionSize))
        None
      else
        Some(Version(major, minor, fix))
    } catch {
      case _: Throwable => None
    }
  }

  def parseWithRegex(v: String): Option[Version] = {
    val m = MyRegex.matcher(v)
    if (m.matches()) {
      val major = m.group(1).toInt
      val minor = m.group(2).toInt
      val fix = m.group(3).toInt
      if (major > MaxVersionSize || minor > MaxVersionSize || fix > MaxVersionSize)
        None
      else
        Some(Version(major, minor, fix))
    } else {
      None
    }
  }

  def parseOptimized1(v: String): Option[Version] = {
    val index1 = v.indexOf('.')
    if (index1 == -1) {
      return None
    }

    val index2 = v.indexOf('.', index1 + 1)
    if (index2 == -1) {
      return None
    }

    if (v.indexOf('.', index2 + 1) != -1) {
      return None
    }

    if (!isNumber3(v, 0, index1) || !isNumber3(v, index1 + 1, index2) || !isNumber3(v, index2 + 1, v.length)) {
      return None
    }

    val majorStr = v.substring(0, index1)
    val minorStr = v.substring(index1 + 1, index2)
    val fixStr = v.substring(index2 + 1)

    val major = majorStr.toInt
    val minor = minorStr.toInt
    val fix = fixStr.toInt

    if (major > MaxVersionSize || minor > MaxVersionSize || fix > MaxVersionSize)
      None
    else
      Some(Version(major, minor, fix))
  }

  def parseOptimized2(v: String): Option[Version] = {
    val index1 = v.indexOf('.')
    if (index1 == -1) {
      return None
    }

    val index2 = v.indexOf('.', index1 + 1)
    if (index2 == -1) {
      return None
    }

    if (v.indexOf('.', index2 + 1) != -1) {
      return None
    }

    val len = v.length
    if (!isNumber3(v, 0, index1) || !isNumber3(v, index1 + 1, index2) || !isNumber3(v, index2 + 1, len)) {
      return None
    }

    val major = Integer.parseUnsignedInt(v, 0, index1, 10)
    val minor = Integer.parseUnsignedInt(v, index1 + 1, index2, 10)
    val fix = Integer.parseUnsignedInt(v, index2 + 1, len, 10)

    if (major > MaxVersionSize || minor > MaxVersionSize || fix > MaxVersionSize)
      None
    else
      Some(Version(major, minor, fix))
  }

  def parseOptimized3(v: String): Option[Version] = {
    var i = 0
    val len = v.length
    var major = -1
    var minor = -1
    var lastDotIndex = -1

    while (i < len) {
      v.charAt(i) match {
        case '0' | '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' =>
          if (i - (lastDotIndex + 1) > 5) {
            return None
          }
        case '.' =>
          if (major == -1) {
            val parsed = ParseVersionUtil.parseIntSafeEmpty(v, lastDotIndex + 1, i)
            if (parsed == -1 || parsed > MaxVersionSize) {
              return None
            }
            major = parsed
          } else if (minor == -1) {
            val parsed = ParseVersionUtil.parseIntSafeEmpty(v, lastDotIndex + 1, i)
            if (parsed == -1 || parsed > MaxVersionSize) {
              return None
            }
            minor = parsed
          } else {
            return None // 3rd dot
          }
          lastDotIndex = i
        case _ =>
          return None
      }

      i += 1
    }

    if (minor != -1 && major != -1) {
      val parsed = ParseVersionUtil.parseIntSafeEmpty(v, lastDotIndex + 1, len)
      if (parsed == -1 || parsed > MaxVersionSize) {
        return None
      }
      Some(Version(major, minor, parsed))
    } else {
      None
    }
  }

  def parseOptimized3Java(v: String): Option[Version] =
    Option(ParseVersionUtil.parse(v))

  def parseOptimized3JavaNoSwitch(v: String): Option[Version] =
    Option(ParseVersionUtil.parseNoSwitch(v))

  def parseOptimized4(v: String): Option[Version] =
    Option(ParseVersionUtil.parseHardCore(v))

  def parseOptimized5(v: String): Option[Version] =
    Option(ParseVersionUtil.parseHardCore2(v))

  def parseOptimized6(v: String): Option[Version] =
    Option(ParseVersionUtil.parseHardCore3SingleLoop(v))

  def parseOptimized6Scala(v: String): Option[Version] = {
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
          return None
        }
        if (current < 0 || current > MaxVersionSize) {
          return None
        }

        dots match {
          case 0 =>
            major = current
            dots = 1

          case 1 =>
            minor = current
            dots = 2

          case _ =>
            return None
        }
        lastDotIndex = i
        current = 0
      } else if (digit < 0 || digit > 9) {
        return None
      } else { // overflow is possible!
        current = current * 10 + digit
      }
    }

    if (dots != 2 || lastDotIndex == len - 1)
      None
    else if (current < 0 || current > MaxVersionSize)
      None
    else
      Some(Version(major, minor, current))
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
}
