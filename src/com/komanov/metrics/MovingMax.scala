package com.komanov.metrics

trait MovingMax {
  def update(value: Long): Unit

  def getMaxInWindow: Long
}

trait FixedMovingMax extends MovingMax {
  def getMeasurementsForTests: Array[Long]
}
