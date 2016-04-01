package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import my.android.project.builder
import my.android.project.helpers





class testHandler extends FlatSpec with helpers {



 val h = new handler()

 it should "check complete encode and decode cycle" in {
       
	val testString = "hello"
	val encode = h.encode(testString)
		
	
	
	
	assert(h.decode2(1,"",h.encode(testString)) === testString)
    }
	
	
	it should "check lookup is functioning correctly" in {
       
	 
	
val look = List(('t',List(One(), Zero()).asInstanceOf[List[h.Bit]]))
val bits = List(One(),Zero()).asInstanceOf[List[h.Bit]]
	
assert(h.lookup(bits,look) === Some("t"))
    }
	
	
	}
 
 
	
	
	
	
