package my.android.project

import scala.language.implicitConversions
import android.view.View
import java.io._
import scala.collection._
import spray.json._
import DefaultJsonProtocol._
import android.util.Log

trait helpers {
  
    val x = List()//Saved me creating multiple empty lists
    
    abstract class HTree
    case class Empty() extends HTree
    case class Branch(value: Int, left: HTree, right: HTree) extends HTree
    case class Leaf( char: Char, freq: Int) extends HTree
       
    
    implicit def Runnable[A](f: => A): Runnable =  new Runnable() { def run() = f }
    
    
    
    def getListOfSubDirectories(directoryName: String): Array[String] = {
        return (new File(directoryName)).listFiles.filter(_.isDirectory).map(_.getName)
    }
    
    def getListOfFiles(directoryName: String): Array[String] = {
        (new File(directoryName)).listFiles.filter{ f => f.isFile && (f.getName.endsWith(".jpeg")||f.getName.endsWith(".jpg")|| f.getName.endsWith(".png") || f.getName.endsWith(".txt")) }.map(_.getAbsolutePath)
    }
    
    
}