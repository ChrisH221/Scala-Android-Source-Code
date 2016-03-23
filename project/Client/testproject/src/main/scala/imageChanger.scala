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
	
     val options = new BitmapFactory.Options();
   //  val   bitmap = BitmapFactory.decodeByteArray(bImage, 0, bImage.length, options);
  //    val bitmap = bImage.ToArray
	  val bytes =  getByteArray(bImage)
	  
	 
	  val encodeValue = Base64.encode(bytes, Base64.DEFAULT)
      
      imageString = encodeValue.toString
	
      bos.close()
    } catch {
      case e: IOException => 
    }
    imageString
  }

  def stringToImage(imageString: String): BufferedImage = {
    var bImage: BufferedImage = null
	
    try {
			 val decodeValue = Base64.decode(imageString, Base64.DEFAULT)
             val bais = new ByteArrayInputStream(decodeValue)
            bImage = ImageIO.read(bais)
    } catch {
      case ex: IOException =>
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
//	Log.d("MyTAG","this size" + s.size.toString)
 // 	val copy = s
//	  val divide = findSmallestBreakPoint(s, s.size / 100)
	
//	 val chunks = s.size / divide
//	 var x =  List(h.encode(s.take(chunks)))
		
	//  s = s.drop(chunks)
	 	
	// var a = 0
//	 var list = List()
//	 for (a <- 1 to divide-1){
//	 var t = h.encode(s.take(chunks)) 
	// x = x :+ t
	// s = s.drop(chunks)
	
  //   }
	 var x = h.encode(s)
	 var l = List(x)
	 l
	 
	
  }
  
    def processDecode(list:List[(List[h.Bit],List[(Char,List[h.Bit])])]):String={
    
	var stringDecode = ""
	 for (a <- 0 to div-1){
	 
	 var codeChunk = list(a)
	 var d = h.decode(0,"",codeChunk)
	
  	stringDecode = stringDecode ++ d
	
     }
	 
	 stringDecode
	
  }
  
  
}

            