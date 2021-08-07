package io.jaschdoc
package syntax

class SyntaxError(msg: String, input: String = "") extends FormulaCompilationError(s"$msg" + (if (input.nonEmpty) s"\nInput was: $input" else "")) {
  override def toString: String = s"$msg" + (if (input.nonEmpty) s"\nInput was: $input" else "")
}
