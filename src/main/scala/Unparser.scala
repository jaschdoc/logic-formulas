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
