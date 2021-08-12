package io.jaschdoc

import Ast._

import scala.language.implicitConversions

trait Formula {

  type Formula = Exp

  implicit def fromString(p: String): Formula = AtomExp(p)

  def not(e: Formula): Formula = UnOpExp(NotUnOp, e)

  sealed trait ProtoFormula {
    def and(right: Formula): Formula

    def or(right: Formula): Formula

    def ~>(right: Formula): Formula
  }

  implicit class ProtoString(string: String) extends ProtoFormula {
    override def and(right: Formula): Formula = BinOpExp(string, AndBinOp, right)

    override def or(right: Formula): Formula = BinOpExp(string, OrBinOp, right)

    override def ~>(right: Formula): Formula = BinOpExp(string, ImplicationBinOp, right)
  }
}
