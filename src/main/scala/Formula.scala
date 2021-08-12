package io.jaschdoc

import Ast.{AtomExp, Exp}

import scala.language.implicitConversions

trait Formula {

  implicit def toExp(s: String): Exp = AtomExp(s)

}
