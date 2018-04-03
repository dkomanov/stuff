package com.komanov.serialization.converters

import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain._
import com.komanov.serialization.domain.thrift._
import org.apache.thrift.protocol.TCompactProtocol
import org.apache.thrift.{TBase, TDeserializer, TSerializer}

import scala.collection.convert.ImplicitConversions._
import scala.language.existentials
import scala.reflect.ClassTag

/** https://thrift.apache.org/ */
object JavaThriftConverter extends MyConverter {

  override def toByteArray(site: Site): Array[Byte] = {
    val proto = new SitePb()
      .setId(ConversionUtils.uuidToByteBuffer(site.id))
      .setOwnerId(ConversionUtils.uuidToByteBuffer(site.ownerId))
      .setRevision(site.revision)
      .setSiteType(toSiteTypePb(site.siteType))
      .setFlags(site.flags.map(toSiteFlagPb))
      .setName(site.name)
      .setDescription(site.description)
      .setDomains(site.domains.map { d =>
        new DomainPb()
          .setName(d.name)
          .setPrimary(d.primary)
      })
      .setDefaultMetaTags(site.defaultMetaTags.map(toMetaTagPb))
      .setPages(site.pages.map { p =>
        new PagePb()
          .setName(p.name)
          .setPath(p.path)
          .setMetaTags(p.metaTags.map(toMetaTagPb))
          .setComponents(p.components.map(toComponentPb))
      })
      .setEntryPoints(site.entryPoints.map(toEntryPointPb))
      .setPublished(site.published)
      .setDateCreated(ConversionUtils.instantToLong(site.dateCreated))
      .setDateUpdated(ConversionUtils.instantToLong(site.dateUpdated))

    serializer.serialize(proto)
  }

