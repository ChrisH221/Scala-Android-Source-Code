package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import my.android.project.builder
import my.android.project.helpers





class testHandler extends FlatSpec with helpers {



 val h = new handler()

 it should "check complete encode and decode cycle" in {
       
	  val testString = "iVBORw0KGgoAAAANSUhEUgAAAYMAAAFjCAIAAABQQvDHAAALz0lEQVR42u3c205b6R3GYY4r9aBXkKS5ht5Qr6A9qao5HfUy5gbmHkaa8wHMzuxjDMYQtoox"
	  assert(h.decode(1,"",h.encode(testString)) === testString)
    }
	
	}
 
 
	
	
	
	
