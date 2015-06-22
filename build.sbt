enablePlugins(ScalaJSPlugin)

name := "TasteSpoon Scala.js binding sample"

scalaVersion := "2.11.6"

scalaJSStage in Global := FastOptStage

libraryDependencies += "com.lihaoyi" %%% "utest" % "0.3.1" % "test"

testFrameworks += new TestFramework("utest.runner.Framework")
