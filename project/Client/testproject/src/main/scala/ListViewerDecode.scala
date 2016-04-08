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
import scala.collection._
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import java.io._
import java.io.FileOutputStream
import android.util.Base64
import scala.util.Try
import scala.collection.mutable.ListBuffer

class ListViewerDecode extends Activity with helpers {

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
    	
	} catch {case e: Exception => {
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
		  val fn =  row.getString("fileName")
		  val key =  row.getString("keycode")
		  val eImage =  row.getString("imageEncode")
		  val noExtension = fn.substring(0, fn.lastIndexOf("."))
		  val i = new imageChanger
		   Log.d("MyTAG", key)
		// val newImageBytes = Base64.decode(eImage, Base64.URL_SAFE);
	 	// val bitmap = BitmapFactory.decodeByteArray(newImageBytes, 0, newImageBytes.length);
			
		  val hc = i.JsonToKey(key)
		   
			var Bits = new ListBuffer[Int]()
			eImage.foreach{x => if(x == '1') Bits += 1 else Bits +=0 }
			val hcode = (Bits.toList,hc)
			val h = new handler
			val de =h.decode2(1,"",hcode)
		     val newImageBytes = Base64.decode(de, Base64.URL_SAFE);
	 	    val bitmap = BitmapFactory.decodeByteArray(newImageBytes, 0, newImageBytes.length);
			
			val bmpimg = i.getResizedBitmap(bitmap, 540, 960)


			i.writeDecodedImage(bmpimg,noExtension)
		
      }
    })
  
  }
 
}