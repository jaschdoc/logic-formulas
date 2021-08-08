package io.jaschdoc
package util

import scala.Console.{GREEN, RED, RESET}

object PrintableBoolean {
  def from(b: Boolean): Bool = apply(b)

  def apply(b: Boolean): Bool = if (b) True else False

  sealed trait Bool

  case object True extends Bool {
    override def toString: String = s"${GREEN}T$RESET"
  }

  case object False extends Bool {
    override def toString: String = s"${RED}F$RESET"
  }

}
