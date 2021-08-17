[![Tests](https://github.com/jaschdoc/logic-formulas/actions/workflows/scala.yml/badge.svg?branch=main)](https://github.com/jaschdoc/logic-formulas/actions/workflows/scala.yml)
# Formulae
Simple logic formula assistance.

## Getting Started
First, clone the repo to your local machine. You can choose to fork it and clone that copy, such that you own a copy, you can make changes to. 

### IntelliJ
There are Main and Test run configurations included for IntelliJ, so simply select the correct run configuration and run the program!
If you plan on contributing to the project, you should use the sbt method explained below.

### sbt
This is the recommended method of running and testing the program.
1. [Install sbt](https://www.scala-sbt.org/1.x/docs/Setup.html)
2. [Install SuperSafe](https://www.scalatest.org/supersafe#installation) (optional, but recommended)
3. Run the program with either `sbt run` or `sbt test` in the terminal.

### Syntax
A formula looks like this:
```
p and q -> r or a
```
All binary operators are currently left-associative, however, the '->' operator should be right-associative. There is currently [a fix in progress](https://github.com/jaschdoc/logic-formulas/issues/1) for this.
See the [formal grammar](https://github.com/jaschdoc/logic-formulas/blob/dbd1b6a5d48223b1e6e52fe4f1e342a5c52e90c6/src/main/scala/Ast.scala#L24) for more info.
