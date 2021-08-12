package io.jaschdoc

import Ast._

class ScalaDSLSuite extends UnitSpec {

  trait TestFormula extends Formula {
    def toExp: Exp
  }

  "The Scala DSL" should "allow for atomic expressions 'p'" in {
    case object TestFormula extends TestFormula {
      override def toExp: Exp = "p"
    }

    TestFormula.toExp shouldBe AtomExp("p")
  }

  it should "convert 'p' and 'q' to a BinOpExp" in {
    case object TestFormula extends TestFormula {
      override def toExp: Exp = "p" and "q"
    }

    TestFormula.toExp shouldBe BinOpExp(AtomExp("p"), AndBinOp, AtomExp("q"))
  }
}
