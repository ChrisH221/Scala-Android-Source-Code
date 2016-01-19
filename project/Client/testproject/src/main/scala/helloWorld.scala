package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
import akka.actor.{ActorSystem, ActorLogging, Actor, Props}

abstract class Message 
case class send(text:String) extends Message
case class received(text:String) extends Message

class helloWorld extends Activity with TypedActivity {
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)
	val x = new send("hello did you get my message")
//	setupActor()
	//Button = (Button) findViewById(R.id.button1);
	//val y = new received("hello did you get my message")
//	findView(TR.textview).setText(add1(x))
	//findView(TR.textview2).setText(add1(y))
  }

def add1(m:Message):String = m match{

case send(t) => "Hello!!!"
case received(t) => "Hello again"


}



implicit def onClickListener(f: (View => Unit)): View.OnClickListener = {
  new View.OnClickListener() {
    override def onClick(v: View) {
      f(v)
    }
  }
}

def setupActor():Unit={

val system = ActorSystem("client-akka")

//val sv1 = system.actorOf(Props(new actorMain), "sv1")

}


}
