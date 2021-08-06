package io.jaschdoc

import Ast._

object Interpreter {

  type Value = Boolean
  type Env = Map[Id, Value]

  def eval(e: Exp, env: Env): Value = e match {
    case AtomExp(p) => env.getOrElse(p, throw new InterpreterError("Internal Error!"))
    case UnOpExp(op, p) =>
      val p1 = eval(p, env)
      op match {
        case Not => !p1
      }
    case BinOpExp(p, op, q) =>
      val p1 = eval(p, env)
      val q1 = eval(q, env)
      op match {
        case And => p1 && q1
        case Or => p1 || q1
        case Implication => !p1 || q1
      }
  }

  class InterpreterError(msg: String) extends FormulaError(msg)
}
