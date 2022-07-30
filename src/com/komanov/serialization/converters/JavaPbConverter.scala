package com.komanov.serialization.converters

import com.google.protobuf.GeneratedMessage
import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain._
import com.komanov.serialization.domain.protos.Events._
import com.komanov.serialization.domain.protos.Site.EntryPointPb._
import com.komanov.serialization.domain.protos.Site.PageComponentDataPb._
import com.komanov.serialization.domain.protos.Site._

import scala.collection.convert.ImplicitConversions._
import scala.language.existentials
import scala.reflect.ClassTag

/** https://developers.google.com/protocol-buffers */
object JavaPbConverter extends MyConverter {

  override def toByteArray(site: Site): Array[Byte] = {
    val proto = SitePb.newBuilder()
      .setId(ProtobufConversionUtils.uuidToBytes(site.id))
      .setOwnerId(ProtobufConversionUtils.uuidToBytes(site.ownerId))
      .setRevision(site.revision)
      .setSiteType(toSiteTypePb(site.siteType))
      .addAllFlags(site.flags.map(toSiteFlagPb))
      .setName(site.name)
      .setDescription(site.description)
      .addAllDomains(site.domains.map(d =>
        DomainPb.newBuilder()
          .setName(d.name)
          .setPrimary(d.primary)
          .build()
      ))
      .addAllDefaultMetaTags(site.defaultMetaTags.map(toMetaTagPb))
      .addAllPages(site.pages.map { p =>
        PagePb.newBuilder()
          .setName(p.name)
          .setPath(p.path)
          .addAllMetaTags(p.metaTags.map(toMetaTagPb))
          .addAllComponents(p.components.map(toComponentPb))
          .build()
      })
      .addAllEntryPoints(site.entryPoints.map(toEntryPointPb).toSeq)
      .setPublished(site.published)
      .setDateCreated(ConversionUtils.instantToLong(site.dateCreated))
      .setDateUpdated(ConversionUtils.instantToLong(site.dateUpdated))
    proto.build().toByteArray
  }

  override def fromByteArray(bytes: Array[Byte]): Site = {
    val site = SitePb.parseFrom(bytes)
    Site(
      ProtobufConversionUtils.bytesToUuid(site.getId),
      ProtobufConversionUtils.bytesToUuid(site.getOwnerId),
      site.getRevision,
      fromSiteTypePb(site.getSiteType),
      site.getFlagsList.map(fromSiteFlagPb).toSeq,
      site.getName,
      site.getDescription,
      site.getDomainsList.map(d => Domain(d.getName, d.getPrimary)).toSeq,
      site.getDefaultMetaTagsList.map(fromMetaTagPb).toSeq,
      site.getPagesList.map { p =>
        Page(p.getName, p.getPath, p.getMetaTagsList.map(fromMetaTagPb).toSeq, p.getComponentsList.map(fromComponentPb).toSeq)
      }.toSeq,
      site.getEntryPointsList.map(fromEntryPointPb).toSeq,
      site.getPublished,
      ConversionUtils.longToInstance(site.getDateCreated),
      ConversionUtils.longToInstance(site.getDateUpdated)
    )
  }

  override def toByteArray(event: SiteEvent): Array[Byte] = {
    val EventDescriptor(_, toMessage, _, _) = descriptorMap(event.getClass)
    toMessage(event)
      .build()
      .toByteArray
  }

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = {
    val EventDescriptor(_, _, fromMessage, parser) = descriptorMap(clazz)
    fromMessage(parser(bytes))
  }

  private def toMetaTagPb(mt: MetaTag) = {
    MetaTagPb.newBuilder()
      .setName(mt.name)
      .setValue(mt.value)
      .build()
  }

  private def fromMetaTagPb(mt: MetaTagPb) = MetaTag(mt.getName, mt.getValue)

