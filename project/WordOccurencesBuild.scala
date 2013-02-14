import sbt._
import sbt.Keys._

object WordOccurencesBuild extends Build {

  lazy val wordOccurences = Project(
    id = "word-occurences",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "Word Occurences",
      organization := "info.schleichardt",
      version := "1.0-SNAPSHOT",
      scalaVersion := "2.10.0",
      resolvers += "schleichardts Github" at "http://schleichardt.github.com/jvmrepo/"
      // add other settings here
    ) ++ seq(com.github.retronym.SbtOneJar.oneJarSettings: _*)
  )
}
