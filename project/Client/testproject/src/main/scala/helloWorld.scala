package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View


abstract class Message 
case class send(text:String) extends Message
case class received(text:String) extends Message

class helloWorld extends Activity with TypedActivity with helpers {
  override def onCreate(bundle: Bundle) {
    super.onCreate(bundle)
    setContentView(R.layout.main)
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
  }



}
