package com.komanov.serialization.converters

import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain._
import com.komanov.serialization.domain.protos.events._
import com.komanov.serialization.domain.protos.site.EntryPointPb.{DomainEntryPointPb, FreeEntryPointPb}
import com.komanov.serialization.domain.protos.site.PageComponentDataPb._
import com.komanov.serialization.domain.protos.site._
import scalapb.{GeneratedMessage, GeneratedMessageCompanion, Message}

import scala.language.existentials
import scala.reflect.ClassTag

/** https://github.com/trueaccord/ScalaPB */
object ScalaPbConverter extends MyConverter {

  override def toByteArray(site: Site): Array[Byte] = {
    val proto = SitePb(
      ProtobufConversionUtils.uuidToBytes(site.id),
      ProtobufConversionUtils.uuidToBytes(site.ownerId),
      site.revision,
      toSiteTypePb(site.siteType),
      site.flags.map(toSiteFlagPb),
      site.name,
      site.description,
      site.domains.map(d => DomainPb(d.name, d.primary)),
      site.defaultMetaTags.map(toMetaTagPb),
      site.pages.map { p =>
        PagePb(p.name, p.path, p.metaTags.map(toMetaTagPb), p.components.map(toComponentPb))
      },
      site.entryPoints.map(toEntryPointPb),
      site.published,
      ConversionUtils.instantToLong(site.dateCreated),
      ConversionUtils.instantToLong(site.dateUpdated)
    )
    proto.toByteArray
  }

  override def fromByteArray(bytes: Array[Byte]): Site = {
    val site = SitePb.parseFrom(bytes)
    Site(
      ProtobufConversionUtils.bytesToUuid(site.id),
      ProtobufConversionUtils.bytesToUuid(site.ownerId),
      site.revision,
      site.siteType match {
        case SiteTypePb.Flash => SiteType.Flash
        case SiteTypePb.Silverlight => SiteType.Silverlight
        case SiteTypePb.Html5 => SiteType.Html5
        case SiteTypePb.UnknownSiteType | SiteTypePb.Unrecognized(_) => SiteType.Unknown
      },
      site.flags.map {
        case SiteFlagPb.Free => SiteFlag.Free
        case SiteFlagPb.Premium => SiteFlag.Premium
        case SiteFlagPb.UnknownSiteFlag | SiteFlagPb.Unrecognized(_) => SiteFlag.Unknown
      },
      site.name,
      site.description,
      site.domains.map(d => Domain(d.name, d.primary)),
      site.defaultMetaTags.map(fromMetaTagPb),
      site.pages.map { p =>
        Page(p.name, p.path, p.metaTags.map(fromMetaTagPb), p.components.map(fromComponentPb))
      },
      site.entryPoints.map(fromEntryPointPb),
      site.published,
      ConversionUtils.longToInstance(site.dateCreated),
      ConversionUtils.longToInstance(site.dateUpdated)
    )
  }

  override def toByteArray(event: SiteEvent): Array[Byte] = {
    val EventDescriptor(_, toMessage, _, _) = descriptorMap(event.getClass)

    toMessage(event).toByteArray
  }

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = {
    val EventDescriptor(_, _, fromMessage, parse) = descriptorMap(clazz)
    fromMessage(parse(bytes))
  }

  private def toMetaTagPb(mt: MetaTag) = MetaTagPb(mt.name, mt.value)

  private def fromMetaTagPb(mt: MetaTagPb) = MetaTag(mt.name, mt.value)

  private def toComponentPb(pc: PageComponent) = PageComponentPb(
    ProtobufConversionUtils.uuidToBytes(pc.id),
    pc.componentType match {
      case PageComponentType.Text => PageComponentTypePb.Text
      case PageComponentType.Button => PageComponentTypePb.Button
      case PageComponentType.Blog => PageComponentTypePb.Blog
      case PageComponentType.Unknown => PageComponentTypePb.UnknownPageComponentType
    },
    Some(PageComponentDataPb(
      pc.data match {
        case text: TextComponentData => Data.Text(TextComponentDataPb(text.text))
        case button: ButtonComponentData => Data.Button(ButtonComponentDataPb(button.name, button.text, ProtobufConversionUtils.uuidToBytes(button.action)))
        case blog: BlogComponentData => Data.Blog(BlogComponentDataPb(blog.name, blog.rss, blog.tags))
      }
    )),
    pc.position.map(p => PageComponentPositionPb(x = p.x, y = p.y)),
    ConversionUtils.instantToLong(pc.dateCreated),
    ConversionUtils.instantToLong(pc.dateUpdated)
  )

