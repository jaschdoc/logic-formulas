package io.jaschdoc

import Ast._

class ScalaDSLSuite extends UnitSpec {

  trait TestFormula extends Formula {
    def toExp: Exp
  }

  "The Scala DSL" should "allow for atomic expressions \"p\"" in {
    case object TestFormula extends TestFormula {
      override def toExp: Exp = "p"
    }
    TestFormula.toExp shouldBe AtomExp("p")
  }

  it should "convert '\"p\" and \"q\"' to a BinOpExp" in {
    case object TestFormula extends TestFormula {
      override def toExp: Exp = "p" and "q"
    }
    val expectedAst = BinOpExp(AtomExp("p"), AndBinOp, AtomExp("q"))

    TestFormula.toExp shouldBe expectedAst
  }

  it should "convert '\"p\" or \"q\"' to a BinOpExp" in {
    case object TestFormula extends TestFormula {
      override def toExp: Exp = "p" or "q"
    }
    val expectedAst = BinOpExp(AtomExp("p"), OrBinOp, AtomExp("q"))

    TestFormula.toExp shouldBe expectedAst
  }

  it should "convert '\"p\" ~> \"q\"' to a BinOpExp" in {
    case object TestFormula extends TestFormula {
      override def toExp: Exp = "p" ~> "q"
    }
    val expectedAst = BinOpExp(AtomExp("p"), ImplicationBinOp, AtomExp("q"))

    TestFormula.toExp shouldBe expectedAst
  }

  it should "convert 'not \"p\"' to an UnOpExp" in {
    case object TestFormula extends TestFormula {
      override def toExp: Exp = not("p")
    }
    val expectedAst = UnOpExp(NotUnOp, AtomExp("p"))

    TestFormula.toExp shouldBe expectedAst
  }

}
