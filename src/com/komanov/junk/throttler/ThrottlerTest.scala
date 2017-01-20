package com.komanov.junk.throttler

import java.util.concurrent.{CountDownLatch, Executors, Semaphore, TimeUnit}

import org.specs2.mutable.{After, SpecificationWithJUnit}

import scala.concurrent.{ExecutionContext, Future}

class ThrottlerTest extends SpecificationWithJUnit {

  "Throttler" should {
    "execute sequential" in new ctx {
      var invocationCount = 0
      for (i <- 0 to maxCount) {
        throttler {
          invocationCount += 1
        }
      }
      invocationCount must be_==(maxCount + 1)
    }

    "throw exception once reached the limit [naive, flaky]" in new ctx {
      for (i <- 1 to maxCount) {
        Future {
          throttler(waitForever())
        }
      }

      throttler {} must throwA[ThrottledException]
    }

    "throw exception once reached the limit [naive, bad]" in new ctx {
      for (i <- 1 to maxCount) {
        Future {
          throttler(waitForever())
        }
      }

      Thread.sleep(1000)

      throttler {} must throwA[ThrottledException]
    }

    "throw exception once reached the limit [working]" in new ctx {
      val barrier = new CountDownLatch(maxCount)

      for (i <- 1 to maxCount) {
        Future {
          throttler {
            barrier.countDown()
            waitForever()
          }
        }
      }

      barrier.await(5, TimeUnit.SECONDS) must beTrue

      throttler {} must throwA[ThrottledException]
    }
  }

  trait ctx extends After {
    val maxCount = 3
    val throttler = new Throttler(maxCount)

    val e = Executors.newCachedThreadPool()
    implicit val ec: ExecutionContext = ExecutionContext.fromExecutor(e)

    private val waitForeverLatch = new CountDownLatch(1)

    override def after: Any = {
      waitForeverLatch.countDown()
      e.shutdownNow()
    }

    def waitForever(): Unit = try {
      waitForeverLatch.await()
    } catch {
      case _: InterruptedException =>
      case ex: Throwable => throw ex
    }
  }
}

class ThrottledException extends RuntimeException("Throttled!")

class Throttler(count: Int) {
  require(count > 0)

  private val semaphore = new Semaphore(count)

  def apply(f: => Unit): Unit = {
    if (!semaphore.tryAcquire()) {
      throw new ThrottledException
    }

    try {
      f
    } finally {
      semaphore.release()
    }
  }
}
