/*
*    Copyright (C) 2021  jaschdoc
*
*    This file is part of Formulae.
*
*    Hours is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Hours is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/


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
