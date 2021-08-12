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

  implicit class ProtoString(left: String) extends ProtoFormula {
    override def and(right: Formula): Formula = BinOpExp(left, AndBinOp, right)

    override def or(right: Formula): Formula = BinOpExp(left, OrBinOp, right)

    override def ~>(right: Formula): Formula = BinOpExp(left, ImplicationBinOp, right)
  }

  implicit class ProtoExp(left: Exp) extends ProtoFormula {
    override def and(right: Formula): Formula = BinOpExp(left, AndBinOp, right)

    override def or(right: Formula): Formula = BinOpExp(left, OrBinOp, right)

    override def ~>(right: Formula): Formula = BinOpExp(left, ImplicationBinOp, right)
  }
}
