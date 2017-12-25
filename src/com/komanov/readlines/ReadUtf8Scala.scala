package com.komanov.readlines

import java.util

object ReadUtf8Scala {

  def localMethods(bytes: Array[Byte]): String = {
    var buffer: Array[Char] = new Array[Char](bytes.length * 2)
    var count: Int = 0
    var cp: Int = 0
    var bytesMore: Int = 0

    def putPoint(codePoint: Int, len: Int): Unit = {
      if (count + len > buffer.length) {
        val newBuffer = new Array[Char](buffer.length * 2)
        Array.copy(buffer, 0, newBuffer, 0, count)
        buffer = newBuffer
      }

      if (len == 1) {
        buffer.update(count, codePoint.toChar)
        count += 1
      } else {
        count += Character.toChars(codePoint, buffer, count)
      }

      cp = 0
      bytesMore = 0
    }

    for (index <- 0 until bytes.length) {
      val c = bytes(index).toInt

      if (c > 0) {
        if (cp != 0) {
          putPoint(cp, 2)
        }
        putPoint(c, 1)
      } else {
        if (cp == 0 && bytesMore == 0) {
          if ((c & 0xE0) == 0xC0) {
            cp = c & 0x1F
            bytesMore = 1
          } else if ((c & 0xF0) == 0xE0) {
            cp = c & 0x0F
            bytesMore = 2
          } else if ((c & 0xF8) == 0xF0) {
            cp = c & 0x07
            bytesMore = 3
          } else {
            putPoint(0xFFFD, 2)
          }
        } else {
          cp = (cp << 6) | (c & 0x3F)
          bytesMore -= 1
          if (bytesMore == 0) {
            putPoint(cp, 2)
          }
        }
      }
    }

    if (cp != 0) {
      putPoint(0xFFFD, 2)
    }

    new String(buffer, 0, count)
  }

  def localMethodsIndices(bytes: Array[Byte]): String = {
    var buffer: Array[Char] = new Array[Char](bytes.length * 2)
    var count: Int = 0
    var cp: Int = 0
    var bytesMore: Int = 0

    def putPoint(codePoint: Int, len: Int): Unit = {
      if (count + len > buffer.length) {
        val newBuffer = new Array[Char](buffer.length * 2)
        Array.copy(buffer, 0, newBuffer, 0, count)
        buffer = newBuffer
      }

      if (len == 1) {
        buffer.update(count, codePoint.toChar)
        count += 1
      } else {
        count += Character.toChars(codePoint, buffer, count)
      }

      cp = 0
      bytesMore = 0
    }

    for (index <- bytes.indices) {
      val c = bytes(index).toInt

      if (c > 0) {
        if (cp != 0) {
          putPoint(cp, 2)
        }
        putPoint(c, 1)
      } else {
        if (cp == 0 && bytesMore == 0) {
          if ((c & 0xE0) == 0xC0) {
            cp = c & 0x1F
            bytesMore = 1
          } else if ((c & 0xF0) == 0xE0) {
            cp = c & 0x0F
            bytesMore = 2
          } else if ((c & 0xF8) == 0xF0) {
            cp = c & 0x07
            bytesMore = 3
          } else {
            putPoint(0xFFFD, 2)
          }
        } else {
          cp = (cp << 6) | (c & 0x3F)
          bytesMore -= 1
          if (bytesMore == 0) {
            putPoint(cp, 2)
          }
        }
      }
    }

    if (cp != 0) {
      putPoint(0xFFFD, 2)
    }

    new String(buffer, 0, count)
  }

  def sequentialLoop(bytes: Array[Byte]): String = {
    val buffer = new MyCharBuffer(bytes.length)
    var cp = 0
    var bytesMore = 0
    for (c <- bytes) {
      if (c > 0) {
        if (bytesMore != 0) {
          buffer.putBadChar()
          bytesMore = 0
        }
        buffer.putSingle(c)
      }
      else if (bytesMore == 0) if ((c & 0xE0) == 0xC0) {
        cp = c & 0x1F
        bytesMore = 1
      }
      else if ((c & 0xF0) == 0xE0) {
        cp = c & 0x0F
        bytesMore = 2
      }
      else if ((c & 0xF8) == 0xF0) {
        cp = c & 0x07
        bytesMore = 3
      }
      else buffer.putBadChar()
      else {
        cp = (cp << 6) | (c & 0x3F)
        if ( {bytesMore -= 1; bytesMore} == 0) {
          buffer.putPoint(cp)
          cp = 0
        }
      }
    }
    if (cp != 0 || bytesMore != 0) buffer.putBadChar()
    buffer.toString
  }

