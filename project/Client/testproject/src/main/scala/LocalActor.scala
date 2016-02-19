package my.android.project

import akka.actor.{Props, Actor, ActorSystem}
import android.util.Log

class LocalActor extends Actor {

 
  val remote = context.actorSelection("akka.tcp://server-akka@192.168.1.67:5150/user/sv1")
  Log.d("MyTAG","1:" + remote)

  def receive = {
    
    case msg: String => 
		println("hey" + remote)
		Log.d("MyTAG","2" + remote)
        remote ! "Hello from the LocalActor"
    
        
  }
}