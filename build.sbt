import sbt.Keys._

val sparkVersion = "1.6.0-cdh5.7.0"
val scalaVersionNumber: String = "2.10.6"
val scalaTestVersion = "3.0.0"
val typesafeConfigVersion = "1.3.1"

/**
  * working job
  */
lazy val sparkBlueprints: Project = (project in file("."))
  .settings(commonDependecies("sparkBlueprints"))
  .settings(libraryDependencies ++= addSparkDependencies("provided"))

/**
  * this is going to be used inside intelliJ IDEA
  */
lazy val sparkBlueprintRunner = project
  .in(file("sparkBlueprintsRunner"))
  .dependsOn(sparkBlueprints)
  .settings(commonDependecies("sparkBlueprintsRunner"))
  .settings(libraryDependencies ++= addSparkDependencies("compile"))

/**
  * add all common dependencies
  * @param moduleName
  * @return
  */
def commonDependecies(moduleName: String) = {

  Seq(
    organization := "it.jesinity.spark",
    name := moduleName,
    version := "1.0.0-SNAPSHOT",
    scalaVersion := scalaVersionNumber,
    javaOptions += "-Xms512m -Xmx2G",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % scalaTestVersion,
      "com.typesafe" % "config" % typesafeConfigVersion
    ),
    resolvers ++= Seq(
      Resolver.mavenLocal,
      "Cloudera CDH" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
    )
  )
}

/**
  * add all spark dependencies
  * @param scope
  * @return
  */
def addSparkDependencies(scope: String): Seq[ModuleID] = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % scope,
  "org.apache.spark" %% "spark-sql" % sparkVersion % scope,
  "org.apache.spark" %% "spark-streaming" % sparkVersion % scope,
  "org.apache.spark" %% "spark-mllib" % sparkVersion % scope
)
