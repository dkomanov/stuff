package com.komanov.mysql.streaming

import com.wix.mysql.EmbeddedMysql
import com.wix.mysql.EmbeddedMysql._
import com.wix.mysql.config.Charset
import com.wix.mysql.config.MysqldConfig._
import com.wix.mysql.config.SchemaConfig._
import com.wix.mysql.distribution.Version

object MysqlRunner {

  private lazy val mysql: EmbeddedMysql = {
    val config = aMysqldConfig(Version.v5_6_latest)
      .withPort(Drivers.Port)
      .withUser(Drivers.UserName, Drivers.Password)
      .withCharset(Charset.UTF8MB4)
      .build()

    val mysqld = anEmbeddedMysql(config)
      .start()

    mysqld.addSchema(
      aSchemaConfig(Drivers.DataBase)
        .withCommands(Query.CreateSql)
        .build()
    )

    mysqld
  }

  def run(): Unit = {
    mysql.toString
  }

}
