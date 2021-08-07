package io.jaschdoc

import syntax.Parser
import syntax.Parser.FormulaParserError

class ParserSpec extends UnitSpec {

  "The parser" should "not accept an empty program" in {
    assertThrows[FormulaParserError] {
      Parser.parse("")
    }
  }
}
