package io.jaschdoc

import Ast._

import scala.language.implicitConversions

trait Formula {

  implicit def fromString(p: String): Exp = AtomExp(p)

  def not(e: Exp): Exp = UnOpExp(NotUnOp, e)

  sealed trait ProtoExp {
    def and(right: Exp): Exp

    def or(right: Exp): Exp

    def ~>(right: Exp): Exp
  }

  implicit class ProtoString(string: String) extends ProtoExp {
    override def and(right: Exp): Exp = BinOpExp(string, AndBinOp, right)

    override def or(right: Exp): Exp = BinOpExp(string, OrBinOp, right)

    override def ~>(right: Exp): Exp = BinOpExp(string, ImplicationBinOp, right)
  }
}
