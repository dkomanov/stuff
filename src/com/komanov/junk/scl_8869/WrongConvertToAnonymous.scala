package com.komanov.junk.scl_8869

// https://youtrack.jetbrains.com/issue/SCL-8869
class WrongConvertToAnonymous extends MockitoStubsCut {
  val myInterface: MyInterface = null

  myInterface.convert(1) answers (o => o match {
    case t: Int => t.toString
  })

  myInterface.convert(1) answers (_ match {
    case t: Int => t.toString
  })

}

trait MyInterface {
  def convert(i: Int): String
}

trait MockitoStubsCut {
  implicit def theStubbed[T](c: => T): Stubbed[T] = new Stubbed(c)

  class Stubbed[T](c: => T) {
    def answers(function: Any => T) = {
    }

    def answers(function: (Any, Any) => T) = {
    }
  }
}
