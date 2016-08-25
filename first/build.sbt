name := """first"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.typesafe.slick" %% "slick" % "3.1.0-RC2",
  "org.xerial" % "sqlite-jdbc" % "3.8.11.2",
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "org.slf4j" % "slf4j-nop" % "1.7.10"
)

