package io.jaschdoc

import Ast._
import Tokens._

import scala.util.parsing.combinator.Parsers


// Grammar
// Symbols in backticks are terminals / literals
// ###################################################################
// Exp      ::= Atom | UnOp Exp | Exp BinOp Exp | (Exp)
// Atom     ::= [a-z]
// UnOp     ::= `not`
// BinOp    ::= `and` | `or` | `->`
// ###################################################################
// Operator precedence (from strongest to weakest)
// 1. Unary operator `not` binds the strongest
// 2. Binary Operators `and` and `or` (left associative)
// 3. Binary Operator `implies` (right associative)


object Parser extends Parsers {

  override type Elem = LogicToken

  private def expression: Parser[Exp] = phrase {
    unopexp | binopexp | parenexp | atomexp
  }

  private lazy val identifier: Parser[IDENTIFIER] = {
    accept("identifier", { case id: IDENTIFIER => id })
  }

  private lazy val not: Parser[UnOp] = NOT ^^ { _ => NotUnOp }

  private lazy val and: Parser[BinOp] = AND ^^ { _ => AndBinOp }

  private lazy val or: Parser[BinOp] = OR ^^ { _ => OrBinOp }

  private lazy val implication: Parser[BinOp] = ARROW ^^ { _ => ImplicationBinOp }

  private lazy val unop: Parser[UnOp] = not

  private lazy val binop: Parser[BinOp] = and | or | implication

  private lazy val unopexp: Parser[Exp] = ???
  private lazy val binopexp: Parser[Exp] = ???
  private lazy val parenexp: Parser[Exp] = ???
  private lazy val atomexp: Parser[Exp] = ???
}
