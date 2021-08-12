package io.jaschdoc

import Ast._

import scala.language.implicitConversions

trait Formula {

  implicit def fromString(p: String): Exp = AtomExp(p)

  def not(e: Exp): Exp = UnOpExp(NotUnOp, e)

  implicit class ProtoString(string: String) {
    def and(right: Exp): Exp = BinOpExp(string, AndBinOp, right)
  }
}
