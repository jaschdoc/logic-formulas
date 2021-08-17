name := "logic-formulas"

version := "0.1"

scalaVersion := "2.13.6"

lazy val packagePrefix = "io.jaschdoc"
idePackagePrefix.withRank(KeyRanks.Invisible) := Some(packagePrefix)
libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.0.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.9" % "test"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"

resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"

Test / logBuffered := false
