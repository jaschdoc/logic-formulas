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

import Ast._
import parser.{Parser, SyntaxError}

class ParserSuite extends UnitSpec {
  // TODO: Refactor tests to use natural language - see InterpreterSuite

  "The parser" should "not accept an empty program" in {
    assertThrows[SyntaxError] {
      Parser.parse("")
    }
  }

  it must "accept a single atom" in {
    val expected = AtomExp("p")
    assertResult(expected) {
      Parser.parse("p")
    }
  }

  it should "not accept two disjunctive expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("p q")
    }
  }

  it must "recognize a negation expression" in {
    val expected = UnOpExp(NotUnOp, AtomExp("p"))
    assertResult(expected) {
      Parser.parse("not p")
    }
  }

  it should "not accept a NOT operator with no expression" in {
    assertThrows[SyntaxError] {
      Parser.parse("not")
    }
  }

  it should "not accept an AND operator with no expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("and")
    }
  }

  it should "not accept an AND operator with only a left expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("p and")
    }
  }

  it should "not accept an AND operator with only a right expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("and p")
    }
  }

  it must "accept an AND operator with two expressions" in {
    val expected = BinOpExp(AtomExp("p"), AndBinOp, AtomExp("q"))
    assertResult(expected) {
      Parser.parse("p and q")
    }
  }

  it should "not accept an OR operator with no expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("or")
    }
  }

  it should "not accept an OR operator with only a left expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("p or")
    }
  }

  it should "not accept an OR operator with only a right expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("or p")
    }
  }

  it must "recognize an OR operator" in {
    val expected = BinOpExp(AtomExp("p"), OrBinOp, AtomExp("q"))
    assertResult(expected) {
      Parser.parse("p or q")
    }
  }

  it should "not accept an IMPLICATION operator with no expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("->")
    }
  }

  it should "not accept an IMPLICATION operator with only a left expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("p ->")
    }
  }

  it should "not accept an IMPLICATION operator with only a right expressions" in {
    assertThrows[SyntaxError] {
      Parser.parse("-> p")
    }
  }

  it must "recognize an IMPLICATION operator" in {
    val expected = BinOpExp(AtomExp("p"), ImplicationBinOp, AtomExp("q"))
    assertResult(expected) {
      Parser.parse("p -> q")
    }
  }

  it must "account for operator precedence" in {
    // p and not q or s -> not a or b
    // ((p and (not q)) or s) -> ((not a) or (not b))
    val expected = BinOpExp(
      BinOpExp(
        BinOpExp(
          AtomExp("p"),
          AndBinOp,
          UnOpExp(
            NotUnOp,
            AtomExp("q")
          )
        ),
        OrBinOp,
        AtomExp("s")
      ),
      ImplicationBinOp,
      BinOpExp(
        UnOpExp(
          NotUnOp,
          AtomExp("a")
        ),
        OrBinOp,
        UnOpExp(
          NotUnOp,
          AtomExp("b")
        )
      )
    )

    assertResult(expected) {
      Parser.parse("p and not q or s -> not a or not b")
    }
  }

  it must "account for parenthesis precedence" in {
    val expected = BinOpExp(
      AtomExp("p"),
      AndBinOp,
      BinOpExp(
        UnOpExp(
          NotUnOp,
          AtomExp("q")
        ),
        OrBinOp,
        BinOpExp(
          AtomExp("s"),
          ImplicationBinOp,
          UnOpExp(
            NotUnOp,
            BinOpExp(
              AtomExp("a"),
              OrBinOp,
              AtomExp("b")
            )
          )
        )
      )
    )

    val actual = Parser.parse("p and (not q or (s -> not (a or b)))")

    withClue(Unparser.unparse(actual)) {
      assertResult(expected) {
        actual
      }
    }
  }

  it should "not be sensitive to whitespaces" in {
    val expected = BinOpExp(AtomExp("p"), AndBinOp, AtomExp("q"))
    assertResult(expected) {
      Parser.parse("     p     and       q        ")
    }
  }

  it should "not accept symbol-characters" in {
    assertThrows[SyntaxError] {
      Parser.parse("- and *")
    }
  }

  ignore should "respect right associativity of the IMPLICATION operator" in {
    // p -> p -> p -> q == p -> (p -> (p -> (q)))
    val expectedAST =
      BinOpExp(
        AtomExp("p"),
        ImplicationBinOp,
        BinOpExp(
          AtomExp("p"),
          ImplicationBinOp,
          BinOpExp(
            AtomExp("p"),
            ImplicationBinOp,
            AtomExp("q")
          )
        )
      )
    info(Unparser.unparse(expectedAST))
    info(Unparser.unparse(Parser.parse("p -> p -> p -> q")))
    Parser.parse("p -> p -> p -> q") shouldBe expectedAST
  }

  it should "respect left associativity of the AND operator" in {
    // p and q and p and q == (((p and q) and p) and q)
    val expectedAST =
      BinOpExp(
        BinOpExp(
          BinOpExp(
            AtomExp("p"),
            AndBinOp,
            AtomExp("q")
          ),
          AndBinOp,
          AtomExp("p")
        ),
        AndBinOp,
        AtomExp("q")
      )

    Parser.parse("p and q and p and q") shouldBe expectedAST
  }
}
