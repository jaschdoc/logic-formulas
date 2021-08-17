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

object Unparser {

  def unparse(node: AstNode): String = node match {
    case e: Exp => e match {
      case AtomExp(p) => p
      case UnOpExp(op, p) => s"${unparse(op)} (${unparse(p)})"
      case BinOpExp(p, op, q) => s"(${unparse(p)}) ${unparse(op)} (${unparse(q)})"
    }
    case op: UnOp => op match {
      case NotUnOp => "not"
    }
    case op: BinOp => op match {
      case AndBinOp => "and"
      case OrBinOp => "or"
      case ImplicationBinOp => "->"
    }
  }
}
