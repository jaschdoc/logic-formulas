package io.jaschdoc

object Ast {
  /**
   * Grammar
   *
   * Exp   ::= Atom | UnOp Exp | Exp BinOp Exp | (Exp)
   * Atom  ::= [a-z]
   * UnOp  ::= not
   * BinOp ::= and | or | ->
   *
   * Operator precedence (from strongest to weakest)
   * 1. UnOp binds the strongest
   * 2. and | or
   * 3. -> (implication)
   */

  type Id = String

  sealed trait AstNode


  sealed trait Exp extends AstNode


  sealed trait UnOp extends AstNode

  case object NotUnOp extends UnOp


  sealed trait BinOp extends AstNode

  case object AndBinOp extends BinOp

  case object OrBinOp extends BinOp

  case object ImplicationBinOp extends BinOp


  case class AtomExp(p: Id) extends Exp

  case class UnOpExp(op: UnOp, p: Exp) extends Exp

  case class BinOpExp(p: Exp, op: BinOp, q: Exp) extends Exp

}
