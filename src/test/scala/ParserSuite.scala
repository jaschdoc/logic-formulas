package io.jaschdoc

import Ast._
import syntax.{Parser, SyntaxError}

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
}
