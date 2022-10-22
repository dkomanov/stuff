package com.komanov.jwt.base64.jni.tests

import com.komanov.jwt.base64.jni
import org.specs2.mutable.SpecWithJUnit

class NativeBazelTest extends NativeTestBase(jni.NativeBazel.INSTANCE)

// To do actual test we need to put proper path to library.path
class NativeCargoTest extends NativeTestBase(jni.NativeCargo.INSTANCE)

abstract class NativeTestBase(lib: jni.Native) extends SpecWithJUnit {

  private val encoded = "YWJj".getBytes()
  private val decoded = "abc".getBytes()

  "encode" >> {
    "succeed encodeConfigUrlSafe" >> {
      lib.encodeConfigUrlSafe(decoded) mustEqual encoded
    }
    "succeed encodeConfigSlice1NoCache" >> {
      lib.encodeConfigSlice1NoCache(decoded) mustEqual encoded
    }
    "succeed encodeConfigSlice1Cache" >> {
      lib.encodeConfigSlice1Cache(decoded) mustEqual encoded
    }
    "succeed encodeConfigSlice2CacheInputOutput" >> {
      lib.encodeConfigSlice1Cache(decoded) mustEqual encoded
    }
    "succeed encodeSimd" >> {
      lib.encodeSimd(decoded) mustEqual encoded
    }
  }

  "decode" >> {
    "succeed decodeConfigUrlSafe1" >> {
      lib.decodeConfigUrlSafe1(encoded) mustEqual decoded
    }
    "succeed decodeConfigUrlSafe2" >> {
      lib.decodeConfigUrlSafe2(encoded) mustEqual decoded
    }
    "succeed decodeConfigUrlSafe3" >> {
      lib.decodeConfigUrlSafe3(encoded, encoded.length) mustEqual decoded
    }
    "succeed decodeConfigUrlSafe4" >> {
      lib.decodeConfigUrlSafe4(encoded, encoded.length) mustEqual decoded
    }
    "succeed decodeConfigSliceUrlSafe1" >> {
      lib.decodeConfigSliceUrlSafe1(encoded, encoded.length) mustEqual decoded
    }
    "succeed decodeConfigSliceUrlSafe2NoCache" >> {
      lib.decodeConfigSliceUrlSafe2NoCache(encoded) mustEqual decoded
    }
    "succeed decodeConfigSliceUrlSafe2Cache" >> {
      lib.decodeConfigSliceUrlSafe2Cache(encoded) mustEqual decoded
    }
    "succeed decodeConfigSliceUrlSafe3CacheInputOutput" >> {
      lib.decodeConfigSliceUrlSafe3CacheInputOutput(encoded) mustEqual decoded
    }
    "succeed decodeSimd" >> {
      lib.decodeSimd(encoded) mustEqual decoded
    }
    "succeed decodeSimdInPlace" >> {
      lib.decodeSimdInPlace(encoded) mustEqual decoded
    }
  }
}
