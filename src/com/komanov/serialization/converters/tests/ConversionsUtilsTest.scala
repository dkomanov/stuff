package com.komanov.serialization.converters.tests

import java.time.Instant
import java.util.UUID

import com.komanov.serialization.converters._

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

class ConversionsUtilsTest extends SpecificationWithJUnit {

  "UUID" should {
    "be serialized-parsed" in new ctx {
      val zero = new UUID(0, 0)
      val rnd = UUID.randomUUID()
      ConversionUtils.bytesToUuid(ConversionUtils.uuidToBytes(null)) must beNull
      ConversionUtils.bytesToUuid(ConversionUtils.uuidToBytes(zero)) must be_===(zero)
      ConversionUtils.bytesToUuid(ConversionUtils.uuidToBytes(rnd)) must be_===(rnd)

      ConversionUtils.bytesToUuid(ConversionUtils.uuidToByteBuffer(null)) must beNull
      ConversionUtils.bytesToUuid(ConversionUtils.uuidToByteBuffer(zero)) must be_===(zero)
      ConversionUtils.bytesToUuid(ConversionUtils.uuidToByteBuffer(rnd)) must be_===(rnd)
    }
  }

  "Instant" should {
    "be serialized-parsed" in new ctx {
      val zero = Instant.ofEpochMilli(0)
      val now = Instant.now()
      ConversionUtils.longToInstance(ConversionUtils.instantToLong(zero)) must be_===(zero)
      ConversionUtils.longToInstance(ConversionUtils.instantToLong(now)) must be_===(now)
    }
  }

  class ctx extends Scope {
  }

}
