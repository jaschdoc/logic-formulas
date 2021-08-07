package io.jaschdoc

import syntax.Parser

class InterpreterSuite extends UnitSpec {

  "The Interpreter" must "evaluate true to true" in {
    val exp = Parser.parse("p")
    val env = Map("p" -> true)
    Interpreter.eval(exp, env) should equal(true)
  }

  it must "evaluate false to false" in {
    val exp = Parser.parse("p")
    val env = Map("p" -> false)
    Interpreter.eval(exp, env) should equal(false)
  }

  it must "adhere to the truth table of the NOT operator (1)" in {
    val exp = Parser.parse("not p")
    val env = Map("p" -> true)
    Interpreter.eval(exp, env) should equal(false)
  }

  it must "adhere to the truth table of the NOT operator (2)" in {
    val exp = Parser.parse("not p")
    val env = Map("p" -> false)
    Interpreter.eval(exp, env) should equal(true)
  }

  it must "adhere to the truth table of the AND operator (1)" in {
    val exp = Parser.parse("p and q")
    val env = Map("p" -> true, "q" -> true)
    Interpreter.eval(exp, env) should equal(true)
  }

  it must "adhere to the truth table of the AND operator (2)" in {
    val exp = Parser.parse("p and q")
    val env = Map("p" -> true, "q" -> false)
    Interpreter.eval(exp, env) should equal(false)
  }

  it must "adhere to the truth table of the AND operator (3)" in {
    val exp = Parser.parse("p and q")
    val env = Map("p" -> false, "q" -> true)
    Interpreter.eval(exp, env) should equal(false)
  }

  it must "adhere to the truth table of the AND operator (4)" in {
    val exp = Parser.parse("p and q")
    val env = Map("p" -> false, "q" -> false)
    Interpreter.eval(exp, env) should equal(false)
  }

  it must "adhere to the truth table of the OR operator (1)" in {
    val exp = Parser.parse("p or q")
    val env = Map("p" -> true, "q" -> true)
    Interpreter.eval(exp, env) should equal(true)
  }

  it must "adhere to the truth table of the OR operator (2)" in {
    val exp = Parser.parse("p or q")
    val env = Map("p" -> true, "q" -> false)
    Interpreter.eval(exp, env) should equal(true)
  }

  it must "adhere to the truth table of the OR operator (3)" in {
    val exp = Parser.parse("p or q")
    val env = Map("p" -> false, "q" -> true)
    Interpreter.eval(exp, env) should equal(true)
  }

  it must "adhere to the truth table of the OR operator (4)" in {
    val exp = Parser.parse("p or q")
    val env = Map("p" -> false, "q" -> false)
    Interpreter.eval(exp, env) should equal(false)
  }
}
