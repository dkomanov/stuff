package com.komanov.metrics

import com.codahale.metrics.Clock

import scala.concurrent.duration.FiniteDuration

class FakeClock extends Clock {
  private var tick = 0L

  override def getTick: Long = tick

  def moveForward(nanos: Long): Unit = {
    tick += nanos
  }

  def moveForward(v: FiniteDuration): Unit = {
    tick += v.toNanos
  }

  def setValue(nanos: Long): Unit = {
    tick = nanos
  }
}
