package my.android.project

import scala.io.Source
import java.io._
import java.io.FileOutputStream
import android.util.Log
import java.awt._
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


def test(s:String):Unit = { 
	
		val folder = new File("/sdcard/download/.encoded")
		if (!folder.exists()){
		
		folder.mkdir()
		
		}
		
		val Card = new File(s)
		Card.createNewFile()
		val fos = new FileOutputStream(Card)
		fos.write(s.getBytes())
		fos.close()
		
		
  
  }



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
   
  def writeBytes(fn:String,key:String)={
		 
		val folder = new File("/sdcard/encoded")
		if (!folder.exists()){
		
		folder.mkdir()
		
		}
		
		val Card = new File("/sdcard/encoded/" + fn)
		Card.createNewFile()
		val fos = new FileOutputStream(Card)
		fos.write(key.getBytes())
		fos.close()
		
		
	  
  }
  
  def writeImage(img:BufferedImage){
  
    
   val f = new File("C:\\Users\\Chris\\Desktop\\testImageTEST.png");
   ImageIO.write(img, "PNG", f);
  
  
  
  }
  
  def removeWord(s:String, word:String)= s filterNot (word contains _)
  
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