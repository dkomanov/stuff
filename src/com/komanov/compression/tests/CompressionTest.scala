package com.komanov.compression.tests

import com.komanov.compression.CompressionAlgorithms
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments

import java.util
import scala.util.Random

class CompressionTest extends SpecificationWithJUnit {

  sequential

  "encode/decode" >> {
    Fragments.foreach(for {
      method <- CompressionAlgorithms.values()
      data <- Seq(
        "".getBytes(),
        "a".getBytes(),
        Random.alphanumeric.take(100).mkString.getBytes,
        Random.alphanumeric.take(128 * 1024).mkString.getBytes,
        randomBytes(1000),
        randomBytes(128 * 1024),
      )
    } yield (method, data)) { case (method, data) =>
      s"work 2 way for ${data.length} bytes [${method.name}]" >> {
        val encoded = method.encode(util.Arrays.copyOf(data, data.length))
        encoded mustNotEqual data
        method.decode(encoded) mustEqual data
      }
    }
  }

  private def randomBytes(len: Int): Array[Byte] = {
    val bytes = new Array[Byte](len)
    Random.nextBytes(bytes)
    bytes
  }
}
