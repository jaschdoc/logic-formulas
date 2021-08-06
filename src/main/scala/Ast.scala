package io.jaschdoc

object Ast {
  type Id = String

  sealed trait AstNode


  sealed trait Exp extends AstNode

  sealed trait UnOpExp extends Exp

  sealed trait BinOpExp extends Exp


  case class AtomExp(p: Id) extends Exp

  case class NotExp(p: Exp) extends UnOpExp

  case class AndExp(p: Exp, q: Exp) extends BinOpExp

  case class OrExp(p: Exp, q: Exp) extends BinOpExp

  case class ImplicationExp(p: Exp, q: Exp) extends BinOpExp
}
