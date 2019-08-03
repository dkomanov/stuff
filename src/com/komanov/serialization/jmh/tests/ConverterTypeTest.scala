package com.komanov.serialization.jmh.tests

import com.komanov.serialization.converters.Converters
import com.komanov.serialization.jmh.ConverterType
import org.specs2.mutable.SpecWithJUnit

class ConverterTypeTest extends SpecWithJUnit {

  "ConverterType" should {
    "contain all converters" >> {
      ConverterType.values().map(_.converter).toSet mustEqual Converters.all.map(_._2).toSet
    }
  }

}
