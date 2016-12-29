package com.komanov.serialization.converters

object IoUtils {

  def using[T <: AutoCloseable, K](stream: => T)(f: T => K): K = {
    var s = null.asInstanceOf[T]
    try {
      s = stream
      f(s)
    } finally {
      if (s != null) {
        s.close()
      }
    }
  }

}
