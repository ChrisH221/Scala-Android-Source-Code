package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack



class testHandler extends FlatSpec with helpers {

val h = new handler

it should "check extracting code is functioning" in {
  
  //  val code = h.extractCode(h.makeTree("hello"),List(),List())
//	val check = List(('l',List(0)), ('o',List(1, 0)), ('e',List(1, 1, 0)), ('h',List(1, 1, 1)))
//	assert(code === check)
		 
    }

it should "check bitlist builder is functioning" in {
	val test = "fdskufhjfkdguhfkdjghfkjghfkdughfdkghfdkjghfdkjghfdjkghfdjkghfdkjghsidghdufghfdklughfdkughdfkljghfdjkgfhdkjghfdughfdukghfdughfdgjfhdkgjhfdlkughfdklughfdkughfdukghfdkughfdkughfdkughfdkjghfdughfdughfdlughfdkughfdklbuhfdlughfdlughfdlkughfdukghfdlkughfdlughfdukgfhdglujkhfdgufhdkguhukishdf;uhsdfluhdsgukfhdgukhfgukhgdfklughfdklhugfdg"

	val res = h.encode(test)
//	println(res)
//	println(res)
//	val tree = h.makeTree("hello")
  //  val bit = h.bitList("Hello",tree,x,x)
//	println(bit)
    }


	
}