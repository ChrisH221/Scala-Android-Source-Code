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

/**
 * @author Chris Howell
 *
 *The ListViewer class 
 *
 * 
 */
 
class ListViewer extends Activity {

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
	return (new File(directoryName)).listFiles.filter(_.isFile).map(_.getName)
} 
  
//  def getListOfFiles(dir: String):List[File] = {
 // val d = new File(dir)
  //if (d.exists && d.isDirectory) {
   // d.listFiles.filter(_.isFile).toList
  //} else {
  //  List[File]()
  //}
//}
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
		if(new File(x).isFile){
		
		val rw = new read_write(x)
		val h = new handler()
		val text = h.encode(rw.readFile(x).mkString.toString)
	//	rw.writeBytes(x ,text)
		}
		Log.d("MyTAG",x)
		if (x.equals(".")) updateList("/sdcard", "")
		else if(x.equals("..")) updateList(dir, "")
		else updateList(path, x)
      }
    })
 
 
 }
  
  
}


