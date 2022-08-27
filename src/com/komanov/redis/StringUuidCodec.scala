package com.komanov.redis

import io.lettuce.core.codec.{RedisCodec, StringCodec}

import java.nio.ByteBuffer
import java.util.UUID

class StringUuidCodec extends RedisCodec[String, UUID] {
  override def decodeKey(bytes: ByteBuffer): String =
    StringCodec.ASCII.decodeKey(bytes)

  override def decodeValue(bytes: ByteBuffer): UUID =
    new UUID(bytes.getLong, bytes.getLong)

  override def encodeKey(key: String): ByteBuffer =
    StringCodec.ASCII.encodeKey(key)

  override def encodeValue(value: UUID): ByteBuffer = {
    val bb = ByteBuffer.allocate(16)
    bb.putLong(value.getMostSignificantBits)
    bb.putLong(value.getLeastSignificantBits)
    bb.rewind()
    bb
  }
}

object StringUuidCodec extends StringUuidCodec
