package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
import akka.actor.{ActorSystem, ActorLogging, Actor, Props}
import com.typesafe.config.ConfigFactory
import java.io.File
import android.util.Log



abstract class Message 
case class send(text:String) extends Message
case class received(text:String) extends Message

class helloWorld extends Activity with TypedActivity with helpers{
  override def onCreate(bundle: Bundle) {
  
    super.onCreate(bundle)
    setContentView(R.layout.main)

	val configFile = getClass.getClassLoader.getResource("application.conf").getFile
	
    val config = ConfigFactory.parseFile(new File(configFile))
    val system = ActorSystem("ClientSystem",config)
	
    val localActor = system.actorOf(Props[LocalActor])
	//val uiActor = system.actorOf(Props[actorM],"ui")
	val button = findView(TR.button1)
	button.setOnClickListener((v : View) => {
		
		findView(TR.textview).setText("")
		localActor ! "START"
		//uiActor ! "change"
	
    })
  }


class actorM extends Actor {
   def receive = {
	
	case "change" => changeUI()
	
      
   }
   
   }

def changeUI(){

	val h = new handler
	val editText = findView(TR.editTextResult)
	findView(TR.textview).setText(h.encode(editText.getText().toString())._1 mkString)

}

}
