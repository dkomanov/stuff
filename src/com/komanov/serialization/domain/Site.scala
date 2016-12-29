package com.komanov.serialization.domain

import java.time.Instant
import java.util.UUID

import com.fasterxml.jackson.annotation.{JsonSubTypes, JsonTypeInfo}

import scala.pickling.directSubclasses


case class Domain(name: String,
                  primary: Boolean)


case class MetaTag(name: String,
                   value: String)


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(Array(
  new JsonSubTypes.Type(value = classOf[DomainEntryPoint], name = "DomainEntryPoint"),
  new JsonSubTypes.Type(value = classOf[FreeEntryPoint], name = "FreeEntryPoint")
))
@directSubclasses(Array(
  classOf[DomainEntryPoint],
  classOf[FreeEntryPoint]
))
sealed trait EntryPoint {
  def lookupKey: String

  def primary: Boolean
}

final case class DomainEntryPoint(domain: String, primary: Boolean) extends EntryPoint {
  override def lookupKey: String = domain
}

final case class FreeEntryPoint(userName: String, siteName: String, primary: Boolean) extends EntryPoint {
  override def lookupKey: String = s"$userName.wix.com/$siteName"
}


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(Array(
  new JsonSubTypes.Type(value = classOf[TextComponentData], name = "TextComponentData"),
  new JsonSubTypes.Type(value = classOf[ButtonComponentData], name = "ButtonComponentData"),
  new JsonSubTypes.Type(value = classOf[BlogComponentData], name = "BlogComponentData")
))
@directSubclasses(Array(
  classOf[TextComponentData],
  classOf[ButtonComponentData],
  classOf[BlogComponentData]
))
sealed trait PageComponentData

final case class TextComponentData(text: String) extends PageComponentData

final case class ButtonComponentData(name: String,
                                     text: String,
                                     action: UUID) extends PageComponentData

final case class BlogComponentData(name: String,
                                   rss: Boolean,
                                   tags: Boolean) extends PageComponentData


case class PageComponentPosition(x: Int,
                                 y: Int)

case class PageComponent(id: UUID,
                         componentType: PageComponentType,
                         data: PageComponentData,
                         position: Option[PageComponentPosition],
                         dateCreated: Instant,
                         dateUpdated: Instant)


case class Page(name: String,
                path: String,
                metaTags: Seq[MetaTag],
                components: Seq[PageComponent])


case class Site(id: UUID,
                ownerId: UUID,
                revision: Long,
                siteType: SiteType,
                flags: Seq[SiteFlag],
                name: String,
                description: String,
                domains: Seq[Domain],
                defaultMetaTags: Seq[MetaTag],
                pages: Seq[Page],
                entryPoints: Seq[EntryPoint],
                published: Boolean,
                dateCreated: Instant,
                dateUpdated: Instant)