  private def toComponentPb(pc: PageComponent) = {
    val proto = PageComponentPb.newBuilder()
      .setId(ProtobufConversionUtils.uuidToBytes(pc.id))
      .setComponentType(toPageComponentTypePb(pc.componentType))
      .setData(pc.data match {
        case text: TextComponentData =>
          PageComponentDataPb
            .newBuilder()
            .setText(
              TextComponentDataPb.newBuilder()
                .setText(text.text)
            )
            .build()

        case button: ButtonComponentData =>
          PageComponentDataPb.newBuilder()
            .setButton(
              ButtonComponentDataPb.newBuilder()
                .setName(button.name)
                .setText(button.text)
                .setAction(ProtobufConversionUtils.uuidToBytes(button.action))
                .build()
            )
            .build()

        case blog: BlogComponentData =>
          PageComponentDataPb.newBuilder()
            .setBlog(
              BlogComponentDataPb.newBuilder()
                .setName(blog.name)
                .setRss(blog.rss)
                .setTags(blog.tags)
                .build()
            )
            .build()
      })
      .setDateCreated(ConversionUtils.instantToLong(pc.dateCreated))
      .setDateUpdated(ConversionUtils.instantToLong(pc.dateUpdated))

    pc.position.foreach(p => proto.setPosition(PageComponentPositionPb.newBuilder().setX(p.x).setY(p.y).build()))

    proto.build()
  }

  private def fromComponentPb(pc: PageComponentPb) = PageComponent(
    ProtobufConversionUtils.bytesToUuid(pc.getId),
    fromPageComponentTypePb(pc.getComponentType),
    pc.getData.getDataCase match {
      case DataCase.TEXT =>
        val text = pc.getData.getText
        TextComponentData(text.getText)

      case DataCase.BUTTON =>
        val button = pc.getData.getButton
        ButtonComponentData(button.getName, button.getText, ProtobufConversionUtils.bytesToUuid(button.getAction))

      case DataCase.BLOG =>
        val blog = pc.getData.getBlog
        BlogComponentData(blog.getName, blog.getRss, blog.getTags)

      case DataCase.DATA_NOT_SET =>
        throw new RuntimeException("Expected data")
    },
    if (pc.hasPosition) Some(PageComponentPosition(x = pc.getPosition.getX, y = pc.getPosition.getY)) else None,
    ConversionUtils.longToInstance(pc.getDateCreated),
    ConversionUtils.longToInstance(pc.getDateUpdated)
  )

  private def toEntryPointPb(entryPoint: EntryPoint): EntryPointPb = entryPoint match {
    case ep: DomainEntryPoint =>
      EntryPointPb.newBuilder()
        .setDomain(
          DomainEntryPointPb.newBuilder()
            .setPrimary(ep.primary)
            .setDomain(ep.domain)
        )
        .build()

    case ep: FreeEntryPoint =>
      EntryPointPb.newBuilder()
        .setFree(
          FreeEntryPointPb.newBuilder()
            .setPrimary(ep.primary)
            .setUserName(ep.userName)
            .setSiteName(ep.siteName)
        )
        .build()
  }

  private def fromEntryPointPb(entryPoint: EntryPointPb): EntryPoint = entryPoint.getEpCase match {
    case EpCase.DOMAIN =>
      val ep = entryPoint.getDomain
      DomainEntryPoint(ep.getDomain, ep.getPrimary)

    case EpCase.FREE =>
      val ep = entryPoint.getFree
      FreeEntryPoint(ep.getUserName, ep.getSiteName, ep.getPrimary)

    case EpCase.EP_NOT_SET =>
      throw new RuntimeException("Expected entry point")
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
    case SiteTypePb.UnknownSiteType | SiteTypePb.UNRECOGNIZED => SiteType.Unknown
  }

  private def toSiteFlagPb(f: SiteFlag): SiteFlagPb = f match {
    case SiteFlag.Free => SiteFlagPb.Free
    case SiteFlag.Premium => SiteFlagPb.Premium
    case SiteFlag.Unknown => SiteFlagPb.UnknownSiteFlag
  }

  private def fromSiteFlagPb(f: SiteFlagPb): SiteFlag = f match {
    case SiteFlagPb.Free => SiteFlag.Free
    case SiteFlagPb.Premium => SiteFlag.Premium
    case SiteFlagPb.UnknownSiteFlag | SiteFlagPb.UNRECOGNIZED => SiteFlag.Unknown
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
    case PageComponentTypePb.UnknownPageComponentType | PageComponentTypePb.UNRECOGNIZED => PageComponentType.Unknown
  }

