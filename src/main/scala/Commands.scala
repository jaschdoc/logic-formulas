package io.jaschdoc

object Commands {

  def from(args: Array[String]): Set[Command] = ???

  def allCommands: Set[]

  sealed trait Command {
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
}
