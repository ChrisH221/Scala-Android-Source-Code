package my.android.project

import scala.io.Source
import java.io._
import java.io.File
import java.io.FileOutputStream

/**INCOMPLETE
 * @author Chris Howell
 *
 * The reader class handles all IO related to txt files, this includes reading and writing files.
 */
class read_write(filePath:String) extends handler {

  /*
   *Reads a file from the filePath
   */
  def readFile():Iterator[String] ={

    val lines = Source.fromFile(filePath).getLines()
    lines
  }

  def test(s:String):String = { 
	
		val file = new File("/users/mkyong/filename.txt")
		val bw = new BufferedWriter(new FileWriter(file))
		bw.write("Praise")
		"Print Successful"
  
  }
  
  def writeBytes(key:(List[Bit],List[(Char,List[Bit])])) ={

    val file = new File(filePath)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(key._1.toString())
    bw.close()

  }

  def createKeyFile(key:(List[Bit],List[(Char,List[Bit])])){

    val file = new File(filePath ++ "HCodeMap.txt")
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(key._2.toString())
    bw.close()


  }

 // def readKey(filePath:String):List[(Char,List[Bit])]={

//    val lines = Source.fromFile(filePath).getLines()
  //  lines.toList

  //}




}