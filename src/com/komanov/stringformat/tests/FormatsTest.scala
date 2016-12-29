package com.komanov.stringformat.tests

import com.komanov.stringformat.{JavaFormats, ScalaFormats, InputArg}

import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragment

class FormatsTest extends SpecificationWithJUnit {

  val formats: List[(String, (Int, String, Object) => String)] = List(
    "javaConcat" -> JavaFormats.concat,
    "stringFormat" -> JavaFormats.stringFormat,
    "messageFormat" -> JavaFormats.messageFormat,
    "slf4j" -> JavaFormats.slf4j,
    "scalaConcat" -> ScalaFormats.concat,
    "optimizedConcat1" -> ScalaFormats.optimizedConcat1,
    "optimizedConcat2" -> ScalaFormats.optimizedConcat2,
    "optimizedConcatMacros" -> ScalaFormats.optimizedConcatMacros,
    "sInterpolator" -> ScalaFormats.sInterpolator,
    "fInterpolator" -> ScalaFormats.fInterpolator,
    "rawInterpolator" -> ScalaFormats.rawInterpolator,
    "sfiInterpolator" -> ScalaFormats.sfiInterpolator
  )

  Fragment.foreach(formats) { case (name, f) =>
    s"$name" should {
      "product the same result as JavaConcat" >> {
        f(1, "str", null) must beEqualTo(JavaFormats.concat(1, "str", null))
        f(1, null, "str") must beEqualTo(JavaFormats.concat(1, null, "str"))
      }
    }
  }

  val formatsWithInputArgs = for {
    (name, f) <- formats
    arg <- InputArg.values
  } yield (name, arg, f)

  Fragment.foreach(formatsWithInputArgs) { case (name, arg, f) =>
    s"$name" should {
      s"product the same result as JavaConcat for $arg" >> {
        f(arg.value1, arg.value2, null) must beEqualTo(JavaFormats.concat(arg.value1, arg.value2, null))
      }
    }
  }

}
