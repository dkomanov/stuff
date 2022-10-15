package com.komanov.jwt.base64.tests

import com.komanov.jwt.base64.Base64Helper.{Commons, Jdk}
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments

import java.nio.charset.StandardCharsets

class Base64HelperTest extends SpecificationWithJUnit {
  Fragments.foreach[(String, String)](Seq(
    "" -> "",
    "a" -> "YQ==",
    "abcd" -> "YWJjZA==",
    "nmmфывыаывпврапопррло" -> "bm1t0YTRi9Cy0YvQsNGL0LLQv9Cy0YDQsNC/0L7Qv9GA0YDQu9C+",
  )) { case (data, expected) =>
    s"(jdk) regular encode-decode for input: $data" >> {
      val encoded = Jdk.encode(data.getBytes(StandardCharsets.UTF_8))
      str(encoded) mustEqual expected
      val bytes = Jdk.decode(encoded)
      str(bytes) mustEqual data
    }

    s"(commons) regular encode-decode for input: $data" >> {
      val encoded = Commons.encode(data.getBytes(StandardCharsets.UTF_8))
      str(encoded) mustEqual expected
      val bytes = Commons.decode(encoded)
      str(bytes) mustEqual data
    }
  }

  Fragments.foreach[(String, String)](Seq(
    "" -> "",
    "a" -> "YQ",
    "abcd" -> "YWJjZA",
    "nmmфывыаывпврапопррло" -> "bm1t0YTRi9Cy0YvQsNGL0LLQv9Cy0YDQsNC_0L7Qv9GA0YDQu9C-",
  )) { case (data, expected) =>
    s"(jdk) regular encode-decode for input: $data" >> {
      val encoded = Jdk.encodeUrlSafe(data.getBytes(StandardCharsets.UTF_8))
      str(encoded) mustEqual expected
      val bytes = Jdk.decodeUrlSafe(encoded)
      str(bytes) mustEqual data
    }

    s"(commons) regular encode-decode for input: $data" >> {
      val encoded = Commons.encodeUrlSafe(data.getBytes(StandardCharsets.UTF_8))
      str(encoded) mustEqual expected
      val bytes = Commons.decodeUrlSafe(encoded)
      str(bytes) mustEqual data
    }
  }

  private def str(bytes: Array[Byte]): String =
    new String(bytes, StandardCharsets.UTF_8)
}
