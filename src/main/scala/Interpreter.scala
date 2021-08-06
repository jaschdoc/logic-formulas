package io.jaschdoc

import Ast._

object Interpreter {

  type Value = Boolean
  type Env = Map[Id, Value]

  def eval(e: Exp, env: Env): Value = e match {
    case AtomExp(p) => env.getOrElse(p, throw new InterpreterError(s"Unknown identifier $p"))
    case UnOpExp(op, p) =>
      val p1 = eval(p, env)
      op match {
        case NotUnOp => !p1
      }
    case BinOpExp(p, op, q) =>
      val p1 = eval(p, env)
      val q1 = eval(q, env)
      op match {
        case AndBinOp => p1 && q1
        case OrBinOp => p1 || q1
        case ImplicationBinOp => !p1 || q1
      }
  }

  class InterpreterError(msg: String) extends FormulaError(msg)
}
