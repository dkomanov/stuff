package com.komanov.mysql.pool

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.util.Timer
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.{AtomicBoolean, AtomicInteger}

import com.komanov.mysql.pool.ConnectionSpawningStrategy.SpawnData
import com.komanov.mysql.pool.JdbcThreadPool._
import javax.sql.DataSource

import scala.concurrent.{Future, Promise}

trait ConnectionSpawningStrategy {
  def spawn(data: SpawnData): Boolean
}

object ConnectionSpawningStrategy {
  case class SpawnData(queueSize: Int, threadCount: Int, spawningCount: Int)

  def oneAtTime(minQueueSize: Int): ConnectionSpawningStrategy =
    new OneAtTime(minQueueSize)

  private class OneAtTime(queueSizeTrigger: Int) extends ConnectionSpawningStrategy {
    override def spawn(data: SpawnData) =
      data.spawningCount == 0 && data.queueSize >= queueSizeTrigger
  }
}


/**
 * Represents a thread pool, each of which threads have an access to a thread-confined JDBC connection.
 */
class JdbcThreadPool(ds: DataSource, minIdleCount: Int, maxSize: Int, timer: Timer) {

  private val threads = new Array[JdbcWorkerThread](maxSize)
  private val queue = new LinkedBlockingQueue[JdbcTask]()
  private val closed = new AtomicBoolean(false)
  private val spawningCount = new AtomicInteger(0)

  private def threadCount = 0

  def submit[T](f: DataSource => T): Future[T] = {
    // TODO Double check after putting stuff in a queue
    if (closed.get()) {
      return Future.failed(new IllegalStateException("JdbcThreadPool is shut down"))
    }

    if (threadCount < maxSize && queue.size > 10) {
      // try to create new thread + connection
    }

    val promise = Promise[T]()
    val task = JdbcTask(f, promise.asInstanceOf[Promise[Any]])
    queue.put(task)
    promise.future
  }

  def shutdown(): Unit = {
    if (closed.compareAndSet(false, true)) {
      queue.forEach {
        case JdbcTask(_, promise, _) =>
          promise.failure(new IllegalStateException("shutdown"))
        case _ =>
      }
      queue.clear()
      threads.foreach { thread =>
        if (thread != null) {
          thread.flags = DieFlag
          thread.interrupt()
        }
      }
    }
  }

  class JdbcWorkerThread extends Thread("worker-thread") {
    setDaemon(true)

    @volatile
    var flags = 0

    override def run(): Unit = {
      val connection = try {
        openConnection
      } catch {
        case e: Throwable =>
          // TODO report
          null
      }

      if (connection == null) {
        return
      }

      val ds = new NonShareableDataSource(connection)

      var continue = true

      while (continue) {
        continue = try {
          val task = queue.take()
          tryCatch {
            val r = task.f(ds)
            task.promise.success(r)
            (): Unit
          } {
            case e =>
              task.promise.failure(e)
          }(() => (): Unit)
        } catch {
          case _: InterruptedException =>
            val currentFlags = flags
            flags = 0
            currentFlags match {
              case PingFlag =>
                var st: PreparedStatement = null
                var rs: ResultSet = null
                tryCatch {
                  st = connection.prepareStatement("SELECT 1")
                  rs = st.executeQuery()
                  rs.next()
                  rs.close()
                }(PartialFunction.empty)(() => (): Unit)
                true

              case DieFlag =>
                closeConnection(connection)
                Thread.currentThread().interrupt()
                false

              case _ =>
                Thread.currentThread().interrupt()
                false
            }

          case e: Throwable =>
            // TODO report
            e.printStackTrace()
            closeConnection(connection)
            false
        }
      }
    }
  }

  private def openConnection: Connection = {
    ds.getConnection
  }

  private def closeConnection(c: Connection): Unit = {
    // TODO try..catch
    c.close()
  }

  private def tryCatch(tryClause: => Unit)
                      (catchClause: PartialFunction[Throwable, Unit])
                      (finallyClause: () => Unit): Boolean = {
    try {
      tryClause
      true
    } catch {
      case _: InterruptedException =>
        catchClause(new IllegalStateException("interrupted")) // cache exception?
        Thread.currentThread().interrupt()
        false
      case e: Throwable =>
        if (catchClause.isDefinedAt(e))
          catchClause(e)
        else
        // REPORT
          (): Unit
        true
    } finally {
      finallyClause()
    }
  }
}

object JdbcThreadPool {
  private case class JdbcTask(f: DataSource => Any, promise: Promise[Any], timestamp: Long = System.nanoTime)

  val PingFlag = 1 << 0;
  val DieFlag = 1 << 1;
}
