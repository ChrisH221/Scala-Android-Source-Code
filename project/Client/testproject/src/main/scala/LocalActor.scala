package my.android.project

import akka.actor.{Props, Actor, ActorSystem}
import android.util.Log

class LocalActor extends Actor {

 
  val remote = context.actorFor("akka.tcp://server-akka@127.0.0.1:5150/user/sv1")
  Log.d("MyTAG","1:" + remote)

  def receive = {
    
    case msg: String => 
		println("hey" + remote)
		Log.d("MyTAG","2" + remote)
        remote ! "Hello from the LocalActor"
    
        
  }
}