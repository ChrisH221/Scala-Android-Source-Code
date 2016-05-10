package my.android.project

import scala.language.implicitConversions
import android.view.View
import java.io._
import scala.collection._
import spray.json._
import DefaultJsonProtocol._
import android.view.View

trait helpers {
  
    val x = List()
	
            
   /* Code copied from function onClickListener 
    * obtained from http://blog.andresteingress.com/2011/09/20/programming-android-with-scala/ 
	*/   
	implicit  def onClickListener(f: (View => Unit)): View.OnClickListener = {
		new View.OnClickListener() {
				override def onClick(v: View) {
			f(v)
			}
		}

		}
		
		
    
    abstract class HTree
    case class Empty() extends HTree
    case class Branch(value: Int, left: HTree, right: HTree) extends HTree
    case class Leaf( char: Char, freq: Int) extends HTree
       
	 
    /* Code copied from function funToRunnable
    * obtained from  http://stackoverflow.com/questions/3073677/implicit-conversion-to-runnable 
	*/  
    implicit def Runnable[A](f: => A): Runnable =  new Runnable() { def run() = f }
    
   
    /* Code copied from function getListOfSubDirectories
    * obtained from  http://alvinalexander.com/scala/scala-function-list-subdirectories-in-directory 
	*/  
    def getListOfSubDirectories(directoryName: String): Array[String] = {
         return (new File(directoryName)).listFiles.filter(_.isDirectory).map(_.getName)
    }
    
	/* Code modified from getListOfSubDirectories
    * obtained from    http://alvinalexander.com/scala/scala-function-list-subdirectories-in-directory 
	*/  	
    def getListOfFiles(directoryName: String): Array[String] = {
        (new File(directoryName)).listFiles.filter{ f => f.isFile && (f.getName.endsWith(".jpeg")||f.getName.endsWith(".jpg")|| f.getName.endsWith(".png") || f.getName.endsWith(".txt")) }.map(_.getAbsolutePath)
    }
    
    
}