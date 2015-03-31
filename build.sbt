name := "lassie-sim"

version := "0.1"

scalaVersion := "2.11.6"

resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("releases"),
  "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
)

libraryDependencies ++= Seq(
  //"org.scalaz" %% "scalaz-core" % "7.1.1",
  "org.apache.pdfbox" % "pdfbox" % "1.8.8"
)
