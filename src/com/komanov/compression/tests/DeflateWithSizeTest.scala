package com.komanov.compression.tests

import com.komanov.compression.{CompressionAlgorithms, DeflatePlusSize}
import org.specs2.mutable.SpecificationWithJUnit

class DeflateWithSizeTest extends SpecificationWithJUnit {
  "deflate with size" >> {
    "return empty array for empty input" >> {
      val emptyArray = new Array[Byte](0)
      DeflatePlusSize.INSTANCE.encode(emptyArray) mustEqual emptyArray
      DeflatePlusSize.INSTANCE.decode(emptyArray) mustEqual emptyArray
    }
  }
}
