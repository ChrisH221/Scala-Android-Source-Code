import android.Keys._

android.Plugin.androidBuild

name := "testProject"

scalaVersion := "2.11.7"

scalacOptions in Compile += "-feature"

platformTarget in Android := "android-16"

proguardCache in Android ++= Seq(
)

proguardOptions in Android ++= Seq("-dontobfuscate", "-dontoptimize", "-dontwarn scala.collection.mutable.**", "-dontwarn sun.misc.Unsafe",  "-keep class sun.misc.Unsafe{*;}",
"-keep class java.io.ObjectInputStream"

)

libraryDependencies ++= Seq(
                            "org.scalatest" %% "scalatest" % "2.2.6" % "test"
							)
							

scalacOptions in Compile += "-feature"

scalacOptions := Seq("-encoding", "utf8", "-target:jvm-1.7")

javacOptions ++= Seq("-encoding", "utf8", "-source", "1.7", "-target", "1.7")

// call install and run without having to prefix with android:
run <<= run in Android

install <<= install in Android

retrieveManaged := true