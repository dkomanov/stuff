package com.komanov.mysql.streaming

import com.komanov.mysql.streaming.Drivers._

object Drivers {

  val Host = "localhost"
  val Port = 3316
  val UserName = "sa"
  val Password = "sa"
  val DataBase = "streaming_test"

  val list = Seq(
    ConnectorJDriver,
    MariaDbDriver,
    DrizzleDriver
  )

}

sealed trait MysqlDriver {
  def name: String

  def url: String
}

case object ConnectorJDriver extends MysqlDriver {
  override val name = "ConnectorJ"

  override val url = s"jdbc:mysql://$Host:$Port/$DataBase?user=$UserName&password=$Password&cacheServerConfiguration=true&createDatabaseIfNotExist=false"
}

case object MariaDbDriver extends MysqlDriver {
  override val name = "MariaDB"

  override val url = s"jdbc:mariadb://$Host:$Port/$DataBase?user=$UserName&password=$Password&fastConnect=true&tcpAbortiveClose=true"
}

case object DrizzleDriver extends MysqlDriver {
  override val name = "Drizzle"

  override val url = s"jdbc:drizzle://$UserName:$Password@$Host:$Port/$DataBase"
}
