package com.komanov.junk.exception

/*
the latest run:

convert  avg 2 ns, total 24027983
ctor     avg 23 ns, total 233135146
create   avg 1456 ns, total 14560671210
 */
object ExceptionCost extends App {

  private val N = 10000000

  type ConvertFunc = () => Unit

  val scalaTry = scala.util.Failure(new RuntimeException)

  val convertF: ConvertFunc = () => convertException()
  val ctorF: ConvertFunc = () => constructorException()
  val createF: ConvertFunc = () => createException()

  val algorithms = Map(
    "convert " -> convertF,
    "ctor    " -> ctorF,
    "create  " -> createF
  )

  doWarmUp()
  for ((name, f) <- algorithms) {
    doTest(name, f)
  }

  private def doTest(name: String, f: ConvertFunc): Unit = {
    val start = System.nanoTime()
    for (_ <- 0 until N) {
      f()
    }
    val duration = System.nanoTime() - start
    val avg = duration / N
    println(s"$name avg $avg ns, total $duration")
  }

  private def doWarmUp() = {
    // warm up
    for (i <- 0 until (N / 10)) {
      for (a <- algorithms) {
        a._2()
      }
    }
  }

  private def convertException(): Unit = {
    if (scalaTry.isFailure) scala.util.Failure(scalaTry.exception) else scala.util.Success(scalaTry.get)
  }

  private def constructorException(): Unit = {
    scala.util.Try(scalaTry.get)
  }

  private def createException(): Unit = {
    new RuntimeException
  }

}
