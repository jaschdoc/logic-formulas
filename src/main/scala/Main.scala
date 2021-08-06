package io.jaschdoc


object Main extends App {
  val input = "(a and b)"
  val program = Parser.parse(Lexer.on(input))
  println(program)
  // val result = Interpreter.eval(program, Environment.from(program))
}

