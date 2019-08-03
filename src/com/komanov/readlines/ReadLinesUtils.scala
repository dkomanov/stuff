package com.komanov.readlines

import java.io.{BufferedInputStream, BufferedReader, CharArrayReader, EOFException}
import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.{Files, Path}
import java.nio.{ByteBuffer, CharBuffer}
import java.util

import com.komanov.readlines.ReadUtf8Scala.MyCharBuffer

object ReadLinesUtils {

  def readAllLines(path: Path): util.List[String] = {
    Files.readAllLines(path)
  }

  def readBytesAndThenBufferedReader(path: Path): util.ArrayList[String] = {
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

  def readBytesOwnLineSplit(path: Path): util.ArrayList[String] = {
    val chars = readChars(path, StandardCharsets.UTF_8)
    val result = new util.ArrayList[String](chars.limit() / 80)
    while (chars.hasRemaining) {
      var end = 0
      var ch: Char = '\u0000'
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

  def decodeUtf8FromBytes(bytes: Array[Byte], f: String => Unit): Unit = {
    val buffer = new MyCharBuffer(85)
    var i = 0

    val length: Int = {
      if (bytes.endsWith(List('\r', '\n').map(_.toByte))) {
        bytes.length - 2
      } else if (bytes.length > 0 && isNewlineChar(bytes.last.toChar)) {
        bytes.length - 1
      } else {
        bytes.length
      }
    }

    while (i < length) {
      val b1 = bytes(i)
      if (b1 > 0) {
        val ch = b1.toChar
        if (isNewlineChar(ch)) {
          if (ch == '\r' && i + 1 < length && bytes(i + 1).toChar == '\n') {
            i += 1
          }

          f(buffer.toString)
          buffer.reset()
        } else {
          buffer.putSingle(b1)
        }
        i += 1
      } else if ((b1 >> 5) == -2 && (b1 & 0x1e) != 0) {
        assert(i + 1 < bytes.length)
        val b2 = bytes(i + 1)
        buffer.putSingle((((b1 << 6) ^ b2) ^ ((0xC0.toByte << 6) ^ (0x80.toByte << 0))).toChar)
        i += 2
      } else if ((b1 >> 4) == -2) {
        assert(i + 2 < bytes.length)
        val b2 = bytes(i + 1)
        val b3 = bytes(i + 2)
        buffer.putSingle(((b1 << 12) ^ (b2 << 6) ^ (b3 ^ ((0xE0.toByte << 12) ^ (0x80.toByte << 6) ^ (0x80.toByte << 0)))).toChar)
        i += 3
      } else if ((b1 >> 3) == -2) {
        assert(i + 3 < bytes.length)
        val b2 = bytes(i + 1)
        val b3 = bytes(i + 2)
        val b4 = bytes(i + 3)
        buffer.putDouble((b1 << 18) ^ (b2 << 12) ^ (b3 << 6) ^ (b4 ^ ((0xF0.toByte << 18) ^ (0x80.toByte << 12) ^ (0x80.toByte << 6) ^ (0x80.toByte << 0))))
        i += 4
      } else {
        buffer.putBadChar()
        i += 1
      }
    }

    if (buffer.getCount > 0 || length < bytes.length) {
      f(buffer.toString)
    }
  }

  def readBytesAndCustomUtf8Decoder(path: Path, f: String => Unit): Unit = {
    val bytes = Files.readAllBytes(path)
    decodeUtf8FromBytes(bytes, f)
  }

  private class MyStream(val s: BufferedInputStream) {
    def nextInt: Int = s.read()

    def nextByteOrThrow: Int = {
      val b = s.read()
      if (b == -1) {
        throw new EOFException("unexpected end of stream")
      }
      b.toByte.toInt
    }
  }

  def bufferedInputStreamAndCustomUtf8Decoder(path: Path, f: String => Unit): Unit = {
    val stream = new MyStream(new BufferedInputStream(Files.newInputStream(path)))
    try {
      val buffer = new MyCharBuffer(85)

      var currentByte = stream.nextInt
      while (currentByte != -1) {
        val b1 = currentByte.toByte.toInt
        if (b1 > 0) {
          val ch = b1.toChar
          if (isNewlineChar(ch)) {
            if (ch == '\r') {
              currentByte = stream.nextInt
              if (currentByte != -1 && currentByte.toByte.toInt.toChar == '\n') {
                currentByte = stream.nextInt
              }
            } else {
              currentByte = stream.nextInt
            }

            f(buffer.toString)
            buffer.reset()
          } else {
            buffer.putSingle(b1)
            currentByte = stream.nextInt
          }
        } else if ((b1 >> 5) == -2 && (b1 & 0x1e) != 0) {
          val b2 = stream.nextByteOrThrow
          buffer.putSingle((((b1 << 6) ^ b2) ^ ((0xC0.toByte << 6) ^ (0x80.toByte << 0))).toChar)
          currentByte = stream.nextInt
        } else if ((b1 >> 4) == -2) {
          val b2 = stream.nextByteOrThrow
          val b3 = stream.nextByteOrThrow
          buffer.putSingle(((b1 << 12) ^ (b2 << 6) ^ (b3 ^ ((0xE0.toByte << 12) ^ (0x80.toByte << 6) ^ (0x80.toByte << 0)))).toChar)
          currentByte = stream.nextInt
        } else if ((b1 >> 3) == -2) {
          val b2 = stream.nextByteOrThrow
          val b3 = stream.nextByteOrThrow
          val b4 = stream.nextByteOrThrow
          buffer.putDouble((b1 << 18) ^ (b2 << 12) ^ (b3 << 6) ^ (b4 ^ ((0xF0.toByte << 18) ^ (0x80.toByte << 12) ^ (0x80.toByte << 6) ^ (0x80.toByte << 0))))
          currentByte = stream.nextInt
        } else {
          buffer.putBadChar()
          currentByte = stream.nextInt
        }
      }

      if (buffer.getCount > 0) {
        f(buffer.toString)
      }
    } finally {
      stream.s.close()
    }
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
