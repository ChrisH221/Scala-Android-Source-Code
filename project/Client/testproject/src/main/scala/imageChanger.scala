package my.android.project


import java.io._
import javax.imageio.ImageIO
import javax.swing._
import java.awt.image.BufferedImage
import java.io._
import android.util.Log
import android.util.Base64
import java.util.logging._
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import java.io._
import java.io.FileOutputStream
import java.nio.file.NoSuchFileException


/**
 * @author Chris Howell
 *
 * The class handles converting bitmaps to base64 strings and back
 */


object imageChanger {


  def processEncode(path:String):String={
    
	
    val file = new File(path)
	var bitmap = BitmapFactory.decodeFile(file.getAbsolutePath)
	var output = new ByteArrayOutputStream(bitmap.getByteCount());
	bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
	var imageBytes = output.toByteArray();
	
	 var encodedString = Base64.encodeToString(imageBytes, Base64.URL_SAFE);
		
	try {
    file.delete()
	} catch{ case e: Exception => println("error")}
			
	 encodedString
	
  }
  
  
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


            