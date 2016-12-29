package com.komanov.serialization.converters

object ReflectionUtils {

  def getCompanionObject(clazz: Class[_]): Any = {
    import scala.reflect.runtime.{currentMirror => cm}
    val classSymbol = cm.classSymbol(clazz)
    val moduleSymbol = classSymbol.companion.asModule
    val moduleMirror = cm.reflectModule(moduleSymbol)
    moduleMirror.instance
  }

}
