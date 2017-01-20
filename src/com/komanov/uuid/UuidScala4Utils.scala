package com.komanov.uuid

import java.util.UUID

object UuidScala4Utils {

  def fromStringFast(s: String): UUID = {
    var component = 0
    var most = 0L
    val least = s.map({
      case '0' => 0
      case '1' => 1
      case '2' => 2
      case '3' => 3
      case '4' => 4
      case '5' => 5
      case '6' => 6
      case '7' => 7
      case '8' => 8
      case '9' => 9
      case 'A' | 'a' => 10
      case 'B' | 'b' => 11
      case 'C' | 'c' => 12
      case 'D' | 'd' => 13
      case 'E' | 'e' => 14
      case 'F' | 'f' => 15
      case '-' => -1
      case '+' => 0
      case ch => throw new IllegalArgumentException(s"unexpected character $ch in $s")
    }).foldLeft(0L) { (result, digit) => {
      if (digit == -1) {
        component += 1
        component match {
          case 1 | 2 | 4 => result
          case 3 => most = result; 0L
          case _ => throw new IllegalArgumentException(s"too many components in $s")
        }
      } else {
        result * 16 + digit
      }
    }
    }

    new UUID(most, least)
  }
}
