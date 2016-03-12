package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.io.ByteArrayInputStream




class testImage extends FlatSpec with helpers {



 val h = new handler()

 it should "read the image, turning into bytestring, encode using huffman and then decode to same string" in {
       
	  //val testPath = "C:\Users\Chris\Desktop"
	  val i = new imageChanger()
	  val s = i.imageToString("C://Users//Chris//Desktop//testImage.png")
	//  println("Before encoding:" + s)
	//  val result = h.encode(s)
	//  println("After encoding:"+result)
	 // val d = h.decode(0,"",result)
		
		val rw = new read_write()
	//	val in = new ByteArrayInputStream(d.getBytes);
 //       val bImageFromConvert = ImageIO.read(in);
		val im = i.stringToImage(s)
		rw.writeImage(im)
	 // assert( d === s)
    }
	
	}
 
 
	
	
	
	
