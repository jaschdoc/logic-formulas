package io.jaschdoc

import Ast._
import syntax.Parser

object Main extends App {
  val input = "p and q -> f"
  val program = Parser.parse(input)
  println(program)
  val results = Environment.allPossibleFrom(program).map(env => (env, Interpreter.eval(program, env)))
  println(results)
  println()

  val e = BinOpExp(
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

  println(Unparser.unparse(e))
}

