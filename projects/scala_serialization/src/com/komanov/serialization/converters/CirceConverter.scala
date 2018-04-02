package com.komanov.serialization.converters

import java.nio.charset.StandardCharsets
import java.time.Instant

import com.komanov.serialization.domain._
import io.circe._
import io.circe.generic.auto._
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

  implicit val encodeSite: Encoder[Site] = Encoder[Site]
  implicit val decodeSite: Decoder[Site] = Decoder[Site]

  implicit val encodeSiteEvent: Encoder[SiteEvent] = Encoder[SiteEvent]
  implicit val decodeSiteEvent: Decoder[SiteEvent] = Decoder[SiteEvent]

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
