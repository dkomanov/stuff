package com.komanov.uuid.bin

import java.util.UUID

import com.komanov.uuid._

/*
The lastest run result:

original avg 353 ns, total 7072824462
java 0   avg 473 ns, total 9462440447
java 1   avg 340 ns, total 6819987157
java 2   avg 171 ns, total 3433067273
java 3   avg 145 ns, total 2914499208
java 4   avg 111 ns, total 2226519520
java 5   avg 81 ns, total 1628705887
java f   avg 81 ns, total 1623636361
*/
object GuidParserPerformanceTest extends App {

  val N = 10000000

  type ParserFunc = String => UUID

  val uuid1 = "00000000-0000-0000-0000-000000000000"
  val uuid2 = "01234567-89ab-cdef-ABCD-EF1234567890"
  val uuid3 = "ffffffff-ffff-ffff-ffff-ffffffffffff"

  val originalF: ParserFunc = UUID.fromString
  val scalaFast1F: ParserFunc = UuidScala1Utils.fromStringFast
  val scalaFast2F: ParserFunc = UuidScala2Utils.fromStringFast
  val scalaFast3F: ParserFunc = UuidScala3Utils.fromStringFast
  val scalaFast4F: ParserFunc = UuidScala4Utils.fromStringFast
  val scalaFastFF: ParserFunc = UuidScalaFinalUtils.fromStringFast
  val scalaFastEF: ParserFunc = UuidScalaEpicUtils.fromStringFast
  val javaFast0F: ParserFunc = UuidJava0Utils.fromStringFast
  val javaFast1F: ParserFunc = UuidJava1Utils.fromStringFast
  val javaFast2F: ParserFunc = UuidJava2Utils.fromStringFast
  val javaFast3F: ParserFunc = UuidJava3Utils.fromStringFast
  val javaFast4F: ParserFunc = UuidJava4Utils.fromStringFast
  val javaFast5F: ParserFunc = UuidJava5Utils.fromStringFast
  val javaFastFF: ParserFunc = UuidJavaFinalUtils.fromStringFast
  val javaFastEF: ParserFunc = UuidJavaEpicUtils.fromStringFast
  val javaFastE2F: ParserFunc = UuidJavaEpic2Utils.fromStringFast

  val algorithms = Seq(
    originalF,
    scalaFast1F, scalaFast2F, scalaFast3F, scalaFast4F, scalaFastFF, scalaFastEF,
    javaFast0F, javaFast1F, javaFast2F, javaFast3F, javaFast4F, javaFast5F, javaFastFF, javaFastEF, javaFastE2F
  )

  doWarmUp()
  doFuncTest()

  doTest("original", originalF)
  doTest("scala 1  ", scalaFast1F)
  doTest("scala 2  ", scalaFast2F)
  doTest("scala 3  ", scalaFast3F)
  doTest("scala 4  ", scalaFast4F)
  doTest("scala f  ", scalaFastFF)
  doTest("scala e  ", scalaFastEF)
  doTest("java 0  ", javaFast0F)
  doTest("java 1  ", javaFast1F)
  doTest("java 2  ", javaFast2F)
  doTest("java 3  ", javaFast3F)
  doTest("java 4  ", javaFast4F)
  doTest("java 5  ", javaFast5F)
  doTest("java f  ", javaFastFF)
  doTest("java E  ", javaFastEF)
  doTest("java E2 ", javaFastE2F)

  def doTest(name: String, f: ParserFunc): Unit = {
    Runtime.getRuntime.gc()
    Runtime.getRuntime.runFinalization()
    Runtime.getRuntime.gc()
    Runtime.getRuntime.gc()

    val start = System.nanoTime()
    for (_ <- 0 until N) {
      f(uuid2)
    }
    val duration = System.nanoTime() - start
    val avg = duration / N
    println(s"$name avg $avg ns, total $duration")
  }

  def doWarmUp() = {
    // warm up
    for (i <- 0 until (N / 10)) {
      for (a <- algorithms) {
        a(uuid2)
      }
    }

  }

  def doFuncTest() = {
    val uuids = Seq(uuid1, uuid2, uuid3)

    algorithms.foreach(a => new UuidTest(a).run())

    val results = algorithms.map(a => uuids.map(a))
    val result = results.head
    for (r <- results) {
      require(r == result)
    }
  }

}
