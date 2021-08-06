package io.jaschdoc

import scala.util.parsing.combinator.RegexParsers


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

object Lexer extends RegexParsers {
  override def skipWhitespace: Boolean = true

  val keywords = Set("not", "and", "or", "->")


}
