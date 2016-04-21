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
  
    val code = h.extractCode(h.makeTree("hello"),List(),List())
	
	assert(code.toString ===  "List((l,List(0, 0)), (h,List(0, 1)), (o,List(1, 0)), (e,List(1, 1)))")
		 
    }
	
	it should "check bit list is functioning" in {
  
 
	val code = h.extractCode(h.makeTree("hello"),x,x)
	val res = h.bitList("hello",0,x,code,x)
	
	assert(res.toString ==="List(0, 1, 0, 0, 0, 0, 1, 1, 1, 0)")
		 
    }

it should "check bitlist builder is functioning" in {
	
    }

it should "check encode and decode " in {

val testString = "Hey, how are you?"
val encoded = h.encode(testString)
val decode = h.decode(new StringBuffer(),encoded)
assert(decode ===testString)
}
	
}