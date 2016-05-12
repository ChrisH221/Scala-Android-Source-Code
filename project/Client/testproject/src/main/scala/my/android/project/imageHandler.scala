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
import java.io.ByteArrayOutputStream






/**
* @author Chris Howell
* This class handles converting bitmaps to base64 strings and back again.
* It also writes bitmaps to the device storage.
*/

class imageHandler extends helpers {
    
    
   /**
    * Takes a Bitmap and a file name then writes the png image to the sd card.
    * @returns Unit
    */
	
    def processEncode(path:String):String={
        
        val file = new File(path)
        val fn = file.getAbsolutePath
        val extension = fn.substring(fn.lastIndexOf('.'),fn.length())
        
        
        var bitmap = BitmapFactory.decodeFile(file.getAbsolutePath)
        var output = new ByteArrayOutputStream(bitmap.getByteCount())
		
        if(extension ==".png")bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
        if(extension ==".jpeg")bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output)
		
        var imageBytes = output.toByteArray();
        
        var encodedString = Base64.encodeToString(imageBytes, Base64.URL_SAFE)
        
        encodedString
    }
    
    
    
    /**
    * Takes a Bitmap and a file name, then writes the png image to the sd card.
    * @returns Unit
    */
    def writeDecodedImage(image:Bitmap,noExtension:String )={
        
        
        val folder = new File("/decoded")
        
        if (!folder.exists()){folder.mkdir()}
        
        val file = new File("/decoded/", noExtension + ".png")
        
        file.createNewFile()
        
        val fOut = new FileOutputStream(file);
        
        image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        fOut.flush();
        fOut.close();
        
        
        
    }
      
    
}



