package com.komanov.serialization.converters

import com.komanov.serialization.converters.api.MyConverter
import com.komanov.serialization.domain.{Site, SiteEvent}
import com.twitter.chill.ScalaKryoInstantiator

/** https://github.com/twitter/chill */
object ChillConverter extends MyConverter {

  private val pool = ScalaKryoInstantiator.defaultPool

  override def toByteArray(site: Site): Array[Byte] = {
    pool.toBytesWithoutClass(site)
  }

  // WOW Chill mutates input array! We need to clone it. https://github.com/twitter/chill/issues/181
  override def fromByteArray(bytes: Array[Byte]): Site = {
    pool.fromBytes(bytes.clone(), classOf[Site])
  }

  override def toByteArray(event: SiteEvent): Array[Byte] = {
    pool.toBytesWithoutClass(event)
  }

  override def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent = {
    pool.fromBytes(bytes.clone(), clazz).asInstanceOf[SiteEvent]
  }

}
