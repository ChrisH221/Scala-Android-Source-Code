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
import java.io.ByteArrayInputStream

class imageChanger {


	

   def imageToString(path: String): String = {
  // Log.d("MyTAG", path)
   val bImage: BufferedImage  = ImageIO.read(new File(path))
    var imageString: String = null
   
    val baos = new ByteArrayOutputStream()
    try {
      ImageIO.write(bImage, "png", baos)
      baos.flush()
	  println("1" )
      val imageAsRawBytes = baos.toByteArray()
      baos.close()
	   println("2" )
      imageString = imageAsRawBytes.toString
	  println("hey" + imageString)
    } catch {
      case ex: IOException =>println(ex.toString)
    }
    imageString
  }

  def stringToImage(imageString: String): BufferedImage = {
    var bImage: BufferedImage = null
    val bais = new ByteArrayInputStream(imageString.getBytes)
    try {
	println("this far")
      bImage = ImageIO.read(bais)
    } catch {
      case ex: IOException => println("no")
    }
    bImage
  }
  
  
}
