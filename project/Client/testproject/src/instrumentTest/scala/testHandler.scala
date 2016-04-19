package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import my.android.project.builder
import my.android.project.helpers





class testHandler extends FlatSpec with helpers {



 val h = new handler()

 it should "check complete encode and decode cycle" in {
       
	  val testString = "Heyy hows"
	  
	println(h.makeTree(testString))
	assert(h.decode(new StringBuffer(),h.encode(testString)) === testString)
    }
	
	}
 