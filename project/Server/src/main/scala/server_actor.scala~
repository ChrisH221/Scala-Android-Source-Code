import akka.actor.{ActorSystem, ActorLogging, Actor, Props}

class ServerActor1 extends Actor with ActorLogging {
  def receive = {
    case value:String => println("Hello Android")
    case onFailure => println("Not sure what you mean")
  }
}

object main extends App {
  val system = ActorSystem("server-akka")

  val sv1 = system.actorOf(Props(new ServerActor1), "sv1")

  sv1 ! "Can you hear me?"
  

  system.shutdown()
}
