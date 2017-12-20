package com.komanov.readlines

import java.io.{BufferedReader, CharArrayReader}
import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.{Files, Path}
import java.nio.{ByteBuffer, CharBuffer}
import java.util

object ReadLinesUtils {

  def nioFiles(path: Path): util.List[String] = {
    Files.readAllLines(path)
  }

  def readBytesFirst(path: Path): util.ArrayList[String] = {
    val chars = readChars(path, StandardCharsets.UTF_8)
    val result = new util.ArrayList[String](chars.limit() / 80)
    val reader = new BufferedReader(new CharArrayReader(chars.array(), 0, chars.limit()))
    var line: String = reader.readLine()
    while (line != null) {
      result.add(line)
      line = reader.readLine()
    }
    result
  }

  def readBytesFirstCustom(path: Path): util.ArrayList[String] = {
    val chars = readChars(path, StandardCharsets.UTF_8)
    val result = new util.ArrayList[String](chars.limit() / 80)
    while (chars.hasRemaining) {
      var end = 0
      var ch: Char = '\0'
      do {
        ch = chars.charAt(end)
        end += 1
      } while (chars.remaining() > end && !isNewlineChar(ch))

      if (isNewlineChar(ch)) {
        result.add(if (end == 1) "" else chars.subSequence(0, end - 1).toString)

        def thereIsMore = chars.position() + end < chars.limit()
        if (thereIsMore && ch == '\r' && chars.charAt(end) == '\n') {
          end += 1
        }
      } else {
        result.add(chars.subSequence(0, end).toString)
      }

      chars.position(chars.position() + end)
    }
    result
  }

  def adHocBytes(bytes: Array[Byte], f: String => Unit): Unit = {
    var lineBuffer: Array[Char] = new Array[Char](80)
    var end: Int = -1
    var index = 0

    var bytesEndIndex = bytes.length - 1
    if (bytesEndIndex >= 0 && bytes(bytesEndIndex).toChar == '\n') {
      bytesEndIndex -= 1
      if (bytesEndIndex >= 0 && bytes(bytesEndIndex).toChar == '\r') {
        bytesEndIndex -= 1
      }
    }

    def putPoint(codePoint: Int): Unit = {
      end += Character.toChars(codePoint, lineBuffer, end + 1)
    }

    def reportString(): Unit = {
      val line = if (end == -1) "" else new String(lineBuffer, 0, end + 1)
      f(line)
    }

    while (index <= bytesEndIndex) {
      var c = bytes(index).toInt

      if (end + 1 >= lineBuffer.length) {
        val newBuffer = new Array[Char](lineBuffer.length + 80)
        Array.copy(lineBuffer, 0, newBuffer, 0, end + 1)
        lineBuffer = newBuffer
      }

      if (c > 0) {
        val ch = c.toChar
        if (isNewlineChar(ch)) {
          if (ch == '\r' && index + 1 <= bytesEndIndex && bytes(index).toChar == '\n') {
            index += 1
          }

          reportString()
          end = -1
        } else {
          putPoint(c)
        }

        index += 1
      } else {
        val moreBytesNeeded = if ((c & 0xE0) == 0xC0) {
          c &= 0x1F
          1
        } else if ((c & 0xF0) == 0xE0) {
          c &= 0x0F
          2
        } else if ((c & 0xF8) == 0xF0) {
          c &= 0x07
          3
        } else {
          -1
        }

        if (moreBytesNeeded == -1 || index + moreBytesNeeded > bytesEndIndex) {
          // invalid char OR not enough bytes. Replacing with <?> symbol.
          putPoint(0xFFFD)
          index += 1
        } else {
          for (i <- 1 to moreBytesNeeded) {
            val next: Int = bytes(index + i)
            c = (c << 6) | (next & 0x3F)
          }
          putPoint(c)
          index += 1 + moreBytesNeeded
        }
      }
    }

    if (end != -1 || bytesEndIndex < bytes.length - 1) {
      reportString()
    }
  }

  def adHoc(path: Path, f: String => Unit): Unit = {
    val bytes = Files.readAllBytes(path)
    adHocBytes(bytes, f)
  }

  private def isNewlineChar(ch: Char): Boolean = {
    ch == '\n' || ch == '\r'
  }

  def forEachLine(path: Path, f: String => Unit): Unit = {
    val stream = Files.newBufferedReader(path, StandardCharsets.UTF_8)
    try {
      var line: String = stream.readLine()
      while (line != null) {
        f(line)
        line = stream.readLine()
      }
    } finally {
      stream.close()
    }
  }

  private def readChars(path: Path, cs: Charset): CharBuffer = {
    val bytes = Files.readAllBytes(path)
    val bb = ByteBuffer.wrap(bytes)
    cs.decode(bb)
  }

}
