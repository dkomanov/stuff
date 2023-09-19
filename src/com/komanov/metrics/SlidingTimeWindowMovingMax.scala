package com.komanov.metrics

import com.codahale.metrics.{Clock, SlidingTimeWindowReservoir}

import scala.concurrent.duration.FiniteDuration

class SlidingTimeWindowMovingMax(clock: Clock, window: FiniteDuration) extends MovingMax {
  private val slidingWindow = new SlidingTimeWindowReservoir(window.length, window.unit, clock)

  override def update(value: Long): Unit = {
    slidingWindow.update(value)
  }

  override def getMaxInWindow: Long = {
    slidingWindow.getSnapshot.getMax
  }
}
