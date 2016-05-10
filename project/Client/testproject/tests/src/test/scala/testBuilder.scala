package my.android.project

import org.scalatest.FlatSpec

/**
* @author Chris Howell
*
* Tests for the builder class.
*/
class testBuilder extends FlatSpec  with testHelpers {
    
    
    
    
    /*
    * A test for checking if frequency table generates correctly.
    * The test is run multiple times and tests randomly generated strings from the testHelper trait
    */
    it should "check freq function is running correctly" in {
        
        
        listString.foreach{x =>
		
            var t = x
            
            var listF = b.freq(x, List())
            
            for (i <- 0 until listF.length-1 ) {
                
                assert(listF(i)._1 === (t.head))
                t = t.filter{x => x != t.head}
                
            }
            
            
            
        }
        
    }
    
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
    * Test for inserting a new node into a list of leaf nodes.
    * Uses the helper trait leaf list to test over multiple lists of leaf nodes
    */
    it should "insert a new node into a list of leaf nodes and check is in descending order" in {
        
        listLeaf.foreach{x =>
            var y = x
            y = h.insert(new h.Leaf('d',n.nextInt(100) + 2),y)
            
            for (i <- 0 until y.length ) {
                
                
                if(i != y.length - 1) assert(h.freqNode(y(i)) <= h.freqNode(y(i+1)))
				// to ensure the last node is checked against the previous node
                if(i == y.length-1)  assert(h.freqNode(y(i)) >=  h.freqNode(y(i-1))) 
            }
            
            
            
        }
        
        
        
        
    }
    
    
    /*
    * Test for inserting a new node into a list of leaf nodes.
    * Uses the helper trait leaf list to test over multiple lists of leaf nodes
    */
    it should "merge a list of HTree nodes into a single HTree" in {
        
        
        listLeaf.foreach{ x => assert(List(h.merge(x)).length === 1)}
        
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
    * Test for inserting a new node into a list of leaf nodes.
    * Uses the helper trait leaf list to test over multiple lists of leaf nodes
    */
    it should "take a string and make a tree" in {
        
        val tree = b.makeTree("What's the story?")
        assert(tree.toString =="Branch(17,Branch(8,Branch(4,Branch(2,Leaf(a,1),Leaf(W,1)),Branch(2,Leaf(e,1),Leaf(',1))),Branch(4,Leaf(s,2),Leaf(h,2))),Branch(9,Branch(4,Branch(2,Leaf(r,1),Leaf(o,1)),Branch(2,Leaf(?,1),Leaf(y,1))),Branch(5,Leaf( ,2),Leaf(t,3))))")
        
    }
}