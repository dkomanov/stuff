package com.komanov.serialization.converters

import com.komanov.serialization.domain.{SiteEvent, SiteEventData}

trait EventConverter {

  def toByteArray(event: SiteEvent): Array[Byte]

  def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent

}
