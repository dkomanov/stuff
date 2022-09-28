package com.komanov.serialization.jmh;

import com.komanov.serialization.converters.*;
import com.komanov.serialization.converters.api.MyConverter;

public enum ConverterType {
    JSON(JsonConverter$.MODULE$),
    CBOR(JacksonCborConverter$.MODULE$),
    SMILE(JacksonSmileConverter$.MODULE$),
    SCALA_PB(ScalaPbConverter$.MODULE$),
    JAVA_PB(JavaPbConverter$.MODULE$),
    JAVA_THRIFT(JavaThriftConverter$.MODULE$),
    SERIALIZABLE(JavaSerializationConverter$.MODULE$),
    BOOPICKLE(BoopickleConverter$.MODULE$),
    CHILL(ChillConverter$.MODULE$),
    JSONITER(JsoniterScalaConverter$.MODULE$),
    CIRCE(CirceConverter$.MODULE$),
    UPICKLE_JSON(UpickleJsonConverter$.MODULE$),
    UPICKLE_POOLED_JSON(UpicklePooledJsonConverter$.MODULE$),
    UPICKLE_MSGPACK(UpickleMsgpackConverter$.MODULE$),
    CAP_N_PROTO(CapnprotoConverter$.MODULE$),
    CAP_N_PROTO_POOLED(CapnprotoPooledConverter$.MODULE$),
    /**/;

    public final MyConverter converter;

    ConverterType(MyConverter converter) {
        this.converter = converter;
    }
}
