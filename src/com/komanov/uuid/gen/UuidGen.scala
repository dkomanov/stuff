package com.komanov.uuid.gen

import java.util.UUID

// uuidgen in linux is very slow (~13 millis per single uuid).
object UuidGen extends App {

  val n = args.headOption
    .flatMap(_.toIntOption)
    .filter(_ > 0)
    .getOrElse(throw new IllegalArgumentException("Expected positive number argument"))

  for (l <- (1 to n).grouped(1000)) {
    // reducing IO via grouping
    println(l.map(_ => UUID.randomUUID).mkString("\n"))
  }
}
