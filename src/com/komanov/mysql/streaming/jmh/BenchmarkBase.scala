package com.komanov.mysql.streaming.jmh

import java.util.concurrent.TimeUnit

import com.komanov.mysql.streaming._
import org.openjdk.jmh.annotations._

@State(Scope.Benchmark)
@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Fork(value = 1, jvmArgs = Array("-Xmx1G"))
@Threads(1)
@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
abstract class BenchmarkBase(driver: MysqlDriver) {

  OneTimeInitialization.initialize(driver)

  @Param(Array(
    "1", "2", "3", "4", "5", "6", "7", "8", "9",
    "10", "20", "30", "40", "50", "60", "70", "80", "90",
    "100", "200", "300", "400", "500", "600", "700", "800", "900",
    "1000"
  ))
  var limit: Int = 0

  @Benchmark
  def atOnce(): List[TestTableRow] = {
    Query.selectAtOnce(driver, limit)
  }

  @Benchmark
  def stream(): List[TestTableRow] = {
    Query.selectViaStreaming(driver, limit)
  }

}

private[jmh] object OneTimeInitialization {
  private var initialized = false

  def initialize(driver: MysqlDriver): Unit = synchronized {
    if (!initialized) {
      MysqlRunner.run()

      Query.clearTable(driver)
      Query.prepareTable(driver)

      initialized = true
    }
  }
}
