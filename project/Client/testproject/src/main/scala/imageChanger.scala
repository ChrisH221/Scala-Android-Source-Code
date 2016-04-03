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


/**
 * @author Chris Howell
 *
 * The class handles converting bitmaps to base64 strings and back
 */


class imageChanger {

val h = new handler
var div = 0

/**
*Converts Bitmap to an Array of bytes
*@returns Array[Byte]
*/		
	
  def getByteArray(bitmap: Bitmap): Array[Byte] = {
    val bos = new ByteArrayOutputStream()
    bitmap.compress(CompressFormat.PNG, 0, bos)
    bos.toByteArray()
  }
  
/**
*Converts an Array of bytes into a Bitmap
*@returns Bitmap
*/	  
  
  def getBitmap(bitmap: Array[Byte]): Bitmap = {
    BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length)
  }

  
  
  def imageToString(bImage: Bitmap): String = {
  var imageString: String = null
    val byteArrayOutputStream = new ByteArrayOutputStream()
    try {
	
	
    bImage.compress(Bitmap.CompressFormat.PNG, 50, byteArrayOutputStream)
    val byteArray = byteArrayOutputStream.toByteArray()
	val bytes = byteArrayOutputStream .toByteArray();
	  
	 
	  imageString = Base64.encodeToString(bytes, Base64.DEFAULT)
      
    
	
      byteArrayOutputStream.close()
    } catch {
      case e: IOException => Log.d("MyTAG", "ERROR")
    }
	Log.d("MyTAG", "1"+imageString)
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
  
  def processEncode(path:String):List[List[(List[h.Bit],List[(Char,List[h.Bit])])]]={
    
	
    val file = new File(path)
	var bitmap = BitmapFactory.decodeFile(file.getAbsolutePath)
	var output = new ByteArrayOutputStream(bitmap.getByteCount());
	bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
	var imageBytes = output.toByteArray();
	Log.d("MyTAG","THE BYTES " + imageBytes.toString)
	 var encodedString = Base64.encodeToString(imageBytes, Base64.NO_WRAP);
	 val encodedString2 = encodedString
	  val divide = findSmallestBreakPoint(encodedString, encodedString.size / 100)
	
	 val chunks = encodedString.size / divide
	 
	 var x =  List(List(h.encode(encodedString.take(chunks))))
		
	encodedString = encodedString.drop(chunks)
	 	
	 var a = 0
	
	 for (a <- 1 to divide-1){
	 var t = List(h.encode(encodedString.take(chunks))) 
	 x = x :+ t
	 encodedString = encodedString.drop(chunks)
	
     }
	 var newImageBytes = Base64.decode(encodedString2, Base64.NO_WRAP);
	 
	 Log.d("MyTAG", "THE NEW BYTES " + newImageBytes.toString) 
	 bitmap = BitmapFactory.decodeByteArray(newImageBytes, 0, newImageBytes.length);
	// writeDecodedImage(bitmap)
	
	x
	 
	
  }
  
  
     def writeDecodedImage(image:Bitmap)={
   
  
	
	// val i = new imageChanger
	 
	// val de = i.getBitmap(decode.getBytes)
	

	 
	 val folder = new File("/sdcard/decoded")
		
		if (!folder.exists()){folder.mkdir()}
	
		val Card = new File("/sdcard/decoded/", "testImage.png")
		
		Card.createNewFile()
		
	  Log.d("MyTAG", "THIS FAR")
		val fOut = new FileOutputStream(Card);
		// 100 means no compression, the lower you go, the stronger the compression
		image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		fOut.flush();
		fOut.close();
	//	//Convert bitmap to byte array
//		val bitmap = de
//		val bos = new ByteArrayOutputStream()
	//	bitmap.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
	//	val bitmapdata = bos.toByteArray();
		
	//	val fos = new FileOutputStream(Card)
		
	//	fos.write(bitmapdata);
		
		
	//	fos.flush()
	  //  fos.write(prettyPrint(encode(0)._1.asInstanceOf[List[this.Bit]]).getBytes())
	//	fos.close()
		
  
  }
  
  
  
    def processDecode(list:(List[h.Bit],List[(Char,List[h.Bit])])):String={
    
		 
	 var d = h.decode(0,"",list)
	
  	d
	 
	 
	
  }
  
  
}

            