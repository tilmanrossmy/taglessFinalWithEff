name := "taglessFinalWithEff"

version := "1.0"

scalaVersion := "2.12.0"


libraryDependencies += "org.atnos" %% "eff" % "3.0.4"
libraryDependencies +="org.atnos" %% "eff-scalaz" % "3.0.4"

// to write types like Reader[String, ?]
addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.3")

// to get types like Reader[String, ?] (with more than one type parameter) correctly inferred for scala 2.11 < 2.11.9
// you can use the [Typelevel Scala compiler](http://typelevel.org/scala)
scalaOrganization in ThisBuild := "org.typelevel"

// to get types like Reader[String, ?] (with more than one type parameter) correctly inferred for scala 2.12.x
scalacOptions += "-Ypartial-unification"


    
