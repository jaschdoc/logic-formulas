/*
*    Copyright (C) 2021  jaschdoc
*
*    This file is part of Formulae.
*
*    Hours is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Hours is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/


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
