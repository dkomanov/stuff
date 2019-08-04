package com.komanov.serialization.converters

import com.komanov.serialization.converters.api.MyConverter

object Converters {

  val all: Seq[(String, MyConverter)] = Seq(
    "JSON" -> JsonConverter,
    "CBOR" -> JacksonCborConverter,
    "Smile" -> JacksonSmileConverter,
    "ScalaPB" -> ScalaPbConverter,
    "Java PB" -> JavaPbConverter,
    "Java Thrift" -> JavaThriftConverter,
    "Scrooge" -> ScroogeConverter,
    "Serializable" -> JavaSerializationConverter,
    "BooPickle" -> BoopickleConverter,
    "Chill" -> ChillConverter,
    "KryoMacros" -> KryoMacrosConverter,
    "Jsoniter" -> JsoniterScalaConverter,
    "Circe" -> CirceConverter
  )

}
