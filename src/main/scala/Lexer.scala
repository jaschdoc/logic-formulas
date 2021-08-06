package io.jaschdoc

import Tokens._

import scala.util.matching.Regex
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

  override val whiteSpace: Regex = """[ \t\r\f\n]""".r

  private val keywords = Set("not", "and", "or", "->")

  private def identifier: Parser[IDENTIFIER] = {
    "[a-zA-Z_][a-zA-Z0-9_]*".r ^^ { case str if !keywords.contains(str) => IDENTIFIER(str) }
  }

  private def leftParen: Parser[LEFT_PAREN.type] = "(" ^^ { _ => LEFT_PAREN }

  private def rightParen: Parser[RIGHT_PAREN.type] = ")" ^^ { _ => RIGHT_PAREN }

  private def not: Parser[NOT.type] = "not" ^^ { _ => NOT }

  private def and: Parser[AND.type] = "and" ^^ { _ => AND }

  private def or: Parser[OR.type] = "or" ^^ { _ => OR }

  private def arrow: Parser[ARROW.type] = "->" ^^ { _ => ARROW }

}
