package com.komanov.serialization.domain

import java.time.Instant
import java.util.UUID

import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(Array(
  new JsonSubTypes.Type(value = classOf[SiteCreated], name = "SiteCreated"),
  new JsonSubTypes.Type(value = classOf[SiteNameSet], name = "SiteNameSet"),
  new JsonSubTypes.Type(value = classOf[SiteDescriptionSet], name = "SiteDescriptionSet"),
  new JsonSubTypes.Type(value = classOf[SiteRevisionSet], name = "SiteRevisionSet"),
  new JsonSubTypes.Type(value = classOf[SitePublished], name = "SitePublished"),
  new JsonSubTypes.Type(value = classOf[SiteUnpublished], name = "SiteUnpublished"),
  new JsonSubTypes.Type(value = classOf[SiteFlagAdded], name = "SiteFlagAdded"),
  new JsonSubTypes.Type(value = classOf[SiteFlagRemoved], name = "SiteFlagRemoved"),
  new JsonSubTypes.Type(value = classOf[DomainAdded], name = "DomainAdded"),
  new JsonSubTypes.Type(value = classOf[DomainRemoved], name = "DomainRemoved"),
  new JsonSubTypes.Type(value = classOf[PrimaryDomainSet], name = "PrimaryDomainSet"),
  new JsonSubTypes.Type(value = classOf[DefaultMetaTagAdded], name = "DefaultMetaTagAdded"),
  new JsonSubTypes.Type(value = classOf[DefaultMetaTagRemoved], name = "DefaultMetaTagRemoved"),
  new JsonSubTypes.Type(value = classOf[PageAdded], name = "PageAdded"),
  new JsonSubTypes.Type(value = classOf[PageRemoved], name = "PageRemoved"),
  new JsonSubTypes.Type(value = classOf[PageNameSet], name = "PageNameSet"),
  new JsonSubTypes.Type(value = classOf[PageMetaTagAdded], name = "PageMetaTagAdded"),
  new JsonSubTypes.Type(value = classOf[PageMetaTagRemoved], name = "PageMetaTagRemoved"),
  new JsonSubTypes.Type(value = classOf[PageComponentAdded], name = "PageComponentAdded"),
  new JsonSubTypes.Type(value = classOf[PageComponentRemoved], name = "PageComponentRemoved"),
  new JsonSubTypes.Type(value = classOf[PageComponentPositionSet], name = "PageComponentPositionSet"),
  new JsonSubTypes.Type(value = classOf[PageComponentPositionReset], name = "PageComponentPositionReset"),
  new JsonSubTypes.Type(value = classOf[TextComponentDataSet], name = "TextComponentDataSet"),
  new JsonSubTypes.Type(value = classOf[ButtonComponentDataSet], name = "ButtonComponentDataSet"),
  new JsonSubTypes.Type(value = classOf[BlogComponentDataSet], name = "BlogComponentDataSet"),
  new JsonSubTypes.Type(value = classOf[DomainEntryPointAdded], name = "DomainEntryPointAdded"),
  new JsonSubTypes.Type(value = classOf[FreeEntryPointAdded], name = "FreeEntryPointAdded"),
  new JsonSubTypes.Type(value = classOf[EntryPointRemoved], name = "EntryPointRemoved"),
  new JsonSubTypes.Type(value = classOf[PrimaryEntryPointSet], name = "PrimaryEntryPointSet")
))
sealed trait SiteEvent {
}

final case class SiteEventData(id: UUID,
                               event: SiteEvent,
                               timestamp: Instant)


final case class SiteCreated(id: UUID, ownerId: UUID, siteType: SiteType) extends SiteEvent

final case class SiteNameSet(name: String) extends SiteEvent

final case class SiteDescriptionSet(description: String) extends SiteEvent

final case class SiteRevisionSet(revision: Long) extends SiteEvent

final case class SitePublished() extends SiteEvent

final case class SiteUnpublished() extends SiteEvent

// SiteFlag

final case class SiteFlagAdded(siteFlag: SiteFlag) extends SiteEvent

final case class SiteFlagRemoved(siteFlag: SiteFlag) extends SiteEvent

// Domain

final case class DomainAdded(name: String) extends SiteEvent

final case class DomainRemoved(name: String) extends SiteEvent

final case class PrimaryDomainSet(name: String) extends SiteEvent

// DefaultMetaTag

final case class DefaultMetaTagAdded(name: String, value: String) extends SiteEvent

final case class DefaultMetaTagRemoved(name: String) extends SiteEvent

// Page

final case class PageAdded(path: String) extends SiteEvent

final case class PageRemoved(path: String) extends SiteEvent

final case class PageNameSet(path: String, name: String) extends SiteEvent

final case class PageMetaTagAdded(path: String, name: String, value: String) extends SiteEvent

final case class PageMetaTagRemoved(path: String, name: String) extends SiteEvent

// Page::PageComponent

final case class PageComponentAdded(pagePath: String, id: UUID, componentType: PageComponentType) extends SiteEvent

final case class PageComponentRemoved(pagePath: String, id: UUID) extends SiteEvent

final case class PageComponentPositionSet(id: UUID, position: PageComponentPosition) extends SiteEvent

final case class PageComponentPositionReset(id: UUID) extends SiteEvent

final case class TextComponentDataSet(id: UUID, text: String) extends SiteEvent

final case class ButtonComponentDataSet(id: UUID, name: String, text: String, action: UUID) extends SiteEvent

final case class BlogComponentDataSet(id: UUID, name: String, rss: Boolean, tags: Boolean) extends SiteEvent

// EntryPoint

final case class DomainEntryPointAdded(domain: String) extends SiteEvent

final case class FreeEntryPointAdded(userName: String, siteName: String) extends SiteEvent

final case class EntryPointRemoved(lookupKey: String) extends SiteEvent

final case class PrimaryEntryPointSet(lookupKey: String) extends SiteEvent
