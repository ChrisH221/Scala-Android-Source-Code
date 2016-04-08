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






/**
 * @author Chris Howell
 * This object handles converting bitmaps to base64 strings and back again.
 * Object instead of class to allow access to the methods contained inside the object without
 * needing to create different instances.
 */

class imageChanger extends helpers {



 




  sealed trait Bit
  case class EmptyBit() extends Bit
  case class Zero() extends Bit
  case class One() extends Bit
	val b = new builder
	val h = new handler
/**
 * Takes an image path and returns the Base64 Bitmap string
 * @returns Base64 String
 */
  def processEncode(path:String):(List[Int],List[(Char,List[Int])])={
    val file = new File(path)
	var bitmap = BitmapFactory.decodeFile(file.getAbsolutePath)
	
	val imageOpts = new BitmapFactory.Options();
	
	
	val thumb = getResizedBitmap(bitmap,5,5)
	
	
	var output = new ByteArrayOutputStream(thumb.getByteCount());
	thumb.compress(Bitmap.CompressFormat.PNG, 100, output);
	var imageBytes = output.toByteArray();

	 var encodedString = Base64.encodeToString(imageBytes, Base64.URL_SAFE);
	
	 val encodedString2 = encodedString
	 val divide = 5
	
	 val chunks = encodedString.size / divide
	
	 var x =  (h.encode(encodedString))
	
	x.asInstanceOf[(List[Int], List[(Char, List[Int])])]
	
  }
  
  
  def keyToJson(list:(List[Int],List[(Char,List[Int])])):String={
  
   case class HCodeMap(list:List[(Char,List[Int])])

  implicit val testFormat: JsonFormat[HCodeMap] = jsonFormat(HCodeMap.apply _, "list")
  
  val pair = new HCodeMap(list._2).toJson
 
  
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
  
  
   def getResizedBitmap(bm: Bitmap, newHeight: Int, newWidth: Int): Bitmap = {
    val width = bm.getWidth
    val height = bm.getHeight
    val scaleWidth = newWidth.toFloat / width
    val scaleHeight = newHeight.toFloat / height
    val matrix = new Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    val resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
    resizedBitmap
  }
  
    
 def createBit(str:String): List[List[Int]]={
 
var Bits = new ListBuffer[Int]()
var bitList = new ListBuffer[List[Int]]()
 
 str.toCharArray.foreach{x => if (x == "-1") {
 bitList += Bits.toList
 Bits = new ListBuffer[Int]()
 }else{
  
  Bits += x.toInt
  
 } 
 
 }
 Log.d("MyTAG", bitList.toString)
 bitList.toList
 
 
 }
 
 
 }	

  
  



            