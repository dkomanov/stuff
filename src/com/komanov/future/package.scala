package com.komanov

import com.komanov.future.SupportTypes.BooleanOrFutureOfBoolean._
import com.komanov.future.SupportTypes._

import scala.annotation.implicitNotFound
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import scala.language.implicitConversions
import scala.util.Try
import scala.util.control.NonFatal

package object future {

  object ControlException extends Throwable("control", null, false, false) {
    def unapply(e: Throwable): Boolean = e.isInstanceOf[ControlException.type]
  }

  val directExecutionContext: ExecutionContextExecutor =
    ExecutionContext.fromExecutor((runnable: Runnable) => runnable.run())

  object Implicits {
    implicit def direct: ExecutionContext = directExecutionContext
  }

  // All utility functions here shouldn't require rescheduling of tasks, so it will be executed immediately.

  import Implicits.direct

  val unitF: Future[Unit] = Future.unit

  val noneF: Future[Option[Nothing]] = Future.successful(None)
  def someF[T](f: => T): Future[Option[T]] = wrap(f).map(Some.apply)

  val trueF: Future[Boolean] = Future.successful(true)
  val falseF: Future[Boolean] = Future.successful(false)

  implicit class OptionFutureExtensions[T](val v: Option[T]) extends AnyVal {
    /** Folds Option to Future. */
    def orFail(e: => Throwable): Future[T] =
      v.fold[Future[T]](safeFuture(Future.failed(e)))(value => Future.successful(value))

    /** Maps Option to Future of Option. */
    def mapF[U](f: T => Future[U]): Future[Option[U]] =
      v.map(value => safeFuture(f(value)).map(Some(_))).getOrElse(noneF)

    /** Maps Option to Future of Option. Analog of orElse. */
    def flatMapF[U](f: T => Future[Option[U]]): Future[Option[U]] =
      v.map(value => safeFuture(f(value))).getOrElse(noneF)
  }

  implicit class FutureOfOptionExtensions[T](val v: Future[Option[T]]) extends AnyVal {
    /** Folds Option with ControlException. */
    def orFilterOut: Future[T] = orFail(ControlException)

    /** Folds Option with specified exception. Analog of <code>getOrElse(throw Exception)</code> */
    def orFail(e: => Throwable): Future[T] = v.flatMap(_ orFail e)

    def orElse(elseCase: => Future[Option[T]]): Future[Option[T]] =
      v.flatMap(_.fold(elseCase)(ifCase => Future.successful(Some(ifCase))))

    def getOrElse(default: => Future[T]): Future[T] =
      v.flatMap(_.fold(default)(value => Future.successful(value)))

    def flatMapO[U](f: T => Future[Option[U]]): Future[Option[U]] =
      v.flatMap(_.map(f).getOrElse(noneF))
  }

  implicit class BooleanFutureExtensions(val v: Boolean) extends AnyVal {
    def &&(other: => Future[Boolean]): Future[Boolean] =
      if (v) safeFuture(other) else falseF

    def ||(other: => Future[Boolean]): Future[Boolean] =
      if (v) trueF else safeFuture(other)
  }

  implicit class FutureOfBooleanExtensions(val v: Future[Boolean]) extends AnyVal {
    /**
    EXAMPLE:
      <pre>
      (for {
        _ <- Future.successful(false).orFilterOut
      } yield something)
        .recoverFilter(somethingElse)
      </pre>

    Simplification of:
      <pre>
      (for {
        value <- Future.successful(false)
        if value
      } yield something)
        .recoverFilter(somethingElse)
      </pre>
      */
    def orFilterOut: Future[Unit] = orFail(ControlException)

    def orFail(e: => Throwable): Future[Unit] =
      v.flatMap(v => if (v) unitF else Future.failed(e))

    def &&(other: => BooleanOrFutureOfBoolean): Future[Boolean] =
      v.flatMap { value =>
        if (!value)
          falseF
        else
          other match {
            case Bool(otherValue) => Future.successful(otherValue)
            case Fut(future) => future
          }
      }

    def ||(other: => BooleanOrFutureOfBoolean): Future[Boolean] =
      v.flatMap { value =>
        if (value)
          trueF
        else
          other match {
            case Bool(otherValue) => Future.successful(otherValue)
            case Fut(future) => future
          }
      }

    def not: Future[Boolean] = v.map(!_)
  }

  implicit class FutureExtensions[T](val future: Future[T]) extends AnyVal {
    /** recover for NoSuchElementException and ControlException. */
    def recoverFilter(f: => T): Future[T] =
      future.recover {
        case _: NoSuchElementException => f
        case ControlException => f
      }

    /** recoverWith for NoSuchElementException and ControlException. */
    def recoverFilterWith(f: => Future[T]): Future[T] =
      future.recoverWith {
        case _: NoSuchElementException => f
        case ControlException => f
      }

    def filterOrFail(f: T => Boolean, e: => Throwable): Future[T] =
      future.flatMap(value => if (f(value)) Future.successful(value) else Future.failed(e))
  }

  /**
    * Wraps code block into Try and then into Future.
    */
  def wrap[T](f: => T): Future[T] = Future.fromTry(Try(f))

  /**
    * Wraps code block that returns Future with try..catch. If an exception is thrown, a failed Future
    * will be created. For example:
    * <pre>
    * def anotherMethodReturningFuture: Future[Unit
    * def someMethod(throwInTheMiddle: Boolean): Future[Unit] = safeFuture {
    * // some code
    * if (throwInTheMiddle) {
    * throw new IllegalArgumentException
    * }
    * // some code
    * for {
    * _ <- anotherMethodReturningFuture
    * } yield (): Unit
    * }
    * </pre>
    *
    * In this case `someMethod` will return Future.failed(new IllegalArgumentException) instead of throwing,
    * which may lead to unhandled exception, which is undesired behavior.
    */
  def safeFuture[T](f: => Future[T]): Future[T] = {
    try {
      f
    } catch {
      case NonFatal(e) => Future.failed(e)
    }
  }

  /**
    * Analog of <code>getOrElse(throw ControlException)</code> for Future of Option.
    */
  def someOrFilter[T](f: => Option[T]): Future[T] =
    wrap(f).orFail(ControlException)

  /**
    * Analog of <code>if (condition) (): Unit else throw new Exception</code>.
    */
  def filterOrFail(f: => Boolean)(failure: => Throwable): Future[Unit] = safeFuture {
    if (f)
      Future.successful((): Unit)
    else
      Future.failed(failure)
  }

  def withRetries[T](n: Int)(f: => Future[T]): Future[T] = {
    var future = f
    var exception: Throwable = null
    for (i <- 0 to n) {
      future = future.recoverWith {
        case e =>
          if (exception == null) {
            exception = e
          } else {
            exception.addSuppressed(e)
          }

          if (i == n)
            Future.failed(exception)
          else
            f
      }
    }
    future
  }

  object SupportTypes {
    @implicitNotFound("This method supports only arguments of type Boolean OR Future[Boolean]")
    sealed trait BooleanOrFutureOfBoolean

    object BooleanOrFutureOfBoolean {
      case class Bool(value: Boolean) extends BooleanOrFutureOfBoolean
      case class Fut(future: Future[Boolean]) extends BooleanOrFutureOfBoolean

      implicit def `Boolean to BooleanOrFutureOfBoolean`(v: Boolean): BooleanOrFutureOfBoolean = Bool(v)
      implicit def `Future[Boolean] to BooleanOrFutureOfBoolean`(v: Future[Boolean]): BooleanOrFutureOfBoolean = Fut(v)
    }
  }
}
