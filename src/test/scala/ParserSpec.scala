package io.jaschdoc

import Ast._
import syntax.Parser
import syntax.Parser.FormulaParserError

class ParserSpec extends UnitSpec {

  "The parser" should "not accept an empty program" in {
    assertThrows[FormulaParserError] {
      Parser.parse("")
    }
  }

  it must "accept a single atom" in {
    val expected = AtomExp("p")
    assertResult(expected) {
      Parser.parse("p")
    }
  }

  ignore should "not accept two atoms only" in {
    Parser.parse("p q")
    fail("Not implemented yet")
  }

  it must "recognize a negation expression" in {
    val expected = UnOpExp(NotUnOp, AtomExp("p"))
    assertResult(expected) {
      Parser.parse("not p")
    }
  }

  it must "recognize an AND operator" in {
    val expected = BinOpExp(AtomExp("p"), AndBinOp, AtomExp("q"))
    assertResult(expected) {
      Parser.parse("p and q")
    }
  }

  it must "recognize an OR operator" in {
    val expected = BinOpExp(AtomExp("p"), OrBinOp, AtomExp("q"))
    assertResult(expected) {
      Parser.parse("p or q")
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
      Parser.parse("p and not q or s -> not a or b")
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
          AtomExp(""),
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

    assertResult(expected) {
      Parser.parse("(p and (not q or (s -> not (a or b)))")
    }
  }
}
