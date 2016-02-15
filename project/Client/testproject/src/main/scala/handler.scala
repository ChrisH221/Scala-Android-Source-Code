package my.android.project
import my.android.project.builder
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
  def extractCode(t:HTree,acc:List[Bit],newList:List[(Char,List[Bit])]):List[(Char,List[Bit])] = t match{

    case t:Leaf =>(t.char,acc)::newList
    case t:Branch =>  extractCode(t.left,new Zero::acc,newList):::extractCode(t.right,new One::acc,newList)
    case nil => List()


  }
  
  /*
   * A function for taking a string and generates a list of bits   
   * @returns List[Bit}
   */
  def bitList(s:String, acc:List[Bit],list:List[(Char,List[Bit])],list2:List[(Char,List[Bit])]):List[Bit]={
    
	
	if (s.isEmpty || list.isEmpty) acc
	

    else if(list.head._1.equals(s.toCharArray.head)) bitList(s.drop(1), acc:::list.head._2, list:::list2,x)
	

    else  bitList(s, acc,list.drop(1), list.head::list2)
  }
  
  
  /*
   * Encode function takes a string then calls bitList() and extractCode() to generate a pair containing the list of
   * bits and another pair containing char/bit combinations 
   * @returns Pair(List[Bit} , HCodeMap)
   * 
   */

  def encode(s:String):(List[Bit],List[(Char,List[Bit])])={



    if(s.isEmpty) return (x,x)

    if (s.length > 1 ) (bitList(s,x,extractCode(makeTree(s),x,x),x).reverse,extractCode(makeTree(s),x,x))

    else (List(new Zero),List((s.charAt(0),List( new Zero))))

  }

  /*
   *A lookup function that searches the HCodeMap for matching bit patterns
   * @returns Option String
   */

  def lookup(list:List[Bit],list2:List[(Char,List[Bit])]): Option[String]={

    if(list2.isEmpty)  None

    else if(list.reverse == list2.head._2) Some(list2.head._1.toString)

    else lookup(list,list2.drop(1))

  }

  /*
   *Decode function for matching groups of bits in a list to characters in the HCodeMap
   *@returns String
   */

  def decode(acc:Int,s:String,list:(List[Bit],List[(Char,List[Bit])])):String ={

    if(list._2.isEmpty) s

    if(list._1.isEmpty) s.reverse

    else if(lookup(list._1.take(acc),list._2).isDefined){

      val x = s ++ lookup(list._1.take(acc),list._2).getOrElse("a")

      if(list._2.length == 1)  x
      else decode(1,x, (list._1.drop(acc),list._2))

    }

    else decode(acc+1,s, list)



  }

}