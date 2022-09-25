package com.komanov.serialization.converters

import java.nio.{Buffer, ByteBuffer}
import java.time.Instant
import java.util.UUID

object ConversionUtils {

  def uuidToByteBuffer(uuid: UUID): ByteBuffer = {
    if (uuid == null) {
      return null
    }

    val buffer = ByteBuffer.allocate(16)
    buffer.putLong(uuid.getMostSignificantBits)
    buffer.putLong(uuid.getLeastSignificantBits)
    // asInstanceOf is for JDK8 support: java.lang.NoSuchMethodError: java.nio.ByteBuffer.rewind()Ljava/nio/ByteBuffer;
    // After JDK8 it was overridden to return ByteBuffer instead of Buffer.
    // https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/nio/ByteBuffer.html#rewind()
    // https://docs.oracle.com/javase/8/docs/api/java/nio/ByteBuffer.html
    buffer.asInstanceOf[Buffer].rewind()
    buffer
  }

  def bytesToUuid(bb: ByteBuffer): UUID = {
    if (bb == null) {
      return null
    }

    val length = bb.limit() - bb.position()
    if (length == 0) {
      return null
    }

    require(length >= 16, s"expected 16 bytes: ${bb.capacity()} / ${bb.limit()}")

    new UUID(bb.getLong, bb.getLong)
  }

  def instantToLong(v: Instant) = v.toEpochMilli

  def longToInstance(v: Long) = Instant.ofEpochMilli(v)

}
