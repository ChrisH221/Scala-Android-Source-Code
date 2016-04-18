package my.android.project


import android.util.Log
import android.util.Base64
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import java.io._
import java.io.FileOutputStream
import scala.util.Try
import spray.json._
import DefaultJsonProtocol._
import scala.collection.mutable._
import scala.io.Source
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import scala.collection.mutable.ListBuffer
import  android.graphics.Matrix
import java.io.ByteArrayOutputStream






/**
 * @author Chris Howell
 * This object handles converting bitmaps to base64 strings and back again.
 * Object instead of class to allow access to the methods contained inside the object without
 * needing to create different instances.
 */

class textHandler extends helpers {


  def processEncode(path:String):(List[Int],List[(Char,List[Int])])={
	
   
	
     val bufferedReader = new BufferedReader(new FileReader(path))
    val stringBuffer = new StringBuffer()
    var line: String = null
	while( {line = bufferedReader.readLine();  line!= null} ) 
    {
      stringBuffer.append(line).append("\n")
    }
  
	val h = new handler
	 
	var x =  (h.encode(stringBuffer.toString))

	var d = h.decode2(1,"",x)
	// var d = (h.decode(1,"",x))
	
	x.asInstanceOf[(List[Int], List[(Char, List[Int])])]
	
  }
  
  
  def keyToJson(list:(List[Int],List[(Char,List[Int])])):String={
  
   case class HCodeMap(list:List[(Char,List[Int])])

  implicit val testFormat: JsonFormat[HCodeMap] = jsonFormat(HCodeMap.apply _, "list")
  
  val pair = new HCodeMap(list._2).toJson
 
		//Log.d("MyTAG", "2" + pair.toString)
		pair.toString
  
  }
  
  /**
 * Converts a JSON string to a HCodeMap
 * @returns HCodeMap
 */
  
   def JsonToKey(json:String):List[(Char,List[Int])]={
  
   case class HCodeMap(list:List[(Char,List[Int])])

   implicit val testFormat: JsonFormat[HCodeMap] = jsonFormat(HCodeMap.apply _, "list")
  
    	
	val map2 = json.parseJson
	val map3 = map2.convertTo[HCodeMap]
	Log.d("MyTAG", "1" + map3.list.toString)
    map3.list
  }
  
 
  
 /**
 * Takes a Bitmap and a file name then writes the png image to the sd card.
 * @returns Unit
 */
     def writeDecodedImage(image:Bitmap,noExtension:String )={
   
  	 
	 val folder = new File("/sdcard/decoded")
		
		if (!folder.exists()){folder.mkdir()}
	
		val Card = new File("/sdcard/decoded/", noExtension + ".png")
		
		Card.createNewFile()
		
	  Log.d("MyTAG", "THIS FAR")
		val fOut = new FileOutputStream(Card);
		
		image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		fOut.flush();
		fOut.close();
	
		
  
  }
  
  

    

 
 }	

  
  



            