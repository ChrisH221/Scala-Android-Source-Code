package my.android.project

import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitSuite
import scala.util.Random



/**
* @author Chris Howell
*
* Black box tests for handler and builder classes.
*/
class blackBoxTests extends FlatSpec with testHelpers{
    
	
	   
    /**
    * Test the encode and decode function on one randomly generated string
    */
    it should "check encode and decode cycle " in {
        
        val testString = "hello"
        val encoded = h.encode(testString)
        val decode = h.decode(encoded)
        assert(decode ===testString)
        
    }
    
    /**
    * Test the encode and decode function on an empty string
    */
    it should "check encode and decode when passed empty string " in {
        
        
        val encoded = h.encode("")
        val decode = h.decode(encoded)
        assert(decode ==="")
        
    }
    
    /**
    * Test the encode and decode function on one randomly generated string
    */
    it should "check encode and decode when passed a single character string " in {
        
        
        val encoded = h.encode("h")
        val decode = h.decode(encoded)
        assert(decode ==="h")
        
    }
    
    /**
    * Test the encode and decode function on one randomly generated string
    */
    it should "check encode and decode when passed a two character string " in {
        
        
        val encoded = h.encode("hi")
        val decode = h.decode(encoded)
        assert(decode ==="hi")
        
    }
    
    /**
    * Test the encode and decode function on a randomly generated string
    */
    it should "check encode and decode when passed a large string " in {
        
        var largeString = Random.alphanumeric.take(10000).mkString
        
        val encoded = h.encode(largeString)
        val decode = h.decode(encoded)
        assert(decode ===largeString)
        
    }
    
    /**
    * Test the encode and decode function an even larger string
    */
    it should "check encode and decode when passed an even larger string " in {
        
        var largeString = Random.alphanumeric.take(100000).mkString
        
        val encoded = h.encode(largeString)
        val decode = h.decode(encoded)
        assert(decode ===largeString)
        
    }
    
        

    /**
    * Check encoding and decoding integer string
    */
    it should "check encode and decode integer string" in {
        
        val testString = "12456486453435248456435454354?"
        val encoded = h.encode(testString)
        val decode = h.decode(encoded)
        assert(decode ===testString)
        
    }
	
	it should "check encode and decode chinese string" in {
	
	
		val testString = "??????"
		val encoded = h.encode(testString)
        val decode = h.decode(encoded)
        assert(decode ===testString)
	}
    
  
    
 
}