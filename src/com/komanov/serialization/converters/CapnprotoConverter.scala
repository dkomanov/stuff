package com.komanov.serialization.converters

import com.komanov.serialization.converters.CapnprotoConversions.ByteArrayChannel
import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain._
import com.komanov.serialization.domain.capnproto.{CommonCapnproto, EventsCapnproto, SiteCapnproto}
import org.capnproto._

import java.nio.ByteBuffer
import java.nio.channels.WritableByteChannel
import java.time.Instant
import java.util.UUID
import scala.jdk.CollectionConverters._

// https://github.com/capnproto/capnproto-java
// https://dwrensha.github.io/capnproto-java/index.html
object CapnprotoPooledConverter extends MyConverter {
  private val tl = new ThreadLocal[ByteArrayChannel] {
    override def initialValue(): ByteArrayChannel = new ByteArrayChannel(32 * 1024)
  }

  override def toByteArray(event: SiteEvent): Array[Byte] = {
    val message = CapnprotoConversions.toProto(event)

    val ch = tl.get()
    ch.reset()
    SerializePacked.writeToUnbuffered(ch, message)
    ch.toByteArray
  }

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent =
    CapnprotoConversions.siteEventFromByteArray(bytes)

  override def toByteArray(site: Site): Array[Byte] = {
    val message = CapnprotoConversions.toProto(site)

    val ch = tl.get()
    ch.reset()
    SerializePacked.writeToUnbuffered(ch, message)
    ch.toByteArray
  }

  override def fromByteArray(bytes: Array[Byte]): Site =
    CapnprotoConversions.fromByteArray(bytes)
}

object CapnprotoConverter extends MyConverter {
  override def toByteArray(event: SiteEvent): Array[Byte] = {
    val message = CapnprotoConversions.toProto(event)

    val ch = new ByteArrayChannel(1024)
    SerializePacked.writeToUnbuffered(ch, message)
    ch.toByteArray
  }

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent =
    CapnprotoConversions.siteEventFromByteArray(bytes)

  override def toByteArray(site: Site): Array[Byte] = {
    val message = CapnprotoConversions.toProto(site)

    val ch = new ByteArrayChannel(1024)
    SerializePacked.writeToUnbuffered(ch, message)
    ch.toByteArray
  }

  override def fromByteArray(bytes: Array[Byte]): Site =
    CapnprotoConversions.fromByteArray(bytes)
}

