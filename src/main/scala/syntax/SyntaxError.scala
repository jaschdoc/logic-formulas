package io.jaschdoc
package syntax

class SyntaxError(msg: String, input: String = "") extends FormulaCompilationError(s"$msg - Input was: $input") {
  override def toString: String = s"$msg - Input was: $input"
}
