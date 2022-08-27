package com.komanov.redis.bin

import com.komanov.redis.StringUuidCodec
import io.lettuce.core.RedisClient
import io.lettuce.core.api.StatefulRedisConnection

import java.nio.file.{Files, Paths}
import java.util.UUID
import scala.collection.mutable

// Usage: SET_NAME INPUT_FILE
object DataFiller extends App {

  val Array(setName, input) = args

  val inputPath = Paths.get(input)
  require(Files.exists(inputPath), s"INPUT_FILE doesn't exist: $input")

  val client = RedisClient.create("redis://localhost:6379")
  val connection: StatefulRedisConnection[String, UUID] = client.connect(StringUuidCodec);
  val sync = connection.sync();

  val chunk = mutable.ListBuffer[UUID]()

  def dumpChunk(): Unit = {
    val num = sync.sadd(setName, chunk.toSeq: _*)
    require(num == chunk.size, s"Failed to SADD: $num != ${chunk.size}")
    chunk.clear()
  }

  Files.lines(inputPath).forEach { line =>
    chunk += UUID.fromString(line)
    if (chunk.size == 1000) {
      dumpChunk()
    }
  }
  if (chunk.nonEmpty) {
    dumpChunk()
  }

  client.shutdown()

}
