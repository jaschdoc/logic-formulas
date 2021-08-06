package io.jaschdoc


object Main extends App {
  val input = "p and q -> f"
  val lexed = Lexer.on(input)
  println(lexed)
  val program = Parser.parse(lexed)
  println(program)
  // val results = Environment.allPossibleFrom(program).map(env => (env, Interpreter.eval(program, env)))
}

