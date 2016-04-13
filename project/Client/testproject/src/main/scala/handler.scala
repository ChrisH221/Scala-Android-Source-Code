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
  def extractCode(t:HTree,acc:List[Int],newList:List[(String,List[Int])]):List[(String,List[Int])] = t match{

    case t:Leaf =>(t.char,acc.reverse)::newList
    case t:Branch =>  extractCode(t.left,0::acc,newList):::extractCode(t.right,1::acc.reverse,newList)
    case nil => List()


  }
  
  /*
   * A function for taking a string and generates a list of bits   
   * @returns List[Bit}
   */
  def bitList(s:String,count:Int, acc:List[Int],list:List[(String,List[Int])],list2:List[(String,List[Int])]):List[Int]={
 
   val mutableListBuffer = scala.collection.mutable.ArrayBuffer(list: _*)
    val ListBuffer = scala.collection.mutable.ArrayBuffer(acc: _*)	
   var string = s

   var incre = 0
   while(!string.isEmpty){
    
	
   
   if(mutableListBuffer(incre)._1.equals(string.take(bp))){
	
   mutableListBuffer(incre)._2.foreach{x => ListBuffer += x}
   string = string.drop(bp)
 
   incre = 0
   }
   else incre = incre + 1
	if(incre >= mutableListBuffer.length)  string = ""
   }
   

	ListBuffer.toList.reverse
 }

  // var c = count
//	println(list(c)._1)
	//println(list.head._1)
	//if (s.isEmpty) acc.reverse
	
	
    //else if(list(c)._1.equals(s.take(2))){
	//c = 0
	//println(acc)
	//val string = s.drop(2)
	//bitList(string, c , acc:::list.head._2, list:::list2,x)
	
//	}
	
   // else  bitList(s, c+1 ,acc,list, list.head::list2)
	
  
  
  
  
  /*
   * Encode function takes a string then calls bitList() and extractCode() to generate a pair containing the list of
   * bits and another pair containing char/bit combinations 
   * @returns Pair(List[Bit} , HCodeMap)
   * 
   */

  def encode(s:String):(List[Int],List[(String,List[Int])])={



    if(s.isEmpty) return (x,x)

    if (s.length > 1 ) {
	 Log.d("MyTAG","the size before" + s.length.toString)
	 bp = math.ceil(s.length / 50).toInt
	  Log.d("MyTAG","the size after" + bp.toString)
	(bitList(s,0,x,extractCode(makeTree(s),x,x),x).reverse,extractCode(makeTree(s),x,x).reverse)
	
	}
    else (List(0),List((s,List( 0))))

  }
  
 // def prettyPrint(l:List[Bit]):String ={
  
 // l.foreach{ x => x.}
  
  
 // } 
  

  /*
   *A lookup function that searches the HCodeMap for matching bit patterns
   * @returns Option String
   */

def lookup(list:List[Int],list2:List[(String,List[Int])]): Option[String]={
	
	
	val mutableListBuffer = scala.collection.mutable.ArrayBuffer(list2: _*)
	var option:Option[String]= None
   
   for(i <- 0 until mutableListBuffer.length){
   
  if(mutableListBuffer(i)._2 == list){
 
 option =  Some(mutableListBuffer(i)._1.toString)
  
  }
 
   }

	option
	

  }

  /*
   *Decode function for matching groups of bits in a list to characters in the HCodeMap
   *@returns String
   */

 def decode(acc:Int,s:String,list:(List[Int],List[(String,List[Int])])):String ={

    if(list._2.isEmpty) s

    if(list._1.isEmpty) s.reverse

    else if(lookup(list._1.take(acc),list._2).isDefined){

      val x = s ++ lookup(list._1.take(acc),list._2).getOrElse("a")

      if(list._2.length == 1)  x
	  
      else decode(1,x, (list._1.drop(acc),list._2))

    }

    else decode(acc+1,s, list)



  }
  
  def decode2(acc:Int,s:String,list:(List[Int],List[(String,List[Int])])):String ={
  
  var s = ""
  var accum = 1
  val listp1 = scala.collection.mutable.ArrayBuffer(list._1: _*)
  val listp2 = scala.collection.mutable.ArrayBuffer(list._2: _*)
  var tupList = (listp1,listp2)
  
	println(tupList)
   while(!tupList._1.isEmpty){
  
   if(lookup(tupList._1.take(accum).toList,tupList._2.toList).isDefined){
   println(accum)
   s = s ++ lookup(tupList._1.take(accum).toList,tupList._2.toList).getOrElse("a")
  println(s)

  tupList._1.remove(0,accum)
 
   accum = accum - accum + 1
   }
   else{
    accum =  accum + 1
   }
   
   
   
   }

  s


  }
  

  
  
  

}