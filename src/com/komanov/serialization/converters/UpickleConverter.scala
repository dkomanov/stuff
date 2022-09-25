package com.komanov.serialization.converters

import com.komanov.serialization.converters.UpickleShared._
import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain._
import upickle.default._

import java.nio.charset.{Charset, StandardCharsets}
import java.time.Instant

object UpickleJsonConverter extends MyConverter {
  Charset.defaultCharset()
  override def toByteArray(event: SiteEvent): Array[Byte] = writeToByteArray(event)

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = {
    // https://github.com/com-lihaoyi/upickle/issues/413
    read[SiteEvent](new String(bytes, StandardCharsets.UTF_8))
  }

  override def toByteArray(site: Site): Array[Byte] = writeToByteArray(site)

  override def fromByteArray(bytes: Array[Byte]): Site = read[Site](new String(bytes, StandardCharsets.UTF_8))
}

object UpickleMsgpackConverter extends MyConverter {
  override def toByteArray(event: SiteEvent): Array[Byte] = writeBinary(event)

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = readBinary[SiteEvent](bytes)

  override def toByteArray(site: Site): Array[Byte] = writeBinary(site)

  override def fromByteArray(bytes: Array[Byte]): Site = readBinary[Site](bytes)
}

object UpickleShared {
  implicit val rwInstant: ReadWriter[Instant] = readwriter[Long].bimap(ConversionUtils.instantToLong, ConversionUtils.longToInstance)
  implicit val rwSiteType: ReadWriter[SiteType] = readwriter[String].bimap(_.name, SiteType.valueOf)
  implicit val rwSiteFlag: ReadWriter[SiteFlag] = readwriter[String].bimap(_.name, SiteFlag.valueOf)
  implicit val pctSiteType: ReadWriter[PageComponentType] = readwriter[String].bimap(_.name, PageComponentType.valueOf)
  implicit val rwDomain: ReadWriter[Domain] = macroRW
  implicit val rwMetaTag: ReadWriter[MetaTag] = macroRW
  implicit val rwPageComponentData: ReadWriter[PageComponentData] = ReadWriter.merge(
    macroRW[TextComponentData],
    macroRW[ButtonComponentData],
    macroRW[BlogComponentData],
  )
  implicit val rwPageComponentPosition: ReadWriter[PageComponentPosition] = macroRW
  implicit val rwPageComponent: ReadWriter[PageComponent] = macroRW
  implicit val rwPage: ReadWriter[Page] = macroRW
  implicit val rwEntryPoint: ReadWriter[EntryPoint] = ReadWriter.merge(
    macroRW[DomainEntryPoint],
    macroRW[FreeEntryPoint],
  )
  implicit val rwSite: ReadWriter[Site] = macroRW
  implicit val rwEvent: ReadWriter[SiteEvent] = ReadWriter.merge(
    macroRW[SiteCreated],
    macroRW[SiteNameSet],
    macroRW[SiteDescriptionSet],
    macroRW[SiteRevisionSet],
    macroRW[SitePublished],
    macroRW[SiteUnpublished],
    macroRW[SiteFlagAdded],
    macroRW[SiteFlagRemoved],
    macroRW[DomainAdded],
    macroRW[DomainRemoved],
    macroRW[PrimaryDomainSet],
    macroRW[DefaultMetaTagAdded],
    macroRW[DefaultMetaTagRemoved],
    macroRW[PageAdded],
    macroRW[PageRemoved],
    macroRW[PageNameSet],
    macroRW[PageMetaTagAdded],
    macroRW[PageMetaTagRemoved],
    macroRW[PageComponentAdded],
    macroRW[PageComponentRemoved],
    macroRW[PageComponentPositionSet],
    macroRW[PageComponentPositionReset],
    macroRW[TextComponentDataSet],
    macroRW[ButtonComponentDataSet],
    macroRW[BlogComponentDataSet],
    macroRW[DomainEntryPointAdded],
    macroRW[FreeEntryPointAdded],
    macroRW[EntryPointRemoved],
    macroRW[PrimaryEntryPointSet]
  )
}
