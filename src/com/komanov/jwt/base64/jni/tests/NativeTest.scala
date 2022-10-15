package com.komanov.jwt.base64.jni.tests

import com.komanov.jwt.base64.jni.Native
import org.specs2.mutable.SpecWithJUnit

class NativeTest extends SpecWithJUnit {

  private val encoded = "YWJj".getBytes()
  private val decoded = "abc".getBytes()

  "encode" >> {
    "succeed encodeConfigUrlSafe" >> {
      Native.encodeConfigUrlSafe(decoded) mustEqual encoded
    }
    "succeed encodeConfigSlice1NoCache" >> {
      Native.encodeConfigSlice1NoCache(decoded) mustEqual encoded
    }
    "succeed encodeConfigSlice1Cache" >> {
      Native.encodeConfigSlice1Cache(decoded) mustEqual encoded
    }
    "succeed encodeConfigSlice2CacheInputOutput" >> {
      Native.encodeConfigSlice1Cache(decoded) mustEqual encoded
    }
  }

  "decode" >> {
    "succeed decodeConfigUrlSafe1" >> {
      Native.decodeConfigUrlSafe1(encoded) mustEqual decoded
    }
    "succeed decodeConfigUrlSafe2" >> {
      Native.decodeConfigUrlSafe2(encoded) mustEqual decoded
    }
    "succeed decodeConfigUrlSafe3" >> {
      Native.decodeConfigUrlSafe3(encoded, encoded.length) mustEqual decoded
    }
    "succeed decodeConfigUrlSafe4" >> {
      Native.decodeConfigUrlSafe4(encoded, encoded.length) mustEqual decoded
    }
    "succeed decodeConfigSliceUrlSafe1" >> {
      Native.decodeConfigSliceUrlSafe1(encoded, encoded.length) mustEqual decoded
    }
    "succeed decodeConfigSliceUrlSafe2NoCache" >> {
      Native.decodeConfigSliceUrlSafe2NoCache(encoded) mustEqual decoded
    }
    "succeed decodeConfigSliceUrlSafe2Cache" >> {
      Native.decodeConfigSliceUrlSafe2Cache(encoded) mustEqual decoded
    }
    "succeed decodeConfigSliceUrlSafe3CacheInputOutput" >> {
      Native.decodeConfigSliceUrlSafe3CacheInputOutput(encoded) mustEqual decoded
    }
  }
}
