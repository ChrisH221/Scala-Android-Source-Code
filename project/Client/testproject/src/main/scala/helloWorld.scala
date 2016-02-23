package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
import akka.actor.{ActorSystem, ActorLogging, Actor, Props}
import com.typesafe.config.ConfigFactory
import java.io.File
import android.util.Log
import dispatch._, Defaults._



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
  
	// val system = ActorSystem("ClientSystem")
   // val uiAct = system.actorOf(Props(new actorUI))
	val t = new read_write("hey")
    val localActor = system.actorOf(Props[LocalActor])
	val h = new handler
//	val uiAct = system.actorOf(Props[actorUI])
	val editText = findView(TR.editTextResult)
	val button = findView(TR.button1)
	button.setOnClickListener((v : View) => {
	   val d = new db
	   d.query()
		val s = (h.encode(editText.getText().toString())._1)
		t.test(s mkString)
		//t.createKeyFile(h.encode("heyy"))
		localActor ! "hey"
		//uiAct ! "change"
	
    })
  }


class actorUI extends Actor {
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



