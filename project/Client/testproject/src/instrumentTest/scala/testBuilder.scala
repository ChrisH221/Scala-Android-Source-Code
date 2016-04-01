package my.android.project
import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import my.android.project.builder
import my.android.project.helpers





class testBuilder extends FlatSpec with helpers {

  val b = new builder()

 it should "check count function is running correctly" in {
       
      assert(b.count('h',"hhh") === 3)
	   assert(b.count('d',"ddddd") === 5)
	    assert(b.count('a',"a") === 1)
		 assert(b.count('e',"eeee") === 4)
    }
	
 it should "check freq function is running correctly" in {
		
		
	
 //   assert(b.freq("hiya", List())(0) === ('h',1))
// assert(b.freq("hiya", List())(1) === ('i',1))
// assert(b.freq("hiya", List())(2) === ('y',1))
// assert(b.freq("hiya", List())(3) === ('a',1))
    }
	
	
	it should "check node frequency" in {
		
		val leafList = List( new  Leaf(1, 'f'), new Leaf(1, 'd'), new  Leaf(1, 'e'))
		
	
	 
		
		//for (x <- leafList if freqNode(x) == 1)
   //    println(x)
	 
	//	assert(leafList.foreach{x -> x._1 == 1 })
		
     
    }
	

	
	
	
	}
 
	
	
	
