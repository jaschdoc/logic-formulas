package io.jaschdoc

object Ast {
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
