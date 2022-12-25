package com.komanov.mysql.blob.tests

import com.komanov.mysql.blob.IntUtils.{readInt, writeInt}
import org.specs2.mutable.SpecificationWithJUnit

import java.io.ByteArrayOutputStream

class IntUtilsTest extends SpecificationWithJUnit {
  "read/writeInt" should {
    def write(v: Int) = {
      val baos = new ByteArrayOutputStream(4)
      writeInt(v, baos)
      baos.toByteArray
    }

    def test(v: Int) = {
      readInt(write(v)) mustEqual v

      val arr = new Array[Byte](4)
      writeInt(v, arr)
      readInt(arr) mustEqual v
    }

    "work" >> {
      test(0)
      test(5)
      test(100)
      test(500)
      test(1000)
      test(10000)
      test(100000)
      test(1000000)
      test(10000000)
      test(100000000)
    }
  }
}
