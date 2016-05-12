package my.android.project

import org.scalatest.FlatSpec

/**
* @author Chris Howell
*
* Tests for the builder class.
*/
class propertyTests extends FlatSpec  with testHelpers {
    
    
    
    
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
    
   /**
    * Test the encode and decode function on a list of randomly generated strings
    */
    it should "check multiple encode/decode with random strings " in {
        
        listString.foreach{x =>
            
            val encoded = h.encode(x)
            val decode = h.decode(encoded)
            assert(decode ===x)
            
        }
        
        
    }

   /**
    * A test for counting the number of leaf nodes in a tree
    * and checking if the result equals the same size as the freqency table.
    * Runs multiple times as it iterates over the list of strings
    * generated in the testHelper trait.
    */
    it should "count the number of leaf nodes to check consistant with freqency table" in{
        
        listFreq.foreach{x =>
            
            val code = h.merge(h.makeNode(x,List()))
            val leafNum = h.countLeaves(code,List()).reduce((x:Int, y:Int) => x + y)
            assert(x.length === leafNum)
            
        }
        
    }

}