package com.komanov.serialization.converters

import java.nio.charset.StandardCharsets
import java.time.Instant

import com.komanov.serialization.domain._
import io.circe._
import io.circe.generic.semiauto._
import io.circe.parser._
import io.circe.syntax._

import scala.util.control.NonFatal

/** https://circe.github.io/circe/ */
object CirceConverter extends MyConverter {

  implicit val encodeInstant: Encoder[Instant] = Encoder.encodeLong.contramap[Instant](_.toEpochMilli)
  implicit val decodeInstant: Decoder[Instant] = decodeLong(Instant.ofEpochMilli)

  implicit val encodePageComponentType: Encoder[PageComponentType] = Encoder.encodeString.contramap[PageComponentType](_.toString)
  implicit val decodePageComponentType: Decoder[PageComponentType] = decodeString(PageComponentType.valueOf)

  implicit val encodeSiteFlag: Encoder[SiteFlag] = Encoder.encodeString.contramap[SiteFlag](_.toString)
  implicit val decodeSiteFlag: Decoder[SiteFlag] = decodeString(SiteFlag.valueOf)

  implicit val encodeSiteType: Encoder[SiteType] = Encoder.encodeString.contramap[SiteType](_.toString)
  implicit val decodeSiteType: Decoder[SiteType] = decodeString(SiteType.valueOf)

  implicit val encodeDomain: Encoder[Domain] = deriveEncoder[Domain]
  implicit val decodeDomain: Decoder[Domain] = deriveDecoder[Domain]
  implicit val encodeMetaTag: Encoder[MetaTag] = deriveEncoder[MetaTag]
  implicit val decodeMetaTag: Decoder[MetaTag] = deriveDecoder[MetaTag]
  implicit val encodePage: Encoder[Page] = deriveEncoder[Page]
  implicit val decodePage: Decoder[Page] = deriveDecoder[Page]
  implicit val encodeDomainEntryPoint: Encoder[DomainEntryPoint] = deriveEncoder[DomainEntryPoint]
  implicit val decodeDomainEntryPoint: Decoder[DomainEntryPoint] = deriveDecoder[DomainEntryPoint]
  implicit val encodeFreeEntryPoint: Encoder[FreeEntryPoint] = deriveEncoder[FreeEntryPoint]
  implicit val decodeFreeEntryPoint: Decoder[FreeEntryPoint] = deriveDecoder[FreeEntryPoint]
  implicit val encodeEntryPoint: Encoder[EntryPoint] = deriveEncoder[EntryPoint]
  implicit val decodeEntryPoint: Decoder[EntryPoint] = deriveDecoder[EntryPoint]
  implicit val encodeTextComponentData: Encoder[TextComponentData] = deriveEncoder[TextComponentData]
  implicit val decodeTextComponentData: Decoder[TextComponentData] = deriveDecoder[TextComponentData]
  implicit val encodeButtonComponentData: Encoder[ButtonComponentData] = deriveEncoder[ButtonComponentData]
  implicit val decodeButtonComponentData: Decoder[ButtonComponentData] = deriveDecoder[ButtonComponentData]
  implicit val encodeBlogComponentData: Encoder[BlogComponentData] = deriveEncoder[BlogComponentData]
  implicit val decodeBlogComponentData: Decoder[BlogComponentData] = deriveDecoder[BlogComponentData]
  implicit val encodePageComponentData: Encoder[PageComponentData] = deriveEncoder[PageComponentData]
  implicit val decodePageComponentData: Decoder[PageComponentData] = deriveDecoder[PageComponentData]
  implicit val encodePageComponentPosition: Encoder[PageComponentPosition] = deriveEncoder[PageComponentPosition]
  implicit val decodePageComponentPosition: Decoder[PageComponentPosition] = deriveDecoder[PageComponentPosition]
  implicit val encodePageComponent: Encoder[PageComponent] = deriveEncoder[PageComponent]
  implicit val decodePageComponent: Decoder[PageComponent] = deriveDecoder[PageComponent]
  implicit val encodeSite: Encoder[Site] = deriveEncoder[Site]
  implicit val decodeSite: Decoder[Site] = deriveDecoder[Site]

  implicit val encodeSiteEvent: Encoder[SiteEvent] = deriveEncoder[SiteEvent]
  implicit val decodeSiteEvent: Decoder[SiteEvent] = deriveDecoder[SiteEvent]

  override def toByteArray(site: Site): Array[Byte] =
    site.asJson.noSpaces.getBytes(StandardCharsets.UTF_8)

  override def fromByteArray(bytes: Array[Byte]): Site =
    decode[Site](new String(bytes, StandardCharsets.UTF_8)).right.get

  override def toByteArray(event: SiteEvent): Array[Byte] =
    event.asJson.noSpaces.getBytes(StandardCharsets.UTF_8)

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent =
    decode[SiteEvent](new String(bytes, StandardCharsets.UTF_8)).right.get


  private def decodeLong[T](f: Long => T): Decoder[T] = Decoder.decodeLong.emap { v =>
    try {
      Right(f(v))
    } catch {
      case NonFatal(e) => Left(e.toString)
    }
  }

  private def decodeString[T](f: String => T): Decoder[T] = Decoder.decodeString.emap { str =>
    try {
      Right(f(str))
    } catch {
      case NonFatal(e) => Left(e.toString)
    }
  }
}
