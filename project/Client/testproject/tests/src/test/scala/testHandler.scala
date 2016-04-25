package my.android.project

import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitSuite
import org.scalatest.prop.Checkers
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalacheck._
import scala.util.Random 



/**
* @author Chris Howell
*
* Test class for the handler class.
*/

class testHandler extends FlatSpec with helpers with testHelpers{



/**
* A test for checking if the extractCode 
*/

	
    it should "check extracting code is functioning" in {
  
    val code = h.extractCode(h.makeTree("hello"),List(),List())
	assert(code.toString ===  "List((l,List(0)), (o,List(1, 0)), (e,List(1, 1, 0)), (h,List(1, 1, 1)))")
  
    }
	
	it should "look up a find a bit combination to make the char  " in {
	
	val pair1 = ('c', List(0,0,1))
	val pair2 = ('d', List(1,0,0))
	val list = List(pair1,pair2)
	val result = h.lookupBit('d', list)
	assert(result.toString === "Some(List(1, 0, 0))")
	
	}
	
	
	it should "check bit list is functioning" in {
  
 
	val code = h.extractCode(h.makeTree("hello"),x,x)
	val res = h.bitList("hello",code).reverse
	
     assert(res.toString ==="List(1, 1, 1, 1, 1, 0, 0, 0, 1, 0)")
		 
    }
	
	it should "count the number of leaf nodes to check consistant with freqency table" in{
	
	listFreq.foreach{x =>

	val code = h.merge(h.makeNode(x,List()))
	val leafNum = h.countLeaves(code,List()).reduce((x:Int, y:Int) => x + y)
	assert(x.length === leafNum)
	
	}
	
	}

	it should "collect the leaf nodes from a tree" in {
	
	val freq = h.freq("hello",List())
	val code = h.makeTree("hello")
	val leafNum = h.collectLeaves(code,x)
	
	assert(freq.length === leafNum.length)
	
    }
	
	it should "collect the leaf nodes from and check are in descending order" in {
	
	val freq = h.freq("What's the story",List())
	val code = h.makeTree("What's the story")
	val leafNum = h.collectLeaves(code,x)
	val leafTest =  h.makeNode(freq,x).reverse
	
	assert(leafNum.toString === leafTest.toString)
	
    }
	
	 
	
	
	it should "check encode and decode " in {

	
	val encoded = h.encode(testString)
	val decode = h.decode(new StringBuffer,encoded)
	assert(decode ===testString)
	
	}
	
		
	it should "check multiple encode/decode strings " in {
	
	listString.foreach{x => 
	
	val encoded = h.encode(x)
	val decode = h.decode(new StringBuffer,encoded)
	assert(decode ===x)
	
	}


    }

	it should "check encode and decode numbers " in {

	val testString = "12456486453435248456435454354?"
	val encoded = h.encode(testString)
	val decode = h.decode(new StringBuffer,encoded)
	assert(decode ===testString)
	
	}

	
	
}