package io.jaschdoc

import Ast._

object Interpreter {

  type Value = Boolean

  def eval(e: Exp): Value = e match {
    case AtomExp(p) => p
    case AndExp(p, q) => eval(p) && eval(q)
    case OrExp(p, q) => eval(p) || eval(q)
    case ImplicationExp(p, q) => !eval(p) || eval(q)
  }
}
