package com.komanov.serialization.converters

import java.nio.ByteBuffer
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
    buffer.rewind()
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