private[converters] object CapnprotoConversions {

  def toProto(event: SiteEvent): MessageBuilder = {
    val message = new MessageBuilder
    val builder = message.initRoot(EventsCapnproto.SiteEvent.factory)
    val evB = builder.initEv()

    event match {
      case SiteCreated(id, ownerId, siteType) =>
        val b = evB.initSiteCreated()
        initUuid(id, b.initId)
        initUuid(ownerId, b.initOwnerId)
        b.setSiteType(fromSiteType(siteType))

      case SiteNameSet(name) =>
        val b = evB.initSiteNameSet()
        b.setName(name)

      case SiteDescriptionSet(description) =>
        val b = evB.initSiteDescriptionSet()
        b.setDescription(description)

      case SiteRevisionSet(revision) =>
        val b = evB.initSiteRevisionSet()
        b.setRevision(revision)

      case SitePublished() =>
        evB.initSitePublished()

      case SiteUnpublished() =>
        evB.initSiteUnpublished()

      case SiteFlagAdded(siteFlag) =>
        val b = evB.initSiteFlagAdded()
        b.setSiteFlag(fromSiteFlag(siteFlag))

      case SiteFlagRemoved(siteFlag) =>
        val b = evB.initSiteFlagRemoved()
        b.setSiteFlag(fromSiteFlag(siteFlag))

      case DomainAdded(name) =>
        val b = evB.initDomainAdded()
        b.setName(name)

      case DomainRemoved(name) =>
        val b = evB.initDomainRemoved()
        b.setName(name)

      case PrimaryDomainSet(name) =>
        val b = evB.initPrimaryDomainSet()
        b.setName(name)

      case DefaultMetaTagAdded(name, value) =>
        val b = evB.initDefaultMetaTagAdded()
        b.setName(name)
        b.setValue(value)

      case DefaultMetaTagRemoved(name) =>
        val b = evB.initDefaultMetaTagRemoved()
        b.setName(name)

      case PageAdded(path) =>
        val b = evB.initPageAdded()
        b.setPath(path)

      case PageRemoved(path) =>
        val b = evB.initPageRemoved()
        b.setPath(path)

      case PageNameSet(path, name) =>
        val b = evB.initPageNameSet()
        b.setPath(path)
        b.setName(name)

      case PageMetaTagAdded(path, name, value) =>
        val b = evB.initPageMetaTagAdded()
        b.setPath(path)
        b.setName(name)
        b.setValue(value)

      case PageMetaTagRemoved(path, name) =>
        val b = evB.initPageMetaTagRemoved()
        b.setPath(path)
        b.setName(name)

      case PageComponentAdded(pagePath, id, componentType) =>
        val b = evB.initPageComponentAdded()
        b.setPagePath(pagePath)
        initUuid(id, b.initId)
        b.setComponentType(fromPageComponentType(componentType))

      case PageComponentRemoved(pagePath, id) =>
        val b = evB.initPageComponentRemoved()
        b.setPagePath(pagePath)
        initUuid(id, b.initId)

      case PageComponentPositionSet(id, position) =>
        val b = evB.initPageComponentPositionSet()
        initUuid(id, b.initId)
        b.setX(position.x)
        b.setY(position.y)

      case PageComponentPositionReset(id) =>
        val b = evB.initPageComponentPositionReset()
        initUuid(id, b.initId)

      case TextComponentDataSet(id, text) =>
        val b = evB.initTextComponentDataSet()
        initUuid(id, b.initId)
        b.setText(text)

      case ButtonComponentDataSet(id, name, text, action) =>
        val b = evB.initButtonComponentDataSet()
        initUuid(id, b.initId)
        b.setName(name)
        b.setText(text)
        initUuid(action, b.initAction)

      case BlogComponentDataSet(id, name, rss, tags) =>
        val b = evB.initBlogComponentDataSet()
        initUuid(id, b.initId)
        b.setName(name)
        b.setRss(rss)
        b.setTags(tags)

      case DomainEntryPointAdded(domain) =>
        val b = evB.initDomainEntryPointAdded()
        b.setDomain(domain)

      case FreeEntryPointAdded(userName, siteName) =>
        val b = evB.initFreeEntryPointAdded()
        b.setUserName(userName)
        b.setSiteName(siteName)

      case EntryPointRemoved(lookupKey) =>
        val b = evB.initEntryPointRemoved()
        b.setLookupKey(lookupKey)

      case PrimaryEntryPointSet(lookupKey) =>
        val b = evB.initPrimaryEntryPointSet()
        b.setLookupKey(lookupKey)
    }

    message
  }

  def siteEventFromByteArray(bytes: Array[Byte]): SiteEvent = {
    val reader = SerializePacked.readFromUnbuffered(new ArrayInputStream(ByteBuffer.wrap(bytes)))
    val eventReader = reader.getRoot(EventsCapnproto.SiteEvent.factory).getEv
    eventReader.which match {
      case EventsCapnproto.SiteEvent.Ev.Which.SITE_CREATED =>
        val ev = eventReader.getSiteCreated
        SiteCreated(toUuid(ev.getId), toUuid(ev.getOwnerId), toSiteType(ev.getSiteType))

      case EventsCapnproto.SiteEvent.Ev.Which.SITE_NAME_SET =>
        val ev = eventReader.getSiteNameSet
        SiteNameSet(ev.getName.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.SITE_DESCRIPTION_SET =>
        val ev = eventReader.getSiteDescriptionSet
        SiteDescriptionSet(ev.getDescription.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.SITE_REVISION_SET =>
        val ev = eventReader.getSiteRevisionSet
        SiteRevisionSet(ev.getRevision)

      case EventsCapnproto.SiteEvent.Ev.Which.SITE_PUBLISHED =>
        SitePublished()

      case EventsCapnproto.SiteEvent.Ev.Which.SITE_UNPUBLISHED =>
        SiteUnpublished()

      case EventsCapnproto.SiteEvent.Ev.Which.SITE_FLAG_ADDED =>
        val ev = eventReader.getSiteFlagAdded
        SiteFlagAdded(toSiteFlag(ev.getSiteFlag))

      case EventsCapnproto.SiteEvent.Ev.Which.SITE_FLAG_REMOVED =>
        val ev = eventReader.getSiteFlagRemoved
        SiteFlagRemoved(toSiteFlag(ev.getSiteFlag))

      case EventsCapnproto.SiteEvent.Ev.Which.DOMAIN_ADDED =>
        val ev = eventReader.getDomainAdded
        DomainAdded(ev.getName.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.DOMAIN_REMOVED =>
        val ev = eventReader.getDomainRemoved
        DomainRemoved(ev.getName.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.PRIMARY_DOMAIN_SET =>
        val ev = eventReader.getPrimaryDomainSet
        PrimaryDomainSet(ev.getName.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.DEFAULT_META_TAG_ADDED =>
        val ev = eventReader.getDefaultMetaTagAdded
        DefaultMetaTagAdded(ev.getName.toString, ev.getValue.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.DEFAULT_META_TAG_REMOVED =>
        val ev = eventReader.getDefaultMetaTagRemoved
        DefaultMetaTagRemoved(ev.getName.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_ADDED =>
        val ev = eventReader.getPageAdded
        PageAdded(ev.getPath.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_REMOVED =>
        val ev = eventReader.getPageRemoved
        PageRemoved(ev.getPath.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_NAME_SET =>
        val ev = eventReader.getPageNameSet
        PageNameSet(ev.getPath.toString, ev.getName.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_META_TAG_ADDED =>
        val ev = eventReader.getPageMetaTagAdded
        PageMetaTagAdded(ev.getPath.toString, ev.getName.toString, ev.getValue.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_META_TAG_REMOVED =>
        val ev = eventReader.getPageMetaTagRemoved
        PageMetaTagRemoved(ev.getPath.toString, ev.getName.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_COMPONENT_ADDED =>
        val ev = eventReader.getPageComponentAdded
        PageComponentAdded(ev.getPagePath.toString, toUuid(ev.getId), toPageComponentType(ev.getComponentType))

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_COMPONENT_REMOVED =>
        val ev = eventReader.getPageComponentRemoved
        PageComponentRemoved(ev.getPagePath.toString, toUuid(ev.getId))

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_COMPONENT_POSITION_SET =>
        val ev = eventReader.getPageComponentPositionSet
        PageComponentPositionSet(toUuid(ev.getId), PageComponentPosition(ev.getX, ev.getY))

      case EventsCapnproto.SiteEvent.Ev.Which.PAGE_COMPONENT_POSITION_RESET =>
        val ev = eventReader.getPageComponentPositionReset
        PageComponentPositionReset(toUuid(ev.getId))

      case EventsCapnproto.SiteEvent.Ev.Which.TEXT_COMPONENT_DATA_SET =>
        val ev = eventReader.getTextComponentDataSet
        TextComponentDataSet(toUuid(ev.getId), ev.getText.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.BUTTON_COMPONENT_DATA_SET =>
        val ev = eventReader.getButtonComponentDataSet
        ButtonComponentDataSet(toUuid(ev.getId), ev.getName.toString, ev.getText.toString, toUuid(ev.getAction))

      case EventsCapnproto.SiteEvent.Ev.Which.BLOG_COMPONENT_DATA_SET =>
        val ev = eventReader.getBlogComponentDataSet
        BlogComponentDataSet(toUuid(ev.getId), ev.getName.toString, ev.getRss, ev.getTags)

      case EventsCapnproto.SiteEvent.Ev.Which.DOMAIN_ENTRY_POINT_ADDED =>
        val ev = eventReader.getDomainEntryPointAdded
        DomainEntryPointAdded(ev.getDomain.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.FREE_ENTRY_POINT_ADDED =>
        val ev = eventReader.getFreeEntryPointAdded
        FreeEntryPointAdded(ev.getUserName.toString, ev.getSiteName.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.ENTRY_POINT_REMOVED =>
        val ev = eventReader.getEntryPointRemoved
        EntryPointRemoved(ev.getLookupKey.toString)

      case EventsCapnproto.SiteEvent.Ev.Which.PRIMARY_ENTRY_POINT_SET =>
        val ev = eventReader.getPrimaryEntryPointSet
        PrimaryEntryPointSet(ev.getLookupKey.toString)

      case EventsCapnproto.SiteEvent.Ev.Which._NOT_IN_SCHEMA =>
        throw new IllegalStateException("unknown site event")
    }
  }

  def toProto(site: Site): MessageBuilder = {
    val message = new MessageBuilder

    val b = message.initRoot(SiteCapnproto.Site.factory)
    initUuid(site.id, b.initId)
    initUuid(site.ownerId, b.initOwnerId)
    b.setRevision(site.revision)
    b.setSiteType(fromSiteType(site.siteType))
    initList(site.flags, b.initFlags) { (flag, fb) =>
      fb.setValue(fromSiteFlag(flag))
    }
    b.setName(site.name)
    b.setDescription(site.description)
    initList(site.domains, b.initDomains) { (domain, db) =>
      db.setName(domain.name)
      db.setPrimary(domain.primary)
    }
    initMetaTags(site.defaultMetaTags, b.initDefaultMetaTags)
    initList(site.pages, b.initPages) { (page, pb) =>
      pb.setName(page.name)
      pb.setPath(page.path)
      initMetaTags(page.metaTags, pb.initMetaTags)
      initList(page.components, pb.initComponents) { (component, cb) =>
        initUuid(component.id, cb.initId)
        cb.setComponentType(fromPageComponentType(component.componentType))
        val db = cb.initData
        component.data match {
          case TextComponentData(text) =>
            val bb = db.initText
            bb.setText(text)
          case ButtonComponentData(name, text, action) =>
            val bb = db.initButton
            bb.setName(name)
            bb.setText(text)
            initUuid(action, bb.initAction)
          case BlogComponentData(name, rss, tags) =>
            val bb = db.initBlog
            bb.setName(name)
            bb.setRss(rss)
            bb.setTags(tags)
        }
        component.position.foreach { position =>
          val pb = cb.initPosition
          pb.setX(position.x)
          pb.setY(position.y)
        }
        initInstant(component.dateCreated, cb.initDateCreated)
        initInstant(component.dateUpdated, cb.initDateUpdated)
      }
    }
    initList(site.entryPoints, b.initEntryPoints) { (ep, epb) =>
      epb.setPrimary(ep.primary)
      ep match {
        case DomainEntryPoint(domain, _) =>
          val db = epb.initDomain
          db.setDomain(domain)
        case FreeEntryPoint(userName, siteName, _) =>
          val fb = epb.initFree
          fb.setUserName(userName)
          fb.setSiteName(siteName)
      }
    }
    b.setPublished(site.published)
    initInstant(site.dateCreated, b.initDateCreated)
    initInstant(site.dateUpdated, b.initDateUpdated)

    message
  }

  def fromByteArray(bytes: Array[Byte]): Site = {
    val reader = SerializePacked.readFromUnbuffered(new ArrayInputStream(ByteBuffer.wrap(bytes)))
    val site = reader.getRoot(SiteCapnproto.Site.factory)
    Site(
      toUuid(site.getId),
      toUuid(site.getOwnerId),
      site.getRevision,
      toSiteType(site.getSiteType),
      if (site.hasFlags) site.getFlags.iterator.asScala.map(w => toSiteFlag(w.getValue)).toSeq else Nil,
      site.getName.toString,
      site.getDescription.toString,
      site.getDomains.iterator.asScala.map(r => Domain(r.getName.toString, r.getPrimary)).toSeq,
      site.getDefaultMetaTags.iterator.asScala.map(r => MetaTag(r.getName.toString, r.getValue.toString)).toSeq,
      site.getPages.iterator.asScala.map(r => Page(
        r.getName.toString,
        r.getPath.toString,
        r.getMetaTags.iterator.asScala.map(r => MetaTag(r.getName.toString, r.getValue.toString)).toSeq,
        r.getComponents.iterator.asScala.map { r =>
          val data = r.getData
          PageComponent(
            toUuid(r.getId),
            toPageComponentType(r.getComponentType),
            data.which match {
              case SiteCapnproto.PageComponentData.Which.TEXT =>
                val d = data.getText
                TextComponentData(d.getText.toString)
              case SiteCapnproto.PageComponentData.Which.BUTTON =>
                val d = data.getButton
                ButtonComponentData(d.getName.toString, d.getText.toString, toUuid(d.getAction))
              case SiteCapnproto.PageComponentData.Which.BLOG =>
                val d = data.getBlog
                BlogComponentData(d.getName.toString, d.getRss, d.getTags)
              case SiteCapnproto.PageComponentData.Which._NOT_IN_SCHEMA =>
                throw new IllegalStateException("unknown PageComponentData")
            },
            if (r.hasPosition) {
              val p = r.getPosition
              Some(PageComponentPosition(p.getX, p.getY))
            } else {
              None
            },
            toInstant(r.getDateCreated),
            toInstant(r.getDateUpdated),
          )
        }.toSeq,
      )).toSeq,
      site.getEntryPoints.iterator.asScala.map(r => r.which match {
        case SiteCapnproto.EntryPoint.Which.DOMAIN =>
          val ep = r.getDomain
          DomainEntryPoint(ep.getDomain.toString, r.getPrimary)
        case SiteCapnproto.EntryPoint.Which.FREE =>
          val ep = r.getFree
          FreeEntryPoint(ep.getUserName.toString, ep.getSiteName.toString, r.getPrimary)
        case SiteCapnproto.EntryPoint.Which._NOT_IN_SCHEMA =>
          throw new IllegalStateException("unknown EntryPoint")
      }).toSeq,
      site.getPublished,
      toInstant(site.getDateCreated),
      toInstant(site.getDateUpdated),
    )
  }

  private def initMetaTags(tags: Seq[MetaTag], createBuilder: Int => StructList.Builder[SiteCapnproto.MetaTag.Builder]): Unit = {
    initList(tags, createBuilder)((tag, b) => {
      b.setName(tag.name)
      b.setValue(tag.value)
    })
  }

  private def initList[T, B <: StructBuilder](list: Seq[T], createListBuilder: Int => StructList.Builder[B])(init: (T, B) => Unit): Unit = {
    if (list.nonEmpty) {
      val b = createListBuilder(list.size)
      var i = 0
      for (item <- list) {
        init(item, b.get(i))
        i += 1
      }
    }
  }

  private def toUuid(r: CommonCapnproto.Uuid.Reader): UUID =
    new UUID(r.getMostSignificantBits, r.getLeastSignificantBits)

  private def initUuid(v: UUID, b: CommonCapnproto.Uuid.Builder): Unit = {
    b.setMostSignificantBits(v.getMostSignificantBits)
    b.setLeastSignificantBits(v.getLeastSignificantBits)
  }

  private def toInstant(r: CommonCapnproto.Instant.Reader): Instant =
    Instant.ofEpochMilli(r.getMillis)

  private def initInstant(r: Instant, b: CommonCapnproto.Instant.Builder): Unit = {
    b.setMillis(r.toEpochMilli)
  }

  private def fromSiteType(t: SiteType): CommonCapnproto.SiteType = t match {
    case SiteType.Flash => CommonCapnproto.SiteType.FLASH
    case SiteType.Silverlight => CommonCapnproto.SiteType.SILVERLIGHT
    case SiteType.Html5 => CommonCapnproto.SiteType.HTML5
    case SiteType.Unknown => CommonCapnproto.SiteType.UNKNOWN_SITE_TYPE
  }

  private def toSiteType(t: CommonCapnproto.SiteType): SiteType = t match {
    case CommonCapnproto.SiteType.FLASH => SiteType.Flash
    case CommonCapnproto.SiteType.SILVERLIGHT => SiteType.Silverlight
    case CommonCapnproto.SiteType.HTML5 => SiteType.Html5
    case CommonCapnproto.SiteType.UNKNOWN_SITE_TYPE | CommonCapnproto.SiteType._NOT_IN_SCHEMA => SiteType.Unknown
  }

  private def fromSiteFlag(f: SiteFlag): CommonCapnproto.SiteFlag = f match {
    case SiteFlag.Free => CommonCapnproto.SiteFlag.FREE
    case SiteFlag.Premium => CommonCapnproto.SiteFlag.PREMIUM
    case SiteFlag.Unknown => CommonCapnproto.SiteFlag.UNKNOWN_SITE_FLAG
  }

  private def toSiteFlag(f: CommonCapnproto.SiteFlag): SiteFlag = f match {
    case CommonCapnproto.SiteFlag.FREE => SiteFlag.Free
    case CommonCapnproto.SiteFlag.PREMIUM => SiteFlag.Premium
    case CommonCapnproto.SiteFlag.UNKNOWN_SITE_FLAG | CommonCapnproto.SiteFlag._NOT_IN_SCHEMA => SiteFlag.Unknown
  }

  private def fromPageComponentType(t: PageComponentType): CommonCapnproto.PageComponentType = t match {
    case PageComponentType.Text => CommonCapnproto.PageComponentType.TEXT
    case PageComponentType.Button => CommonCapnproto.PageComponentType.BUTTON
    case PageComponentType.Blog => CommonCapnproto.PageComponentType.BLOG
    case PageComponentType.Unknown => CommonCapnproto.PageComponentType.UNKNOWN_PAGE_COMPONENT_TYPE
  }

  private def toPageComponentType(t: CommonCapnproto.PageComponentType): PageComponentType = t match {
    case CommonCapnproto.PageComponentType.TEXT => PageComponentType.Text
    case CommonCapnproto.PageComponentType.BUTTON => PageComponentType.Button
    case CommonCapnproto.PageComponentType.BLOG => PageComponentType.Blog
    case CommonCapnproto.PageComponentType.UNKNOWN_PAGE_COMPONENT_TYPE | CommonCapnproto.PageComponentType._NOT_IN_SCHEMA => PageComponentType.Unknown
  }

  class ByteArrayChannel(initialCapacity: Int) extends WritableByteChannel {
    private var buf: Array[Byte] = new Array[Byte](initialCapacity)
    private var count = 0

    override def write(src: ByteBuffer): Int = {
      val n = src.remaining
      ensureCapacity(n)
      src.get(buf, count, n)
      count += n
      n
    }

    override def isOpen: Boolean = true

    override def close(): Unit = {}

    def reset(): Unit = {
      count = 0
    }

    def toByteArray: Array[Byte] =
      java.util.Arrays.copyOf(buf, count)

    private def ensureCapacity(n: Int): Unit = {
      if (count + n > buf.length) {
        buf = java.util.Arrays.copyOf(buf, (-1 >>> Integer.numberOfLeadingZeros(buf.length | (count + n))) + 1)
      }
    }
  }
}
