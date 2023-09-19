package com.komanov.metrics.tests

import com.komanov.metrics.FixedMovingMaxUtils._
import org.specs2.mutable.SpecificationWithJUnit

import scala.concurrent.duration._

class FixedMovingMaxUtilsTest extends SpecificationWithJUnit {

  "DiscardedIndices" >> {
    def verify(length: Int, cell: Int, toDiscardCount: Int, discarded: Int*) = {
      val discardedIndices = Seq.newBuilder[Int]
      discardBuckets(cell, toDiscardCount, length, i => discardedIndices += i)

      discardedIndices.result() mustEqual discarded.toSeq

      val foundIndices = Seq.newBuilder[Int]
      findMaxInBuckets(cell, toDiscardCount, length, i => {
        foundIndices += i
        666L
      }) must ===(0L).or(===(666L))

      discardedIndices.result().toSet.intersect(foundIndices.result().toSet) must beEmpty
      (discardedIndices.result().toSet ++ foundIndices.result().toSet).toSeq.sorted mustEqual (0 until length)
    }

    // All
    verify(
      6, 0, 7,
      0, 1, 2, 3, 4, 5,
    )
    // Nothing
    verify(
      6, 0, 0,
    )

    verify(
      6, 0, 1,
      0,
    )
    verify(
      6, 1, 1,
      1,
    )
    verify(
      6, 5, 1,
      5,
    )
    verify(
      6, 5, 2,
      4, 5,
    )
    verify(
      6, 1, 3,
      0, 1, 5,
    )
  }


  "getCellIndexByTick" >> {
    def cell(tick: Int, window: FiniteDuration, resolution: FiniteDuration) = {
      val buckets = validateAndGetBuckets(window.toNanos, resolution.toNanos)
      getCellIndexByTick(tick * resolution.toNanos, resolution.toNanos, buckets)
    }

    cell(0, 1.minute, 1.second) mustEqual 0
    cell(1, 1.minute, 1.second) mustEqual 1
    cell(59, 1.minute, 1.second) mustEqual 59
    cell(60, 1.minute, 1.second) mustEqual 0
    cell(61, 1.minute, 1.second) mustEqual 1

    cell(0, 1.minute, 10.seconds) mustEqual 0
    cell(5, 1.minute, 10.seconds) mustEqual 5
    cell(6, 1.minute, 10.seconds) mustEqual 0
  }

  "getDiscardCount" >> {
    def discardCount(now: Int, tick: Int) = {
      val resolution = 1.second
      getDiscardCount(now * resolution.toNanos, tick * resolution.toNanos, resolution.toNanos)
    }

    discardCount(0, 0) mustEqual 0
    discardCount(1, 0) mustEqual 1
    discardCount(2, 0) mustEqual 2
    discardCount(60, 0) mustEqual 60
    discardCount(10, 5) mustEqual 5
  }

  "validateAndGetBuckets" >> {
    def cellCount(window: FiniteDuration, resolution: FiniteDuration) =
      validateAndGetBuckets(window.toNanos, resolution.toNanos)

    cellCount(1.minute, 1.second) mustEqual 60
    cellCount(1.minute, 10.seconds) mustEqual 6
    cellCount(1.hour, 1.second) mustEqual 3600
    cellCount(1.hour, 1.minute) mustEqual 60
    cellCount(1.hour, 10.minutes) mustEqual 6
    cellCount(1.hour, 15.minutes) mustEqual 4
  }

}
