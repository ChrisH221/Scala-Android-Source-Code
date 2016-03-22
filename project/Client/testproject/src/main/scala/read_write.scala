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
   
  
	Log.d("MyTAG", "writeimage start")
	 val i = new imageChanger
	 Log.d("MyTAG", path)
	 val encode = i.processEncode(path)
	 	 Log.d("MyTAG", "22")
	 val l = List()
	 encode.foreach { x => l:+ x._1 }
	 Log.d("MyTAG", "2")
	 val folder = new File(writePath)
		
		if (!folder.exists()){folder.mkdir()}
		
		val Card = new File(writePath + fn)
		Card.createNewFile()
		
		val fos = new FileOutputStream(Card)
		fos.write(prettyPrint(l.flatten).getBytes())
		fos.close()
		
  
  }
  
  
  def pMatch(b:Bit):String = b match{
  
  case b:One => "1"
  case b:Zero =>  "0"
  case b:EmptyBit =>  "-1"
   
  
  }

  def prettyPrint(bitList:List[Bit]):String={
  
	 var string = ""
  	 
     var a = 0
	 var nlCount = 0
	 for (a <- 0 to bitList.size-1){
	 nlCount = nlCount + 1
	 
	 if(nlCount >= 10){  

	 string = string ++ " /n "
	 nlCount = 0
	 }
	  var bitStr = pMatch(bitList(a))
	  
      string = string ++ bitStr
	       
      }
    string
  
  
  }
   /*INCOMPLETE
   *Writes the key to a text file
   *@returns Unit
   */
 // def createKeyFile(key:(List[Bit],List[(Char,List[Bit])])){

	//	val Card  = new File(removeWord(filePath, ".txt") ++ "_HCodeMap.txt")
	//	Card.createNewFile()
	//	val fos = new FileOutputStream(Card)
	//	fos.write(key._2.toString().getBytes())
		//fos.close()


 // }

   /* INCOMPLETE
   *Read the key from the file and parse it into HCodeMap
   *@returns Unit
   */  
 // def readKey(filePath:String):List[(Char,List[Bit])]={

//    val lines = Source.fromFile(filePath).getLines()
  //  lines.toList

  //}




}