  private def fromComponentPb(pc: PageComponentPb) = PageComponent(
    ProtobufConversionUtils.bytesToUuid(pc.id),
    pc.componentType match {
      case PageComponentTypePb.Text => PageComponentType.Text
      case PageComponentTypePb.Button => PageComponentType.Button
      case PageComponentTypePb.Blog => PageComponentType.Blog
      case PageComponentTypePb.UnknownPageComponentType | PageComponentTypePb.Unrecognized(_) => PageComponentType.Unknown
    },
    pc.data.map(_.data).get match {
      case Data.Text(text) => TextComponentData(text.text)
      case Data.Button(button) => ButtonComponentData(button.name, button.text, ProtobufConversionUtils.bytesToUuid(button.action))
      case Data.Blog(blog) => BlogComponentData(blog.name, blog.rss, blog.tags)
      case Data.Empty => throw new RuntimeException("Expected data")
    },
    pc.position.map(p => PageComponentPosition(x = p.x, y = p.y)),
    ConversionUtils.longToInstance(pc.dateCreated),
    ConversionUtils.longToInstance(pc.dateUpdated)
  )

  private def toEntryPointPb(entryPoint: EntryPoint): EntryPointPb = entryPoint match {
    case ep: DomainEntryPoint => EntryPointPb(EntryPointPb.Ep.Domain(DomainEntryPointPb(ep.domain, ep.primary)))
    case ep: FreeEntryPoint => EntryPointPb(EntryPointPb.Ep.Free(FreeEntryPointPb(ep.userName, ep.siteName, ep.primary)))
  }

  private def fromEntryPointPb(entryPoint: EntryPointPb): EntryPoint = entryPoint.ep match {
    case EntryPointPb.Ep.Domain(ep) => DomainEntryPoint(ep.domain, ep.primary)
    case EntryPointPb.Ep.Free(ep) => FreeEntryPoint(ep.userName, ep.siteName, ep.primary)
    case EntryPointPb.Ep.Empty => throw new RuntimeException("Expected entry point")
  }

  private def toSiteTypePb(t: SiteType): SiteTypePb = t match {
    case SiteType.Flash => SiteTypePb.Flash
    case SiteType.Silverlight => SiteTypePb.Silverlight
    case SiteType.Html5 => SiteTypePb.Html5
    case SiteType.Unknown => SiteTypePb.UnknownSiteType
  }

  private def fromSiteTypePb(t: SiteTypePb): SiteType = t match {
    case SiteTypePb.Flash => SiteType.Flash
    case SiteTypePb.Silverlight => SiteType.Silverlight
    case SiteTypePb.Html5 => SiteType.Html5
    case SiteTypePb.UnknownSiteType | SiteTypePb.Unrecognized(_) => SiteType.Unknown
  }

  private def toSiteFlagPb(f: SiteFlag): SiteFlagPb = f match {
    case SiteFlag.Free => SiteFlagPb.Free
    case SiteFlag.Premium => SiteFlagPb.Premium
    case SiteFlag.Unknown => SiteFlagPb.UnknownSiteFlag
  }

  private def fromSiteFlagPb(f: SiteFlagPb): SiteFlag = f match {
    case SiteFlagPb.Free => SiteFlag.Free
    case SiteFlagPb.Premium => SiteFlag.Premium
    case SiteFlagPb.UnknownSiteFlag | SiteFlagPb.Unrecognized(_) => SiteFlag.Unknown
  }

  private def toPageComponentTypePb(t: PageComponentType): PageComponentTypePb = t match {
    case PageComponentType.Text => PageComponentTypePb.Text
    case PageComponentType.Button => PageComponentTypePb.Button
    case PageComponentType.Blog => PageComponentTypePb.Blog
    case PageComponentType.Unknown => PageComponentTypePb.UnknownPageComponentType
  }

  private def fromPageComponentTypePb(t: PageComponentTypePb): PageComponentType = t match {
    case PageComponentTypePb.Text => PageComponentType.Text
    case PageComponentTypePb.Button => PageComponentType.Button
    case PageComponentTypePb.Blog => PageComponentType.Blog
    case PageComponentTypePb.UnknownPageComponentType | PageComponentTypePb.Unrecognized(_) => PageComponentType.Unknown
  }

