package io.jaschdoc

import Tokens.{IDENTIFIER, LogicToken}

import scala.util.parsing.combinator.Parsers


// Grammar
// Symbols in backticks are terminals / literals
// ###################################################################
// Exp      ::= Atom | UnOpExp | BinOpExp | (Exp)
// Atom     ::= [a-z]
// UnOpExp  ::= `not` Exp
// BinOpExp ::= Exp `and` Exp | Exp `or` Exp | Exp `implies` Exp
// ###################################################################
// Operator precedence (from strongest to weakest)
// 1. Unary operator `not` binds the strongest
// 2. Binary Operators `and` and `or` (left associative)
// 3. Binary Operator `implies` (right associative)


object Parser extends Parsers {

  override type Elem = LogicToken

  private def identifier: Parser[IDENTIFIER] = {
    accept("identifier", { case id: IDENTIFIER => id })
  }


}
