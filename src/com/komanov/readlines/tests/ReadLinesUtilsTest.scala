package com.komanov.readlines

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path}
import java.util.function.Consumer

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.{After, Scope}
import org.specs2.specification.core.Fragments

import scala.collection.JavaConverters._
import scala.util.Random

class ReadLinesUtilsTest extends SpecificationWithJUnit {

  sequential

  "adHoc" should {
    "convert correctly UTF-8 to String" >> {
      val b = List.newBuilder[String]
      ReadLinesUtils.adHocBytes(
        List(
          0x41,
          0xC3, 0x9F,
          0xE6, 0x9D, 0xB1,
          0xF0, 0x90, 0x90, 0x80
        )
          .map(_.toByte)
          .toArray[Byte],
        b.+=
      )
      b.result() mustEqual List("Aß東\uD801\uDC00")
    }

    "support all Unicode characters" >> {
      val codePoints = (40 to 0x8040).filter(Character.isValidCodePoint).toArray[Int]
      val s = new String(codePoints, 0, codePoints.length)
      val b = List.newBuilder[String]
      ReadLinesUtils.adHocBytes(
        s.getBytes(StandardCharsets.UTF_8),
        b.+=
      )
      b.result() mustEqual List(s)
    }

    "support long lines" >> {
      val b = List.newBuilder[String]
      val line = Random.alphanumeric.take(500).mkString
      ReadLinesUtils.adHocBytes(
        line.getBytes(StandardCharsets.UTF_8),
        b.+=
      )
      b.result() mustEqual List(line)
    }
  }

  Fragments.foreach(List(
    TestCase("nioFiles", path => ReadLinesUtils.nioFiles(path).asScala.toList),
    TestCase("readBytesFirst", path => ReadLinesUtils.readBytesFirst(path).asScala.toList),
    TestCase("readBytesFirstCustom", path => ReadLinesUtils.readBytesFirstCustom(path).asScala.toList),
    TestCase("adHoc", path => {
      val result = List.newBuilder[String]
      ReadLinesUtils.adHoc(path, result.+=)
      result.result()
    }),
    TestCase("forEachLine", path => {
      val result = List.newBuilder[String]
      ReadLinesUtils.forEachLine(path, result.+=)
      result.result()
    }),
    TestCase("forEachJava", path => {
      val result = List.newBuilder[String]
      ReadLinesJavaStreams.forEachJava(path, new Consumer[String] {
        override def accept(t: String): Unit = result += t
      })
      result.result()
    })
  )) { case TestCase(name, f) =>
    name should {
      "succeed for empty file" in new ctx {
        f(path) must beEmpty
      }

      "treat single newline as empty line" in new ctx {
        givenFileContent("\n")
        f(path) mustEqual List("")
      }

      "process single line without newline" in new ctx {
        givenFileContent("line")
        f(path) must be_===(List("line"))
      }

      "process single line with newline as a single line" in new ctx {
        givenFileContent("line\n")
        f(path) must be_===(List("line"))
      }

      "process single line with newline2 as a single line" in new ctx {
        givenFileContent("line\r")
        f(path) must be_===(List("line"))
      }

      "process single line with newline3 as a single line" in new ctx {
        givenFileContent("line\r\n")
        f(path) mustEqual List("line")
      }

      "support empty lines" in new ctx {
        givenFileContent("\nline1\n\nline2\n\n")
        f(path) must be_===(List("", "line1", "", "line2", ""))
      }
    }
  }

  private abstract class ctx extends Scope with After {
    val path = Files.createTempFile("ReadLinesUtilsTest", "test")

    def givenFileContent(s: String): Unit = {
      Files.write(path, s.getBytes(StandardCharsets.UTF_8))
    }

    override def after: Any = {
      Files.deleteIfExists(path)
    }
  }

  private case class TestCase(name: String, f: Path => List[String])

}
