package my.android.project


import java.io._
import java.util.logging._
import javax.imageio.ImageIO
import javax.swing._
import java.awt.image.BufferedImage
import java.io._
import sun.misc.BASE64Decoder
import sun.misc.BASE64Encoder

import android.util.Log

class imageChanger {

val h = new handler
var div = 0
	

  def imageToString(bImage: BufferedImage): String = {
  var imageString: String = null
    val bos = new ByteArrayOutputStream()
    try {
      ImageIO.write(bImage, "png", bos)
      val imageBytes = bos.toByteArray()
      val encoder = new BASE64Encoder()
      imageString = encoder.encode(imageBytes)
      bos.close()
    } catch {
      case e: IOException => Log.d("MyTAG", e.toString)
    }
    imageString
  }

  def stringToImage(imageString: String): BufferedImage = {
    var bImage: BufferedImage = null
	val b64dec = new BASE64Decoder()
    try {
	
            val output = b64dec.decodeBuffer(imageString)
            val bais = new ByteArrayInputStream(output)
            bImage = ImageIO.read(bais)
    } catch {
      case ex: IOException => Log.d("MyTAG", ex.toString)
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
    
	
   val image = ImageIO.read(new File(path))
	 
	  var s = imageToString(image)
	val copy = s
	  val divide = findSmallestBreakPoint(s, s.size / 100)
	
	 val chunks = s.size / divide
	 var x =  List(h.encode(s.take(chunks)))
	
	  s = s.drop(chunks)
	 
	 var a = 0
	 var list = List()
	 for (a <- 1 to divide-1){
	 var t = h.encode(s.take(chunks)) 
	 x = x :+ t
	 s = s.drop(chunks)
	
     }
	 
	 x
	 
	
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

            