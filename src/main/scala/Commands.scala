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

import parser.Parser

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object Commands {

  // FIXME: Aliases not working somehow???
  def from(input: String): Command = {
    val cmds: Set[Command] = allCommands.filter(_.triggers.contains(input))
    cmds.headOption match {
      case Some(value) => value
      case None => InvalidCommand
    }
  }

  def allCommands: Set[Command] = Set(AllCombinations, Help)

  sealed trait Command {

    def stringify: String =
      s"""$toCommand\t${
        val a = aliases.mkString(", ")
        if (a.isEmpty) ""
        else if (aliases.size == 1) "Alias: " + a
        else "Aliases: " + a
      }
         |\t  $description
         |""".stripMargin

    def toCommand: String

    def aliases: Set[String]

    def triggers: Set[String] = (aliases + toCommand).map(_.toLowerCase.trim)

    def description: String

    def run: String
  }

  case object AllCombinations extends Command {
    override def toCommand: String = "solve"

    override def aliases: Set[String] = Set("solve-all", "sat")

    override def description: String = "Checks all combinations of truth values for all variables in a formula (SAT-solver)."

    override def run: String = {
      val input = StdIn.readLine("Please input a formula: ").trim
      Try(Parser.parse(input)) match {
        case Failure(exception) => exception.getMessage
        case Success(exp) =>
          val evaluations = Environment.allPossibleFrom(exp)
            .map(env => (env.toList.sorted, Interpreter.eval(exp, env)))

          val header = evaluations.map(_._1).head.map(_._1).mkString("| ", " | ", " | result |")
          val body = evaluations.map(t =>
            t._1.map(
              tt => util.PrintableBoolean.from(tt._2))
              .mkString("| ", " | ", " | ")
              + "  " + util.PrintableBoolean.from(t._2) + "    |")
            .mkString("\n")

          s"Parsed as: ${Unparser.unparse(exp)}\n\n$header\n${"-".repeat(header.length)}\n$body"
      }
    }
  }

  case object Help extends Command {
    override def toCommand: String = "help"

    override def aliases: Set[String] = Set()

    override def description: String = "Shows this help message."

    override def run: String =
      s"""Commands:
         |${Commands.allCommands.map(c => "\t" + c.stringify).mkString("\n")}""".stripMargin
  }

  case object InvalidCommand extends Command {
    override def toCommand: String = ""

    override def aliases: Set[String] = Set()

    override def description: String = ""

    override def run: String = "Invalid Command. Type 'help' for more information."
  }
}
