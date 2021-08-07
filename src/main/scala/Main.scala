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
