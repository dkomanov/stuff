package com.komanov.mysql.blob.tests

import com.komanov.mysql.blob.BlobGenerator
import com.komanov.mysql.blob.Mysql.{mysqlCompress, mysqlDecompress}
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments

class MysqlTest extends SpecificationWithJUnit {
  private val emptyArray = new Array[Byte](0)

  "compression" should {
    "return empty array for empty input" >> {
      mysqlCompress(emptyArray) mustEqual emptyArray
      mysqlDecompress(emptyArray) mustEqual emptyArray
    }

    Fragments.foreach(Seq(1, 100, 10000, 1000000)) { len =>
      s"work 2-ways for $len bytes" >> {
        val blob = BlobGenerator.randomBlob(len)
        val compressed = mysqlCompress(blob)
        compressed must not(===(blob))
        mysqlDecompress(compressed) mustEqual blob
      }
    }
  }
}
