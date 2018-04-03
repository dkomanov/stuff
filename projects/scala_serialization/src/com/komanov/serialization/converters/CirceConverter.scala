package com.komanov.serialization.converters

import java.nio.charset.StandardCharsets
import java.time.Instant

import com.komanov.serialization.converters.CirceImplicits._
import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain._
import io.circe.parser._
import io.circe.syntax._

import scala.util.Try

/** https://circe.github.io/circe/ */
object CirceConverter extends MyConverter {

  override def toByteArray(site: Site): Array[Byte] =
    site.asJson.noSpaces.getBytes(StandardCharsets.UTF_8)

  override def fromByteArray(bytes: Array[Byte]): Site =
    decode[Site](new String(bytes, StandardCharsets.UTF_8)).right.get

  override def toByteArray(event: SiteEvent): Array[Byte] =
    event.asJson.noSpaces.getBytes(StandardCharsets.UTF_8)

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent =
    decode[SiteEvent](new String(bytes, StandardCharsets.UTF_8)).right.get
}

private[converters] object CirceImplicits {

  import io.circe._
  import io.circe.generic.auto._

  implicit val encodeInstant: Encoder[Instant] = Encoder.encodeLong.contramap[Instant](_.toEpochMilli)
  implicit val decodeInstant: Decoder[Instant] = Decoder.decodeLong.emapTry(v => Try(Instant.ofEpochMilli(v)))

  implicit val encodePageComponentType: Encoder[PageComponentType] = Encoder.encodeString.contramap[PageComponentType](_.toString)
  implicit val decodePageComponentType: Decoder[PageComponentType] = Decoder.decodeString.emapTry(v => Try(PageComponentType.valueOf(v)))

  implicit val encodeSiteFlag: Encoder[SiteFlag] = Encoder.encodeString.contramap[SiteFlag](_.toString)
  implicit val decodeSiteFlag: Decoder[SiteFlag] = Decoder.decodeString.emapTry(v => Try(SiteFlag.valueOf(v)))

  implicit val encodeSiteType: Encoder[SiteType] = Encoder.encodeString.contramap[SiteType](_.toString)
  implicit val decodeSiteType: Decoder[SiteType] = Decoder.decodeString.emapTry(v => Try(SiteType.valueOf(v)))

  implicit val encodeSite: Encoder[Site] = Encoder[Site]
  implicit val decodeSite: Decoder[Site] = Decoder[Site]

  implicit val encodeSiteEvent: Encoder[SiteEvent] = Encoder[SiteEvent]
  implicit val decodeSiteEvent: Decoder[SiteEvent] = Decoder[SiteEvent]
}
