package com.komanov.serialization.converters.bin

import com.komanov.serialization.converters.{Converters, MyConverter}
import com.komanov.serialization.domain.testdata.TestData
import com.komanov.serialization.domain.{EventProcessor, Site, SiteEvent}

trait BasePerfTestApp[Input, Output] {

  val N = 100000

  def createInput(converter: MyConverter, site: Site): Input

  def convert(converter: MyConverter, input: Input): Output

  def doTest(): Unit = {
    println("Warming up...")
    doWarmUp()

    println("Testing!")

    println("Converter," + TestData.sites.map(_._1).mkString(","))

    for ((converterName, converter) <- Converters.all) {
      val results = for {
        (name, site) <- TestData.sites
        input = createInput(converter, site)
      } yield doTest(converter, input)

      println(converterName + "," + results.map(_._2).mkString(","))
    }
  }

  private def doTest(c: MyConverter, input: Input): (Long, Long) = {
    Runtime.getRuntime.gc()
    Runtime.getRuntime.runFinalization()
    Runtime.getRuntime.gc()
    Runtime.getRuntime.gc()

    val start = System.nanoTime()
    runXTimes(c, input, N)
    val duration = System.nanoTime() - start
    val avg = duration / N
    duration -> avg
  }

  private def runXTimes(c: MyConverter, input: Input, x: Int): Unit = {
    for (_ <- 0 until x) {
      convert(c, input)
    }
  }

  private def doWarmUp() = {
    val x = N / 10

    for ((converterName, c) <- Converters.all) {
      print(s"$converterName... ")
      for (data <- TestData.sites) {
        val input = createInput(c, data._2)
        runXTimes(c, input, x)
      }
      println("done")
    }
  }

}

/*
Converter,1k,2k,4k,8k,64k
JSON,4365,8437,16771,35164,270175
ScalaPB,2176,3936,7475,14822,133119
Java PB,3173,6393,10123,21379,209716
Java Thrift,3657,6805,13074,27667,261673
Scrooge,3572,6506,12050,25036,233895
Serializable,13156,21203,36457,79045,652942
Pickles,53991,83601,220440,589888,4162785
Boopickle,5451,10628,17533,29765,225717
Chill,7202,9783,15130,27338,207871
 */
object SiteSerializationPerfTestApp extends App with BasePerfTestApp[Site, Array[Byte]] {
  override def createInput(converter: MyConverter, site: Site): Site = site

  override def convert(converter: MyConverter, input: Site): Array[Byte] = converter.toByteArray(input)

  doTest()
}

/*
Converter,1k,2k,4k,8k,64k
JSON,7670,12964,24804,51578,384623
ScalaPB,2335,4576,7326,14754,128730
Java PB,3504,6076,10269,19792,168952
Java Thrift,3451,5812,10048,20693,176020
Scrooge,3640,6522,12740,25081,230556
Serializable,61455,84196,102870,126839,575232
Pickles,40337,63840,165109,446043,3201348
Boopickle,2848,5017,8454,15962,97270
Chill,6675,9654,14770,25261,193136
 */
object SiteDeserializationPerfTestApp extends App with BasePerfTestApp[Array[Byte], Site] {
  override def createInput(converter: MyConverter, site: Site): Array[Byte] = converter.toByteArray(site)

  override def convert(converter: MyConverter, input: Array[Byte]): Site = converter.fromByteArray(input)

  doTest()
}

/*
Converter,1k,2k,4k,8k,64k
JSON,9192,17366,34574,76571,701055
ScalaPB,2194,4542,8618,17011,170413
Java PB,3110,6390,11922,25144,283493
Java Thrift,4357,9180,17560,37924,405784
Scrooge,4842,10226,19922,42428,423060
Serializable,16957,31195,68399,160541,1492595
Pickles,47793,83754,236829,648561,4936980
Boopickle,16867,32278,62663,135614,1379687
Chill,3704,7098,15025,33376,326856
 */
object EventsSerializationPerfTestApp extends App with BasePerfTestApp[Seq[SiteEvent], Unit] {
  override def createInput(converter: MyConverter, site: Site): Seq[SiteEvent] = {
    EventProcessor.unapply(site).map(_.event)
  }

  override def convert(converter: MyConverter, input: Seq[SiteEvent]): Unit = {
    for (e <- input) {
      converter.toByteArray(e)
    }
  }

  doTest()
}

/*
Converter,1k,2k,4k,8k,64k
JSON,12125,23012,45171,98008,880806
ScalaPB,3394,6644,12681,26589,251012
Java PB,2690,5550,10564,20359,214071
Java Thrift,3556,6974,13436,29135,281339
Scrooge,3911,7678,15867,33832,331989
Serializable,78081,138535,323729,774177,6725015
Pickles,34623,61638,169895,462075,3522794
Boopickle,4828,9941,18158,38296,389896
Chill,4770,9203,18638,38146,382506
 */
object EventsDeserializationPerfTestApp extends App with BasePerfTestApp[Seq[(Class[_], Array[Byte])], Unit] {
  override def createInput(converter: MyConverter, site: Site): Seq[(Class[_], Array[Byte])] = {
    EventProcessor.unapply(site).map(_.event).map(e => e.getClass -> converter.toByteArray(e))
  }

  override def convert(converter: MyConverter, input: Seq[(Class[_], Array[Byte])]): Unit = {
    for ((clazz, bytes) <- input) {
      converter.siteEventFromByteArray(clazz, bytes)
    }
  }

  doTest()
}
