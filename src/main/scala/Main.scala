package io.jaschdoc


object Main extends App {


  val formula = "p and q -> f"
  val malformedFormula = "p and q <_ f"
  println(Lexer(formula))
  println(Lexer(malformedFormula))

}

