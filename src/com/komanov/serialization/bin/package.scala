package com.komanov.serialization

import com.komanov.serialization.converters._
import com.komanov.serialization.converters.api.MyConverter

import java.util
import scala.jdk.CollectionConverters._

package object bin {
  val skip = new util.IdentityHashMap[MyConverter, Boolean](Seq(
    CapnprotoPooledConverter,
    CirceConverter,
    JsoniterScalaConverter,
    ScalaPbConverter,
    UpickleJsonConverter,
    UpicklePooledJsonConverter,
  ).map(_ -> true).toMap.asJava)
}
