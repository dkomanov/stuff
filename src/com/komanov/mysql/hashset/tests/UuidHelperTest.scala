package com.komanov.mysql.hashset.tests

import com.komanov.mysql.hashset.UuidHelper
import org.specs2.mutable.SpecWithJUnit

import java.util.UUID

class UuidHelperTest extends SpecWithJUnit {
  "UuidHelper" should {
    "convert 2-ways" >> {
      val uuid = UUID.randomUUID
      UuidHelper.fromBytes(UuidHelper.toBytes(uuid)) mustEqual uuid
    }

    "have correct binary representation" >> {
      val uuid = UUID.fromString("524ab4a5-a1e8-47bf-8220-6a58569c0a62")
      val array = UuidHelper.toBytes(uuid)
      array mustEqual Array[Byte](
        0x52.toByte,
        0x4a.toByte,
        0xb4.toByte,
        0xa5.toByte,
        0xa1.toByte,
        0xe8.toByte,
        0x47.toByte,
        0xbf.toByte,
        0x82.toByte,
        0x20.toByte,
        0x6a.toByte,
        0x58.toByte,
        0x56.toByte,
        0x9c.toByte,
        0x0a.toByte,
        0x62.toByte,
      )
    }
  }
}
