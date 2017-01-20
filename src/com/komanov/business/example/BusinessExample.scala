package com.komanov.business.example

import java.util.UUID

import com.komanov.business._

import scala.util.Try

class BusinessExample {

  def processRequestOld(userId: UUID, requestId: UUID): BusinessResult = {
    val userOpt = getUser(userId)
    if (userOpt.isEmpty) {
      return BusinessResult.UserNotFound
    }

    val requestOpt = getRequestById(requestId)
    if (requestOpt.isEmpty) {
      return BusinessResult.RequestNotFound
    }

    if (checkAccess(requestOpt.get, userOpt.get).isFailure) {
      return BusinessResult.NotOwner
    }

    BusinessResult.Ok
  }

  def processRequestWithException(userId: UUID, requestId: UUID): BusinessResult = {
    case class BusinessException(result: BusinessResult) extends RuntimeException

    try {
      val user = getUser(userId).getOrElse(throw new BusinessException(BusinessResult.UserNotFound))
      val request = getRequestById(requestId).getOrElse(throw new BusinessException(BusinessResult.RequestNotFound))
      checkAccess(request, user).toOption.getOrElse(throw new BusinessException(BusinessResult.NotOwner))
      BusinessResult.Ok
    } catch {
      case be: BusinessException => be.result
    }
  }

  def processRequestCollections(userId: UUID, requestId: UUID): BusinessResult = {
    getUser(userId).fold(BusinessResult.UserNotFound)(user => {
      getRequestById(requestId).fold(BusinessResult.RequestNotFound)(request => {
        checkAccess(request, user).toOption.fold(BusinessResult.NotOwner)(_ => BusinessResult.Ok)
      })
    })
  }

  def processRequestEither(userId: UUID, requestId: UUID): BusinessResult = {
    val result: Either[BusinessResult, BusinessResult] = for {
      user <- either(getUser(userId), BusinessResult.UserNotFound)
      request <- either(getRequestById(requestId), BusinessResult.RequestNotFound)
      _ <- either(checkAccess(request, user).toOption, BusinessResult.NotOwner)
    } yield BusinessResult.Ok

    result.fold(identity, identity)
  }

  private def either[T](opt: Option[T], result: BusinessResult) =
    opt.fold[Either[BusinessResult, T]](Left(result))(Right(_)).right

  def processRequestNew(userId: UUID, requestId: UUID): BusinessResult = {
    for {
      user <- getUser(userId) orResult BusinessResult.UserNotFound
      request <- getRequestById(requestId) orResult BusinessResult.RequestNotFound
      _ <- checkAccess(request, user) orResult BusinessResult.NotOwner
    } yield BusinessResult.Ok
  }

  private def getUser(userId: UUID): Option[String] = None

  private def getRequestById(requestId: UUID): Option[Request] = None

  private def checkAccess(request: Request, user: String): Try[Unit] = Try(throw new RuntimeException)

}

case class Request(id: UUID,
                   owner: UUID)
