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
import java.io.IOException
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
import java.awt.image.BufferedImage
import javax.imageio.ImageIO





/**
 * @author Chris Howell
 *
 *The ListViewer class implements a filesystem navigation for the user to
 *browse the filesystem and select a file for encoding.
 * 
 */
 
class ListViewer extends Activity with helpers {

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
	encode()
	}
  
  
  
  
  def encode(){
  
  
   setContentView(R.layout.a_main)
	val path = "/sdcard"
	val arr = getListOfFiles(path)
	//val arr = x.map (_.toString).toArray
	val dirarr = getListOfSubDirectories(path)
	val currentDirectory =  dirarr ++ arr
	
    val theAdapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, currentDirectory)
    val theListView = findViewById(R.id.theListView).asInstanceOf[ListView]
    
	theListView.setAdapter(theAdapter)
	
	theListView.setOnItemClickListener(new OnItemClickListener() {

      override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long) {
		 
		val x = currentDirectory(position)		 
		updateList(path, x)
      }
    })
  
  
  }
  
  def getListOfSubDirectories(directoryName: String): Array[String] = {
	return (new File(directoryName)).listFiles.filter(_.isDirectory).map(_.getName)
}

 def getListOfFiles(directoryName: String): Array[String] = {
	return (new File(directoryName)).listFiles.filter(_.isFile).map(_.getAbsolutePath)
} 
 
 def updateList(dir:String, curDir:String){
 
  setContentView(R.layout.a_main)
    val path = dir +"/" + curDir
	val x = getListOfFiles(path)
	val arr = x.map (_.toString).toArray
	val dirarr = getListOfSubDirectories(path)
	val currentDirectory = Array(".", "..") ++ dirarr ++ arr
	
    val theAdapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, currentDirectory)
    val theListView = findViewById(R.id.theListView).asInstanceOf[ListView]
    theListView.setAdapter(theAdapter)
	

  theListView.setOnItemClickListener(new OnItemClickListener() {

      override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long) {
		 
		val x = currentDirectory(position)
		
		val z = new File(x)

		
		if (z.isFile){
		
		
		val p = promise[String] 
	    val f = p. future 
	 
	 	 future { 
		
		val site = "http://monad.uk/addKey_scala.php"
		try {
		
	
		val inte = getIntent
		val username = inte.getExtras.getString("user")
		val url = new URL(site)
		val urlConn = url.openConnection()
		val httpConn = urlConn.asInstanceOf[HttpURLConnection]
	
		httpConn.setDoOutput(true)
		val os = httpConn.getOutputStream
		val rw = new read_write()
		val i = new imageChanger
		val key = i.processEncode(z.getAbsolutePath)	

       
       		
		//rw.writeImage(z.getName,z.getAbsolutePath,"/sdcard/encoded",key.asInstanceOf[(List[rw.Bit], List[(Char, List[rw.Bit])])])
				
					 

			//val list = List()
		var k = ""	
		key.foreach(x => k + rw.MaptoString(x._1.asInstanceOf[List[(List[Bit],List[(Char,List[Bit])])]]) )
		k = "BREAK" + "EOF"
		val POST_PARAMS = "username="+username.toString +"&key="+ k +"&fileName=" +z.getName.toString 
		
		os.write(POST_PARAMS.getBytes)
		val responseCode = httpConn.getResponseCode
		httpConn.connect()
	
      val input = httpConn.getInputStream
      val reader = new BufferedReader(new InputStreamReader(input))
      val result = new StringBuilder()
      var line: String = null
	  val str = Stream.continually(reader.readLine()).takeWhile(_ != null).mkString("\n")
	  	 
	  
	  
	 p success str 
    	
	} catch {
	    case e: Exception => {
		Log.d("MyTAG", e.toString)
        println("Error: " + e)
        e.printStackTrace()
        null
      }
    }	 
	 } 
	f onSuccess {  case result => runOnUiThread{updateList("/sdcard", "")}}
	
		}
		else if (x.equals(".")) updateList("/sdcard", "")
		else if(x.equals("..")) updateList(dir, "")
		else updateList(path, x)
      }
    })
 
 
 }
  
  
}




