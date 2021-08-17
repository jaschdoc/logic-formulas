/*
*    Copyright (C) 2021  jaschdoc
*
*    This file is part of Formulae.
*
*    Hours is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Hours is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/


package io.jaschdoc

import parser.Parser

class InterpreterSuite extends UnitSpec {

  "The Interpreter" must "evaluate true to true" in {
    val exp = Parser.parse("p")
    val env = Map("p" -> true)
    Interpreter.eval(exp, env) shouldBe true
  }

  it must "evaluate false to false" in {
    val exp = Parser.parse("p")
    val env = Map("p" -> false)
    Interpreter.eval(exp, env) shouldBe false
  }

  it must "adhere to the truth table of the NOT operator (1)" in {
    val exp = Parser.parse("not p")
    val env = Map("p" -> true)
    Interpreter.eval(exp, env) shouldBe false
  }

  it must "adhere to the truth table of the NOT operator (2)" in {
    val exp = Parser.parse("not p")
    val env = Map("p" -> false)
    Interpreter.eval(exp, env) shouldBe true
  }

  it must "adhere to the truth table of the AND operator (1)" in {
    val exp = Parser.parse("p and q")
    val env = Map("p" -> true, "q" -> true)
    Interpreter.eval(exp, env) shouldBe true
  }

  it must "adhere to the truth table of the AND operator (2)" in {
    val exp = Parser.parse("p and q")
    val env = Map("p" -> true, "q" -> false)
    Interpreter.eval(exp, env) shouldBe false
  }

  it must "adhere to the truth table of the AND operator (3)" in {
    val exp = Parser.parse("p and q")
    val env = Map("p" -> false, "q" -> true)
    Interpreter.eval(exp, env) shouldBe false
  }

  it must "adhere to the truth table of the AND operator (4)" in {
    val exp = Parser.parse("p and q")
    val env = Map("p" -> false, "q" -> false)
    Interpreter.eval(exp, env) shouldBe false
  }

  it must "adhere to the truth table of the OR operator (1)" in {
    val exp = Parser.parse("p or q")
    val env = Map("p" -> true, "q" -> true)
    Interpreter.eval(exp, env) shouldBe true
  }

  it must "adhere to the truth table of the OR operator (2)" in {
    val exp = Parser.parse("p or q")
    val env = Map("p" -> true, "q" -> false)
    Interpreter.eval(exp, env) shouldBe true
  }

  it must "adhere to the truth table of the OR operator (3)" in {
    val exp = Parser.parse("p or q")
    val env = Map("p" -> false, "q" -> true)
    Interpreter.eval(exp, env) shouldBe true
  }

  it must "adhere to the truth table of the OR operator (4)" in {
    val exp = Parser.parse("p or q")
    val env = Map("p" -> false, "q" -> false)
    Interpreter.eval(exp, env) shouldBe false
  }

  it must "adhere to the truth table of the IMPLICATION operator (1)" in {
    val exp = Parser.parse("p -> q")
    val env = Map("p" -> true, "q" -> true)
    Interpreter.eval(exp, env) shouldBe true
  }

  it must "adhere to the truth table of the IMPLICATION operator (2)" in {
    val exp = Parser.parse("p -> q")
    val env = Map("p" -> true, "q" -> false)
    Interpreter.eval(exp, env) shouldBe false
  }

  it must "adhere to the truth table of the IMPLICATION operator (3)" in {
    val exp = Parser.parse("p -> q")
    val env = Map("p" -> false, "q" -> true)
    Interpreter.eval(exp, env) shouldBe true
  }

  it must "adhere to the truth table of the IMPLICATION operator (4)" in {
    val exp = Parser.parse("p -> q")
    val env = Map("p" -> false, "q" -> false)
    Interpreter.eval(exp, env) shouldBe true
  }
}
