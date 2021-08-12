package io.jaschdoc
package parser

object Tokens {

  sealed trait LogicToken

  case class IDENTIFIER(str: String) extends LogicToken

  case object LEFT_PAREN extends LogicToken

  case object RIGHT_PAREN extends LogicToken

  case object NOT extends LogicToken

  case object AND extends LogicToken

  case object OR extends LogicToken

  case object ARROW extends LogicToken
}
