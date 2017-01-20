package com.komanov.junk.mockito

import org.specs2.matcher.{Matcher, Matchers}
import org.specs2.mock.Mockito
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope

class MockitoAndMutableTest extends SpecificationWithJUnit with Mockito {
  "doBusiness" should {
    "replace 'a' with 'ab'" in new ctx {
      override def makeDao = mock[ContainerDao]

      manager.doBusiness(container)

      container must beModifiedContainer

      got {
        one(dao).update(beModifiedContainer)
        noMoreCallsTo(dao)
      }
    }

    "replace 'a' with 'ab' in a JMock way" in new ctx {
      override def makeDao = mock[ContainerDao].defaultAnswer(i => throw new IllegalStateException(s"Unexpected call: $i"))

      doAnswer(_ => {}).when(dao).update(beModifiedContainer)

      manager.doBusiness(container)
      container must beModifiedContainer

      got {
        one(dao).update(any)
        noMoreCallsTo(dao)
      }
    }.pendingUntilFixed("This test is correct - it catches a bug in a code. It demonstrates the threat of Mockito -- previous test doesn't check correctness")
  }

  trait ctx extends Scope {
    def makeDao: ContainerDao

    lazy val dao = makeDao
    lazy val manager = new ContainerManager(dao)

    val container = MutableContainer(Seq("b", "a"))

    def beModifiedContainer =
      MutableContainerMatchers.isMutableContainer(list = be_===(Seq("b", "ab")))
  }

}

case class MutableContainer(var list: Seq[String])

object MutableMutator {
  def mutate(c: MutableContainer)(pf: PartialFunction[String, String]): Unit = {
    c.list = c.list.map(s => if (pf.isDefinedAt(s)) pf(s) else s)
  }
}

trait ContainerDao {
  def update(c: MutableContainer): Unit
}

class ContainerManager(dao: ContainerDao) {
  def doBusiness(c: MutableContainer): Unit = {
    MutableMutator.mutate(c) {
      case s@"a" =>
        val newValue = s + "b"
        dao.update(c)
        newValue
    }
  }
}

class MutableContainerMatchers extends Matchers {

  object isMutableContainer {
    def apply(list: Matcher[Seq[String]]): Matcher[MutableContainer] = {
      list ^^ {
        (_: MutableContainer).list aka "list"
      }
    }
  }

}

object MutableContainerMatchers extends MutableContainerMatchers
