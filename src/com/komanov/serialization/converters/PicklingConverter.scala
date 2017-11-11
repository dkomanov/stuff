package com.komanov.serialization.converters

import com.komanov.serialization.converters.pickling._
import com.komanov.serialization.domain._

import scala.pickling.Defaults._
import scala.pickling._
import scala.pickling.binary._
import scala.pickling.shareNothing._
import scala.pickling.static._

/** https://github.com/scala/pickling */
object PicklingConverter extends MyConverter {

  private implicit val pageComponentTypePickler = new JavaEnumPickler[PageComponentType]
  private implicit val siteFlagPickler = new JavaEnumPickler[SiteFlag]
  private implicit val siteTypePickler = new JavaEnumPickler[SiteType]

  private implicit val domainPickler = PicklerUnpickler.generate[Domain]
  private implicit val metaTagPickler = PicklerUnpickler.generate[MetaTag]
  private implicit val textComponentDataPickler = PicklerUnpickler.generate[TextComponentData]
  private implicit val buttonComponentDataPickler = PicklerUnpickler.generate[ButtonComponentData]
  private implicit val blogComponentDataPickler = PicklerUnpickler.generate[BlogComponentData]
  private implicit val pageComponentDataPickler = PicklerUnpickler.generate[PageComponentData]
  private implicit val pageComponentPositionPickler = PicklerUnpickler.generate[PageComponentPosition]
  private implicit val pageComponentPickler = PicklerUnpickler.generate[PageComponent]
  private implicit val pagePickler = PicklerUnpickler.generate[Page]
  private implicit val domainEntryPointPickler = PicklerUnpickler.generate[DomainEntryPoint]
  private implicit val freeEntryPointPickler = PicklerUnpickler.generate[FreeEntryPoint]
  private implicit val entryPointPickler = PicklerUnpickler.generate[EntryPoint]
  private implicit val sitePickler = PicklerUnpickler.generate[Site]

  private implicit val siteEventPickler = PicklerUnpickler.generate[SiteEvent]
  private implicit val siteEventDataPickler = PicklerUnpickler.generate[SiteEventData]

  override def toByteArray(site: Site): Array[Byte] = {
    site.pickle.value
  }

  override def fromByteArray(bytes: Array[Byte]): Site = {
    bytes.unpickle[Site]
  }

  override def toByteArray(event: SiteEvent): Array[Byte] = {
    event.pickle.value
  }

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = {
    bytes.unpickle[SiteEvent]
  }

}
