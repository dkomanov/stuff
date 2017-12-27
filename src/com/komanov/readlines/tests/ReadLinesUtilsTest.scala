package com.komanov.readlines

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path}
import java.util.function.Consumer

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments
import org.specs2.specification.{After, Scope}

import scala.collection.JavaConverters._

class ReadLinesUtilsTest extends SpecificationWithJUnit {

  sequential

  Fragments.foreach(List(
    IterateTestCase("readAllLines", path => ReadLinesUtils.readAllLines(path).asScala.toList),
    IterateTestCase("readBytesAndThenBufferedReader", path => ReadLinesUtils.readBytesAndThenBufferedReader(path).asScala.toList),
    IterateTestCase("readBytesOwnLineSplit", path => ReadLinesUtils.readBytesOwnLineSplit(path).asScala.toList),
    IterateTestCase("readBytesAndCustomUtf8Decoder", path => {
      val result = List.newBuilder[String]
      ReadLinesUtils.readBytesAndCustomUtf8Decoder(path, result.+=)
      result.result()
    }),
    IterateTestCase("bufferedInputStreamAndCustomUtf8Decoder", path => {
      val result = List.newBuilder[String]
      ReadLinesUtils.bufferedInputStreamAndCustomUtf8Decoder(path, result.+=)
      result.result()
    }),
    IterateTestCase("forEachLine", path => {
      val result = List.newBuilder[String]
      ReadLinesUtils.forEachLine(path, result.+=)
      result.result()
    }),
    IterateTestCase("forEachJava", path => {
      val result = List.newBuilder[String]
      ReadLinesJavaStreams.forEachJava(path, new Consumer[String] {
        override def accept(t: String): Unit = result += t
      })
      result.result()
    })
  )) { case IterateTestCase(name, f) =>
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

  private case class IterateTestCase(name: String, f: Path => List[String])

}
