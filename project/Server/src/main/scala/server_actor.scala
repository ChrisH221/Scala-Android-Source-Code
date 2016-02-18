import akka.actor.{ActorSystem, ActorLogging, Actor, Props}
import com.typesafe.config.ConfigFactory
import java.io.File

class ServerActor1 extends Actor {
  override def receive: Receive = {
    case msg: String => {
      println("remote received " + msg + " from " + sender)
    
    }
    case _ => println("Received unknown msg ")
  }
}

object main extends App {

  val configFile = getClass.getClassLoader.getResource("application.conf").getFile
	
  val config = ConfigFactory.parseFile(new File(configFile))

  val system = ActorSystem("server-akka", config)

  val sv1 = system.actorOf(Props(new ServerActor1), "sv1")

  sv1 ! "Can you hear me?"
  

  
}