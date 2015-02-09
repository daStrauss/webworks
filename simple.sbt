lazy val commonSettings = Seq(
  organization := "com.oheasytiger",
  version := "0.1.0",
  scalaVersion := "2.10.4",
  testOptions in Test += Tests.Argument("-oD"),
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "2.2.1" % "test"
    )
)

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(
    name := "webworks",
    libraryDependencies ++= {
      val akkaV = "2.3.9"
      val sprayV = "1.3.2"
      Seq(
        "io.spray"            %%  "spray-can"     % sprayV,
        "io.spray"            %%  "spray-routing" % sprayV,
        "io.spray"            %%  "spray-servlet" % sprayV,
        "io.spray"            %%  "spray-testkit" % sprayV  % "test",
        "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
        "com.typesafe.akka"   %%  "akka-testkit"  % akkaV   % "test"
      )
    },
    resolvers ++= Seq(
      "Spray Repository" at "http://repo.spray.cc/",
      "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    )
  )

Revolver.settings

tomcat()