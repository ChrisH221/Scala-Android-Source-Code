import akka.actor.{ActorSystem, ActorLogging, Actor, Props}

class ServerActor1 extends Actor {
  override def receive: Receive = {
    case msg: String => {
      println("remote received " + msg + " from " + sender)
    
    }
    case _ => println("Received unknown msg ")
  }
}

object main extends App {

  val system = ActorSystem("server-akka")

  val sv1 = system.actorOf(Props(new ServerActor1), "sv1")

  sv1 ! "Can you hear me?"
  

  
}