package com.komanov.junk.mockito

import org.specs2.mock.Mockito
import org.specs2.mock.mockito.MockitoStubs
import org.specs2.mutable.SpecificationWithJUnit

// http://www.komanov.com/#!Small-design-issues/yvypt/5590583b0cf2d9841200e545
class RespondExtensionTest extends SpecificationWithJUnit with Mockito with Mockitoz {
  // Not working, since there are overload for answers and type should be specified
  // myInterface.convert(any) answers { case t: Int => t.toString }

  "Old" should {
    "" >> {
      val myInterface = mock[MyInterface]

      myInterface.convert(any) answers { o => o match {
        case t: Int => t.toString
      }
      }

      myInterface.convert(any) answers (o => o match {
        case t: Int => t.toString
      })

      myInterface.convert(any) answers (_ match {
        case t: Int => t.toString
      })

      myInterface.convert(any) answerz {
        case t: Int => t.toString
      }

      myInterface.convert(1) === "1"
    }
  }

  "Test" should {
    "respond with generic type" in {
      val myInterface = mock[MyInterface]
      myInterface.convert(any) respond { i: Int => (i + 1).toString }
      myInterface.convert(5) === "6"
    }

    "respond with generic type 2" in {
      val myInterface = mock[MyInterface]
      myInterface.sum(any, any) respond { t: Array[Any] => (t(0).asInstanceOf[Int] + t(1).asInstanceOf[Double].toInt).toString }
      myInterface.sum(5, 3) === "8"
    }

    "answerz with anonymous case function" in {
      val myInterface = mock[MyInterface]
      myInterface.convert(any) answerz {
        case t: Int => (t + 1).toString
      }
      myInterface.convert(any)
      myInterface.convert(5) === "6"
    }

    "answerz with anonymous case function 2" in {
      val myInterface = mock[MyInterface]
      myInterface.sum(any, any) answerz {
        case Array(a: Int, b: Double) => (a + b.toInt).toString
      }
      myInterface.sum(5, 3) === "8"
    }

    "answerz2 with anonymous case function 2" in {
      val myInterface = mock[MyInterface]
      myInterface.convert(any) answerz2 {
        case (Array(a: Int), mock: Any) => (a + 1).toString
      }
      myInterface.convert(3) === "4"
    }
  }

}

trait MyInterface {
  def convert(i: Int): String
  def sum(a: Int, b: Double): String
}

trait Mockitoz extends MockitoStubs {
  implicit def theStubbedz[T](c: => T): Stubbedz[T] = new Stubbedz(c)

  class Stubbedz[T](c: => T) extends Stubbed(c) {
    def answerz(function: Any => T) = answers(function)
    def answerz2(function: (Any, Any) => T) = answers(function)
    def respond[A](function: A => T) = answers(o => function(o.asInstanceOf[A]))
  }

}
