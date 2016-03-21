package my.android.project

import java.awt._
import java.io._
import java.util.logging._
import javax.imageio.ImageIO
import javax.swing._
//remove if not needed
import scala.collection.JavaConversions._
import java.awt.image.BufferedImage
//import android.util.Log
import java.io._
import sun.misc.BASE64Decoder
import sun.misc.BASE64Encoder

class imageChanger {


	

  def imageToString(bImage: BufferedImage, format:String): String = {
  var imageString: String = null
    val bos = new ByteArrayOutputStream()
    try {
      ImageIO.write(bImage, format, bos)
      val imageBytes = bos.toByteArray()
      val encoder = new BASE64Encoder()
      imageString = encoder.encode(imageBytes)
      bos.close()
    } catch {
      case e: IOException => e.printStackTrace()
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
      case ex: IOException => println("no")
    }
    bImage
  }
  
  
}

            