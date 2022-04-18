package com.komanov.serialization.converters.tests

import java.time.Instant
import java.util.UUID

import com.komanov.serialization.converters._
import org.specs2.mutable.SpecWithJUnit

class ConversionsUtilsTest extends SpecWithJUnit {

  "UUID" should {
    "be serialized-parsed" >> {
      val zero = new UUID(0, 0)
      val rnd = UUID.randomUUID()
      ConversionUtils.bytesToUuid(ConversionUtils.uuidToByteBuffer(null)) must beNull
      ConversionUtils.bytesToUuid(ConversionUtils.uuidToByteBuffer(zero)) must be_===(zero)
      ConversionUtils.bytesToUuid(ConversionUtils.uuidToByteBuffer(rnd)) must be_===(rnd)
    }
  }

  "Instant" should {
    "be serialized-parsed" >> {
      val zero = Instant.ofEpochMilli(0)
      val now = Instant.now()
      ConversionUtils.longToInstance(ConversionUtils.instantToLong(zero)) must be_===(zero)
      ConversionUtils.longToInstance(ConversionUtils.instantToLong(now)) must be_===(Instant.ofEpochMilli(now.toEpochMilli))
    }
  }

}
