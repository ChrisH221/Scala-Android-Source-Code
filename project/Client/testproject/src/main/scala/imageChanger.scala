package my.android.project


import android.util.Log
import android.util.Base64
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import java.io._
import java.io.FileOutputStream
import scala.util.Try



/**
 * @author Chris Howell
 * This object handles converting bitmaps to base64 strings and back again.
 * Object instead of class to allow access to the methods contained inside the object without
 * needing to create different instances.
 */
object imageChanger {

/**
 * Takes an image path and returns the Base64 Bitmap string
 * @returns Base64 String
 */
  def processEncode(path:String):String={
    
	
    val file = new File(path)
	var bitmap = BitmapFactory.decodeFile(file.getAbsolutePath)
	var output = new ByteArrayOutputStream(bitmap.getByteCount());
	bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
	var imageBytes = output.toByteArray();
	
    var encodedString = Base64.encodeToString(imageBytes, Base64.URL_SAFE);
		
		
	Try(file.delete())
	
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
		val fOut = new FileOutputStream(Card);
		
		image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		fOut.flush();
		fOut.close();
	
		
  
  }
    
  
  
  
}


            