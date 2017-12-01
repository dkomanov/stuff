package com.komanov.serialization.tmp

import java.io.File

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode

object ConvertApp extends App {
  val mapper = new ObjectMapper()
  val root = mapper.readTree(new File("/home/dmitryk/src/dkomanov/monorepo/site/public/data/scala-serialization/jmh.json"))
  require(root.isArray)

  //jmh_2016-06-26.json
  val benchmarkIt = root.iterator()
  while (benchmarkIt.hasNext) {
    val node = benchmarkIt.next().asInstanceOf[ObjectNode]
    val benchmark = node.get("benchmark").asText()
    // com.komanov.serialization.jmh.BooPickleBenchmark.both_events_1k
    val Array(before, after) = benchmark.split("Benchmark")
    val Array(action, dataType, dataSize) = after.split("_")
    val converter = before.substring(before.lastIndexOf('.') + 1)

    node.put("benchmark", "com.komanov.serialization.jmh." + (if (dataType == "site") "Site" else "Event") + "Benchmark" + action)
    node.putObject("params")
      .put("converterType", converter match {
        case "Json" => "JSON"
        case "ScalaPb" => "SCALA_PB"
        case "JavaPb" => "JAVA_PB"
        case "JavaThrift" => "JAVA_THRIFT"
        case "Scrooge" => "SCROOGE"
        case "JavaSerialization" => "SERIALIZABLE"
        case "Pickling" => "PICKLING"
        case "BooPickle" => "BOOPICKLE"
        case "Chill" => "CHILL"
      })
      .put("inputType", dataSize match {
        case "1k" => "_1_K"
        case "2k" => "_2_K"
        case "4k" => "_4_K"
        case "8k" => "_8_K"
        case "64k" => "_64_K"
      })
  }

  mapper.writerWithDefaultPrettyPrinter()
    .writeValue(new File("/home/dmitryk/src/dkomanov/monorepo/site/public/data/scala-serialization/jmh_2016-06-26.json"), root)
}
