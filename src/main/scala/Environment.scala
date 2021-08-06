package io.jaschdoc

import Ast._
import Interpreter.Env

object Environment {

  def freeVars(e: Exp): Set[Id] = e match {
    case AtomExp(p) => Set(p)
    case UnOpExp(_, p) => freeVars(p)
    case BinOpExp(p, _, q) => freeVars(p) ++ freeVars(q)
  }

  def allPossibleFrom(e: Exp): Set[Env] = {
    def allPossibleFromAcc(ids: Set[Id], envs: Set[Env]): Set[Env] = {
      ???
    }

    allPossibleFromAcc(freeVars(e), Set())
  }

}
