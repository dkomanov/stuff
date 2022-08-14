package com.komanov.serialization.domain

import java.time.Instant
import java.util.UUID

object EventProcessor {
  
  private val nullInstant = Instant.ofEpochMilli(0)
  private val nullSite = Site(null, null, 0, null, Nil, null, null, Nil, Nil, Nil, Nil, published = false, nullInstant, nullInstant)

  def apply(list: Seq[SiteEventData]): Site = {
    list.foldLeft(nullSite) {
      case (s, SiteEventData(_, SiteCreated(id, ownerId, siteType), dateCreated)) =>
        s.copy(id = id, ownerId = ownerId, siteType = siteType, dateCreated = dateCreated)

      case (s, SiteEventData(_, SiteRevisionSet(rev), dateUpdated)) =>
        s.copy(revision = rev, dateUpdated = dateUpdated)

      case (s, SiteEventData(_, SiteNameSet(name), _)) =>
        s.copy(name = name)

      case (s, SiteEventData(_, SiteDescriptionSet(description), _)) =>
        s.copy(description = description)

      case (s, SiteEventData(_, SitePublished(), _)) =>
        s.copy(published = true)

      case (s, SiteEventData(_, SiteUnpublished(), _)) =>
        s.copy(published = false)

      case (s, SiteEventData(_, SiteFlagAdded(flag), _)) =>
        s.copy(flags = Set(s.flags: _*).+(flag).toSeq)

      case (s, SiteEventData(_, SiteFlagRemoved(flag), _)) =>
        s.copy(flags = Set(s.flags: _*).-(flag).toSeq)

      case (s, SiteEventData(_, DomainAdded(name), _)) =>
        s.copy(domains = s.domains :+ Domain(name, primary = false))

      case (s, SiteEventData(_, PrimaryDomainSet(name), _)) =>
        s.copy(domains = s.domains.map(d => if (d.name == name) d.copy(primary = true) else d))

      case (s, SiteEventData(_, DomainRemoved(name), _)) =>
        s.copy(domains = s.domains.filter(_.name != name))

      case (s, SiteEventData(_, DefaultMetaTagAdded(name, value), _)) =>
        s.copy(defaultMetaTags = s.defaultMetaTags :+ MetaTag(name, value))

      case (s, SiteEventData(_, DefaultMetaTagRemoved(name), _)) =>
        s.copy(defaultMetaTags = s.defaultMetaTags.filter(_.name != name))

      case (s, SiteEventData(_, PageAdded(path), _)) =>
        s.pages.find(_.path == path).fold(s.copy(pages = s.pages :+ Page(null, path, Nil, Nil)))(_ => s)

      case (s, SiteEventData(_, PageRemoved(path), _)) =>
        s.pages.find(_.path == path).fold(s.copy(pages = s.pages.filter(_.path != path)))(_ => s)

      case (s, SiteEventData(_, PageNameSet(path, name), _)) =>
        modifyPage(s, path, p => p.copy(name = name))

      case (s, SiteEventData(_, PageMetaTagAdded(path, name, value), _)) =>
        modifyPage(s, path, p => p.copy(metaTags = p.metaTags :+ MetaTag(name, value)))

      case (s, SiteEventData(_, PageMetaTagRemoved(path, name), _)) =>
        modifyPage(s, path, p => p.copy(metaTags = p.metaTags.filter(_.name != name)))

      case (s, SiteEventData(_, PageComponentAdded(path, id, componentType), dateCreated)) =>
        modifyPage(s, path, p => p.copy(components = p.components :+ PageComponent(id, componentType, null, None, dateCreated, nullInstant)))

      case (s, SiteEventData(_, PageComponentRemoved(path, id), _)) =>
        modifyPage(s, path, p => p.copy(components = p.components.filter(_.id != id)))

      case (s, SiteEventData(_, PageComponentPositionSet(id, position), dateUpdated)) =>
        modifyComponent(s, id, c => c.copy(position = Some(position), dateUpdated = dateUpdated))

      case (s, SiteEventData(_, PageComponentPositionReset(id), dateUpdated)) =>
        modifyComponent(s, id, c => c.copy(position = None, dateUpdated = dateUpdated))

      case (s, SiteEventData(_, TextComponentDataSet(id, text), _)) =>
        modifyComponent(s, id, c => c.copy(data = TextComponentData(text)))

      case (s, SiteEventData(_, ButtonComponentDataSet(id, name, text, action), _)) =>
        modifyComponent(s, id, c => c.copy(data = ButtonComponentData(name, text, action)))

      case (s, SiteEventData(_, BlogComponentDataSet(id, name, rss, tags), _)) =>
        modifyComponent(s, id, c => c.copy(data = BlogComponentData(name, rss, tags)))

      case (s, SiteEventData(_, DomainEntryPointAdded(domain), _)) =>
        s.copy(entryPoints = s.entryPoints :+ DomainEntryPoint(domain, primary = false))

      case (s, SiteEventData(_, FreeEntryPointAdded(userName, siteName), _)) =>
        s.copy(entryPoints = s.entryPoints :+ FreeEntryPoint(userName, siteName, primary = false))

      case (s, SiteEventData(_, EntryPointRemoved(lookupKey), _)) =>
        s.copy(entryPoints = s.entryPoints.filter(_.lookupKey != lookupKey))

      case (s, SiteEventData(_, PrimaryEntryPointSet(lookupKey), _)) =>
        s.copy(entryPoints = s.entryPoints.map { ep =>
          if (ep.lookupKey == lookupKey) {
            ep match {
              case d: DomainEntryPoint => d.copy(primary = true)
              case f: FreeEntryPoint => f.copy(primary = true)
            }
          } else {
            ep
          }
        })
    }
  }

