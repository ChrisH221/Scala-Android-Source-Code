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
	val l = login()
	
	
  }
  
  
def login(){

 setContentView(R.layout.check)


}
def setupMain(){

	// val system = ActorSystem("ClientSystem")
   // val uiAct = system.actorOf(Props(new actorUI))
	val t = new read_write("hey")
    //val localActor = system.actorOf(Props[LocalActor])
	val h = new handler
//	val uiAct = system.actorOf(Props[actorUI])
	val editText = findView(TR.editTextResult)
	val button = findView(TR.button1)
	button.setOnClickListener((v : View) => {
	   
	 
	
    })

}

}



