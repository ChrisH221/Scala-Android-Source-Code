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


/**
 * @author Chris Howell
 *
 * The class handles converting bitmaps to base64 strings and back
 */


class imageChanger {

val h = new handler
var div = 0
	 
  def getByteArray(bitmap: Bitmap): Array[Byte] = {
    val bos = new ByteArrayOutputStream()
    bitmap.compress(CompressFormat.PNG, 0, bos)
    bos.toByteArray()
  }
  
  def getBitmap(bitmap: Array[Byte]): Bitmap = {
    BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length)
  }

  def imageToString(bImage: Bitmap): String = {
  var imageString: String = null
    val bos = new ByteArrayOutputStream()
    try {
	

	  val bytes =  getByteArray(bImage)
	  
	 
	  val encodeValue = Base64.encode(bytes, Base64.DEFAULT)
      
      imageString = encodeValue.toString
	Log.d("MyTAG", "1"+imageString)
      bos.close()
    } catch {
      case e: IOException => 
    }
    imageString
  }

  def stringToImage(imageString: String): Bitmap = {
    var bImage: Bitmap = null
	
    try {
			
	  val Bitmap =  getBitmap(imageString.getBytes)
	       
      bImage = Bitmap
	
      
    } catch {
      case ex: IOException =>Log.d("MyTAG","not working")
    }
    bImage
  }
  
  def findSmallestBreakPoint(str:String, start:Int):Int = {
  
	var found = false
	var divider = start
	while(!found){
		divider = divider+1
		if (str.size % divider == 0) found = true
		
	}
  
  div = divider
  divider
  
  
  }
  
  def processEncode(path:String):List[(List[h.Bit],List[(Char,List[h.Bit])])]={
    
	
    val file = new File(path)
    val bitmap = BitmapFactory.decodeFile(file.getAbsolutePath)
	 	
	 var s = imageToString(bitmap)
	 var x = h.encode(s)
	 var l = List(x)
	 l
	 
	
  }
  
    def processDecode(list:(List[h.Bit],List[(Char,List[h.Bit])])):String={
    
		 
	 var d = h.decode(0,"",list)
	
  	d
	 
	 
	
  }
  
  
}

            