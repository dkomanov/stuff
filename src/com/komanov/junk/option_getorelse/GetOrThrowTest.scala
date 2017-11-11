package com.komanov.junk.option_getorelse

import org.specs2.mutable.SpecificationWithJUnit

class GetOrThrowTest extends SpecificationWithJUnit {
  "Option.getOrElse" should {
    "throw on None" in {
      val o = Option[String](null)
      o.getOrElse(throw new MyException(true)) must throwA[MyException]
    }

    "not throw on Some" in {
      val o = Option("1")
      o.getOrElse(throw new MyException(false)) === "1"
    }
  }

  class MyException(shouldBeThrow: Boolean) extends Throwable {
    require(shouldBeThrow)
  }

}
