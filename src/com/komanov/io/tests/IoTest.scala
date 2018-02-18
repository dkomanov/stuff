package com.komanov.io.tests

import com.komanov.io._
import org.specs2.mock.Mockito
import org.specs2.mutable.SpecWithJUnit

class IoTest extends SpecWithJUnit with Mockito {

  "withResources" should {
    "succeed" >> {
      val resource = mock[AutoCloseable]

      withResources(resource)(_ => "shalom") mustEqual "shalom"

      got {
        one(resource).close()
      }
    }

    "close resource if function threw" >> {
      val resource = mock[AutoCloseable]
      val ex = new IllegalStateException("Boom!")

      withResources[AutoCloseable, String](resource)(_ => throw ex) must throwA[IllegalStateException](ex)

      got {
        one(resource).close()
      }
    }

    "add suppressed exception if function threw and close threw" >> {
      val resource = mock[AutoCloseable]
      val ex = new IllegalStateException("Boom!")
      val suppressed = new RuntimeException("Failed to close!")

      resource.close() throws suppressed

      withResources[AutoCloseable, String](resource)(_ => throw ex) must throwA[IllegalStateException].like {
        case e =>
          e.getSuppressed.toSeq must contain(exactly(suppressed.asInstanceOf[Throwable]))
          e mustEqual ex
      }

      got {
        one(resource).close()
      }
    }

    "throw exception thrown on close" >> {
      val resource = mock[AutoCloseable]
      val ex = new RuntimeException("Failed to close!")

      resource.close() throws ex

      withResources(resource)(_ => "shalom") must throwA[RuntimeException](ex)

      got {
        one(resource).close()
      }
    }

    "throw if resource is null" >> {
      withResources[AutoCloseable, String](null)(_ => "shalom") must throwA[IllegalArgumentException]
    }
  }

}
