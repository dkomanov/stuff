package src.com.komanov.stringformat.macros.tests

import com.komanov.stringformat.macros.MacroConcat._
import org.specs2.mock.Mockito
import org.specs2.mutable.SpecificationWithJUnit

class MacrosConcatTest extends SpecificationWithJUnit with Mockito {

  "Super Fast Interpolation" should {
    "work as s" >> {
      sfi"" must be_===(s"")
      sfi"abc" must be_===(s"abc")
      sfi"$o1" must be_===(s"$o1")
      sfi"${o1}after" must be_===(s"${o1}after")
      sfi"before${o1}" must be_===(s"before${o1}")
      sfi"before${o1}after" must be_===(s"before${o1}after")
      sfi"$o1$o2" must be_===(s"$o1$o2")
      sfi"!$o1!$o2!" must be_===(s"!$o1!$o2!")
      sfi"!$s1!$s2!$s3!$s4!$s5!$s6!$s7!$o1!$o2!$o3!$o4!$o5!$o6!$o7!" must be_===(s"!$s1!$s2!$s3!$s4!$s5!$s6!$s7!$o1!$o2!$o3!$o4!$o5!$o6!$o7!")
    }

    "serialize null as s" >> {
      sfi"$nullObject" must be_===(s"$nullObject")
    }

    "support expressions" >> {
      sfi"${car.name}" must be_===(s"${car.name}")
      sfi"${if (true) "1" else "0"}" must be_===(s"${if (true) "1" else "0"}")
    }

    "support parametric types" >> {
      testSfi(1) must be_===(testS(1))
      testSfi('A') must be_===(testS('A'))
      testSfi("A") must be_===(testS("A"))
    }

    def testSfi[A](a: A): String = sfi"$a"

    def testS[A](a: A): String = s"$a"

    "don't call expression multiple times" >> {
      val m = mock[Something]

      m.id returns 1
      m.name returns "name"
      m.obj returns null

      sfi"${m.id} - ${m.name} - ${m.obj} - ${m.id}" must be_===("1 - name - null - 1")

      got {
        two(m).id
        one(m).name
        one(m).obj
        noMoreCallsTo(m)
      }
    }
  }

  "Optimized Concatenation Interpolation" should {
    "work as s" >> {
      so"" must be_===(s"")
      so"abc" must be_===(s"abc")
      so"$o1" must be_===(s"$o1")
      so"${o1}after" must be_===(s"${o1}after")
      so"before${o1}" must be_===(s"before${o1}")
      so"before${o1}after" must be_===(s"before${o1}after")
      so"$o1$o2" must be_===(s"$o1$o2")
      so"!$o1!$o2!" must be_===(s"!$o1!$o2!")
      so"!$s1!$s2!$s3!$s4!$s5!$s6!$s7!$o1!$o2!$o3!$o4!$o5!$o6!$o7!" must be_===(s"!$s1!$s2!$s3!$s4!$s5!$s6!$s7!$o1!$o2!$o3!$o4!$o5!$o6!$o7!")
    }

    "serialize null as s" >> {
      so"$nullObject" must be_===(s"$nullObject")
    }

    "support expressions" >> {
      so"${car.name}" must be_===(s"${car.name}")
    }

    "don't call expression multiple times" >> {
      val m = mock[Something]

      m.id returns 1
      m.name returns "name"
      m.obj returns null

      so"${m.id} - ${m.name} - ${m.obj} - ${m.id}" must be_===("1 - name - null - 1")

      got {
        two(m).id
        one(m).name
        one(m).obj
        noMoreCallsTo(m)
      }
    }
  }

  val o1: Object = Int.box(10000)
  val o2: Object = Int.box(200000)
  val o3: Object = Int.box(3000000)
  val o4: Object = Int.box(40000000)
  val o5: Object = Int.box(500000000)
  val o6: Object = Int.box(60000000)
  val o7: Object = Int.box(7000000)
  val nullObject: Object = null

  val s1 = "10000"
  val s2 = "200000"
  val s3 = "3000000"
  val s4 = "40000000"
  val s5 = "500000000"
  val s6 = "60000000"
  val s7 = "7000000"

  val car = Car("f1")

  trait Something {
    def name: String

    def id: Int

    def obj: Object
  }

  case class Car(name: String)

}
