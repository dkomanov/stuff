package com.komanov.mysql.streaming

import java.sql._
import java.util

import scala.collection.mutable

object Query {

  val TableName = "test_table"
  val CreateSql =
    s"""
CREATE TABLE $TableName (
  id INT,
  name VARCHAR(1000),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
"""

  private val MillionSql =
    s"""
SELECT
  t1.id AS id1, t1.name AS name1, t2.id AS id2, t2.name AS name2,
  t2.name AS more1, t2.name AS more2, t2.name AS more3, t2.name AS more4
FROM
  $TableName t1
  LEFT JOIN
  $TableName t2
    ON TRUE
"""

  val TableSize = 1000

  val TestData = generateTestData(200)

  private val map = new util.IdentityHashMap[MysqlDriver, Connection]()

  private def generateTestData(length: Int) = {
    (1 to TableSize)
      .map(i => TestTableRow(i, i.toString.padTo(length, '0')))
      .toList
  }

  def prepareTable(driver: MysqlDriver, length: Int = 200): Unit = {
    val conn = getConnection(driver)
    val st = conn.prepareStatement(s"INSERT INTO $TableName (id, name) VALUES(?, ?)")
    for (row <- generateTestData(length)) {
      st.setInt(1, row.id)
      st.setString(2, row.name)
      require(st.executeUpdate() == 1)
    }
    st.close()
  }

  def clearTable(driver: MysqlDriver): Unit = {
    withStatement(driver) { st =>
      st.execute(s"TRUNCATE TABLE $TableName")
    }
  }

  def selectAtOnce(driver: MysqlDriver): List[TestTableRow] = {
    withStatement(driver) { st =>
      val rs = st.executeQuery(s"SELECT id, name FROM $TableName")
      val result = mutable.ListBuffer[TestTableRow]()
      while (rs.next()) {
        result += mapRow(rs)
      }
      result.toList
    }
  }

  def selectAtOnce(driver: MysqlDriver, limit: Int): List[TestTableRow] = {
    withStatement(driver) { st =>
      val rs = st.executeQuery(s"SELECT id, name FROM $TableName LIMIT $limit")
      val result = mutable.ListBuffer[TestTableRow]()
      while (rs.next()) {
        result += mapRow(rs)
      }
      result.toList
    }
  }

  def selectViaStreaming(driver: MysqlDriver): List[TestTableRow] = {
    val result = mutable.ListBuffer[TestTableRow]()
    forEach(driver, r => result += r)
    result.toList
  }

  def selectViaStreaming(driver: MysqlDriver, limit: Int): List[TestTableRow] = {
    val result = mutable.ListBuffer[TestTableRow]()
    forEach(driver, limit, r => result += r)
    result.toList
  }

  def forEach(driver: MysqlDriver, f: TestTableRow => Unit): Unit = {
    withStatement(driver) { st =>
      st.setFetchSize(Int.MinValue)

      val rs = st.executeQuery(s"SELECT id, name FROM $TableName")
      while (rs.next()) {
        f(mapRow(rs))
      }
    }
  }

  def forEach(driver: MysqlDriver, limit: Int, f: TestTableRow => Unit): Unit = {
    withStatement(driver) { st =>
      st.setFetchSize(Int.MinValue)

      val rs = st.executeQuery(s"SELECT id, name FROM $TableName LIMIT $limit")
      while (rs.next()) {
        f(mapRow(rs))
      }
    }
  }

  def forEachMillionAtOnce(driver: MysqlDriver): Unit = {
    withStatement(driver) { st =>
      // no setFetchSize!

      var count = 0
      val rs = st.executeQuery(MillionSql)
      while (rs.next()) {
        mapRow(rs)
        count += 1
      }
      require(count == TableSize * TableSize)
    }
  }

  def forEachMillionViaStreaming(driver: MysqlDriver): Unit = {
    withStatement(driver) { st =>
      st.setFetchSize(Int.MinValue)

      var count = 0
      val rs = st.executeQuery(MillionSql)
      while (rs.next()) {
        mapRow(rs)
        count += 1
      }
      require(count == TableSize * TableSize)
    }
  }

  private def mapRow(rs: ResultSet): TestTableRow = {
    TestTableRow(rs.getInt(1), rs.getString(2))
  }

  private def withStatement[T](driver: MysqlDriver)(f: Statement => T): T = {
    val st = getConnection(driver).createStatement()
    try {
      f(st)
    } finally {
      st.close()
    }
  }

  private def getConnection(d: MysqlDriver): Connection = {
    var c = map.get(d)
    if (c == null) {
      c = DriverManager.getConnection(d.url)
      map.put(d, c)
    }
    c
  }

}

case class TestTableRow(id: Int, name: String)
