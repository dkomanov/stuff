package com.komanov.serialization.converters.api

import com.komanov.serialization.domain.SiteEvent

trait EventConverter {

  def toByteArray(event: SiteEvent): Array[Byte]

  def siteEventFromByteArray(clazz: Class[_], bytes: Array[Byte]): SiteEvent

}
