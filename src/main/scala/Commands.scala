package io.jaschdoc

object Commands {

  def from(args: Array[String]): Set[Command] = ???

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

    def description: String

    def run: String
  }

  case object AllCombinations extends Command {
    override def toCommand: String = "all-combinations"

    override def aliases: Set[String] = Set("sat-solve")

    override def description: String = "Checks all combinations of truth values for all variables in a formula (SAT-solver)."

    override def run: String = ???
  }

  case object Help extends Command {
    override def toCommand: String = "help"

    override def aliases: Set[String] = Set()

    override def description: String = "Shows this help message."

    override def run: String =
      s"""Commands:
         |${Commands.allCommands.map(c => "\t" + c.toString).mkString("\n")}
         |""".stripMargin
  }
}
