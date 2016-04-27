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
* Tests for the handler class.
*/
class testHandler extends FlatSpec with testHelpers{
    
    
    
    /**
    * A test for checking if the the codemap is generating correctly
    */    
    it should "check extracting code is functioning" in {
        
        val code = h.extractCode(h.makeTree("hello"),List(),List())
        assert(code.toString ===  "List((l,List(0)), (o,List(1, 0)), (e,List(1, 1, 0)), (h,List(1, 1, 1)))")
        
    }
    
    
    /**
    * A test for checking if the lookupBit function is working
    */
    it should "look up a find a bit combination to make the char  " in {
        
        val pair1 = ('c', List(0,0,1))
        val pair2 = ('d', List(1,0,0))
        val list = List(pair1,pair2)
        val result = h.lookupBit('d', list)
        assert(result.toString === "Some(List(1, 0, 0))")
        
    }
    
    
    /**
    * A test for building a bit list
    */
    it should "check bit list is functioning" in {
        
        
        val code = h.extractCode(h.makeTree("hello"),x,x)
        val res = h.bitList("hello",code).reverse
        
        assert(res.toString ==="List(1, 1, 1, 1, 1, 0, 0, 0, 1, 0)")
        
    }
    
    
    /**
    * A test for counting the number of leaf nodes in a tree and checking if the result equals the same size as the freqency table.
    * Runs multiple times as it iterates over the list of strings generated in the testHelper trait.
    */
    it should "count the number of leaf nodes to check consistant with freqency table" in{
        
        listFreq.foreach{x =>
            
            val code = h.merge(h.makeNode(x,List()))
            val leafNum = h.countLeaves(code,List()).reduce((x:Int, y:Int) => x + y)
            assert(x.length === leafNum)
            
        }
        
    }
    
    /**
    * A test for collecting leaf nodes and checking if the correct number exist in the tree when compared to the size of the frequency table
    */
    it should "collect the leaf nodes from a tree" in {
        
        val freq = h.freq("hello",List())
        val code = h.makeTree("hello")
        val leafNum = h.collectLeaves(code,x)
        
        assert(freq.length === leafNum.length)
        
    }
    
    
    
    /**
    * Test the encode and decode function on one randomly generated string
    */
    it should "check encode and decode " in {
        
        
        val encoded = h.encode(testString)
        val decode = h.decode(new StringBuffer,encoded)
        assert(decode ===testString)
        
    }
    
    /**
    * Test the encode and decode function on a list of randomly generated strings
    */
    it should "check multiple encode/decode with random strings " in {
        
        listString.foreach{x =>
            
            val encoded = h.encode(x)
            val decode = h.decode(new StringBuffer,encoded)
            assert(decode ===x)
            
        }
        
        
    }
    
    /**
    * Check encoding and decoding numbers work
    */
    it should "check encode and decode numbers " in {
        
        val testString = "12456486453435248456435454354?"
        val encoded = h.encode(testString)
        val decode = h.decode(new StringBuffer,encoded)
        assert(decode ===testString)
        
    }
    
    
    
}