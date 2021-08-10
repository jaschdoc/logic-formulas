package io.jaschdoc
package syntax

import scala.util.parsing.input.Positional

object Tokens {

  sealed trait LogicToken extends Positional

  case class IDENTIFIER(str: String) extends LogicToken

  case object LEFT_PAREN extends LogicToken

  case object RIGHT_PAREN extends LogicToken

  case object NOT extends LogicToken

  case object AND extends LogicToken

  case object OR extends LogicToken

  case object ARROW extends LogicToken
}
