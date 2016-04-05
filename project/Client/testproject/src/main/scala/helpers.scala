package my.android.project

import scala.language.implicitConversions
import android.view.View
import _root_.android.view.View.OnClickListener
import java.io._


trait helpers {


  implicit def toRunnable[A](f: => A): Runnable =  new Runnable() { def run() = f } 

  def getListOfSubDirectories(directoryName: String): Array[String] = {
	return (new File(directoryName)).listFiles.filter(_.isDirectory).map(_.getName)
	}

 def getListOfFiles(directoryName: String): Array[String] = {
	return (new File(directoryName)).listFiles.filter(_.isFile).map(_.getAbsolutePath)
	} 


}