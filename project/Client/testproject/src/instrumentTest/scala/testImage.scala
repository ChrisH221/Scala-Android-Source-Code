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
      // = ImageIO.read(classOf[MyClass].getResourceAsStream("/images/grass.png"))

	//  val image = ImageIO.read(new File(testPath))
	 // println(image.getBytes)
	//  val s = i.imageToString(image)
	
	//  val result = h.encode(s)
	//  val d = h.decode(0,"",result)
	//  assert( d === s)
    }
	
	
	
	 it should "read the image, turning into bytestring, encode/decode and then write the image " in {
       
	 val formatName = testPath.substring(testPath.lastIndexOf('.') + 1, testPath.length)
	  val image = ImageIO.read(new File(testPath))
	 
	 var s = i.imageToString(image,formatName)
	 val chunks = s.size / 60
	 var x = List(h.encode(s.take(chunks)))
	  s = s.drop(chunks)
	 
	 var a = 0
	 var list = List()
	 for (a <- 1 to 59){
	 var t = h.encode(s.take(chunks)) 
	 x = x :+ t
	 s = s.drop(chunks)
	
     }
	 var stringDecode = ""
	 for (a <- 0 to 59){
	 var codeChunk = x(a)
	 var d = h.decode(0,"",codeChunk)
	
	stringDecode = stringDecode ++ d
	
     }
	 
	
	
		val rw = new read_write()
		val in = new ByteArrayInputStream(stringDecode.getBytes);
        val bImageFromConvert = ImageIO.read(in);
		val im = i.stringToImage(stringDecode)
		rw.writeImage(im)
	 // assert( d === s)
    }
	
	
	}
 
 
	
	
	
	
