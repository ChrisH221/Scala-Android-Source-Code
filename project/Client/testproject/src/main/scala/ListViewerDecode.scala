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
import scala.concurrent.ExecutionContext._
import scala.concurrent.ExecutionContext.Implicits.global
import android.content.Context

import android.util.Log

class ListViewerDecode extends Activity {

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
	
	setContentView(R.layout.a_main2)
	 val p = promise[String] 
	 val f = p. future 
	 
	 future { 
	 
	 val site = "http://52.18.108.189/getquestion.php"
    try {
      val url = new URL(site)
      val urlConn = url.openConnection()
      val httpConn = urlConn.asInstanceOf[HttpURLConnection]
      httpConn.setRequestMethod("GET")
      httpConn.connect()
      httpConn.getResponseCode
      val input = httpConn.getInputStream
      val reader = new BufferedReader(new InputStreamReader(input))
      val result = new StringBuilder()
      var line: String = null
	  val str = Stream.continually(reader.readLine()).takeWhile(_ != null).mkString("\n")
	 	 
	 p success str 
    } catch {
	    case e: Exception => {
        println("Error: " + e)
        e.printStackTrace()
        null
      }
    }	 
	 } 
	f onSuccess {  case str => decode(str)}

	

  }

  


	
	
  
  def decode(str:String){
    
    
	
	Log.d("MyTAG","0")
    val j = new JSONArray(str)
	Log.d("MyTAG","1")
	
	
	val json_obj = j.getJSONObject(0)
	Log.d("MyTAG","4")
    val question = json_obj.getString("question")
	Log.d("MyTAG","2")
	val ar =  Array(question, " hey")
	 
	
	setContentView(R.layout.a_main2)
	Log.d("MyTAG","hey,hey")
    val theAdapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, ar)
    val theListView = findViewById(R.id.theListView).asInstanceOf[ListView]
    
	theListView.setAdapter(theAdapter)
	
	theListView.setOnItemClickListener(new OnItemClickListener() {

      override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long) {
		 
	
      }
    })
  
  
  }



 def updateList(dir:String, curDir:String){
 
  
 }  

 
}
  