  def changeIndexInsideLoop(bytes: Array[Byte]): String = {
    val buffer = new MyCharBuffer(bytes.length)
    var i = 0
    val length = bytes.length
    while ( {i < length}) {
      val c = bytes(i)
      if (c > 0) {
        buffer.putSingle(c)
        i += 1
      }
      else if ((c & 0xE0) == 0xC0) {
        assert(i + 1 < bytes.length)
        val b1 = c & 0x1F
        val b2 = (b1 << 6) | (bytes(i + 1).toInt & 0x3F)
        buffer.putSingle(b2)
        i += 2
      }
      else if ((c & 0xF0) == 0xE0) {
        assert(i + 2 < bytes.length)
        val b1 = c & 0x0F
        val b2 = (b1 << 6) | (bytes(i + 1).toInt & 0x3F)
        val b3 = (b2 << 6) | (bytes(i + 2).toInt & 0x3F)
        buffer.putSingle(b3)
        i += 3
      }
      else if ((c & 0xF8) == 0xF0) {
        assert(i + 3 < bytes.length)
        val b1 = c & 0x0F
        val b2 = (b1 << 6) | (bytes(i + 1).toInt & 0x3F)
        val b3 = (b2 << 6) | (bytes(i + 2).toInt & 0x3F)
        val b4 = (b3 << 6) | (bytes(i + 3).toInt & 0x3F)
        buffer.putPoint(b4)
        i += 4
      }
      else {
        buffer.putBadChar()
        i += 1
      }
    }
    buffer.toString
  }

  def changeIndexInsideLoopByteMagic(bytes: Array[Byte]): String = {
    val buffer = new MyCharBuffer(bytes.length)
    var i = 0
    val length = bytes.length
    while ( {i < length}) {
      val b1 = bytes(i)
      if (b1 > 0) {
        buffer.putSingle(b1)
        i += 1
      }
      else if ((b1 >> 5) == -2 && (b1 & 0x1e) != 0) {
        assert(i + 1 < bytes.length)
        val b2 = bytes(i + 1)
        buffer.putSingle((((b1 << 6) ^ b2) ^ ((0xC0.toByte << 6) ^ (0x80.toByte << 0))).toChar)
        i += 2
      }
      else if ((b1 >> 4) == -2) {
        assert(i + 2 < bytes.length)
        val b2 = bytes(i + 1)
        val b3 = bytes(i + 2)
        buffer.putSingle(((b1 << 12) ^ (b2 << 6) ^ (b3 ^ ((0xE0.toByte << 12) ^ (0x80.toByte << 6) ^ (0x80.toByte << 0)))).toChar)
        i += 3
      }
      else if ((b1 >> 3) == -2) {
        assert(i + 3 < bytes.length)
        val b2 = bytes(i + 1)
        val b3 = bytes(i + 2)
        val b4 = bytes(i + 3)
        buffer.putDouble((b1 << 18) ^ (b2 << 12) ^ (b3 << 6) ^ (b4 ^ ((0xF0.toByte << 18) ^ (0x80.toByte << 12) ^ (0x80.toByte << 6) ^ (0x80.toByte << 0))))
        i += 4
      }
      else {
        buffer.putBadChar()
        i += 1
      }
    }
    buffer.toString
  }

  private class MyCharBuffer(val len: Int) {
    private var buffer = new Array[Char]((len * 1.1f).toInt)
    private var count = 0

    override def toString = new String(buffer, 0, count)

    def putSingle(codePoint: Int): Unit = {
      ensureLength(1)
      buffer(count) = codePoint.toChar
      count += 1
    }

    def putDouble(codePoint: Int): Unit = {
      ensureLength(2)
      buffer(count + 0) = Character.highSurrogate(codePoint)
      buffer(count + 1) = Character.lowSurrogate(codePoint)
      count += 2
    }

    def putPoint(codePoint: Int): Unit = {
      if (Character.isBmpCodePoint(codePoint)) putSingle(codePoint)
      else if (Character.isValidCodePoint(codePoint)) putDouble(codePoint)
      else throw new IllegalStateException
    }

    def putBadChar(): Unit = {
      putPoint(0xFFFD)
    }

    private def ensureLength(len: Int): Unit = {
      if (count + len > buffer.length) {
        buffer = util.Arrays.copyOf(buffer, buffer.length + 100)
      }
    }
  }

}
