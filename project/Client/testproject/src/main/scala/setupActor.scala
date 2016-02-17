package my.android.project

import akka.actor.{Props, Actor, ActorSystem}
import android.util.Log
import com.typesafe.config.ConfigFactory
import java.io.File


class setupActor(){


	val configFile = getClass.getClassLoader.getResource("application.conf").getFile
	Log.d("MyTAG", configFile)
    val config = ConfigFactory.parseFile(new File(configFile))
    val system = ActorSystem("ClientSystem",config)
	//val localActor = system.actorOf(Props[LocalActor], name="local")
	val remoteActor = system.actorSelection("akka.tcp://HelloRemoteSystem@127.0.0.1:5150/user/remoteActor")
	println("That 's remote:" + remoteActor)
	Log.d("MyTAG","That 's remote:" + remoteActor)
	remoteActor ! "hi"

}