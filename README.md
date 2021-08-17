# Formulae
Tool to make it easier for me to work with logic formulas.

## Getting Started
### IntelliJ
There are Main and Test run configurations included for IntelliJ, so simply select the correct run configuration and run the program!
If you plan on contributing to the project, you should use the sbt method explained below.

### sbt
1. [Install sbt](https://www.scala-sbt.org/1.x/docs/Setup.html)
2. [Install SuperSafe](https://www.scalatest.org/supersafe#installation)
3. Run the program with either `sbt run` or `sbt test` in the terminal.

### Syntax
A formula looks like
```
p and q -> r or a
```
All binary operators are currently left-associative, however, the '->' operator should be right-associative. There is currently [a fix in progress](https://github.com/jaschdoc/logic-formulas/issues/1) for this.
Additionally, see the [formal grammar](src/main/scala/Ast.scala) for more info.
