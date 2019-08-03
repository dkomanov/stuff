package com.komanov.serialization.converters

import java.time.Instant

import com.github.plokhotnyuk.jsoniter_scala.core._
import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain.{EntryPoint, PageComponentData, Site, SiteEvent}

/** https://github.com/plokhotnyuk/jsoniter-scala */
object JsoniterScalaConverter extends MyConverter {
  private val writerConfig = WriterConfig()
  private val readerConfig = ReaderConfig()
  private implicit val instantCodec: JsonValueCodec[Instant] = new JsonValueCodec[Instant] {
    override def nullValue: Instant = null

    override def decodeValue(in: JsonReader, default: Instant): Instant = Instant.ofEpochMilli(in.readLong())

    override def encodeValue(x: Instant, out: JsonWriter): Unit = out.writeVal(x.toEpochMilli)
  }
  // TODO FIX IT:
  // Only case classes & case objects are supported for ADT leaf classes. Please consider using of them for ADT with
  // base 'com.komanov.serialization.domain.SiteEvent' or using a custom implicitly accessible codec for the ADT base.
  private implicit val pageComponentDataCodec: JsonValueCodec[PageComponentData] = new JsonValueCodec[PageComponentData] {
    override def decodeValue(in: JsonReader, default: PageComponentData): PageComponentData = default
    override def encodeValue(x: PageComponentData, out: JsonWriter): Unit = {}
    override def nullValue: PageComponentData = null
  }
  private implicit val entryPointCodec: JsonValueCodec[EntryPoint] = new JsonValueCodec[EntryPoint] {
    override def decodeValue(in: JsonReader, default: EntryPoint): EntryPoint = default
    override def encodeValue(x: EntryPoint, out: JsonWriter): Unit = {}
    override def nullValue: EntryPoint = null
  }
  private implicit val siteCodec: JsonValueCodec[Site] = JsonCodecMaker.make[Site](CodecMakerConfig())
  //private implicit val siteEventCodec: JsonValueCodec[SiteEvent] = JsonCodecMaker.make[SiteEvent](CodecMakerConfig())
  private implicit val siteEventCodec: JsonValueCodec[SiteEvent] = new JsonValueCodec[SiteEvent] {
    override def decodeValue(in: JsonReader, default: SiteEvent): SiteEvent = default
    override def encodeValue(x: SiteEvent, out: JsonWriter): Unit = {}
    override def nullValue: SiteEvent = null
  }

  def toByteArray(site: Site): Array[Byte] = writeToArray(site, writerConfig)

  def fromByteArray(bytes: Array[Byte]): Site = readFromArray[Site](bytes, readerConfig)

  def toByteArray(event: SiteEvent): Array[Byte] = writeToArray(event, writerConfig)

  def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = readFromArray[SiteEvent](bytes, readerConfig)
}
