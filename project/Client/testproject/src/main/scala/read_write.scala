package my.android.project

import scala.io.Source
import java.io._
import java.io.File
import java.io.FileOutputStream
import android.util.Log

/**INCOMPLETE
 * @author Chris Howell
 *
 * The reader class handles all IO related to txt files, this includes reading and writing files.
 */
class read_write(filePath:String) extends handler {


def test(s:String):Unit = { 
	
		val folder = new File("/sdcard/download/encoded")
		if (!folder.exists()){
		
		folder.mkdir()
		
		}
		
		val Card = new File("/sdcard/download/encoded/hello_file.txt")
		Card.createNewFile()
		val fos = new FileOutputStream(Card)
		fos.write(s.getBytes())
		fos.close()
		
		
		Log.d("MyTAG","2" + s.getBytes())
  
  }



  /*
   *Reads a file from the filePath
   *@returns Iterator[String]
   */
  def readFile():Iterator[String] ={

    val lines = Source.fromFile(filePath).getLines()
    lines
  }

	
  /*
   *Writes the encoded bytes to a text file
   *@returns Unit
   */
   
  def writeBytes(key:(List[Bit],List[(Char,List[Bit])])) ={
		 
		val folder = new File(filePath)
		if (!folder.exists()){
		
		folder.mkdir()
		
		}
		
		val Card = new File("/sdcard/download/encoded/hello_file.txt")
		Card.createNewFile()
		val fos = new FileOutputStream(Card)
		fos.write(key._1.toString().getBytes())
		fos.close()
		
	  
  }
  
   /*INCOMPLETE
   *Writes the key to a text file
   *@returns Unit
   */
  def createKeyFile(key:(List[Bit],List[(Char,List[Bit])])){

		val file = new File(filePath ++ "HCodeMap.txt")
		val bw = new BufferedWriter(new FileWriter(file))
		bw.write(key._2.toString())
		bw.close()


  }

   /* INCOMPLETE
   *Read the key from the file and parse it into HCodeMap
   *@returns Unit
   */  
 // def readKey(filePath:String):List[(Char,List[Bit])]={

//    val lines = Source.fromFile(filePath).getLines()
  //  lines.toList

  //}




}