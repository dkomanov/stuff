package com.komanov

import scala.language.implicitConversions
import scala.util.Try

package object business {

  implicit final class ModelOrResultFromOption[M](private val opt: Option[M])
    extends AnyVal {
    // converts Option to Either.RightProjection
    def orResult[R](result: => R) =
      opt.fold[Either[R, M]](Left(result))(Right(_)).right
  }

  implicit final class ModelOrResultFromTry[M](private val opt: Try[M])
    extends AnyVal {
    // converts Try to Either.RightProjection
    def orResult[R](result: => R) =
      (if (opt.isFailure) Left(result) else Right(opt.get)).right
  }

  implicit def toResult[R](e: Either[R, R]): R =
    e.fold(identity, identity)

}
