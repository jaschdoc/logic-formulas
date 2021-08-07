package io.jaschdoc

import syntax.Lexer.FormulaLexerError
import syntax.Parser

class ParserSpec extends UnitSpec {

  "The parser" should "not accept an empty program" in {
    assertThrows[FormulaLexerError] {
      Parser.parse("")
    }
  }
}
