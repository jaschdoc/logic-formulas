package io.jaschdoc

import Ast._

class ScalaDSLSuite extends UnitSpec with Formula {


  "The Scala DSL" should "allow for atomic expressions" in {
    val formula: Formula = "p"
    formula shouldBe AtomExp("p")
  }

  it should "convert p and q to a BinOpExp" in {
    val expectedAst = BinOpExp(AtomExp("p"), AndBinOp, AtomExp("q"))
    "p" and "q" shouldBe expectedAst
  }

  it should "convert p or q to a BinOpExp" in {
    val expectedAst = BinOpExp(AtomExp("p"), OrBinOp, AtomExp("q"))
    "p" or "q" shouldBe expectedAst
  }

  it should "convert p ~> q to a BinOpExp" in {
    val expectedAst = BinOpExp(AtomExp("p"), ImplicationBinOp, AtomExp("q"))
    "p" ~> "q" shouldBe expectedAst
  }

  it should "convert not (p) to an UnOpExp" in {
    val expectedAst = UnOpExp(NotUnOp, AtomExp("p"))
    not("p") shouldBe expectedAst
  }

}
