package my.android.project
import my.android.project.builder
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
  def extractCode(t:HTree,acc:List[Int],newList:List[(Char,List[Int])]):List[(Char,List[Int])] = t match{

    case t:Leaf =>(t.char,acc.reverse)::newList
    case t:Branch =>  extractCode(t.left,0::acc,newList):::extractCode(t.right,1::acc,newList)
    case nil => List()


  }
 
  /*
   * A function for taking a string and generates a list of bits   
   * @returns List[Bit}
   */
  def bitList(s:String,count:Int, acc:List[Int],list:List[(Char,List[Int])],list2:List[(Char,List[Int])]):List[Int]={
 
  
 def lookupBit(c: Char, list: List[(Char, List[Int])]): Option[List[Int]] =  list.find(_._1 == c).map(_._2)
 
   val mutableListBuffer = scala.collection.mutable.ArrayBuffer(list: _*)
    val ListBuffer = scala.collection.mutable.ArrayBuffer(acc: _*)	
   var string = s

   var incre = 0
   while(!string.isEmpty){
    
	
   
   if(mutableListBuffer(incre)._1 == string.head){
	
   mutableListBuffer(incre)._2.foreach{x => ListBuffer += x}
   string = string.drop(1)
 
   incre = 0
   }
   else incre = incre + 1
	if(incre >= mutableListBuffer.length)  string = ""
   }
   
  
	ListBuffer.toList.reverse
 }

  
  
  
  /*
   * Encode function takes a string then calls bitList() and extractCode() to generate a pair containing the list of
   * bits and another pair containing char/bit combinations 
   * @returns Pair(List[Bit} , HCodeMap)
   * 
   */

  def encode(s:String):(List[Int],List[(Char,List[Int])])={



    if(s.isEmpty) return (x,x)

    if (s.length > 1 ) {

	
	(bitList(s,0,x,extractCode(makeTree(s),x,x),x).reverse,extractCode(makeTree(s),x,x).reverse)
	
	}
    else (List(0),List((s.head,List( 0))))

  }
  
 // def prettyPrint(l:List[Bit]):String ={
  
 // l.foreach{ x => x.}
  
  
 // } 
  

  /*
   *A lookup function that searches the HCodeMap for matching bit patterns
   * @returns Option String
   */

def lookup(list: List[Int], list2: List[(Char, List[Int])]): Option[String] = 
 list2.find(_._2 == list).map(_._1.toString)

/*
*Decode function for matching groups of bits in a list to characters    in     the HCodeMap
*@returns String
*/

 def decode(s: StringBuffer, list: (List[Int], List[(Char, List[Int])])): String = {


  var accum = 1
  var listp1 = list._1
  val listp2 = list._2

  while (!listp1.isEmpty) {

    lookup(listp1.take(accum), listp2) match {
      case Some(m) =>
        s.append(m)
        listp1 = listp1.drop(accum)
        accum = 1
      case None =>
        accum = accum + 1
    }
  }
  s.toString
}
  
  
  

}