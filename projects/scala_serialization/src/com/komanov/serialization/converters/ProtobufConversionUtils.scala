package com.komanov.serialization.converters

import java.util.UUID

import com.google.protobuf.ByteString

object ProtobufConversionUtils {
  def uuidToBytes(uuid: UUID): ByteString = {
    val bb = ConversionUtils.uuidToByteBuffer(uuid)
    if (bb == null) ByteString.EMPTY else ByteString.copyFrom(bb)
  }

  def bytesToUuid(bs: ByteString): UUID = {
    ConversionUtils.bytesToUuid(bs.asReadOnlyByteBuffer())
  }
}
