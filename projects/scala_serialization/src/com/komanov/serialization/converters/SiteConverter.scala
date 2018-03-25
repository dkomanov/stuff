package com.komanov.serialization.converters

import com.komanov.serialization.domain.Site

trait SiteConverter {

  def toByteArray(site: Site): Array[Byte]

  def fromByteArray(bytes: Array[Byte]): Site

}
