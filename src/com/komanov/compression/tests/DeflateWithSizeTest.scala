package com.komanov.compression.tests

import com.komanov.compression.CompressionAlgorithms
import org.specs2.mutable.SpecificationWithJUnit

class DeflateWithSizeTest extends SpecificationWithJUnit {
  "deflate with size" >> {
    "return empty array for empty input" >> {
      val emptyArray = new Array[Byte](0)
      CompressionAlgorithms.deflateWithSize.encode(emptyArray) mustEqual emptyArray
      CompressionAlgorithms.deflateWithSize.decode(emptyArray) mustEqual emptyArray
    }
  }
}
