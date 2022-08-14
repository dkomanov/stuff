package com.komanov.serialization.converters

import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain._
import io.circe.parser._
import io.circe.syntax._

import java.nio.charset.StandardCharsets
import scala.util.Try

/** https://circe.github.io/circe/ */
object CirceConverter extends MyConverter {

  import CirceImplicits._

  override def toByteArray(site: Site): Array[Byte] = {
    require(encodeSite != null)
    site.asJson(encodeSite).noSpaces.getBytes(StandardCharsets.UTF_8)
  }

  override def fromByteArray(bytes: Array[Byte]): Site =
    decode[Site](new String(bytes, StandardCharsets.UTF_8)).fold(throw _, identity)

  override def toByteArray(event: SiteEvent): Array[Byte] =
    event.asJson.noSpaces.getBytes(StandardCharsets.UTF_8)

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent =
    decode[SiteEvent](new String(bytes, StandardCharsets.UTF_8)).fold(throw _, identity)

  private object CirceImplicits {

    import io.circe._
    import io.circe.generic.auto._

    implicit val encodePageComponentType: Encoder[PageComponentType] = Encoder.encodeString.contramap[PageComponentType](_.toString)
    implicit val decodePageComponentType: Decoder[PageComponentType] = Decoder.decodeString.emapTry(v => Try(PageComponentType.valueOf(v)))

    implicit val encodeSiteFlag: Encoder[SiteFlag] = Encoder.encodeString.contramap[SiteFlag](_.toString)
    implicit val decodeSiteFlag: Decoder[SiteFlag] = Decoder.decodeString.emapTry(v => Try(SiteFlag.valueOf(v)))

    implicit val encodeSiteType: Encoder[SiteType] = Encoder.encodeString.contramap[SiteType](_.toString)
    implicit val decodeSiteType: Decoder[SiteType] = Decoder.decodeString.emapTry(v => Try(SiteType.valueOf(v)))

    implicit val encodeSite: Encoder[Site] = exportEncoder[Site].instance
    implicit val decodeSite: Decoder[Site] = exportDecoder[Site].instance

    implicit val encodeSiteEvent: Encoder[SiteEvent] = exportEncoder[SiteEvent].instance
    implicit val decodeSiteEvent: Decoder[SiteEvent] = exportDecoder[SiteEvent].instance
  }
}
