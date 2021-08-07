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

  it should "accept a single atom" in {
    val expected = AtomExp("p")
    assertResult(expected) {
      Parser.parse("p")
    }
  }

  ignore should "not accept two atoms only" in {
    Parser.parse("p q")
    fail("Not implemented yet")
  }

  it should "recognize a negation expression" in {
    val expected = UnOpExp(NotUnOp, AtomExp("p"))
    assertResult(expected) {
      Parser.parse("not p")
    }
  }

}
