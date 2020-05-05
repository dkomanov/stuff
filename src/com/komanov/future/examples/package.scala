package com.komanov.future.examples;

import com.komanov.future._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

package object examples {

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
}
