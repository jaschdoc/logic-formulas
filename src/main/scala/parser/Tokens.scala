/*
*    Copyright (C) 2021  jaschdoc
*
*    This file is part of Formulae.
*
*    Formulae is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Formulae is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/


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
