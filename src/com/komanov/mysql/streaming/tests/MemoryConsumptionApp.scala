package com.komanov.mysql.streaming.tests

import com.komanov.mysql.streaming.{Drivers, MysqlRunner, Query}

object MemoryConsumptionApp {

  def main(args: Array[String]) = {
    require(args.length == 3, usage)

    val driver = Drivers.list.find(_.name.equalsIgnoreCase(args(0))).getOrElse(printAndExit("Unknown driver"))
    val streaming = args(1) match {
      case "streaming" => true
      case "at-once" => false
      case _ => printAndExit("Unknown mode")
    }
    val oom = args(2) match {
      case "oom" => true
      case "success" => false
      case _ => printAndExit("Unknown result")
    }

    MysqlRunner.run()

    Query.prepareTable(driver, length = 1000)

    try {
      if (streaming) {
        Query.forEachMillionViaStreaming(driver)
      } else {
        Query.forEachMillionAtOnce(driver)
      }

      if (oom) {
        printAndExit("Expected OOM")
      }
    } catch {
      case _: OutOfMemoryError if oom =>
        println(s"OOM occurred, success")
    }
  }

  private def usage: Nothing = {
    printAndExit(
      s"""Usage: DRIVER MODE RESULT
  DRIVER - one of: ${Drivers.list.map(_.name).mkString("| ")}
  MODE - one of: streaming | at-once
  RESULT - one of: oom | success
""")
  }

  private def printAndExit(s: String): Nothing = {
    Console.err.println(s)
    System.exit(1)
    throw new RuntimeException("Unreachable!")
  }

}
