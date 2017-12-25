package com.komanov.readlines

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments

import scala.util.Random

class ReadUtf8Test extends SpecificationWithJUnit {

  Fragments.foreach(List(
    ReadUtfTestCase("charset", bytes => StandardCharsets.UTF_8.decode(ByteBuffer.wrap(bytes)).toString),
    ReadUtfTestCase("localMethodsScala", ReadUtf8Scala.localMethods),
    ReadUtfTestCase("localMethodsIndicesScala", ReadUtf8Scala.localMethodsIndices),
    ReadUtfTestCase("sequentialLoopScala", ReadUtf8Java.sequentialLoop),
    ReadUtfTestCase("changeIndexInsideLoopScala", ReadUtf8Java.changeIndexInsideLoop),
    ReadUtfTestCase("changeIndexInsideLoopByteMagicScala", ReadUtf8Java.changeIndexInsideLoopByteMagic),
    ReadUtfTestCase("sequentialLoopJava", ReadUtf8Java.sequentialLoop),
    ReadUtfTestCase("changeIndexInsideLoopJava", ReadUtf8Java.changeIndexInsideLoop),
    ReadUtfTestCase("changeIndexInsideLoopByteMagicJava", ReadUtf8Java.changeIndexInsideLoopByteMagic)
  )) { case ReadUtfTestCase(name, f) =>
    name should {
      "convert correctly UTF-8 to String" >> {
        val bytes = List(
          0x41,
          0xC3, 0x9F,
          0xE6, 0x9D, 0xB1,
          0xF0, 0x90, 0x90, 0x80
        )
          .map(_.toByte)
          .toArray[Byte]

        f(bytes) mustEqual "Aß東\uD801\uDC00"
      }

      "support all Unicode characters" >> {
        val codePoints = (40 to 0x8040).filter(Character.isValidCodePoint).toArray[Int]
        val s = new String(codePoints, 0, codePoints.length)
        f(s.getBytes(StandardCharsets.UTF_8)) mustEqual s
      }

      "support long lines" >> {
        val s = Random.alphanumeric.take(500).mkString
        f(s.getBytes(StandardCharsets.UTF_8)) mustEqual s
      }
    }
  }

  private case class ReadUtfTestCase(name: String, f: Array[Byte] => String)

}
