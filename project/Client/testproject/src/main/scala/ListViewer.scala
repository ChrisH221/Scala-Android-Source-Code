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
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.File
import javax.imageio.ImageIO
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64






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
		
	try {
      val image = ImageIO.read(new File(z.getAbsolutePath))
      val baos = new ByteArrayOutputStream()
      ImageIO.write(image, "png", baos)
      val res = baos.toByteArray()
      val encodedImage = Base64.encode(baos.toByteArray())
	  Log.d("MyTAG","2" + encodedImage.toString)
    } catch {
      case e: Exception => e.printStackTrace()
    }
		
	//	val rw = new read_write(x)
		//val h = new handler()
		//val text = h.encode(rw.readFile(x).mkString.toString)
		//rw.writeBytes(x ,text)
		}
		else if (x.equals(".")) updateList("/sdcard", "")
		else if(x.equals("..")) updateList(dir, "")
		else updateList(path, x)
      }
    })
 
 
 }
  
  
}




