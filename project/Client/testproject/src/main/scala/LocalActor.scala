package my.android.project

import akka.actor.{Props, Actor, ActorSystem}
import android.util.Log

class LocalActor extends Actor {

 
  val remote = context.system.actorSelection("akka.tcp://server-akka@127.0.0.1:5150/user/sv1")
  var counter = 0

  def receive = {
    
    case msg: String => 
		println("hey" + remote)
		Log.d("MyTAG","That 's remote:" + remote)
        remote ! "Hello from the LocalActor"
    
        
  }
}