  type ToMessageF = SiteEvent => GeneratedMessage.Builder[_]
  type FromMessageF = GeneratedMessage => SiteEvent
  type ParseF = Array[Byte] => GeneratedMessage

  case class EventDescriptor(siteEventClass: Class[_], generator: ToMessageF, extractor: FromMessageF, parser: ParseF)

  private def createEventDescriptor[T: ClassTag, M, B](generator: T => B, extractor: M => T, parser: Array[Byte] => M): EventDescriptor = {
    EventDescriptor(
      implicitly[ClassTag[T]].runtimeClass,
      generator.asInstanceOf[ToMessageF],
      extractor.asInstanceOf[FromMessageF],
      parser.asInstanceOf[ParseF]
    )
  }

  private val eventHandlers = Seq[EventDescriptor](
    createEventDescriptor[SiteCreated, SiteCreatedPb, SiteCreatedPb.Builder](
      e => SiteCreatedPb.newBuilder()
        .setId(ProtobufConversionUtils.uuidToBytes(e.id))
        .setOwnerId(ProtobufConversionUtils.uuidToBytes(e.ownerId))
        .setSiteType(toSiteTypePb(e.siteType)),
      ev => SiteCreated(ProtobufConversionUtils.bytesToUuid(ev.getId), ProtobufConversionUtils.bytesToUuid(ev.getOwnerId), fromSiteTypePb(ev.getSiteType)),
      SiteCreatedPb.parseFrom
    ),
    createEventDescriptor[SiteNameSet, SiteNameSetPb, SiteNameSetPb.Builder](
      e => SiteNameSetPb.newBuilder().setName(e.name),
      ev => SiteNameSet(ev.getName),
      SiteNameSetPb.parseFrom
    ),
    createEventDescriptor[SiteDescriptionSet, SiteDescriptionSetPb, SiteDescriptionSetPb.Builder](
      e => SiteDescriptionSetPb.newBuilder().setDescription(e.description),
      ev => SiteDescriptionSet(ev.getDescription),
      SiteDescriptionSetPb.parseFrom
    ),
    createEventDescriptor[SiteRevisionSet, SiteRevisionSetPb, SiteRevisionSetPb.Builder](
      e => SiteRevisionSetPb.newBuilder().setRevision(e.revision),
      ev => SiteRevisionSet(ev.getRevision),
      SiteRevisionSetPb.parseFrom
    ),
    createEventDescriptor[SitePublished, SitePublishedPb, SitePublishedPb.Builder](
      e => SitePublishedPb.newBuilder(),
      _ => SitePublished(),
      SitePublishedPb.parseFrom
    ),
    createEventDescriptor[SiteUnpublished, SiteUnpublishedPb, SiteUnpublishedPb.Builder](
      e => SiteUnpublishedPb.newBuilder(),
      _ => SiteUnpublished(),
      SiteUnpublishedPb.parseFrom
    ),
    createEventDescriptor[SiteFlagAdded, SiteFlagAddedPb, SiteFlagAddedPb.Builder](
      e => SiteFlagAddedPb.newBuilder().setSiteFlag(toSiteFlagPb(e.siteFlag)),
      ev => SiteFlagAdded(fromSiteFlagPb(ev.getSiteFlag)),
      SiteFlagAddedPb.parseFrom
    ),
    createEventDescriptor[SiteFlagRemoved, SiteFlagRemovedPb, SiteFlagRemovedPb.Builder](
      e => SiteFlagRemovedPb.newBuilder().setSiteFlag(toSiteFlagPb(e.siteFlag)),
      ev => SiteFlagRemoved(fromSiteFlagPb(ev.getSiteFlag)),
      SiteFlagRemovedPb.parseFrom
    ),
    createEventDescriptor[DomainAdded, DomainAddedPb, DomainAddedPb.Builder](
      e => DomainAddedPb.newBuilder().setName(e.name),
      ev => DomainAdded(ev.getName),
      DomainAddedPb.parseFrom
    ),
    createEventDescriptor[DomainRemoved, DomainRemovedPb, DomainRemovedPb.Builder](
      e => DomainRemovedPb.newBuilder().setName(e.name),
      ev => DomainRemoved(ev.getName),
      DomainRemovedPb.parseFrom
    ),
    createEventDescriptor[PrimaryDomainSet, PrimaryDomainSetPb, PrimaryDomainSetPb.Builder](
      e => PrimaryDomainSetPb.newBuilder().setName(e.name),
      ev => PrimaryDomainSet(ev.getName),
      PrimaryDomainSetPb.parseFrom
    ),
    createEventDescriptor[DefaultMetaTagAdded, DefaultMetaTagAddedPb, DefaultMetaTagAddedPb.Builder](
      e => DefaultMetaTagAddedPb.newBuilder()
        .setName(e.name)
        .setValue(e.value),
      ev => DefaultMetaTagAdded(ev.getName, ev.getValue),
      DefaultMetaTagAddedPb.parseFrom
    ),
    createEventDescriptor[DefaultMetaTagRemoved, DefaultMetaTagRemovedPb, DefaultMetaTagRemovedPb.Builder](
      e => DefaultMetaTagRemovedPb.newBuilder().setName(e.name),
      ev => DefaultMetaTagRemoved(ev.getName),
      DefaultMetaTagRemovedPb.parseFrom
    ),
    createEventDescriptor[PageAdded, PageAddedPb, PageAddedPb.Builder](
      e => PageAddedPb.newBuilder().setPath(e.path),
      ev => PageAdded(ev.getPath),
      PageAddedPb.parseFrom
    ),
    createEventDescriptor[PageRemoved, PageRemovedPb, PageRemovedPb.Builder](
      e => PageRemovedPb.newBuilder().setPath(e.path),
      ev => PageRemoved(ev.getPath),
      PageRemovedPb.parseFrom
    ),
    createEventDescriptor[PageNameSet, PageNameSetPb, PageNameSetPb.Builder](
      e => PageNameSetPb.newBuilder()
        .setPath(e.path)
        .setName(e.name),
      ev => PageNameSet(ev.getPath, ev.getName),
      PageNameSetPb.parseFrom
    ),
    createEventDescriptor[PageMetaTagAdded, PageMetaTagAddedPb, PageMetaTagAddedPb.Builder](
      e => PageMetaTagAddedPb.newBuilder()
        .setPath(e.path)
        .setName(e.name)
        .setValue(e.value),
      ev => PageMetaTagAdded(ev.getPath, ev.getName, ev.getValue),
      PageMetaTagAddedPb.parseFrom
    ),
    createEventDescriptor[PageMetaTagRemoved, PageMetaTagRemovedPb, PageMetaTagRemovedPb.Builder](
      e => PageMetaTagRemovedPb.newBuilder()
        .setPath(e.path)
        .setName(e.name),
      ev => PageMetaTagRemoved(ev.getPath, ev.getName),
      PageMetaTagRemovedPb.parseFrom
    ),
    createEventDescriptor[PageComponentAdded, PageComponentAddedPb, PageComponentAddedPb.Builder](
      e => PageComponentAddedPb.newBuilder()
        .setPagePath(e.pagePath)
        .setId(ProtobufConversionUtils.uuidToBytes(e.id))
        .setComponentType(toPageComponentTypePb(e.componentType)),
      ev => PageComponentAdded(ev.getPagePath, ProtobufConversionUtils.bytesToUuid(ev.getId), fromPageComponentTypePb(ev.getComponentType)),
      PageComponentAddedPb.parseFrom
    ),
    createEventDescriptor[PageComponentRemoved, PageComponentRemovedPb, PageComponentRemovedPb.Builder](
      e => PageComponentRemovedPb.newBuilder()
        .setPagePath(e.pagePath)
        .setId(ProtobufConversionUtils.uuidToBytes(e.id)),
      ev => PageComponentRemoved(ev.getPagePath, ProtobufConversionUtils.bytesToUuid(ev.getId)),
      PageComponentRemovedPb.parseFrom
    ),
    createEventDescriptor[PageComponentPositionSet, PageComponentPositionSetPb, PageComponentPositionSetPb.Builder](
      e => PageComponentPositionSetPb.newBuilder()
        .setId(ProtobufConversionUtils.uuidToBytes(e.id))
        .setX(e.position.x)
        .setY(e.position.y),
      ev => PageComponentPositionSet(ProtobufConversionUtils.bytesToUuid(ev.getId), PageComponentPosition(ev.getX, ev.getY)),
      PageComponentPositionSetPb.parseFrom
    ),
    createEventDescriptor[PageComponentPositionReset, PageComponentPositionResetPb, PageComponentPositionResetPb.Builder](
      e => PageComponentPositionResetPb.newBuilder().setId(ProtobufConversionUtils.uuidToBytes(e.id)),
      ev => PageComponentPositionReset(ProtobufConversionUtils.bytesToUuid(ev.getId)),
      PageComponentPositionResetPb.parseFrom
    ),
    createEventDescriptor[TextComponentDataSet, TextComponentDataSetPb, TextComponentDataSetPb.Builder](
      e => TextComponentDataSetPb.newBuilder()
        .setId(ProtobufConversionUtils.uuidToBytes(e.id))
        .setText(e.text),
      ev => TextComponentDataSet(ProtobufConversionUtils.bytesToUuid(ev.getId), ev.getText),
      TextComponentDataSetPb.parseFrom
    ),
    createEventDescriptor[ButtonComponentDataSet, ButtonComponentDataSetPb, ButtonComponentDataSetPb.Builder](
      e => ButtonComponentDataSetPb.newBuilder()
        .setId(ProtobufConversionUtils.uuidToBytes(e.id))
        .setName(e.name)
        .setText(e.text)
        .setAction(ProtobufConversionUtils.uuidToBytes(e.action)),
      ev => ButtonComponentDataSet(ProtobufConversionUtils.bytesToUuid(ev.getId), ev.getName, ev.getText, ProtobufConversionUtils.bytesToUuid(ev.getAction)),
      ButtonComponentDataSetPb.parseFrom
    ),
    createEventDescriptor[BlogComponentDataSet, BlogComponentDataSetPb, BlogComponentDataSetPb.Builder](
      e => BlogComponentDataSetPb.newBuilder()
        .setId(ProtobufConversionUtils.uuidToBytes(e.id))
        .setName(e.name)
        .setRss(e.rss)
        .setTags(e.tags),
      ev => BlogComponentDataSet(ProtobufConversionUtils.bytesToUuid(ev.getId), ev.getName, ev.getRss, ev.getTags),
      BlogComponentDataSetPb.parseFrom
    ),
    createEventDescriptor[DomainEntryPointAdded, DomainEntryPointAddedPb, DomainEntryPointAddedPb.Builder](
      e => DomainEntryPointAddedPb.newBuilder().setDomain(e.domain),
      ev => DomainEntryPointAdded(ev.getDomain),
      DomainEntryPointAddedPb.parseFrom
    ),
    createEventDescriptor[FreeEntryPointAdded, FreeEntryPointAddedPb, FreeEntryPointAddedPb.Builder](
      e => FreeEntryPointAddedPb.newBuilder()
        .setUserName(e.userName)
        .setSiteName(e.siteName),
      ev => FreeEntryPointAdded(ev.getUserName, ev.getSiteName),
      FreeEntryPointAddedPb.parseFrom
    ),
    createEventDescriptor[EntryPointRemoved, EntryPointRemovedPb, EntryPointRemovedPb.Builder](
      e => EntryPointRemovedPb.newBuilder().setLookupKey(e.lookupKey),
      ev => EntryPointRemoved(ev.getLookupKey),
      EntryPointRemovedPb.parseFrom
    ),
    createEventDescriptor[PrimaryEntryPointSet, PrimaryEntryPointSetPb, PrimaryEntryPointSetPb.Builder](
      e => PrimaryEntryPointSetPb.newBuilder().setLookupKey(e.lookupKey),
      ev => PrimaryEntryPointSet(ev.getLookupKey),
      PrimaryEntryPointSetPb.parseFrom
    )
  )

  private val descriptorMap: Map[Class[_], EventDescriptor] = Map(eventHandlers.map(t => t.siteEventClass -> t): _*)
}
