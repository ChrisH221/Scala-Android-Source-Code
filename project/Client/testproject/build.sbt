import android.Keys._

      android.Plugin.androidBuild

      name := "testProject"

      scalaVersion := "2.11.4"

      scalacOptions in Compile += "-feature"

      platformTarget in Android := "android-16"

      proguardCache in Android ++= Seq(

      )
	apkbuildExcludes in Android += "reference.conf"
    proguardOptions in Android ++= Seq(
    "-dontobfuscate", 
    "-dontoptimize", 
    "-dontwarn scala.collection.mutable.**"
    )
	resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
    libraryDependencies ++= Seq(
                        "org.scalatest" %% "scalatest" % "2.2.6" % "test",
                        "com.typesafe.akka" %% "akka-actor" % "2.3.6",
						"com.typesafe.akka" %% "akka-remote" % "2.3.6",
						"com.typesafe" % "config" % "1.2.0",
						"net.databinder.dispatch" %% "dispatch-core" % "0.11.2")


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
	"-keep class akka.actor.RemoteActorRefProvider$Guardian { *; }",
    "-keep class akka.actor.LocalActorRefProvider$SystemGuardian { *; }",
	    "-keep class akka.actor.RemoteActorRefProvider$SystemGuardian { *; }",
    "-keep class akka.dispatch.UnboundedMailbox { *; }",
    "-keep class akka.actor.DefaultSupervisorStrategy { *; }",
    "-keep class macroid.akka.AkkaAndroidLogger { *; }",
   "-keep class akka.event.Logging$LogExt { *; }",
   "-dontwarn org.jboss.netty.logging.**",
"-dontwarn org.osgi.**",
"-dontwarn javax.servlet.**",
"-dontwarn org.jboss.netty.channel.socket.http.**",
"-dontwarn sun.misc.Unsafe",
"-keep class sun.misc.Unsafe{*;}",
"-keep public class akka.actor.LocalActorRefProvider {public <init>(...);}",
"-keep public class akka.remote.RemoteActorRefProvider {public <init>(...);}",
"-keep class akka.actor.SerializedActorRef {*;}",
"-keep class akka.remote.netty.NettyRemoteTransport {*;}",
"-keep class akka.serialization.JavaSerializer {*;}",
"-keep class akka.serialization.ProtobufSerializer {*;}",
"-keep class com.google.protobuf.GeneratedMessage {*;}",
"-keep class akka.event.Logging*",
"-keep class akka.event.Logging$LogExt{*;}",
"-dontwarn org.jboss.netty.**",
"-keep class org.apache.commons.logging.* {*;}",
"-keep class java.util.concurrent.atomic.AtomicReferenceFieldUpdater {*;}",
"-keep class java.util.concurrent.atomic.AtomicReferenceFieldUpdaterImpl{*;}",
"-keep class org.jboss.netty.channel.DefaultChannelPipeline{volatile <fields>;}",
"-keep class org.jboss.netty.util.internal.QueueFactory{static <fields>;}",
"-keepclassmembernames class org.jboss.netty.util.internal.**{*;}"
   )        
   
   
   

   scalacOptions in Compile += "-feature"

   scalacOptions := Seq("-encoding", "utf8", "-target:jvm-1.6")

   javacOptions ++= Seq("-encoding", "utf8", "-source", "1.6", "-target", "1.6")

   // call install and run without having to prefix with android:
  run <<= run in Android

  install <<= install in Android

   retrieveManaged := true