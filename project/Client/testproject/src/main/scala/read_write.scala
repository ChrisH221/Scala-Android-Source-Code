package my.android.project

import scala.io.Source
import java.io._
import java.io.FileOutputStream
import android.util.Log

import java.io._
import java.util.logging._
import javax.imageio.ImageIO
import javax.swing._
//remove if not needed
import scala.collection.JavaConversions._
import java.awt.image.BufferedImage

/**INCOMPLETE
 * @author Chris Howell
 *
 * The reader class handles all IO related to txt files, this includes reading and writing files.
 * Need to refactor to remove filepath input
 */
class read_write() extends handler with helpers {



  /*
   *Reads a file from the filePath
   *@returns Iterator[String]
   */
  def readFile(filePath:String):Iterator[String] ={

    val lines = Source.fromFile(filePath).getLines()
    lines
  }

	
  /*
   *Writes the encoded bytes to a text file
   *@returns Unit
   */
   
  def writeBytes(fn:String,bitList:List[Bit])={
		 
		val folder = new File("/sdcard/encoded")
		
		if (!folder.exists()){folder.mkdir()}
		
		val Card = new File("/sdcard/encoded/" + fn)
		Card.createNewFile()
		
		val fos = new FileOutputStream(Card)
		fos.write(prettyPrint(bitList).getBytes())
		fos.close()
		
		
	  
  }
  
  def writeImage(fn:String,path:String, writePath:String)={
   
  
	
	 val i = new imageChanger
	 
	 val encode = i.processEncode(path)
	 	
	// val l = List()
	// encode.foreach { x => l:+ x._1 }
	 
	 val folder = new File("/sdcard/encoded")
		
		if (!folder.exists()){folder.mkdir()}
		val noExtension = fn.substring(0, fn.lastIndexOf("."))
		val Card = new File("/sdcard/encoded/" + noExtension + ".txt")
		
		Card.createNewFile()
		
		val fos = new FileOutputStream(Card)
	   fos.write(prettyPrint(encode(0)._1.asInstanceOf[List[this.Bit]]).getBytes())
		fos.close()
		
  
  }
  
  
  def pMatch(b:Bit):String = b match{
  
  case b:One => "1"
  case b:Zero =>  "0"
  case b:EmptyBit =>  "-1"
   
  
  }
  
   
  
  def bMatch(s:String): Bit = s match{    
  
  case "1" => new One()
  case "0" => new Zero()
  case "-1" =>  new EmptyBit()
     
  }
  
  
  def MaptoString(key:List[(Char,List[Bit])]):String={
 
 var result = ""
 var a = 0
 for (a <- 0 to key.size-1){
 
 result = result + key(a)._1.toString + "/=/" + prettyPrint(key(a)._2) + "/n/"
  
  }
  result + "EOF"
  }
  

  
   def StringToMap(str:String,list:List[(Char,String)]):List[(Char,String)]={
   
   var lString = str
   var secString = ""
   var section = ""
  
  
   
    section = lString.take(3)
 	
  if(section != "EOF"){
   Log.d("MyTAG","1" + section)
 
	val key = lString.take(1)
	lString = lString.drop(1)
	
	section = lString.take(3)
		Log.d("MyTAG","1" + lString)
		
   if (section == "/=/"){
    lString = lString.drop(3)   
   while (section != "/n/"){
   
  
  
    section = lString.take(1)
   
    secString  =  secString ++ section
    Log.d("MyTAG","sec string"+secString)
   
   lString = lString.drop(1)
   
    section = lString.take(3)
   
    Log.d("MyTAG",section)
   
   }
   
    Log.d("MyTAG","break" + (key.toCharArray.head,secString))
  
   StringToMap(lString.drop(3), list:+(key.toCharArray.head,secString))
   }
   
   list
   
   }else{
	list
   }
 


 }
 

  
   
   
  

  def prettyPrint(bitList:List[Bit]):String={
  
	 var string = ""
  	 
     var a = 0
	 var nlCount = 0
	 for (a <- 0 to bitList.size-1){
	 nlCount = nlCount + 1
	 
	 if(nlCount >= 10){  

	// string = string ++ " /n "
	 nlCount = 0
	 }
	  var bitStr = pMatch(bitList(a))
	  
      string = string ++ bitStr
	       
      }
    string
  
  
  }
  
  def fromPretty(p:String):List[Bit]={
  
  
  val bitList = List(new One())
  bitList
  }
  


}