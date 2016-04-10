package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack


class testBuilder extends FlatSpec with helpers {

  val b = new builder()
 

    
	
 it should "check freq function is running correctly" in {
		

	println(b.freq("hiya", List()))
 assert(b.freq("hiya", List())(0) === ('h',1))
 assert(b.freq("hiya", List())(1) === ('i',1))
 assert(b.freq("hiya", List())(2) === ('y',1))
 assert(b.freq("hiya", List())(3) === ('a',1))
 
    }
	
	
	it should "check node frequency" in {
		
			
		val leafList = List(new b.Leaf('f', 1 ), new b.Leaf('d',1 ), new  b.Leaf('e', 1))
			
		assert( b.freqNode(leafList(0)) === 1)
		assert( b.freqNode(leafList(1)) === 1)
		assert(b.freqNode(leafList(2)) === 1)
		
	//	assert(leafList.foreach{x -> b.freqNode(x) == 1 })
		
     
    }
	
	
 
  
  it should "insert a new node into a tree" in {
  
 val leafList = List (new b.Leaf('a',1), new b.Leaf('c',3) ) 
   val leaf = new b.Leaf('b',2)
 // val inserted = b.insert(leaf,leafList, List())
  
 // assert(inserted == List ( new b.Leaf('b',2) , new b.Leaf('a',1),new b.Leaf('c',3) ) )
 //
  }
	
	it should "merge a list of HTree nodes into a single HTree" in {
	val list = List (new b.Leaf('h',1),new b.Leaf('i',1),new b.Leaf('y',1),new b.Leaf('a',1))
	val result = b.merge(list)
	
	}
	
  it should "make a link between two nodes" in {
  
  val leaf1 = new b.Leaf('f', 1 )
  val leaf2 = new b.Leaf('e', 3 )
  
  val branch = b.makeLink(leaf1,leaf2)
  assert(branch.toString == "Branch(4,Leaf(f,1),Leaf(e,3))")

  
  
  }
  
  
  it should "take a string and make a tree" in {
  
  
 // val tree = b.makeTree("hello")
//  val testTree = new b.Branch(5,b.Leaf('l',2),new b.Branch(3,new b.Leaf('o',1), new b.Branch(2,new b.Leaf('e',1),new b.Leaf('h',1))))
//  assert(tree === testTree)
  
  }
	
	
	
	}
 