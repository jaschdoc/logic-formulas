package io.jaschdoc

object Ast {
  type Id = String

  sealed trait AstNode


  sealed trait Exp extends AstNode

  sealed trait UnOp extends AstNode

  case object Not extends UnOp

  sealed trait BinOp extends AstNode

  case object And extends BinOp

  case object Or extends BinOp

  case object Implication extends BinOp


  case class AtomExp(p: Id) extends Exp

  case class UnOpExp(op: UnOp, p: Exp)

  case class BinOpExp(p: Exp, op: BinOp, q: Exp)

}
