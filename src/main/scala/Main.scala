package io.jaschdoc

import Ast.{AndExp, AtomExp}

object Main extends App {

  val formulaExp = AndExp(AtomExp(true), AtomExp(false))
  val v = Interpreter.eval(formulaExp)
  println(v)

  val formula = "p and q -> f"
  val malformedFormula = "p and q <_ f"
  println(Lexer(formula))
  println(Lexer(malformedFormula))

}

