/*
*    Copyright (C) 2021  jaschdoc
*
*    This file is part of Formulae.
*
*    Hours is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Hours is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/


package io.jaschdoc
package util

import scala.Console.{GREEN, RED, RESET}

object PrintableBoolean {
  def from(b: Boolean): Bool = apply(b)

  def apply(b: Boolean): Bool = if (b) True else False

  sealed trait Bool

  case object True extends Bool {
    override def toString: String = s"${GREEN}T$RESET"
  }

  case object False extends Bool {
    override def toString: String = s"${RED}F$RESET"
  }

}
