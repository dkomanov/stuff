package com.komanov.mysql.streaming

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope
import org.specs2.specification.core.Fragments

import scala.collection.mutable

class QueryTest extends SpecificationWithJUnit {

  sequential

  MysqlRunner.run()

  Fragments.foreach(Drivers.list) { driver =>
    s"${driver.name}" should {
      s"prepare/select/clear database" in new ctx {
        Query.clearTable(driver)
        Query.prepareTable(driver)
        Query.selectAtOnce(driver) must be_===(Query.TestData)
        Query.selectViaStreaming(driver) must be_===(Query.TestData)
        Query.clearTable(driver)
        Query.selectAtOnce(driver) must beEmpty
      }

      s"stream everything" in new ctx {
        Query.clearTable(driver)
        Query.prepareTable(driver)
        val result = mutable.ListBuffer[TestTableRow]()
        Query.forEach(driver, row => result += row)
        result.toList must be_===(Query.TestData)
      }
    }
  }

  class ctx extends Scope {
  }

}
