package my.android.project

import org.scalatest.FlatSpec

/**
* @author Chris Howell
*
* White box tests for handler and builder classes.
*/
class whiteBoxTests extends FlatSpec  with testHelpers {
    
    
    
     
    /*
    * A test for checking if frequency table generate is correct
    */
    it should "check node frequency" in {
        
        
        val leafList = List(new b.Leaf('f', 1 ), new b.Leaf('d',1 ), new  b.Leaf('e', 1))
        
        assert( b.freqNode(leafList(0)) === 1)
        assert( b.freqNode(leafList(1)) === 1)
        assert(b.freqNode(leafList(2)) === 1)
        
    }
    
    /*
    * Test for inserting a new node into a tree
    */
    it should "insert a new node into a tree" in {
        
        val leafList = List (new b.Leaf('a',1), new b.Leaf('c',3) )
        val leaf = new b.Leaf('b',2)
        val inserted = b.insert(leaf,leafList)
        assert(inserted == List ( new b.Leaf('a',1) ,new b.Leaf('b',2) ,new b.Leaf('c',3) ) )
        
    }
   
  
    
    /*
    * Test for checking the function that connects two leaf nodes into a branch
    */
    it should "make a link between two nodes" in {
        
        val leaf1 = new b.Leaf('f', 1 )
        val leaf2 = new b.Leaf('e', 3 )
        
        val branch = b.makeLink(leaf1,leaf2)
        assert(branch.toString == "Branch(4,Leaf(f,1),Leaf(e,3))")
        
    }
    
    /*
    * Test for checking the function that connects a leaf to a branch
    */
    it should "make a link between a branch and a leaf" in {
        
        val leaf1 = new b.Leaf('d', 5 )
        val Branch = new b.Branch(4, new b.Leaf('e',1), new b.Leaf('f',3))
        val branch = b.makeLink(leaf1,Branch)
        assert(branch.toString == "Branch(9,Branch(4,Leaf(e,1),Leaf(f,3)),Leaf(d,5))")
        
    }
    /*
    * Test merge for branch statement coverage test merge on single input and multiple input
    */
    it should "test for single HTree after merge" in{
        
        val leaf1 = new b.Leaf('d', 5 )
        val Branch = new b.Branch(4, new b.Leaf('e',3), new b.Leaf('f',1))
        val list = List(leaf1,Branch)
        val link = b.merge(list)
        val linkList = List(link)
		
        assert(linkList.length === 1)
		
		
		val singleLeaf = List(leaf1)
		val tree = b.merge(singleLeaf)
		
		assert(List(tree).length === 1)
			

    }
    
    
    /*
    * Test makeTree function is making the tree 
    */
    it should "take a string and make a tree" in {
        
        val tree = b.makeTree("Hello")
        assert(tree.toString =="Branch(5,Leaf(l,2),Branch(3,Leaf(o,1),Branch(2,Leaf(e,1),Leaf(H,1))))")
    }
	
	 /**
    * Tests every branch in the encode function
    */
    
    it should " cover all encode branch statements" in{
        
        val emptyString = ""
        
        val empty = h.encode(emptyString)
        
        assert(empty._1.length === 0 & empty._1.length === 0) // Both lists should equal 0
        
        val singleString = "s"
        
        val one = h.encode(singleString)
        
        assert(one._1.length === 1 & one._2.length === 1)  // Both lists should equal 1
        
        val encoded = h.encode(testString)
        assert(testString !== 1 & encoded._1.length !== 1) // Both lists should not equal 1
        
    }
    
    
    
    /**
    * A test for checking if the the codemap is generating correctly
    */
    it should "check extracting code is functioning" in {
        
        val singleChar = h.extractCode(h.makeTree("h"),List(),List())
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
        
        assert(res.toString ==="List(2, 1, 1, 1, 2, 1, 1, 0, 2, 0, 2, 0, 2, 1, 0)")
        
    }
    
    
   /**
    * A test for collecting leaf nodes and checking if the correct number exist in the tree when compared to the size of the frequency table
    */
    it should "count the leaf nodes" in {
        
        val freq = h.freq("hello",List())
        val code = h.makeTree("hello")
        val leafNum = h.collectLeaves(code,x)
        
        assert(freq.length === leafNum.length)
        
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
	
}