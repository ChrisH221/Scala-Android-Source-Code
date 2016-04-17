package my.android.project

import _root_.android.app.Activity
import _root_.android.os.Bundle
import scala.language.implicitConversions
import android.view.View
import java.io.File
import android.util.Log
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView





class main extends Activity with TypedActivity with helpers{


	
  implicit def onClickListener(f: (View => Unit)): View.OnClickListener = {
		new View.OnClickListener() {
				override def onClick(v: View) {
			f(v)
			}
		}
	}

  override def onCreate(bundle: Bundle) {
  
    super.onCreate(bundle)
	 choose()
	
	
  }
  
  
 
def choose(){

 setContentView(R.layout.user)

 val button1 = findView(TR.encode)
	button1.setOnClickListener((v : View) => {
	   
	  	 var intent= new Intent (this,classOf[ListViewer])
		 val inte = getIntent
		 val username = inte.getExtras.getString("user")
		 
		 intent.putExtra("user", username)
	
	 startActivity(intent)
	
    })

	
	val button2 = findView(TR.decode)
	button2.setOnClickListener((v : View) => {
		val inte = getIntent
		val username = inte.getExtras.getString("user")
		Log.d("MyTAG","2" + username)
			
	  	 var intent= new Intent (this,classOf[ListViewerDecode])
		 intent.putExtra("user", username)
	 startActivity(intent)

	
    })

 

}


def setupMain(){

	
	val editText = findView(TR.editTextResult)
	val button = findView(TR.button1)
	button.setOnClickListener((v : View) => {
	   
	 
	
    })

}

}



