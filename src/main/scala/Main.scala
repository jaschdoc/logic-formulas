package io.jaschdoc

object Main extends App {
  val welcomeMsg =
    s"""
      |Welcome to the logic formula tool!
      |Here are your options:
      |${Commands.Help.run}
      |""".stripMargin

  println(welcomeMsg)

  println()

  println("We should now prompt you for some input")
}
