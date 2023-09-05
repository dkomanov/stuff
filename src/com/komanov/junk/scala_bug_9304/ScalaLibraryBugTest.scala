package com.komanov.junk.scala_bug_9304

import java.util.concurrent.Executors

import org.specs2.matcher.MatchResult
import org.specs2.mutable.SpecificationWithJUnit

import scala.concurrent.{ExecutionContext, Future, Promise, blocking}
import scala.util.{Success, Try}

class ScalaLibraryBugTest extends SpecificationWithJUnit {

  private val directExecutionContext = ExecutionContext.fromExecutor(_.run())

  "Future" >> {
    "must be resolved (not using blocking inside)" >> {
      doTest(useBlocking = false)(directExecutionContext)
    }

    "must be resolved (using blocking inside)" >> {
      doTest(useBlocking = true)(directExecutionContext)
    }//.pendingUntilFixed("Issue: https://github.com/scala/bug/issues/9304") // It works in 2.13

    "must be resolved (using blocking inside) when using regular ExecutionContext" >> {
      val ec = ExecutionContext.fromExecutorService(Executors.newSingleThreadExecutor())
      try {
        doTest(useBlocking = true)(ec)
      } finally {
        ec.shutdownNow()
      }
    }
  }

  private def doTest(useBlocking: Boolean)(implicit ec: ExecutionContext): MatchResult[Any] = {
    val p0 = Promise[Int]()
    val p = Promise[Int]()

    val p1 = Promise[Int]()

    val f = p0.future
      .flatMap { _ =>
        Future.sequence(Seq(
          p.future
            .flatMap { _ =>
              val f = p0.future.flatMap { _ =>
                Future.successful(1)
              }
              // At this point scala.concurrent.Future.InternalCallbackExecutor has 1 runnable in _tasksLocal
              // (flatMap from the previous line)
              if (useBlocking) {
                // Await result sets _tasksLocal to Nil (instead of null). Next it calls Batch.run, which checks
                // that _tasksLocal must be null, throws exception and all tasks are lost.
                Try(blocking {
                  1
                })
              }
              f
            },
        ))
      }

    p.completeWith(p1.future.map(_ + 1))
    p0.complete(Success(0))
    p1.complete(Success(1))

    eventually {
      p.future.value must beSome(Success(2))
    }

    eventually {
      f.isCompleted must beTrue
    }
  }
}
