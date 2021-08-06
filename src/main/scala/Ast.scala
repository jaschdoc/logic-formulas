package io.jaschdoc

object Ast {
  type Atom = Boolean

  sealed trait AstNode

  sealed trait Exp extends AstNode

  case class AtomExp(p: Atom) extends Exp

  case class AndExp(p: Exp, q: Exp) extends Exp

  case class OrExp(p: Exp, q: Exp) extends Exp

  case class ImplicationExp(p: Exp, q: Exp) extends Exp
}
