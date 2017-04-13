package com.komanov.translit

import java.io.IOException
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file._
import java.util.Locale

object RenameTranslitApp extends App {

  def printUsage: Nothing = {
    println("Usage: bin [--dry-run] $path")
    System.exit(1)
    throw new RuntimeException
  }

  val (dryRun, path) = args match {
    case Array("--dry-run", s) => true -> s
    case Array(s) => false -> s
    case _ => printUsage
  }

  println(s"Walking over $path")

  var indent = ""

  def renameIfNeeded(file: Path): Unit = {
    val originalName = file.getFileName.toString
    val transliterated = TranslitUtils.transliterate(originalName)
    if (originalName == transliterated) {
      println(s"$indent$originalName... keep")
    } else {
      println(s"$indent$originalName... rename to $transliterated")
      if (!dryRun) {
        Files.move(file, file.getParent.resolve(transliterated), StandardCopyOption.ATOMIC_MOVE)
      }
    }
  }

  Files.walkFileTree(Paths.get(path), new SimpleFileVisitor[Path]() {
    override def preVisitDirectory(dir: Path, attrs: BasicFileAttributes) = {
      println(s"$indent${dir.getFileName}")
      indent += "  "
      FileVisitResult.CONTINUE
    }

    override def postVisitDirectory(dir: Path, exc: IOException) = {
      renameIfNeeded(dir)
      indent = indent.substring(0, indent.length - 2)
      FileVisitResult.CONTINUE
    }

    override def visitFile(file: Path, attrs: BasicFileAttributes) = {
      renameIfNeeded(file)
      FileVisitResult.CONTINUE
    }
  })

}

object TranslitUtils {
  private val table: Map[Char, String] =
    List(
      'А' -> "A",
      'Б' -> "B",
      'В' -> "V",
      'Г' -> "G",
      'Д' -> "D",
      'Е' -> "E",
      'Ё' -> "YO",
      'Ж' -> "Zh",
      'З' -> "Z",
      'И' -> "I",
      'Й' -> "Y",
      'К' -> "K",
      'Л' -> "L",
      'М' -> "M",
      'Н' -> "N",
      'О' -> "O",
      'П' -> "P",
      'Р' -> "R",
      'С' -> "S",
      'Т' -> "T",
      'У' -> "U",
      'Ф' -> "F",
      'Х' -> "H",
      'Ц' -> "Ts",
      'Ч' -> "Ch",
      'Ш' -> "Sh",
      'Щ' -> "Sch",
      'Ъ' -> "",
      'Ы' -> "Y",
      'Ь' -> "",
      'Э' -> "E",
      'Ю' -> "Yu",
      'Я' -> "Ya",
      'Є' -> "E",
      'Ї' -> "Yi"
    )
      .flatMap { case (ru, en) =>
        List(ru -> en, ru.toLower -> en.toLowerCase(Locale.ENGLISH))
      }
      .toMap

  def transliterate(s: String): String = {
    s.flatMap { ch =>
      table.get(ch).fold(ch.toString)(identity)
    }
  }
}
