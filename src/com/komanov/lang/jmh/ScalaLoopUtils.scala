package com.komanov.lang.jmh

import org.openjdk.jmh.infra.Blackhole

object ScalaLoopUtils {
  def scalaForwardLoop(iterations: Int, b: Blackhole): Long = {
    var r = 0L
    var i = 0
    while (i < iterations) {
      r += i
      b.consume(r)
      i += 1
    }
    r
  }

  def scalaBackwardLoop(iterations: Int, b: Blackhole): Long = {
    var r = 0L
    var i = iterations - 1
    while (i >= 0) {
      r += i
      b.consume(r)
      i -= 1
    }
    r
  }

  def scalaRangeLoop(iterations: Int, b: Blackhole): Long = {
    var r = 0L
    for (i <- 0 until iterations) {
      r += i
      b.consume(r)
    }
    r
  }
}
