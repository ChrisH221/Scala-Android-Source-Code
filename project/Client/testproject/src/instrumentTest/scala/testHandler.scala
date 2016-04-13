package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import android.util.Log
import java.io.IOException
import java.io.ByteArrayOutputStream
import java.util.zip.GZIPOutputStream
import java.util.zip._
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream


class testHandler extends FlatSpec with helpers {

val h = new handler

 

it should "check extracting code is functioning" in {
  
  //  val code = h.extractCode(h.makeTree("hello"),List(),List())
//	val check = List(('l',List(0)), ('o',List(1, 0)), ('e',List(1, 1, 0)), ('h',List(1, 1, 1)))
//	assert(code === check)
		 
    }
	
	it should "check bit list is functioning" in {
  
 // val code = h.extractCode(h.makeTree("hello"),x,x)
//	val res = h.bitList("hello",x,code).reverse
//	println("res" + res)
  //  val code = h.extractCode(h.makeTree("hello"),List(),List())
//	val check = List(('l',List(0)), ('o',List(1, 0)), ('e',List(1, 1, 0)), ('h',List(1, 1, 1)))
//	assert(code === check)
		 
    }

it should "check bitlist builder is functioning" in {
	//val test = "fdskufhjfkdguhfkdjghfkjghfkdughfdkghfdkjghfdkjghfdjkghfdjkghfdkjghsidghdufghfdklughfdkughdfkljghfdjkgfhdkjghfdughfdukghfdughfdgjfhdkgjhfdlkughfdklughfdkughfdukghfdkughfdkughfdkughfdkjghfdughfdughfdlughfdkughfdklbuhfdlughfdlughfdlkughfdukghfdlkughfdlughfdukgfhdglujkhfdgufhdkguhukishdf;uhsdfluhdsgukfhdgukhfgukhgdfklughfdklhugfdg"
	//println(h.freq(test,x))
 //  val tree = h.makeTree(test)
	//println(tree)
	//val codemap = h.extractCode(tree,x,x)
	//println(codemap.toString)
	//println(h.bitList(test,0,x,codemap,x))
	//val t2 = "hihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaahihihityaa"
	//val res = h.encode(test)
//	println(res)
	//println(h.freq(test,x))
	//println(test.size)
//	val des = h.decode(1,"",res)
	//println(des.reverse)
	//assert(test === des.reverse)
//	println(res)
//	val tree = h.makeTree("hello")
  //  val bit = h.bitList("Hello",tree,x,x)
//	println(bit)
    }


	
}