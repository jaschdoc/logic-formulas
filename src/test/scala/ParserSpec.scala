package io.jaschdoc

import Ast.AtomExp
import syntax.Parser
import syntax.Parser.FormulaParserError

class ParserSpec extends UnitSpec {

  "The parser" should "not accept an empty program" in {
    assertThrows[FormulaParserError] {
      Parser.parse("")
    }
  }

  it should "accept a single atom" in {
    assertResult(AtomExp("p")) {
      Parser.parse("p")
    }
  }
}
