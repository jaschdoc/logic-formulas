package io.jaschdoc

import syntax.Parser

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object Commands {

  def from(input: String): Command = {
    val command = allCommands.filter(c => c.triggers.contains(input))
    if (command.nonEmpty) {
      command.head
    } else {
      InvalidCommand
    }
  }

  def allCommands: Set[Command] = Set(AllCombinations, Help)

  sealed trait Command {

    override def toString: String =
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

    def triggers: Set[String] = aliases + toCommand

    def description: String

    def run: String
  }

  case object AllCombinations extends Command {
    override def toCommand: String = "solve"

    override def aliases: Set[String] = Set("solve-all")

    override def description: String = "Checks all combinations of truth values for all variables in a formula (SAT-solver)."

    override def run: String = {
      val input = StdIn.readLine("Please input a formula: ").trim
      Try(Parser.parse(input)) match {
        case Failure(exception) => exception.getMessage
        case Success(exp) =>
          val evaluations = Environment.allPossibleFrom(exp)
            .map(env => (env, Interpreter.eval(exp, env)))

          evaluations.map(t => t._1.mkString("| ", " | ", " | ") + t._2 + " |").mkString("\n")
      }
    }
  }

  case object Help extends Command {
    override def toCommand: String = "help"

    override def aliases: Set[String] = Set()

    override def description: String = "Shows this help message."

    override def run: String =
      s"""Commands:
         |${Commands.allCommands.map(c => "\t" + c.toString).mkString("\n")}""".stripMargin
  }

  case object InvalidCommand extends Command {
    override def toCommand: String = ""

    override def aliases: Set[String] = Set()

    override def description: String = ""

    override def run: String = "Invalid Command. Type 'help' for more information."
  }
}
