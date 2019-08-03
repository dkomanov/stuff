package com.komanov.serialization.converters.tests

import java.util.UUID

import com.komanov.serialization.converters.ProtobufConversionUtils
import org.specs2.mutable.SpecWithJUnit

class ProtobufConversionsUtilsTest extends SpecWithJUnit {

  "UUID" should {
    "be serialized-parsed" >> {
      val zero = new UUID(0, 0)
      val rnd = UUID.randomUUID()

      ProtobufConversionUtils.bytesToUuid(ProtobufConversionUtils.uuidToBytes(null)) must beNull
      ProtobufConversionUtils.bytesToUuid(ProtobufConversionUtils.uuidToBytes(zero)) must be_===(zero)
      ProtobufConversionUtils.bytesToUuid(ProtobufConversionUtils.uuidToBytes(rnd)) must be_===(rnd)
    }
  }

}
