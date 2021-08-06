package io.jaschdoc


object Main extends App {
  val input = "(a and b)"
  val program = Parser.parse(Lexer.on(input))
  println(program)
  // val results = Environment.allPossibleFrom(program).map(env => (env, Interpreter.eval(program, env)))
}

