package com.komanov.mysql.hashset.fill

import com.komanov.mysql.hashset.UuidHelper

import java.nio.file.{Files, Paths}
import java.sql.DriverManager
import java.util.UUID
import scala.collection.mutable

// Usage: USER PASSWORD SET_NAME INPUT_FILE
object DataFiller extends App {

  val Array(user, password, setName, input) = args
  val tableName = setName.replace("-", "")

  // CREATE DATABASE hash_set;
  // CREATE TABLE set1m (id BINARY(16) NOT NULL PRIMARY KEY) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;
  // CREATE TABLE set10m (id BINARY(16) NOT NULL PRIMARY KEY) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;
  // CREATE TABLE set100k (id BINARY(16) NOT NULL PRIMARY KEY) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

  val inputPath = Paths.get(input)
  require(Files.exists(inputPath), s"INPUT_FILE doesn't exist: $input")

  val url = s"jdbc:mysql://localhost:3306/hash_set?cacheServerConfiguration=true&createDatabaseIfNotExist=false&rewriteBatchedStatements=true"

  val chunk = mutable.ListBuffer[UUID]()

  def dumpChunk(): Unit = {
    val conn = DriverManager.getConnection(url, user, password)
    val st = conn.prepareStatement(s"INSERT INTO $tableName (id) VALUES (?)")
    chunk.foreach { uuid =>
      st.setBytes(1, UuidHelper.toBytes(uuid))
      st.addBatch()
      st.clearParameters()
    }
    st.executeBatch()
    st.close()
    conn.close()
    chunk.clear()
  }

  Files.lines(inputPath).forEach { line =>
    chunk += UUID.fromString(line)
    if (chunk.size == 50000) {
      dumpChunk()
    }
  }
  if (chunk.nonEmpty) {
    dumpChunk()
  }

}
