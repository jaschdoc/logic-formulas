package io.jaschdoc

import syntax.Parser

class InterpreterSuite extends UnitSpec {

  "The Interpreter" must "evaluate true to true" in {
    assertResult(true) {
      val exp = Parser.parse("p")
      val env = Map("p" -> true)
      Interpreter.eval(exp, env)
    }
  }

  "The Interpreter" must "evaluate false to false" in {
    assertResult(false) {
      val exp = Parser.parse("p")
      val env = Map("p" -> false)
      Interpreter.eval(exp, env)
    }
  }
}
