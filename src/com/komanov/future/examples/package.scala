package com.komanov.future.examples

import com.komanov.future._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future}

package object examples {

  class PermissionDeniedException(message: String, cause: Throwable = null) extends RuntimeException(message, cause)

  object syncApp {
    case class Request(id: String)
    class PayloadFromDb
    class Payload
    case class Response(payload: Payload)

    def isPermittedViaRpc: Boolean = ???
    def retrieveFromDatabase(id: String): PayloadFromDb = ???
    def convertPayloadFromDb(payload: PayloadFromDb): Payload = ???

    def validateRequest(request: Request): Unit = {
      require(request != null)
    }

    def regularRpcEndpoint(request: Request): Response = {
      validateRequest(request)

      if (!isPermittedViaRpc) {
        throw new PermissionDeniedException("...")
      }

      val fromDb = retrieveFromDatabase(request.id)
      val payload = convertPayloadFromDb(fromDb)
      Response(payload)
    }
  }

  object asyncApp {
    case class Request(id: String)
    class PayloadFromDb
    class Payload
    case class Response(payload: Payload)

    def isPermittedViaRpc: Future[Boolean] = ???
    def retrieveFromDatabase(id: String): Future[PayloadFromDb] = ???
    def convertPayloadFromDb(payload: PayloadFromDb): Payload = ???

    def validateRequest(request: Request): Future[Unit] = {
      if (request == null) Future.failed(new IllegalArgumentException) else Future.unit
    }

    def regularRpcEndpoint(request: Request): Future[Response] = {
      validateRequest(request)
        .flatMap { _ =>
          isPermittedViaRpc.flatMap { isPermitted =>
            if (!isPermitted) {
              Future.failed(new PermissionDeniedException("..."))
            } else {
              retrieveFromDatabase(request.id)
                .map(convertPayloadFromDb)
                .map(Response)
            }
          }
        }
    }
  }

  object ifStatement {

    object sync {
      def condition: Boolean = ???

      def action1(): Unit = ???
      def action2(): Unit = ???

      if (condition) {
        action1()
      } else {
        action2()
      }
    }

    object async {
      def condition: Future[Boolean] = ???

      def action1(): Future[Unit] = ???
      def action2(): Future[Unit] = ???

      condition.flatMap(
        if (_)
          action1()
        else
          action2()
      )
    }

  }

  object forComprehensions {
    def hasPermissions: Future[Boolean] = Future.failed(new Exception)
    def getMovie: Future[Movie] = Future.failed(new Exception)
    def getActors(movie: Movie): Future[Seq[Actor]] = Future.failed(new Exception)
    def getPlot(movie: Movie): Future[MoviePlot] = Future.failed(new Exception)
    def findMovie(title: String): Future[Option[Movie]] = Future.failed(new Exception)

    for {
      movie <- getMovie
      actors <- getActors(movie)
      plot <- getPlot(movie)
    } yield MovieDescription(movie, actors, plot)

    for {
      isPermitted <- hasPermissions
      if isPermitted
      movie <- getMovie
      if !movie.hidden
      actors <- getActors(movie)
      plot <- getPlot(movie)
    } yield MovieDescription(movie, actors, plot)

    for {
      _ <- hasPermissions orFail new PermissionDeniedException("...")
      movie <- getMovie
      if !movie.hidden
      actors <- getActors(movie)
      plot <- getPlot(movie)
    } yield MovieDescription(movie, actors, plot)

    for {
      _ <- hasPermissions orFail new PermissionDeniedException("...")
      movie <- getMovie.filter(!_.hidden).recoverWith {
        case _: NoSuchElementException => Future.failed(new HiddenMovieException("..."))
      }
      actors <- getActors(movie)
      plot <- getPlot(movie)
    } yield MovieDescription(movie, actors, plot)

    for {
      _ <- hasPermissions orFail new PermissionDeniedException("...")
      movie <- getMovie.filterOrFail(!_.hidden, new HiddenMovieException("..."))
      actors <- getActors(movie)
      plot <- getPlot(movie)
    } yield MovieDescription(movie, actors, plot)

    for {
      _ <- hasPermissions orFail new PermissionDeniedException("...")
      movie <- findMovie("Dark Waters").orFail(new MovieNotFoundException("..."))
    } yield movie

    case class Movie(name: String, hidden: Boolean)
    case class Actor(name: String)
    case class MoviePlot(plot: String)
    case class MovieDescription(movie: Movie, actors: Seq[Actor], plot: MoviePlot)
    class PermissionDeniedException(message: String, cause: Throwable = null) extends RuntimeException(message, cause)
    class MovieNotFoundException(message: String, cause: Throwable = null) extends RuntimeException(message, cause)
    class HiddenMovieException(message: String, cause: Throwable = null) extends RuntimeException(message, cause)
  }

  object exceptionHandling {
    def itsSafeInFutureExecutionContext(): Any = {
      Future(42)
        .map[Int](_ => throw new IllegalArgumentException)
        .flatMap[Int](_ => throw new IllegalStateException)
      // Effectively: Future.failed(new IllegalArgumentException)
    }

    def innocentFunction(param: AnyRef): Future[Int] = {
      require(param != null)
      Future.successful(42)
    }

    innocentFunction(null)
      .map(_ => throw new IllegalStateException)
    // an IllegalArgumentException will be thrown before map call

    def rpcFunction(param: AnyRef)(implicit ec: ExecutionContext): Future[AnyRef] = ???

    case class HttpRequest(uri: String)

    def handleFromMemcached(r: HttpRequest): Future[Option[Int]] = {
      require(r.uri != "/memcached-boom", "Memcached Boom!")
      Future.successful(if (r.uri == "/memcached") Some(42) else None)
    }

    def handleFromCdn(r: HttpRequest): Future[Option[Int]] = {
      require(r.uri != "/cnd-boom", "CDN Boom!")
      Future.successful(if (r.uri == "/cdn") Some(42) else None)
    }

    def handleFromHadoop(r: HttpRequest): Future[Int] =
      Future.successful(42)

    def handle(r: HttpRequest): Future[Int] = {
      // suppose here we're inside Future's execution lifecycle, so it's safe to throw exceptions.
      handleFromMemcached(r)
        .flatMap { memcachedResult =>
          memcachedResult.fold {
            handleFromCdn(r).flatMap { cdnResult =>
              cdnResult.fold {
                handleFromHadoop(r)
              }(Future.successful)
            }
          }(Future.successful)
        }
    }

    handle(HttpRequest("/memcached-boom")) // will throw new IllegalArgumentException("Memcached Boom!")

    val handlers: List[HttpRequest => Future[Option[Int]]] = List(
      handleFromMemcached,
      handleFromCdn,
      r => handleFromHadoop(r).map(Some.apply)
    )

    def handleNoNesting(r: HttpRequest): Future[Int] = {
      executeLazily(handlers.map(handler => () => handler(r)))
        .map(_.getOrElse(throw new IllegalStateException("Hadoop should always return Some!")))
    }

    object trivial {
      def rpcCall: Future[Option[String]] = ???
      def reportException(e: Throwable): Unit = e.printStackTrace()

      def getUrlSafe: Future[Option[String]] = {
        rpcCall.recover {
          case e: Throwable =>
            reportException(e)
            None
        }
      }
    }
  }
}