  override def fromByteArray(bytes: Array[Byte]): Site = {
    val site = new SitePb()
    deserializer.deserialize(site, bytes)

    Site(
      ConversionUtils.bytesToUuid(site.id),
      ConversionUtils.bytesToUuid(site.ownerId),
      site.revision,
      fromSiteTypePb(site.siteType),
      site.flags.map(fromSiteFlagPb),
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
    val proto = toMessage(event)
    serializer.serialize(proto)
  }

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = {
    val EventDescriptor(_, _, fromMessage, factory) = descriptorMap(clazz)
    val m = factory()
    deserializer.deserialize(m, bytes)
    fromMessage(m)
  }

  private def serializer = new TSerializer(new TCompactProtocol.Factory())

  private def deserializer = new TDeserializer(new TCompactProtocol.Factory())

  private def toMetaTagPb(mt: MetaTag) = {
    new MetaTagPb()
      .setName(mt.name)
      .setValue(mt.value)
  }

  private def fromMetaTagPb(mt: MetaTagPb) = MetaTag(mt.name, mt.value)

  private def toComponentPb(pc: PageComponent): PageComponentPb = {
    new PageComponentPb()
      .setId(ConversionUtils.uuidToByteBuffer(pc.id))
      .setComponentType(toPageComponentTypePb(pc.componentType))
      .setData(pc.data match {
        case text: TextComponentData =>
          new PageComponentDataPb()
            .setText(new TextComponentDataPb().setText(text.text))

        case button: ButtonComponentData =>
          new PageComponentDataPb()
            .setButton(
              new ButtonComponentDataPb()
                .setName(button.name)
                .setText(button.text)
                .setAction(ConversionUtils.uuidToByteBuffer(button.action))
            )

        case blog: BlogComponentData =>
          new PageComponentDataPb()
            .setBlog(
              new BlogComponentDataPb()
                .setName(blog.name)
                .setRss(blog.rss)
                .setTags(blog.tags)
            )
      })
      .setPosition(pc.position.map(p => new PageComponentPositionPb().setX(p.x).setY(p.y)).orNull)
      .setDateCreated(ConversionUtils.instantToLong(pc.dateCreated))
      .setDateUpdated(ConversionUtils.instantToLong(pc.dateUpdated))
  }

  private def fromComponentPb(pc: PageComponentPb) = PageComponent(
    ConversionUtils.bytesToUuid(pc.id),
    fromPageComponentTypePb(pc.componentType), {
      if (pc.data.isSetText) {
        val text = pc.data.text
        TextComponentData(text.text)
      } else if (pc.data.isSetButton) {
        val button = pc.data.button
        ButtonComponentData(button.name, button.text, ConversionUtils.bytesToUuid(button.action))
      } else if (pc.data.isSetBlog) {
        val blog = pc.data.blog
        BlogComponentData(blog.name, blog.rss, blog.tags)
      } else {
        throw new RuntimeException("Expected data")
      }
    },
    Option(pc.position).map(p => PageComponentPosition(x = p.x, y = p.y)),
    ConversionUtils.longToInstance(pc.dateCreated),
    ConversionUtils.longToInstance(pc.dateUpdated)
  )

  private def toEntryPointPb(entryPoint: EntryPoint): EntryPointPb = entryPoint match {
    case ep: DomainEntryPoint =>
      new EntryPointPb().setDomain(
        new DomainEntryPointPb()
          .setDomain(ep.domain)
          .setPrimary(ep.primary)
      )

    case ep: FreeEntryPoint =>
      new EntryPointPb().setFree(
        new FreeEntryPointPb()
          .setUserName(ep.userName)
          .setSiteName(ep.siteName)
          .setPrimary(ep.primary)
      )
  }

  private def fromEntryPointPb(entryPoint: EntryPointPb): EntryPoint = {
    if (entryPoint.isSetDomain) {
      val ep = entryPoint.domain
      DomainEntryPoint(ep.domain, ep.primary)
    } else if (entryPoint.isSetFree) {
      val ep = entryPoint.free
      FreeEntryPoint(ep.userName, ep.siteName, ep.primary)
    } else {
      throw new RuntimeException("Expected entry point")
    }
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
    case SiteTypePb.UnknownSiteType => SiteType.Unknown
  }

  private def toSiteFlagPb(f: SiteFlag): SiteFlagPb = f match {
    case SiteFlag.Free => SiteFlagPb.Free
    case SiteFlag.Premium => SiteFlagPb.Premium
    case SiteFlag.Unknown => SiteFlagPb.UnknownSiteFlag
  }

  private def fromSiteFlagPb(f: SiteFlagPb): SiteFlag = f match {
    case SiteFlagPb.Free => SiteFlag.Free
    case SiteFlagPb.Premium => SiteFlag.Premium
    case SiteFlagPb.UnknownSiteFlag => SiteFlag.Unknown
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
    case PageComponentTypePb.UnknownPageComponentType => PageComponentType.Unknown
  }

  type ToMessageF = SiteEvent => TBase[_, _]
  type FromMessageF = TBase[_, _] => SiteEvent
  type MessageFactoryF = () => TBase[_, _]

  case class EventDescriptor(siteEventClass: Class[_],
                             toMessage: ToMessageF,
                             fromMessage: FromMessageF,
                             factory: MessageFactoryF)

  private def createEventDescriptor[T: ClassTag, M](toMessage: T => M, factory: () => M, fromMessage: M => T): EventDescriptor = {
    EventDescriptor(
      implicitly[ClassTag[T]].runtimeClass,
      toMessage.asInstanceOf[ToMessageF],
      fromMessage.asInstanceOf[FromMessageF],
      factory.asInstanceOf[MessageFactoryF]
    )
  }

  private val descriptorMap: Map[Class[_], EventDescriptor] = Seq(
    createEventDescriptor[SiteCreated, SiteCreatedPb](
      e => new SiteCreatedPb().setId(ConversionUtils.uuidToByteBuffer(e.id)).setOwnerId(ConversionUtils.uuidToByteBuffer(e.ownerId)).setSiteType(toSiteTypePb(e.siteType)),
      () => new SiteCreatedPb(),
      ev => SiteCreated(ConversionUtils.bytesToUuid(ev.id), ConversionUtils.bytesToUuid(ev.ownerId), fromSiteTypePb(ev.getSiteType))
    ),
    createEventDescriptor[SiteNameSet, SiteNameSetPb](
      e => new SiteNameSetPb().setName(e.name),
      () => new SiteNameSetPb(),
      ev => SiteNameSet(ev.getName)
    ),
    createEventDescriptor[SiteDescriptionSet, SiteDescriptionSetPb](
      e => new SiteDescriptionSetPb().setDescription(e.description),
      () => new SiteDescriptionSetPb(),
      ev => SiteDescriptionSet(ev.getDescription)
  ),
    createEventDescriptor[SiteRevisionSet, SiteRevisionSetPb](
      e => new SiteRevisionSetPb().setRevision(e.revision),
      () => new SiteRevisionSetPb(),
      ev => SiteRevisionSet(ev.getRevision)
    ),
    createEventDescriptor[SitePublished, SitePublishedPb](
      e => new SitePublishedPb(),
      () => new SitePublishedPb(),
      ev => SitePublished()
    ),
    createEventDescriptor[SiteUnpublished, SiteUnpublishedPb](
      e => new SiteUnpublishedPb(),
      () => new SiteUnpublishedPb(),
      ev => SiteUnpublished()
    ),
    createEventDescriptor[SiteFlagAdded, SiteFlagAddedPb](
      e => new SiteFlagAddedPb().setSiteFlag(toSiteFlagPb(e.siteFlag)),
      () => new SiteFlagAddedPb(),
      ev => SiteFlagAdded(fromSiteFlagPb(ev.getSiteFlag))
    ),
    createEventDescriptor[SiteFlagRemoved, SiteFlagRemovedPb](
      e => new SiteFlagRemovedPb().setSiteFlag(toSiteFlagPb(e.siteFlag)),
      () => new SiteFlagRemovedPb(),
      ev => SiteFlagRemoved(fromSiteFlagPb(ev.getSiteFlag))
    ),
    createEventDescriptor[DomainAdded, DomainAddedPb](
      e => new DomainAddedPb().setName(e.name),
      () => new DomainAddedPb(),
      ev => DomainAdded(ev.getName)
    ),
    createEventDescriptor[DomainRemoved, DomainRemovedPb](
      e => new DomainRemovedPb().setName(e.name),
      () => new DomainRemovedPb(),
      ev => DomainRemoved(ev.getName)
    ),
    createEventDescriptor[PrimaryDomainSet, PrimaryDomainSetPb](
      e => new PrimaryDomainSetPb().setName(e.name),
      () => new PrimaryDomainSetPb(),
      ev => PrimaryDomainSet(ev.getName)
    ),
    createEventDescriptor[DefaultMetaTagAdded, DefaultMetaTagAddedPb](
      e => new DefaultMetaTagAddedPb().setName(e.name).setValue(e.value),
      () => new DefaultMetaTagAddedPb(),
      ev => DefaultMetaTagAdded(ev.getName, ev.getValue)
    ),
    createEventDescriptor[DefaultMetaTagRemoved, DefaultMetaTagRemovedPb](
      e => new DefaultMetaTagRemovedPb().setName(e.name),
      () => new DefaultMetaTagRemovedPb(),
      ev => DefaultMetaTagRemoved(ev.getName)
    ),
    createEventDescriptor[PageAdded, PageAddedPb](
      e => new PageAddedPb().setPath(e.path),
      () => new PageAddedPb(),
      ev => PageAdded(ev.getPath)
    ),
    createEventDescriptor[PageRemoved, PageRemovedPb](
      e => new PageRemovedPb().setPath(e.path),
      () => new PageRemovedPb(),
      ev => PageRemoved(ev.getPath)
    ),
    createEventDescriptor[PageNameSet, PageNameSetPb](
      e => new PageNameSetPb().setPath(e.path).setName(e.name),
      () => new PageNameSetPb(),
      ev => PageNameSet(ev.getPath, ev.getName)
    ),
    createEventDescriptor[PageMetaTagAdded, PageMetaTagAddedPb](
      e => new PageMetaTagAddedPb().setPath(e.path).setName(e.name).setValue(e.value),
      () => new PageMetaTagAddedPb(),
      ev => PageMetaTagAdded(ev.getPath, ev.getName, ev.getValue)
    ),
    createEventDescriptor[PageMetaTagRemoved, PageMetaTagRemovedPb](
      e => new PageMetaTagRemovedPb().setPath(e.path).setName(e.name),
      () => new PageMetaTagRemovedPb(),
      ev => PageMetaTagRemoved(ev.getPath, ev.getName)
    ),
    createEventDescriptor[PageComponentAdded, PageComponentAddedPb](
      e => new PageComponentAddedPb().setPagePath(e.pagePath).setId(ConversionUtils.uuidToByteBuffer(e.id)).setComponentType(toPageComponentTypePb(e.componentType)),
      () => new PageComponentAddedPb(),
      ev => PageComponentAdded(ev.getPagePath, ConversionUtils.bytesToUuid(ev.id), fromPageComponentTypePb(ev.getComponentType))
    ),
    createEventDescriptor[PageComponentRemoved, PageComponentRemovedPb](
      e => new PageComponentRemovedPb().setPagePath(e.pagePath).setId(ConversionUtils.uuidToByteBuffer(e.id)),
      () => new PageComponentRemovedPb(),
      ev => PageComponentRemoved(ev.getPagePath, ConversionUtils.bytesToUuid(ev.id))
    ),
    createEventDescriptor[PageComponentPositionSet, PageComponentPositionSetPb](
      e => new PageComponentPositionSetPb().setId(ConversionUtils.uuidToByteBuffer(e.id)).setX(e.position.x).setY(e.position.y),
      () => new PageComponentPositionSetPb(),
      ev => PageComponentPositionSet(ConversionUtils.bytesToUuid(ev.id), PageComponentPosition(ev.getX, ev.getY))
    ),
    createEventDescriptor[PageComponentPositionReset, PageComponentPositionResetPb](
      e => new PageComponentPositionResetPb().setId(ConversionUtils.uuidToByteBuffer(e.id)),
      () => new PageComponentPositionResetPb(),
      ev => PageComponentPositionReset(ConversionUtils.bytesToUuid(ev.id))
    ),
    createEventDescriptor[TextComponentDataSet, TextComponentDataSetPb](
      e => new TextComponentDataSetPb().setId(ConversionUtils.uuidToByteBuffer(e.id)).setText(e.text),
      () => new TextComponentDataSetPb(),
      ev => TextComponentDataSet(ConversionUtils.bytesToUuid(ev.id), ev.getText)
    ),
    createEventDescriptor[ButtonComponentDataSet, ButtonComponentDataSetPb](
      e => new ButtonComponentDataSetPb().setId(ConversionUtils.uuidToByteBuffer(e.id)).setName(e.name).setText(e.text).setAction(ConversionUtils.uuidToByteBuffer(e.action)),
      () => new ButtonComponentDataSetPb(),
      ev => ButtonComponentDataSet(ConversionUtils.bytesToUuid(ev.id), ev.getName, ev.getText, ConversionUtils.bytesToUuid(ev.action))
    ),
    createEventDescriptor[BlogComponentDataSet, BlogComponentDataSetPb](
      e => new BlogComponentDataSetPb().setId(ConversionUtils.uuidToByteBuffer(e.id)).setName(e.name).setRss(e.rss).setTags(e.tags),
      () => new BlogComponentDataSetPb(),
      ev => BlogComponentDataSet(ConversionUtils.bytesToUuid(ev.id), ev.getName, ev.isRss, ev.isTags)
    ),
    createEventDescriptor[DomainEntryPointAdded, DomainEntryPointAddedPb](
      e => new DomainEntryPointAddedPb().setDomain(e.domain),
      () => new DomainEntryPointAddedPb(),
      ev => DomainEntryPointAdded(ev.getDomain)
    ),
    createEventDescriptor[FreeEntryPointAdded, FreeEntryPointAddedPb](
      e => new FreeEntryPointAddedPb().setUserName(e.userName).setSiteName(e.siteName),
      () => new FreeEntryPointAddedPb(),
      ev => FreeEntryPointAdded(ev.getUserName, ev.getSiteName)
    ),
    createEventDescriptor[EntryPointRemoved, EntryPointRemovedPb](
      e => new EntryPointRemovedPb().setLookupKey(e.lookupKey),
      () => new EntryPointRemovedPb(),
      ev => EntryPointRemoved(ev.getLookupKey)
    ),
    createEventDescriptor[PrimaryEntryPointSet, PrimaryEntryPointSetPb](
      e => new PrimaryEntryPointSetPb().setLookupKey(e.lookupKey),
      () => new PrimaryEntryPointSetPb(),
      ev => PrimaryEntryPointSet(ev.getLookupKey)
    )
  ).map(t => t.siteEventClass -> t).toMap

}
