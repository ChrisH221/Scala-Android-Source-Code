package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import my.android.project.builder
import my.android.project.helpers





class testRW extends FlatSpec with helpers {



 val h = new handler()
 val rw = new read_write()

 it should "pretty print a bit list" in {
       
	  val testString = "iVBORw0KGgoAAAANSUhEUgAAAYMAAAFjCAIAAABQQvDHAAALz0lEQVR42u3c205b6R3GYY4r9aBXkKS5ht5Qr6A9qao5HfUy5gbmHkaa8wHMzuxjDMYQtoox"
	  val list = h.encode(testString)
	
	  val bitList = list._1.asInstanceOf[List[rw.Bit]]
	  val pretty = rw.prettyPrint(bitList)
	 
	  assert(pretty !== "")
    }
	
	}
 
 
	
	
	
	
