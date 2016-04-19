import sbt._
import Keys._

object MyBuild extends Build {
  
  lazy val root = project.in(file("."))

 lazy val tests = project
      .settings(
          libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6"% "test",
		  libraryDependencies += "com.geteit" %% "robotest" % "0.12"% "test"
		  ).aggregate(root)

}
