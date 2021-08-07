package io.jaschdoc
package syntax

import Ast._
import syntax.Tokens._

import scala.util.parsing.combinator.Parsers
import scala.util.parsing.input.{NoPosition, Position, Reader}

object Parser extends Parsers {

  override type Elem = LogicToken

  def parse(code: String): Exp = apply(Lexer.on(code))

  def apply(tokens: Seq[LogicToken]): Exp = {
    val reader = new LogicTokenReader(tokens)
    program(reader) match {
      case Success(result, _) => result
      case Error(msg, input) => throw new FormulaParserError(s"$msg\nInput was: $input")
      case Failure(msg, input) => throw new FormulaParserError(s"$msg\nInput was: $input")
    }
  }

  lazy val program: Parser[Exp] = expression()

  private def expression(antiPrecedence: Int = 1): Parser[Exp] = antiPrecedence match {
    case x if x >= 0 =>
      binopexp(antiPrecedence) |
        expression(x - 1)
    case -1 =>
      unopexp |
        expression(-2)
    case -2 =>
      atomexp |
        parenthesis
  }

  private lazy val identifier: Parser[IDENTIFIER] = {
    accept("identifier", { case id: IDENTIFIER => id })
  }

  private lazy val not: Parser[UnOp] = NOT ^^ { _ => NotUnOp }

  private lazy val and: Parser[BinOp] = AND ^^ { _ => AndBinOp }

  private lazy val or: Parser[BinOp] = OR ^^ { _ => OrBinOp }

  private lazy val implication: Parser[BinOp] = ARROW ^^ { _ => ImplicationBinOp }

  private lazy val unop: Parser[UnOp] = not

  private lazy val unopexp: Parser[Exp] = (unop ~ expression()) ^^ { case op ~ exp => UnOpExp(op, exp) }

  private def binop(antiPrecedence: Int): Parser[BinOp] = antiPrecedence match {
    case 0 => and | or
    case 1 => implication
  }

  private def binopexp(antiPrecedence: Int): Parser[Exp] = expression(antiPrecedence - 1) * {
    binop(antiPrecedence) ^^ { op => { (left: Exp, right: Exp) => BinOpExp(left, op, right) } }
  }

  private lazy val parenthesis: Parser[Exp] = (LEFT_PAREN ~ expression() ~ RIGHT_PAREN) ^^ { case _ ~ exp ~ _ => exp }

  private lazy val atomexp: Parser[Exp] = identifier ^^ { id => AtomExp(id.str) }

  class FormulaParserError(msg: String) extends FormulaCompilationError(msg)


  class LogicTokenReader(tokens: Seq[LogicToken]) extends Reader[LogicToken] {
    override def first: LogicToken = tokens.head

    override def rest: Reader[LogicToken] = new LogicTokenReader(tokens.tail)

    override def pos: Position = NoPosition

    override def atEnd: Boolean = tokens.isEmpty
  }

}
