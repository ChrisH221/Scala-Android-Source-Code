import android.Keys._

      android.Plugin.androidBuild

      name := "testProject"

      scalaVersion := "2.11.7"

      scalacOptions in Compile += "-feature"

      platformTarget in Android := "android-16"

      proguardCache in Android ++= Seq(
		ProguardCache("com.robotium.solo") % "com.jayway.android.robotium" % "robotium-solo"
      )
	  
	  apkbuildExcludes in Android += "reference.conf"

    proguardOptions in Android ++= Seq(
    "-dontobfuscate", 
    "-dontoptimize", 
    "-dontwarn scala.collection.mutable.**"
    )
	
	sourceDirectory in Test := baseDirectory.value / "src" / "test"
	
		
	resolvers += "spray repo" at "http://repo.spray.io"
	
    libraryDependencies ++= Seq(
                        "org.scalatest" %% "scalatest" % "2.2.6" ,
						"org.scala-lang" % "scala-reflect" % "2.11.6",
						"org.scala-lang.modules" %% "scala-xml" % "1.0.4",
						"io.spray" %%  "spray-json" % "1.3.2"			
						
						)

					
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

    // ProGuard rules for Akka
   proguardOptions in Android ++= Seq(
   "-keep class akka.actor.Actor$class { *; }",
   "-keep class akka.actor.LightArrayRevolverScheduler { *; }",
   "-keep class akka.actor.LocalActorRefProvider { *; }",
   "-keep class akka.actor.CreatorFunctionConsumer { *; }",
   "-keep class akka.actor.TypedCreatorFunctionConsumer { *; }",
   "-keep class      akka.dispatch.BoundedDequeBasedMessageQueueSemantics      {      *; }",
   "-keep class akka.dispatch.UnboundedMessageQueueSemantics { *; }",
    "-keep class akka.dispatch.UnboundedDequeBasedMessageQueueSemantics     { *; }",
    "-keep class akka.dispatch.DequeBasedMessageQueueSemantics { *; }",
    "-keep class akka.dispatch.MultipleConsumerSemantics { *; }",
    "-keep class akka.actor.LocalActorRefProvider$Guardian { *; }",
    "-keep class akka.actor.LocalActorRefProvider$SystemGuardian { *; }",
    "-keep class akka.dispatch.UnboundedMailbox { *; }",
    "-keep class akka.actor.DefaultSupervisorStrategy { *; }",
    "-keep class macroid.akka.AkkaAndroidLogger { *; }",
   "-keep class akka.event.Logging$LogExt { *; }"
   )        
   
   
   
   

   scalacOptions in Compile += "-feature"

   scalacOptions := Seq("-encoding", "utf8", "-target:jvm-1.6")

   javacOptions ++= Seq("-encoding", "utf8", "-source", "1.6", "-target", "1.6")

   // call install and run without having to prefix with android:
  run <<= run in Android

  install <<= install in Android

   retrieveManaged := true
   
   	sourceDirectory in Test := baseDirectory.value / "src" / "test"