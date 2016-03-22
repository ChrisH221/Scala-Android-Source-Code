package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.ByteArrayInputStream
import java.io._
import scala.io._
import scala.collection.mutable.MutableList




class testImage extends FlatSpec with helpers {

   val h = new handler()
   val i = new imageChanger()
    val testPath = "C:\\Users\\Chris\\Desktop\\testImage.png"
  
 it should "read the image, turning into bytestring, encode using huffman and then decode to same string" in {
     // val image= ImageIO.read(classOf[testImage].getResourceAsStream("testImage.png"))
	  val image = ImageIO.read(new File(testPath))
	  val x = i.processEncode(testPath)
    	val stringDecode = i.processDecode(x)
	 // assert( i.imageToString(image) === stringDecode)
    }
	
	
	
	 it should "read the image, turning into bytestring, encode/decode and then write the image " in {
       
	 val image = ImageIO.read(new File(testPath))
	 val x = i.processEncode(testPath)
     val stringDecode = i.processDecode(x)
	 val rw = new read_write()
	 val in = new ByteArrayInputStream(stringDecode.getBytes);
     //val bImageFromConvert = ImageIO.read(in);
	 //val im = i.stringToImage(stringDecode)
 	// rw.writeImage(testPath.reverse.take(17),im,"C:\\Users\\Chris\\Desktop\\testImageTest.png","C:\\Users\\Chris\\Desktop\\")
	
    }
	
	
	}
 