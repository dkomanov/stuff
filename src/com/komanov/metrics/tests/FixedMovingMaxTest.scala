package com.komanov.metrics.tests

import com.codahale.metrics.Clock
import com.komanov.metrics._
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.Scope
import org.specs2.specification.core.Fragments

import scala.concurrent.duration._

class FixedMovingMaxTest extends SpecificationWithJUnit {

  private type Factory = (Clock, FiniteDuration, FiniteDuration) => FixedMovingMax

  "FixedMovingMax" >> {
    Fragments.foreach(Seq[Factory](
      new ThreadUnsafeFixedMovingMax(_, _, _),
      new ThreadUnsafeInlineFixedMovingMax(_, _, _),
      new ArrayOfAtomicFixedMovingMax(_, _, _),
      new AtomicArrayFixedMovingMax(_, _, _),
    )) { implicit factory: Factory =>
      val name = factory(new FakeClock, 2.seconds, 1.second).getClass.getSimpleName
      s"returns zero [$name]" in new Context {
        mm.getMaxInWindow mustEqual 0L
      }

      s"update current moment [$name]" in new Context {
        mm.update(1L)
        mm.getMaxInWindow mustEqual 1L
        mm.getMeasurementsForTests mustEqual fullArray(1, 0, 0, 0, 0, 0)

        mm.update(2L)
        mm.getMaxInWindow mustEqual 2L
        mm.getMeasurementsForTests mustEqual fullArray(2, 0, 0, 0, 0, 0)

        mm.update(3L)
        mm.getMaxInWindow mustEqual 3L
        mm.getMeasurementsForTests mustEqual fullArray(3, 0, 0, 0, 0, 0)
      }

      s"update 3 seconds [$name]" in new Context {
        moveTimeAndUpdateAndGet(3L)
        mm.getMeasurementsForTests mustEqual fullArray(0, 3, 0, 0, 0, 0)
        moveTimeAndUpdateAndGet(2L)
        mm.getMeasurementsForTests mustEqual fullArray(0, 3, 2, 0, 0, 0)
        moveTimeAndUpdateAndGet(1L)
        mm.getMeasurementsForTests mustEqual fullArray(0, 3, 2, 1, 0, 0)

        mm.getMaxInWindow mustEqual 3L
      }

      s"update over [$name]" in new Context {
        moveTimeAndUpdateAndGet(7L) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(0, 7, 0, 0, 0, 0)

        moveTimeAndUpdateAndGet(6L) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(0, 7, 6, 0, 0, 0)

        moveTimeAndUpdateAndGet(5L) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(0, 7, 6, 5, 0, 0)

        moveTimeAndUpdateAndGet(4L) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(0, 7, 6, 5, 4, 0)

        moveTimeAndUpdateAndGet(3L) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(0, 7, 6, 5, 4, 3)

        moveTimeAndUpdateAndGet(2L) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(2, 7, 6, 5, 4, 3)

        moveTimeAndUpdateAndGet(1L) mustEqual 6L
        mm.getMeasurementsForTests mustEqual fullArray(2, 1, 6, 5, 4, 3)

        moveTimeAndUpdateAndGet(0L) mustEqual 5L
        mm.getMeasurementsForTests mustEqual fullArray(2, 1, 0, 5, 4, 3)

        moveTimeAndUpdateAndGet(1L) mustEqual 4L
        mm.getMeasurementsForTests mustEqual fullArray(2, 1, 0, 1, 4, 3)
      }

      s"update after more than 1 [$name]" in new Context {
        moveTimeAndUpdateAndGet(5L) mustEqual 5L
        mm.getMeasurementsForTests mustEqual fullArray(0, 5, 0, 0, 0, 0)

        moveTimeAndUpdateAndGet(7L, increment = 3) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(0, 5, 0, 0, 7, 0)

        moveTimeAndUpdateAndGet(4L) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(0, 5, 0, 0, 7, 4)

        moveTimeAndUpdateAndGet(3L, increment = 2) mustEqual 7L
        mm.getMeasurementsForTests mustEqual fullArray(0, 3, 0, 0, 7, 4)

        moveTimeAndUpdateAndGet(10L, increment = 4) mustEqual 10L
        mm.getMeasurementsForTests mustEqual fullArray(0, 3, 0, 0, 0, 10)
      }

      s"discard old [$name]" in new Context {
        mm.update(5L)
        mm.getMaxInWindow mustEqual 5L

        moveTime(6)

        mm.getMaxInWindow mustEqual 0L
      }
    }
  }

  private abstract class Context(implicit factory: Factory) extends Scope {
    val clock = new FakeClock
    val resolution = 10.seconds
    val mm = factory(clock, 1.minute, resolution)

    def moveTime(units: Int): Unit = {
      clock.moveForward(units * resolution)
    }

    def moveTimeAndUpdateAndGet(value: Long, increment: Int = 1) = {
      moveTime(increment)
      mm.update(value)
      mm.getMaxInWindow
    }
  }

  private def fullArray(v: Long*): Array[Long] = {
    val array = new Array[Long](v.size)
    v.indices.foreach(i => array.update(i, v(i)))
    array
  }
}
