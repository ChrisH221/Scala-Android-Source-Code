package my.android.project;

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.util.Log
import scala.io.Source
import java.io.File
import scala.collection.JavaConversions._
import android.widget.AdapterView.OnItemClickListener
import android.os.AsyncTask
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import scala.util.Success
import scala.concurrent.{ future, promise }
import scala.concurrent.Future
import scala.concurrent.ExecutionContext._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.language.implicitConversions
import scala.collection.mutable.ArrayBuffer
import android.util.Log

class ListViewerDecode extends Activity with helpers {

	
	case class keyCode(list: List[(Char,List[Bit])])
		
  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
	
	setContentView(R.layout.a_main2)
	 
	 val p = promise[JSONArray] 
	 val f = p. future 
	 
    future { 
	 
	        val site = "http://monad.uk/getfiles_scala.php"
		try {
			val inte = getIntent
			val username = inte.getExtras.getString("user")
			val url = new URL(site)
			val urlConn = url.openConnection()
			val httpConn = urlConn.asInstanceOf[HttpURLConnection]
			httpConn.setDoOutput(true)
			val os = httpConn.getOutputStream
			val POST_PARAMS = "username="+username+""
			os.write(POST_PARAMS.getBytes)
			val responseCode = httpConn.getResponseCode
			httpConn.connect()
	
			val input = httpConn.getInputStream
			val reader = new BufferedReader(new InputStreamReader(input))
			val result = new StringBuilder()
			var line: String = null
			val str = Stream.continually(reader.readLine()).takeWhile(_ != null).mkString("\n")
			val j = new JSONArray(str)	 
	  
	  
	 p success j 
    	
	} catch {
			case e: Exception => {
			println("Error: " + e)
			e.printStackTrace()
			null
      }
    }	 
	 } 
	f onSuccess {  case result =>  runOnUiThread{decode(result)}}
  }

  

  def decode(j:JSONArray){
    
	
	
	setContentView(R.layout.a_main2)
		var ar = ArrayBuffer[String]()
	   
    for (i <- 0 until j.length) {
      val row = j.getJSONObject(i)
      var key = row.getString("fileName")
	
	 ar += key
	 
    }	
	

    val theAdapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, ar)
    val theListView = findViewById(R.id.theListView).asInstanceOf[ListView]
    
	theListView.setAdapter(theAdapter)
	
	theListView.setOnItemClickListener(new OnItemClickListener() {

      override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long) {
	  
		  val row = j.getJSONObject(position)
		  var key = row.getString("keycode")
		 
					
	//	val code = new keyCode(key)
		//  val keyMap = upickle.default.read[keyCode](key)
		  val rw = new read_write()
		  val map = rw.StringToMap(key, x)
		  Log.d("MyTAG", "the lst map" + map.toString)
		  
		  
		  
	
      }
    })
  
  }
 
}