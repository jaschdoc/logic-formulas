package io.jaschdoc
package syntax

import syntax.Tokens.LogicToken

import scala.util.parsing.input.{NoPosition, Position, Reader}

class LogicTokenReader(tokens: Seq[LogicToken]) extends Reader[LogicToken] {
  override def first: LogicToken = tokens.head

  override def rest: Reader[LogicToken] = new LogicTokenReader(tokens.tail)

  override def pos: Position = NoPosition

  override def atEnd: Boolean = tokens.isEmpty
}
