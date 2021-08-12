package io.jaschdoc

import Ast.{AtomExp, Exp}

class ScalaDSLSuite extends UnitSpec {

  trait TestFormula extends Formula {
    def test: Exp
  }

  "The Scala DSL" should "allow for atomic expressions 'p'" in {

    case object TestFormula extends TestFormula {
      override def test: Exp = "p"
    }

    TestFormula.test shouldBe AtomExp("p")
  }

}
