package com.komanov.junk.implicitnpe

import com.komanov.junk.implicitnpe.anotherpackage.MyContext
import com.komanov.junk.implicitnpe.anotherpackage.builders.aMyContext

// https://stackoverflow.com/questions/61917884/nullpointerexception-on-implicit-resolution
case class Context(id: String)
object Context {
  implicit def `ContextHolder to Context`(implicit holder: ContextHolder): Context = holder.context
}
trait ContextHolder {
  def context: Context
}

object anotherpackage {
  case class MyContext(name: String, context: Context) extends ContextHolder

  object builders {
    def aMyContext(name: String)(implicit context: Context = Context("test")): MyContext =
      MyContext(name, context)
  }
}

object SimpleDemo extends App {
  implicit val myContext: MyContext = {
    val myContext = null
    aMyContext("name")
  }
}
