package com.komanov.mysql.blob.tests

import com.komanov.mysql.blob.{BlobGenerator, Lz4Utils}
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments

class Lz4UtilsTest extends SpecificationWithJUnit {
  "lz4 compression" should {
    Fragments.foreach(Seq(1, 100, 10000, 1000000)) { len =>
      s"work 2-ways for $len bytes" >> {
        val blob = BlobGenerator.randomBlob(len)
        val compressed = Lz4Utils.compress(blob)
        compressed must not(===(blob))
        Lz4Utils.decompress(compressed) mustEqual blob
      }
    }
  }
}
