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

/**
 * Grammar
 *
 * Exp   ::= Atom | UnOp Exp | Exp BinOp Exp | (Exp)
 * Atom  ::= [a-z]
 * UnOp  ::= not
 * BinOp ::= and | or | ->
 *
 * Operator precedence (from strongest to weakest)
 * 1. UnOp binds the strongest
 * 2. and | or
 * 3. -> (implication)
 *
 * not | and | or    are all left associative
 * -> (implication)  is right associative
 */
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
