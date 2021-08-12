package io.jaschdoc
package parser

import parser.Tokens._

import scala.util.matching.Regex
import scala.util.parsing.combinator.RegexParsers

object Lexer extends RegexParsers {
  override def skipWhitespace: Boolean = true

  override val whiteSpace: Regex = """\s+""".r

  def on(code: String): Either[SyntaxError, List[LogicToken]] = apply(code.trim)

  def apply(code: String): Either[SyntaxError, List[LogicToken]] = {
    parse(tokens, code) match {
      case NoSuccess(msg, input) => Left(new SyntaxError(msg, input.toString))
      case Success(result, _) => Right(result)
    }
  }

  private val keywords = Set("not", "and", "or", "->")

  private def tokens: Parser[List[LogicToken]] =
    phrase(
      rep1(
        leftParen |
          rightParen |
          not |
          and |
          or |
          arrow |
          identifier
      )
    )

  // TODO: add positioned: positioned { ... impl }
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
