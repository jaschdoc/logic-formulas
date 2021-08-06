package io.jaschdoc

import Ast._
import Interpreter.Env

import scala.annotation.tailrec

object Environment {

  def freeVars(e: Exp): Set[Id] = e match {
    case AtomExp(p) => Set(p)
    case UnOpExp(_, p) => freeVars(p)
    case BinOpExp(p, _, q) => freeVars(p) ++ freeVars(q)
  }

  def allPossibleFrom(e: Exp): Set[Env] = {

    @tailrec
    def allPossibleFromAcc(ids: Set[Id], envs: Set[Env]): Set[Env] =
      if (envs.isEmpty) {
        allPossibleFromAcc(ids, Set(Map()))
      }
      else {
        ids.headOption match {
          case Some(x) =>
            val ids1 = ids - x
            val envs1 = envs.map(env => env + (x -> true))
            val envs2 = envs.map(env => env + (x -> false))
            allPossibleFromAcc(ids1, envs1 ++ envs2)
          case None => envs
        }
      }

    allPossibleFromAcc(freeVars(e), Set(Map()))
  }

}
