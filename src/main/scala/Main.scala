package io.jaschdoc

import syntax.Parser

object Main extends App {
  val input = "p and q -> f"
  val program = Parser.parse(input)
  println(program)
  val results = Environment.allPossibleFrom(program).map(env => (env, Interpreter.eval(program, env)))
  println(results)
}

