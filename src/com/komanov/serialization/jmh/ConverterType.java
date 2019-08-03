package com.komanov.serialization.jmh;

import com.komanov.serialization.converters.*;
import com.komanov.serialization.converters.api.MyConverter;

public enum ConverterType {
    JSON(JsonConverter$.MODULE$),
    CBOR(JacksonCborConverter$.MODULE$),
    SCALA_PB(ScalaPbConverter$.MODULE$),
    JAVA_PB(JavaPbConverter$.MODULE$),
    JAVA_THRIFT(JavaThriftConverter$.MODULE$),
    SCROOGE(ScroogeConverter$.MODULE$),
    SERIALIZABLE(JavaSerializationConverter$.MODULE$),
    BOOPICKLE(BoopickleConverter$.MODULE$),
    //CHILL(ChillConverter$.MODULE$),
    //CIRCE(CirceConverter$.MODULE$),
    /**/;

    public final MyConverter converter;

    ConverterType(MyConverter converter) {
        this.converter = converter;
    }
}
