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

import scala.io.StdIn

object Main extends App {
  val welcomeMsg =
    s"""
       |Welcome to the logic formula tool!
       |
       |Here are your options:
       |
       |${Commands.Help.run}""".stripMargin

  println(welcomeMsg)

  val exitWords = Set(
    "exit",
    "quit",
    "q"
  )

  var running = true
  while (running) {

    val input = StdIn.readLine(s"Please input command (Type ${exitWords.mkString(", ")} to quit): ").trim.toLowerCase

    if (!exitWords.contains(input)) {
      println(Commands.from(input).run)
    } else {
      running = false
      sys.exit()
    }
  }
}
