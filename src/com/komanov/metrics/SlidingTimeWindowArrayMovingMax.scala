package com.komanov.metrics

import com.codahale.metrics.{Clock, SlidingTimeWindowArrayReservoir}

import scala.concurrent.duration.FiniteDuration

class SlidingTimeWindowArrayMovingMax(clock: Clock, window: FiniteDuration) extends MovingMax {
  private val slidingWindow = new SlidingTimeWindowArrayReservoir(window.length, window.unit, clock)

  override def update(value: Long): Unit = {
    slidingWindow.update(value)
  }

  override def getMaxInWindow: Long = {
    slidingWindow.getSnapshot.getMax
  }
}
