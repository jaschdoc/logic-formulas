name := "logic-formulas"

version := "0.1"

scalaVersion := "2.13.6"

idePackagePrefix := Some("io.jaschdoc")
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.9" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"

Test / logBuffered := false
