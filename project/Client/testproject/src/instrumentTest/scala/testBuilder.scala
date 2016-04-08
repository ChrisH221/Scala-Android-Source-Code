package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack


class testBuilder extends FlatSpec with helpers {

  val b = new builder()
 
 it should "check count function is running correctly" in {
       
      assert(b.count('h',"hhh") === 3)
	   assert(b.count('d',"ddddd") === 5)
	    assert(b.count('a',"a") === 1)
		 assert(b.count('e',"eeee") === 4)
	
		
		 
    }
	
 it should "check freq function is running correctly" in {
		

	
 assert(b.freq("hiya", List())(0) === ('a',1))
 assert(b.freq("hiya", List())(1) === ('y',1))
 assert(b.freq("hiya", List())(2) === ('i',1))
 assert(b.freq("hiya", List())(3) === ('h',1))
 
    }
	
	
	it should "check node frequency" in {
		
			
		val leafList = List(new b.Leaf('f', 1 ), new b.Leaf('d',1 ), new  b.Leaf('e', 1))
		//assert(leafList.foreach{x -> b.freqNode(x) == 1 })
		
     
    }
	
	
 
  
  it should "insert a new node into a tree" in {
  
  val leafList = List (new b.Leaf('a',1), new b.Leaf('c',3) ) 
   val leaf = new b.Leaf('b',2)
  val inserted = b.insert(leaf,leafList, List())
  
  assert(inserted == List (new b.Leaf('a',1), new b.Leaf('b',2) ,new b.Leaf('c',3) ) )
 
  }
	
	
  it should "make a link between two nodes" in {
  
  val leaf1 = new b.Leaf('f', 1 )
  val leaf2 = new b.Leaf('e', 3 )
  
  val branch = b.makeLink(leaf1,leaf2)
  assert(branch.toString == "Branch(4,Leaf(f,1),Leaf(e,3))")

  
  
  }
  
  
  it should "take a string and make a tree" in {
  
  
  val tree = b.makeTree("hello")
 // println(tree)
  
  
  }
	
	
	
	}
 