lazy val root = (project in file(".")).
settings(
        name := "delorean",
        version := "0.1.0-SNAPSHOT",
        scalaVersion := "2.11.6")

val sparkVersion = "2.2.0"
val catsVersion = "0.9.0"
val jodaVersion = "2.9.9"

libraryDependencies ++= Seq(
        "org.apache.spark" % "spark-core_2.11" % sparkVersion,
        "org.apache.spark" % "spark-sql_2.11" % sparkVersion,
        "org.typelevel" %% "cats" % catsVersion,
        "joda-time" % "joda-time" % jodaVersion
        )

