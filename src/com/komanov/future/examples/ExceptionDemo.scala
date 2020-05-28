package com.komanov.future.examples

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

object ExceptionDemo extends App {

  def stackTrace(implicit ec: ExecutionContext): Any = {
    Await.result(
      Future.successful(1)
        .map(v => v + 1)
        .flatMap(v => Future.successful(v))
        .map { v =>
          new IllegalStateException().printStackTrace()
          v
        },
      10.seconds
    )
  }

  stackTrace(scala.concurrent.ExecutionContext.Implicits.global)
  stackTrace(com.komanov.future.directExecutionContext)

  /*
  global:

java.lang.IllegalStateException
	at com.komanov.future.examples.ExceptionDemo$.$anonfun$stackTrace$3(ExceptionDemo.scala:14)
	at scala.runtime.java8.JFunction1$mcII$sp.apply(JFunction1$mcII$sp.java:23)
	at scala.util.Success.$anonfun$map$1(Try.scala:255)
	at scala.util.Success.map(Try.scala:213)
	at scala.concurrent.Future.$anonfun$map$1(Future.scala:292)
	at scala.concurrent.impl.Promise.liftedTree1$1(Promise.scala:33)
	at scala.concurrent.impl.Promise.$anonfun$transform$1(Promise.scala:33)
	at scala.concurrent.impl.CallbackRunnable.run(Promise.scala:64)
	at java.util.concurrent.ForkJoinTask$RunnableExecuteAction.exec(ForkJoinTask.java:1402)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)

  direct:
java.lang.IllegalStateException
	at com.komanov.future.examples.ExceptionDemo$.$anonfun$stackTrace$3(ExceptionDemo.scala:14)
	at scala.runtime.java8.JFunction1$mcII$sp.apply(JFunction1$mcII$sp.java:23)
	at scala.util.Success.$anonfun$map$1(Try.scala:255)
	at scala.util.Success.map(Try.scala:213)
	at scala.concurrent.Future.$anonfun$map$1(Future.scala:292)
	at scala.concurrent.impl.Promise.liftedTree1$1(Promise.scala:33)
	at scala.concurrent.impl.Promise.$anonfun$transform$1(Promise.scala:33)
	at scala.concurrent.impl.CallbackRunnable.run(Promise.scala:64)
	at com.komanov.future.package$DirectExecutor.execute(package.scala:29)
	at scala.concurrent.impl.ExecutionContextImpl.execute(ExecutionContextImpl.scala:24)
	at scala.concurrent.impl.CallbackRunnable.executeWithValue(Promise.scala:72)
	at scala.concurrent.impl.Promise$DefaultPromise.dispatchOrAddCallback(Promise.scala:316)
	at scala.concurrent.impl.Promise$DefaultPromise.onComplete(Promise.scala:307)
	at scala.concurrent.impl.Promise.transform(Promise.scala:33)
	at scala.concurrent.impl.Promise.transform$(Promise.scala:31)
	at scala.concurrent.impl.Promise$DefaultPromise.transform(Promise.scala:187)
	at scala.concurrent.Future.map(Future.scala:292)
	at scala.concurrent.Future.map$(Future.scala:292)
	at scala.concurrent.impl.Promise$DefaultPromise.map(Promise.scala:187)
	at com.komanov.future.examples.ExceptionDemo$.stackTrace(ExceptionDemo.scala:13)
	at com.komanov.future.examples.ExceptionDemo$.delayedEndpoint$com$komanov$future$examples$ExceptionDemo$1(ExceptionDemo.scala:22)
	at com.komanov.future.examples.ExceptionDemo$delayedInit$body.apply(ExceptionDemo.scala:6)
	at scala.Function0.apply$mcV$sp(Function0.scala:39)
	at scala.Function0.apply$mcV$sp$(Function0.scala:39)
	at scala.runtime.AbstractFunction0.apply$mcV$sp(AbstractFunction0.scala:17)
	at scala.App.$anonfun$main$1$adapted(App.scala:80)
	at scala.collection.immutable.List.foreach(List.scala:392)
	at scala.App.main(App.scala:80)
	at scala.App.main$(App.scala:78)
	at com.komanov.future.examples.ExceptionDemo$.main(ExceptionDemo.scala:6)
	at com.komanov.future.examples.ExceptionDemo.main(ExceptionDemo.scala)
   */
}
