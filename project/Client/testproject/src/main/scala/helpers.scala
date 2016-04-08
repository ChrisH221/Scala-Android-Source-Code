package my.android.project

import scala.language.implicitConversions
import android.view.View
import java.io._
import scala.collection._
import spray.json._
import DefaultJsonProtocol._

trait helpers {



 val x = List()//Saved me creating multiple empty lists
 
  abstract class HTree
  case class Empty() extends HTree
  case class Branch(value: Int, left: HTree, right: HTree) extends HTree
  case class Leaf( char: Char, freq: Int) extends HTree

  /* 
   * Define an implicit definition for OnClickListener
   * 
   */ 
  
  /* 
   * Define a single bit as a sealed trait that can be either One, Zero or EmptyBit
   * 
   */
 

 
  implicit def toRunnable[A](f: => A): Runnable =  new Runnable() { def run() = f } 

 

  def getListOfSubDirectories(directoryName: String): Array[String] = {
	return (new File(directoryName)).listFiles.filter(_.isDirectory).map(_.getName)
	}

 def getListOfFiles(directoryName: String): Array[String] = {
	return (new File(directoryName)).listFiles.filter(_.isFile).map(_.getAbsolutePath)
	} 


}