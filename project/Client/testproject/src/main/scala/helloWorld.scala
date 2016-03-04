package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
import akka.actor.{ActorSystem, ActorLogging, Actor, Props}
import com.typesafe.config.ConfigFactory
import java.io.File
import android.util.Log
import android.content.Intent
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;



abstract class Message 
case class send(text:String) extends Message
case class received(text:String) extends Message

class helloWorld extends Activity with TypedActivity with helpers{
  override def onCreate(bundle: Bundle) {
  
    super.onCreate(bundle)
	//var f = new FileChooser(this)
	

    val l = login()
	
	
  }
  
  
 
  
def login(){

 setContentView(R.layout.check)
 
	val button = findView(TR.submit)
	button.setOnClickListener((v : View) => {
	   
	  choose()
	
    })

}

def choose(){

 setContentView(R.layout.user)

 val button1 = findView(TR.encode)
	button1.setOnClickListener((v : View) => {
	   
	  	 var intent= new Intent (this,classOf[ListViewer])
	 startActivity(intent)
	
    })

	
	val button2 = findView(TR.decode)
	button2.setOnClickListener((v : View) => {
	  	 var intent= new Intent (this,classOf[ListViewer])
	 startActivity(intent)

	
    })

 

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



