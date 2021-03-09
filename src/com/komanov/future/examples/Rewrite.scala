package com.komanov.future.examples

import com.komanov.future.examples.Rewrite.asyncFramework.RequestContext

import scala.concurrent.Future

object Rewrite {

  object blockingApp {
    // RPC
    trait MovieApi {
      def rate(id: Long, rating: Int): Unit
      def get(id: Long): Movie
    }
    case class Movie(id: Long, title: String, year: Int, rating: Double)

    // Database
    trait MovieDao {
      def rate(id: Long, rating: Int): Unit
      def find(id: Long): MovieDto
    }
    case class MovieDto(id: Long, title: String, year: Int, ratingSum: Long, ratedCount: Long)

    trait PermissionChecker {
      def isPermitted: Boolean
    }

    trait PermissionService {
      def isPermitted(user: Long): Boolean
    }

    class PermissionCheckerImpl(remote: PermissionService) extends PermissionChecker {
      override def isPermitted: Boolean = {
        val user = 1 // extracted from ThreadLocal, for example.
        remote.isPermitted(user)
      }
    }

    class MovieDaoImpl extends MovieDao {
      override def rate(id: Long, rating: Int): Unit = {
        // update query
      }
      override def find(id: Long): MovieDto = {
        // select query
        MovieDto(id, "Warrior Nun", 2020, 7, 1)
      }
    }

    class MovieApiImpl(dao: MovieDao, permissionChecker: PermissionChecker) extends MovieApi {
      override def rate(id: Long, rating: Int): Unit = {
        if (!permissionChecker.isPermitted) {
          throw new IllegalStateException("not permitted")
        }
        dao.rate(id, rating)
      }

      override def get(id: Long): Movie = {
        if (!permissionChecker.isPermitted) {
          throw new IllegalStateException("not permitted")
        }
        val dto = dao.find(id)
        Movie(dto.id, dto.title, dto.year, if (dto.ratedCount == 0) 0 else dto.ratingSum.toDouble / dto.ratedCount)
      }
    }
  }

  object asyncFramework {
    case class RequestContext(user: Long, data: Map[AnyRef, Any])
  }

  object asyncApp {
    import blockingApp.{Movie, MovieDto}

    trait MovieApi {
      def rate(id: Long, rating: Int)(implicit rc: RequestContext): Future[Unit]
      def get(id: Long)(implicit rc: RequestContext): Future[Movie]
    }
    trait PermissionChecker {
      def isPermitted(implicit rc: RequestContext): Future[Boolean]
    }

    trait PermissionService {
      def isPermitted(user: Long): Future[Boolean]
    }

    trait MovieDao {
      def rate(id: Long, rating: Int): Future[Unit]
      def find(id: Long): Future[MovieDto]
    }
  }

}
