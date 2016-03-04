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

class ListViewer extends Activity {

  protected override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.a_main)
	val x = getListOfFiles("/sdcard/download")
	val favoriteTVShows = x.map (_.toString).toArray
	
    val theAdapter = new ArrayAdapter[String](this, android.R.layout.simple_list_item_1, favoriteTVShows)
    val theListView = findViewById(R.id.theListView).asInstanceOf[ListView]
    theListView.setAdapter(theAdapter)
  }
  
  
  def getListOfFiles(dir: String):List[File] = {
  val d = new File(dir)
  if (d.exists && d.isDirectory) {
    d.listFiles.filter(_.isFile).toList
  } else {
    List[File]()
  }
}
  
  
}
