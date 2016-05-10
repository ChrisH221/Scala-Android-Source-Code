package my.android.project

import org.scalatest.FlatSpec
import scala.util.Random

/**
* @author Chris Howell
*
* Helper trait for the tests generates random data.
*/

trait testHelpers extends helpers  {
    
    val h = new handler
    val b = new builder
    val n = scala.util.Random
    var listFreq = List(List(('c',1)))
    var listString = List[String]()
    var incre = n.nextInt(10000)
    var testString = Random.alphanumeric.take(n.nextInt(100) + 2 ).mkString
    listFreq = listFreq.drop(1)
    var listLeaf:List[List[h.HTree]] = List(List(new h.Leaf('c',1)))
   
    listLeaf = listLeaf.drop(1)
	
    while (incre > 0){
        
        testString = Random.alphanumeric.take(n.nextInt(100 + 2)).mkString
		if(testString.length > 1){
		listString = testString::listString
        listFreq = h.freq(testString,List())::listFreq
        listLeaf = h.makeNode(h.freq(testString,List()),List()).reverse::listLeaf
        }
        
        incre = incre - 1
        
    }


    
    
}