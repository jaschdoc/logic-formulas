package io.jaschdoc

import Ast.{AndExp, AtomExp}

object Main extends App {

  val formula = AndExp(AtomExp(true), AtomExp(false))
  val v = Interpreter.eval(formula)
  println(v)

}

