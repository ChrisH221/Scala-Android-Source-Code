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

class imageChanger extends helpers {


  def processEncode(path:String):String={
   Log.d("MyTAG","hey" + path)
    val file = new File(path)
	val imageOpts = new BitmapFactory.Options();
	 imageOpts.inJustDecodeBounds = true
	 
	var bitmap = BitmapFactory.decodeFile(file.getAbsolutePath)
	var output = new ByteArrayOutputStream(bitmap.getByteCount());
	bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
	var imageBytes = output.toByteArray();

	var encodedString = Base64.encodeToString(imageBytes, Base64.URL_SAFE)	
	 
	encodedString
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

  
  



            