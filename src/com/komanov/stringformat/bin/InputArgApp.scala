package com.komanov.stringformat.bin

import com.komanov.stringformat.{InputArg, JavaFormats}

object InputArgApp extends App {

  for (a <- InputArg.values()) {
    println(s"$a: ${JavaFormats.concat(a.value1, a.value2, null).length}")
  }

}
