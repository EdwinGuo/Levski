lazy val root = (project in file(".")).

settings(
  name := "delorean",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.6",
  crossScalaVersions := Seq("2.10.2", "2.10.3", "2.10.4", "2.10.5", "2.11.0", "2.11.1", "2.11.2", "2.11.3", "2.11.4", "2.11.5", "2.11.6"),
  addCompilerPlugin("org.scalamacros" % "paradise" % paradiseVersion cross CrossVersion.full)
)

val paradiseVersion = "2.1.0-M5"
val sparkVersion = "2.2.0"
val catsVersion = "0.9.0"
val jodaVersion = "2.9.9"
val sparkTestingVersion = "2.2.0_0.7.2"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % sparkVersion,
  "org.apache.spark" % "spark-sql_2.11" % sparkVersion,
  "org.typelevel" %% "cats" % catsVersion,
  "javax.mail" % "mail" % "1.5.0-b01",
  "joda-time" % "joda-time" % jodaVersion,
  "com.holdenkarau" % "spark-testing-base_2.11" % sparkTestingVersion
)
