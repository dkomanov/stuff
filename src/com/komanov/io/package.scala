package com.komanov

import scala.util.control.NonFatal

package object io {

  def withResources[T <: AutoCloseable, V](r: => T)(f: T => V): V = {
    var resource: T = null.asInstanceOf[T]
    var exception: Throwable = null
    try {
      resource = r
      require(resource != null, "resource is null")
      f(resource)
    } catch {
      case NonFatal(e) =>
        exception = e
        throw e
    } finally {
      closeAndAddSuppressed(exception, resource)
    }
  }

  private def closeAndAddSuppressed(e: Throwable, resource: AutoCloseable): Unit = {
    if (resource != null) {
      if (e != null) {
        try {
          resource.close()
        } catch {
          case NonFatal(suppressed) =>
            e.addSuppressed(suppressed)
        }
      } else {
        resource.close()
      }
    }
  }

}
