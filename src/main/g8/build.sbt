import play.sbt.routes.RoutesKeys
import sbt._

name := "$name$"
version := "0.0.1"
scalaVersion := "2.12.7"

lazy val root = (project in file("."))
  .settings()
  .enablePlugins(PlayScala)

libraryDependencies ++= Seq(
  guice,
  ehcache,
  filters
)
resolvers ++= Dependencies.resolvers
libraryDependencies ++= Dependencies.deps

routesGenerator := InjectedRoutesGenerator
RoutesKeys.routesImport -= "controllers.Assets.Asset"

unmanagedResourceDirectories in Assets += baseDirectory.value / "app-ui" / "app" / "components" / "templates"

scalacOptions ++= Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-feature", // Emit warning and location for usages of features that should be imported explicitly.
  "-unchecked", // Enable additional warnings where generated code depends on assumptions.
  // disable fatal warnings for the moment because of https://github.com/playframework/playframework/issues/7382
  // "-Xfatal-warnings", // Fail the compilation if there are any warnings.
  "-Xlint", // Enable recommended additional warnings.
  "-Ywarn-adapted-args", // Warn if an argument list is modified to match the receiver.
  "-Ywarn-dead-code", // Warn when dead code is identified.
  "-Ywarn-inaccessible", // Warn about inaccessible types in method signatures.
  "-Ywarn-nullary-override", // Warn when non-nullary overrides nullary, e.g. def foo() over def foo.
  "-Ywarn-numeric-widen", // Warn when numerics are widened.
  "-Ywarn-unused:-imports,_" // Play generates a routes with unused imports
)

PlayKeys.playRunHooks += baseDirectory.map(DockerHook).value

enablePlugins(ScalikejdbcPlugin)
