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

class ListViewer extends Activity {

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.a_main)
	val x = getListOfFiles("/sdcard/download/encoded")
	val arr = x.map (_.toString).toArray
	val dirarr = getListOfSubDirectories("/sdcard/download")
	val currentDirectory = Array(".", "..") ++ dirarr ++ arr
	
    val theAdapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, currentDirectory)
    val theListView = findViewById(R.id.theListView).asInstanceOf[ListView]
    
	theListView.setAdapter(theAdapter)
	
	theListView.setOnItemClickListener(new OnItemClickListener() {

      override def onItemClick(parent: AdapterView[_], 
          view: View, 
          position: Int, 
          id: Long) {
       updateList("/sdcard/download")
      }
    })
	
	
	
	
  }
  
  def getListOfSubDirectories(directoryName: String): Array[String] = {
  return (new File(directoryName)).listFiles.filter(_.isDirectory).map(_.getName)
}

  
  
  def getListOfFiles(dir: String):List[File] = {
  val d = new File(dir)
  if (d.exists && d.isDirectory) {
    d.listFiles.filter(_.isFile).toList
  } else {
    List[File]()
  }
}


 def updateList(dir:String){
 
  setContentView(R.layout.a_main)
	val x = getListOfFiles(dir)
	val arr = x.map (_.toString).toArray
	val currentDirectory = Array(".", "..") ++ arr
	
    val theAdapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, currentDirectory)
    val theListView = findViewById(R.id.theListView).asInstanceOf[ListView]
    theListView.setAdapter(theAdapter)
 
 
 
 }
  
  
}


