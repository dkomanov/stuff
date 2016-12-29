package com.komanov.serialization.converters

import java.nio.ByteBuffer
import java.time.Instant
import java.util.UUID

import com.google.protobuf.ByteString

object ConversionUtils {

  def uuidToBytes(uuid: UUID): ByteString = {
    val bb = uuidToByteBuffer(uuid)
    if (bb == null) ByteString.EMPTY else ByteString.copyFrom(bb)
  }

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

  def bytesToUuid(bs: ByteString): UUID = {
    bytesToUuid(bs.asReadOnlyByteBuffer())
  }

  def instantToLong(v: Instant) = v.toEpochMilli

  def longToInstance(v: Long) = Instant.ofEpochMilli(v)

}
