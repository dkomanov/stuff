package com.komanov.metrics.tests

import com.codahale.metrics.Clock
import com.komanov.metrics._
import org.specs2.mutable.SpecificationWithJUnit
import org.specs2.specification.core.Fragments

import scala.concurrent.duration._

class MovingMaxTest extends SpecificationWithJUnit {

  sequential

  type Factory = (Clock, FiniteDuration) => MovingMax

  "MovingMax" >> {
    Fragments.foreach(Seq[Factory](
      (clock, window) => new SlidingTimeWindowMovingMax(clock, window),
      (clock, window) => new SlidingTimeWindowArrayMovingMax(clock, window),
      (clock, window) => new ThreadUnsafeFixedMovingMax(clock, window, 1.second),
      (clock, window) => new ThreadUnsafeInlineFixedMovingMax(clock, window, 1.second),
      (clock, window) => new ArrayOfAtomicFixedMovingMax(clock, window, 1.second),
      (clock, window) => new AtomicArrayFixedMovingMax(clock, window, 1.second),
    )) { factory: Factory =>
      val name = factory(new FakeClock, 1.minute).getClass.getSimpleName

      s"should return 0 without updates [$name]" >> {
        val mm = factory(new FakeClock, 1.minute)
        mm.getMaxInWindow mustEqual 0L
      }

      s"should return 0 with a single zero update [$name]" >> {
        val mm = factory(new FakeClock, 1.minute)
        mm.update(0L)
        mm.getMaxInWindow mustEqual 0L
      }

      s"should return max from the beginning of the period [$name]" >> {
        val clock = new FakeClock
        val mm = factory(clock, 1.minute)

        clock.moveForward(20.seconds)
        mm.getMaxInWindow mustEqual 0L

        mm.update(3L)
        mm.getMaxInWindow mustEqual 3L
        clock.moveForward(20.seconds)
        mm.getMaxInWindow mustEqual 3L

        mm.update(2L)
        mm.getMaxInWindow mustEqual 3L
        clock.moveForward(20.seconds)
        mm.getMaxInWindow mustEqual 3L

        mm.update(1L)
        mm.getMaxInWindow mustEqual 3L
        clock.moveForward(15.seconds)
        mm.getMaxInWindow mustEqual 3L
      }
    }
  }

}