  def unapply(site: Site): Seq[SiteEventData] = {
    def ev(data: SiteEvent, timestamp: Instant = nullInstant): SiteEventData = SiteEventData(site.id, data, timestamp)

    def basic: Seq[SiteEventData] = Seq(
      ev(SiteCreated(site.id, site.ownerId, site.siteType), site.dateCreated),
      ev(SiteRevisionSet(site.revision), site.dateUpdated),
      ev(SiteNameSet(site.name)),
      ev(SiteDescriptionSet(site.description)),
      if (site.published) ev(SitePublished()) else ev(SiteUnpublished())
    )

    def flags: Seq[SiteEventData] = site.flags.map { flag =>
      ev(SiteFlagAdded(flag))
    }

    def domains: Seq[SiteEventData] = site.domains.flatMap { d =>
      Seq(ev(DomainAdded(d.name))) ++ (if (d.primary) Some(ev(PrimaryDomainSet(d.name))) else None)
    }

    def defaultMetaTags: Seq[SiteEventData] = site.defaultMetaTags.map { tag =>
      ev(DefaultMetaTagAdded(tag.name, tag.value))
    }

    def pages: Seq[SiteEventData] = site.pages.flatMap { page =>
      def basicPage = Seq(
        ev(PageAdded(page.path)),
        ev(PageNameSet(page.path, page.name))
      )

      def tags = page.metaTags.map { tag =>
        ev(PageMetaTagAdded(page.path, tag.name, tag.value))
      }

      def components = page.components.flatMap { c => Seq(
        ev(PageComponentAdded(page.path, c.id, c.componentType), c.dateCreated),
        ev(c.position.map(p => PageComponentPositionSet(c.id, p)).getOrElse(PageComponentPositionReset(c.id)), c.dateUpdated),
        ev(c.data match {
          case TextComponentData(text) => TextComponentDataSet(c.id, text)
          case ButtonComponentData(name, text, action) => ButtonComponentDataSet(c.id, name, text, action)
          case BlogComponentData(name, rss, tags) => BlogComponentDataSet(c.id, name, rss, tags)
        })
      )
      }

      basicPage ++ tags ++ components
    }

    def entryPoints: Seq[SiteEventData] = site.entryPoints.flatMap {
      case ep: DomainEntryPoint =>
        Seq(ev(DomainEntryPointAdded(ep.domain))) ++ (if (ep.primary) Seq(ev(PrimaryEntryPointSet(ep.lookupKey))) else Nil)
      case ep: FreeEntryPoint =>
        Seq(ev(FreeEntryPointAdded(ep.userName, ep.siteName))) ++ (if (ep.primary) Seq(ev(PrimaryEntryPointSet(ep.lookupKey))) else Nil)
    }

    basic ++ flags ++ domains ++ defaultMetaTags ++ pages ++ entryPoints
  }

  private def modifyPage(s: Site, path: String, f: Page => Page): Site = {
    s.copy(pages = s.pages.map(p => if (p.path == path) f(p) else p))
  }

  private def modifyComponent(s: Site, id: UUID, f: PageComponent => PageComponent): Site = {
    s.copy(pages = s.pages.map(p => p.copy(components = p.components.map(c => if (c.id == id) f(c) else c))))
  }
}
