import android.Keys._

      android.Plugin.androidBuild

      name := "testProject"

      scalaVersion := "2.11.7"

      scalacOptions in Compile += "-feature"

      platformTarget in Android := "android-23"

      proguardCache in Android ++= Seq(
		ProguardCache("com.robotium.solo") % "com.jayway.android.robotium" % "robotium-solo"
      )
	  
	  apkbuildExcludes in Android += "reference.conf"

    proguardOptions in Android ++= Seq(
    "-dontobfuscate", 
    "-dontoptimize", 
    "-dontwarn scala.collection.mutable.**"
    )
	
		
		
	resolvers += "spray repo" at "http://repo.spray.io"
	
    libraryDependencies ++= Seq(
						"io.spray" %%  "spray-json" % "1.3.2"												
						)
fork in Test := true
					
     proguardScala in Android := true

      // Generic ProGuard rules
     proguardOptions in Android ++= Seq(
     "-ignorewarnings",
     "-keep class scala.Dynamic",
	 "-dontobfuscate",
	 "-dontpreverify",
	 "-dontskipnonpubliclibraryclassmembers",
	 "-dontskipnonpubliclibraryclasses",
	 "-dontnote **",
	 "-dontwarn **",
	 "-verbose",
	 "-keep class * extends android.os.AsyncTask {*;}"
      )

     
   

   scalacOptions in Compile += "-feature"

   scalacOptions := Seq("-encoding", "utf8", "-target:jvm-1.6")

   javacOptions ++= Seq("-encoding", "utf8", "-source", "1.6", "-target", "1.6")

   // call install and run without having to prefix with android:
  run <<= run in Android

  install <<= install in Android

   retrieveManaged := true
   
   	sourceDirectory in Test := baseDirectory.value / "tests" / "src"