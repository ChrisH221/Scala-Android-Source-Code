package my.android.project

import scala.collection._
import scala.annotation.tailrec
import android.util.Log
/**
* @author Chris Howell
*
* The class for encoding and decoding strings into binary
*/
class handler extends builder{
    /*
    * Matches a list of bits to each char occurance in the HTree by following the left and right
    * hand children and adding either a One or Zero bit depending on the route taken
    * @returns HCodeMap
    *
    */
    def extractCode(t:HTree,acc:List[Int],newList:List[(Char,List[Int])]):List[(Char,List[Int])] ={
        
				
        t match{
            
            case t:Leaf =>(t.char,acc.reverse)::newList
            case t:Branch =>  extractCode(t.left,0::acc,newList):::extractCode(t.right,1::acc,newList)
            case nil => List()
        }
        
    }
    
    /*
    * A function for testing the number of leaf nodes to check consistancy with the frequency table length and amount of leaf nodes in the tree
    * @returns List[Int]
    */
    def countLeaves(t:HTree,acc:List[Int]):List[Int] ={
        
        t match{
            
            case t:Leaf =>1::acc
            case t:Branch =>  countLeaves(t.left,acc):::countLeaves(t.right,acc)
            case nil => List(0)
        }
        
    }
    
    /*
    * A function removing the leaf nodes from a HTree and placing them inside a list of HTree nodes.
    * @returns List[Int]
    */
    
    def collectLeaves(t:HTree,acc:List[HTree]):List[HTree] ={
        
        t match{
            
            case t:Leaf =>new Leaf(t.char,t.freq)::acc
            case t:Branch =>  collectLeaves(t.left,acc):::collectLeaves(t.right,acc)
            case nil => List()
        }
        
    }
    
    
    
    def lookupBit(c: Char, list: List[(Char, List[Int])]): Option[List[Int]] =  list.find(_._1 == c).map(_._2)
    
    
    /*
    * A function for taking a string and generates a list of bits
* @returns List[Bit}
*/
def bitList(s:String,list:List[(Char,List[Int])]):List[Int]={
    
    val t = s.map(x => lookupBit(x,list))
    val list2 = scala.collection.mutable.MutableList[List[Int]]()
    t.toList foreach{
        _ match {
            
            case Some(m) => list2 += 2 :: m
            
            case None => println("Something wrong")
        }
    }
    
    list2.toList.flatten.reverse
    
}


/*
* Encode function takes a string then calls bitList() and extractCode() to generate a pair containing the list of
* bits and another pair containing char/bit combinations
* @returns Pair(List[Bit} , codemap)
*
*/

def encode(s:String):(List[Int],List[(Char,List[Int])])={
    
    
    
    if(s.isEmpty) return (x,x)
    
	
    if (s.length > 1 ) {
        
        
   (bitList(s,extractCode(makeTree(s),x,x)). reverse,extractCode(makeTree(s),x,x).reverse)
        
    }
    else (List(0),List((s.head,List(0))))
    
}



def decode(list: (List[Int], List[(Char, List[Int])])):String ={
    
    var bitList = list._1.mkString
    if(bitList.length == 1) bitList = "2"++bitList
    list._2.foreach{x =>bitList = bitList.replaceAll(("2"  + x._2.mkString),x._1.toString)}
    bitList
}


}