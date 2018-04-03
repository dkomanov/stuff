package com.komanov.serialization.converters

import java.time.Instant

import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain.{Site, SiteEvent}

/** https://github.com/plokhotnyuk/jsoniter-scala */
object JsoniterScalaConverter extends MyConverter {
  private[this] val writerConfig = WriterConfig()
  private[this] val readerConfig = ReaderConfig()
  private[this] implicit val instantCodec: JsonValueCodec[Instant] = new JsonValueCodec[Instant] {
    override def nullValue: Instant = null

    override def decodeValue(in: JsonReader, default: Instant): Instant = Instant.ofEpochMilli(in.readLong())

    override def encodeValue(x: Instant, out: JsonWriter): Unit = out.writeVal(x.toEpochMilli)
  }
  private[this] implicit val siteCodec: JsonValueCodec[Site] = JsonCodecMaker.make[Site](CodecMakerConfig())
  private[this] implicit val siteEventCodec: JsonValueCodec[SiteEvent] = JsonCodecMaker.make[SiteEvent](CodecMakerConfig())

  def toByteArray(site: Site): Array[Byte] = writeToArray(site, writerConfig)

  def fromByteArray(bytes: Array[Byte]): Site = readFromArray[Site](bytes, readerConfig)

  def toByteArray(event: SiteEvent): Array[Byte] = writeToArray(event, writerConfig)

  def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = readFromArray[SiteEvent](bytes, readerConfig)
}
