package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
import akka.actor.{ActorSystem, ActorLogging, Actor, Props}

abstract class Message 
case class send(text:String) extends Message
case class received(text:String) extends Message

class helloWorld extends Activity with TypedActivity with helpers{
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)
	val x = new send("hello did you get my message")
	val system = ActorSystem("client-akka")
	val button = findView(TR.button1)
	button.setOnClickListener((v : View) => {
	
	//val r = new read_write("data/newFile.txt") 
	//r.test("hey")
	findView(TR.textview).setText("")
	val h = new handler
	//val r = new read_write("hey")
	//val result = r.test("hi")
	val editText = findView(TR.editTextResult)
	findView(TR.textview).setText(h.encode(editText.getText().toString())._1 mkString)

    })
	//Button = (Button) findViewById(R.id.button1);
	//val y = new received("hello did you get my message")
//	findView(TR.textview).setText(add1(x))
	//findView(TR.textview2).setText(add1(y))
  }





}
