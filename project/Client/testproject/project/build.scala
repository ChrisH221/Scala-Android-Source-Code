import sbt._
import Keys._

object MyBuild extends Build {
  
  lazy val root = Project(id = "root", base = file("."))

 lazy val tests = project
      .settings(
	    scalaVersion := "2.11.7",

      scalacOptions in Compile += "-feature",
 fork in Test := true ,
     
          libraryDependencies += "org.scalatest" %% "scalatest" % "3.0-rc2"% "test",
		  libraryDependencies += "com.geteit" %% "robotest" % "0.12"% "test"
		  ).dependsOn("root")

}