  type ToMessageF = SiteEvent => GeneratedMessage
  type FromMessageF = GeneratedMessage => SiteEvent
  type ParseF = Array[Byte] => GeneratedMessage

  case class EventDescriptor(siteEventClass: Class[_],
                             toMessage: ToMessageF,
                             fromMessage: FromMessageF,
                             parse: ParseF)

  private def createEventDescriptor[T: ClassTag, M <: GeneratedMessage with Message[M] : ClassTag](generator: T => M, extractor: M => T): EventDescriptor = {
    val companion = ReflectionUtils.getCompanionObject(implicitly[ClassTag[M]].runtimeClass).asInstanceOf[GeneratedMessageCompanion[M]]
    val parseF = (ba: Array[Byte]) => companion.parseFrom(ba)
    EventDescriptor(
      implicitly[ClassTag[T]].runtimeClass,
      generator.asInstanceOf[ToMessageF],
      extractor.asInstanceOf[FromMessageF],
      parseF.asInstanceOf[ParseF]
    )
  }

  private val descriptorMap: Map[Class[_], EventDescriptor] = Seq[EventDescriptor](
    createEventDescriptor[SiteCreated, SiteCreatedPb](
      e => SiteCreatedPb(ProtobufConversionUtils.uuidToBytes(e.id), ProtobufConversionUtils.uuidToBytes(e.ownerId), toSiteTypePb(e.siteType)),
      e => SiteCreated(ProtobufConversionUtils.bytesToUuid(e.id), ProtobufConversionUtils.bytesToUuid(e.ownerId), fromSiteTypePb(e.siteType))
    ),
    createEventDescriptor[SiteNameSet, SiteNameSetPb](
      e => SiteNameSetPb(e.name),
      e => SiteNameSet(e.name)
    ),
    createEventDescriptor[SiteDescriptionSet, SiteDescriptionSetPb](
      e => SiteDescriptionSetPb(e.description),
      e => SiteDescriptionSet(e.description)
    ),
    createEventDescriptor[SiteRevisionSet, SiteRevisionSetPb](
      e => SiteRevisionSetPb(e.revision),
      e => SiteRevisionSet(e.revision)
    ),
    createEventDescriptor[SitePublished, SitePublishedPb](
      _ => SitePublishedPb(),
      _ => SitePublished()
    ),
    createEventDescriptor[SiteUnpublished, SiteUnpublishedPb](
      _ => SiteUnpublishedPb(),
      _ => SiteUnpublished()
    ),
    createEventDescriptor[SiteFlagAdded, SiteFlagAddedPb](
      e => SiteFlagAddedPb(toSiteFlagPb(e.siteFlag)),
      e => SiteFlagAdded(fromSiteFlagPb(e.siteFlag))
    ),
    createEventDescriptor[SiteFlagRemoved, SiteFlagRemovedPb](
      e => SiteFlagRemovedPb(toSiteFlagPb(e.siteFlag)),
      e => SiteFlagRemoved(fromSiteFlagPb(e.siteFlag))
    ),
    createEventDescriptor[DomainAdded, DomainAddedPb](
      e => DomainAddedPb(e.name),
      e => DomainAdded(e.name)
    ),
    createEventDescriptor[DomainRemoved, DomainRemovedPb](
      e => DomainRemovedPb(e.name),
      e => DomainRemoved(e.name)
    ),
    createEventDescriptor[PrimaryDomainSet, PrimaryDomainSetPb](
      e => PrimaryDomainSetPb(e.name),
      e => PrimaryDomainSet(e.name)
    ),
    createEventDescriptor[DefaultMetaTagAdded, DefaultMetaTagAddedPb](
      e => DefaultMetaTagAddedPb(e.name, e.value),
      e => DefaultMetaTagAdded(e.name, e.value)
    ),
    createEventDescriptor[DefaultMetaTagRemoved, DefaultMetaTagRemovedPb](
      e => DefaultMetaTagRemovedPb(e.name),
      e => DefaultMetaTagRemoved(e.name)
    ),
    createEventDescriptor[PageAdded, PageAddedPb](
      e => PageAddedPb(e.path),
      e => PageAdded(e.path)
    ),
    createEventDescriptor[PageRemoved, PageRemovedPb](
      e => PageRemovedPb(e.path),
      e => PageRemoved(e.path)
    ),
    createEventDescriptor[PageNameSet, PageNameSetPb](
      e => PageNameSetPb(e.path, e.name),
      e => PageNameSet(e.path, e.name)
    ),
    createEventDescriptor[PageMetaTagAdded, PageMetaTagAddedPb](
      e => PageMetaTagAddedPb(e.path, e.name, e.value),
      e => PageMetaTagAdded(e.path, e.name, e.value)
    ),
    createEventDescriptor[PageMetaTagRemoved, PageMetaTagRemovedPb](
      e => PageMetaTagRemovedPb(e.path, e.name),
      e => PageMetaTagRemoved(e.path, e.name)
    ),
    createEventDescriptor[PageComponentAdded, PageComponentAddedPb](
      e => PageComponentAddedPb(e.pagePath, ProtobufConversionUtils.uuidToBytes(e.id), toPageComponentTypePb(e.componentType)),
      e => PageComponentAdded(e.pagePath, ProtobufConversionUtils.bytesToUuid(e.id), fromPageComponentTypePb(e.componentType))
    ),
    createEventDescriptor[PageComponentRemoved, PageComponentRemovedPb](
      e => PageComponentRemovedPb(e.pagePath, ProtobufConversionUtils.uuidToBytes(e.id)),
      e => PageComponentRemoved(e.pagePath, ProtobufConversionUtils.bytesToUuid(e.id))
    ),
    createEventDescriptor[PageComponentPositionSet, PageComponentPositionSetPb](
      e => PageComponentPositionSetPb(ProtobufConversionUtils.uuidToBytes(e.id), e.position.x, e.position.y),
      e => PageComponentPositionSet(ProtobufConversionUtils.bytesToUuid(e.id), PageComponentPosition(e.x, e.y))
    ),
    createEventDescriptor[PageComponentPositionReset, PageComponentPositionResetPb](
      e => PageComponentPositionResetPb(ProtobufConversionUtils.uuidToBytes(e.id)),
      e => PageComponentPositionReset(ProtobufConversionUtils.bytesToUuid(e.id))
    ),
    createEventDescriptor[TextComponentDataSet, TextComponentDataSetPb](
      e => TextComponentDataSetPb(ProtobufConversionUtils.uuidToBytes(e.id), e.text),
      e => TextComponentDataSet(ProtobufConversionUtils.bytesToUuid(e.id), e.text)
    ),
    createEventDescriptor[ButtonComponentDataSet, ButtonComponentDataSetPb](
      e => ButtonComponentDataSetPb(ProtobufConversionUtils.uuidToBytes(e.id), e.name, e.text, ProtobufConversionUtils.uuidToBytes(e.action)),
      e => ButtonComponentDataSet(ProtobufConversionUtils.bytesToUuid(e.id), e.name, e.text, ProtobufConversionUtils.bytesToUuid(e.action))
    ),
    createEventDescriptor[BlogComponentDataSet, BlogComponentDataSetPb](
      e => BlogComponentDataSetPb(ProtobufConversionUtils.uuidToBytes(e.id), e.name, e.rss, e.tags),
      e => BlogComponentDataSet(ProtobufConversionUtils.bytesToUuid(e.id), e.name, e.rss, e.tags)
    ),
    createEventDescriptor[DomainEntryPointAdded, DomainEntryPointAddedPb](
      e => DomainEntryPointAddedPb(e.domain),
      e => DomainEntryPointAdded(e.domain)
    ),
    createEventDescriptor[FreeEntryPointAdded, FreeEntryPointAddedPb](
      e => FreeEntryPointAddedPb(e.userName, e.siteName),
      e => FreeEntryPointAdded(e.userName, e.siteName)
    ),
    createEventDescriptor[EntryPointRemoved, EntryPointRemovedPb](
      e => EntryPointRemovedPb(e.lookupKey),
      e => EntryPointRemoved(e.lookupKey)
    ),
    createEventDescriptor[PrimaryEntryPointSet, PrimaryEntryPointSetPb](
      e => PrimaryEntryPointSetPb(e.lookupKey),
      e => PrimaryEntryPointSet(e.lookupKey)
    )
  )
    .map(d => d.siteEventClass -> d)
    .toMap
}
