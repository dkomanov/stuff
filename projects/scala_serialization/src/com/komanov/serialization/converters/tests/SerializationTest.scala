package com.komanov.serialization.converters.tests

import com.komanov.serialization.converters._
import com.komanov.serialization.domain.SiteEventData
import com.komanov.serialization.domain.testdata.TestData
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope
import org.specs2.specification.core.Fragments

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent._
import scala.concurrent.duration._

class SerializationTest extends SpecificationWithJUnit {

  sequential

  doTest("JSON", JsonConverter)
  doTest("ScalaPB", ScalaPbConverter)
  doTest("Java Protobuf", JavaPbConverter)
  doTest("Java Thrift", JavaThriftConverter)
  doTest("Scrooge", ScroogeConverter)
  doTest("Serializable", JavaSerializationConverter)
  doTest("BooPickle", BoopickleConverter)
  doTest("Chill", ChillConverter)
  doTest("Circe", CirceConverter)

  "ScalaPB and Java Protobuf" should {
    Fragments.foreach(TestData.sites) { case (name, site) =>
      s"be interoperable for site of $name" in new ctx {
        val javaMessage = JavaPbConverter.toByteArray(site)
        val scalaMessage = ScalaPbConverter.toByteArray(site)
        toHexDump(javaMessage) must be_===(toHexDump(scalaMessage))
      }
    }

    Fragments.foreach(TestData.events) { case (name, events) =>
      s"be interoperable events of $name" in new ctx {
        for (SiteEventData(_, event, _) <- events) {
          val javaMessage = JavaPbConverter.toByteArray(event)
          val scalaMessage = ScalaPbConverter.toByteArray(event)
          toHexDump(javaMessage) must be_===(toHexDump(scalaMessage))
        }
      }
    }
  }

  "Scrooge and Java Thrift" should {
    Fragments.foreach(TestData.sites) { case (name, site) =>
      s"be interoperable for site of $name" in new ctx {
        val javaMessage = JavaThriftConverter.toByteArray(site)
        val scalaMessage = ScroogeConverter.toByteArray(site)
        toHexDump(javaMessage) must be_===(toHexDump(scalaMessage))
      }
    }

    Fragments.foreach(TestData.events) { case (name, events) =>
      s"be interoperable events of $name" in new ctx {
        for (SiteEventData(_, event, _) <- events) {
          val javaMessage = JavaThriftConverter.toByteArray(event)
          val scalaMessage = ScroogeConverter.toByteArray(event)
          toHexDump(javaMessage) must be_===(toHexDump(scalaMessage))
        }
      }
    }
  }

  class ctx extends Scope

  def toHexDump(arr: Array[Byte]): String = {
    if (arr.isEmpty) {
      ""
    } else {
      val sb = new StringBuilder(3 * arr.length + arr.length / 16 + 1)
      for (i <- arr.indices) {
        val b = arr(i)

        sb.append(java.lang.Integer.toString(b, 16))
        if (i > 0 && i % 4 == 0) {
          sb.append(' ')
        }

        if (i > 0 && i % 16 == 0) {
          sb.append("\n")
        }
      }
      sb.toString()
    }
  }

  def doTest(converterName: String, converter: MyConverter) = {
    converterName should {
      Fragments.foreach(TestData.sites) { case (name, site) =>
        s"serialize-parse site of $name" in new ctx {
          val bytes = converter.toByteArray(site)
          val parsed = converter.fromByteArray(bytes)
          parsed must be_===(site)
        }

        s"work normally with multi-threading for serialization site of $name" in new ctx {
          doParallelTest {
            converter.toByteArray(site)
          }
        }

        s"work normally with multi-threading for deserialization site of $name" in new ctx {
          val bytes = converter.toByteArray(site)
          doParallelTest {
            converter.fromByteArray(bytes)
          }
        }
      }

      Fragments.foreach(TestData.events) { case (name, events) =>
        s"serialize-parse site events of $name" in new ctx {
          for (SiteEventData(_, event, _) <- events) {
            val bytes = converter.toByteArray(event)
            val parsed = converter.siteEventFromByteArray(event.getClass, bytes)
            parsed must be_===(event)
          }
        }

        s"work normally with multi-threading for serialization site events of $name" in new ctx {
          doParallelTest {
            events.foreach { ed =>
              converter.toByteArray(ed.event)
            }
          }
        }

        s"work normally with multi-threading for deserialization site events of $name" in new ctx {
          val classAndBytes = events.map(e => e.event.getClass -> converter.toByteArray(e.event))
          doParallelTest {
            classAndBytes.foreach { case (clazz, bytes) =>
              converter.siteEventFromByteArray(clazz, bytes)
            }
          }
        }
      }
    }
  }

  def doParallelTest[T](f: => T) = {
    val futures = (1 to 100).map(_ => Future(f))
    futures.foreach(f => Await.result(f, 10.seconds))
  }

}
