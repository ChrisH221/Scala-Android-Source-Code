package my.android.project
import scala.collection._
import scala.collection.mutable.ArrayBuffer
/**
 * @author Chris Howell
 *
 * The builder class contains all the functions required to turn a string into a single HTree
 */
  class builder extends helpers{

  
 
  /*
   * Uses pattern matching to create a list containing each frequency for each char in a string
   * that matches the input char
   * @returns List[Pair(Char,Int)]
   */

  def freq(y:String, list:List[(Char,Int)]): List[(Char,Int)] = {

		val list2 = scala.collection.mutable.ArrayBuffer(list: _*)
		var string = y
		
		while(!string.isEmpty){
		
		string.count(_ == string.head)
		var tup = (string.head,string.count(_ == string.head))
		list2+=tup
		string = string.filter(x => x != string.head)
		
		
		}

		list2.toList

    // case "" => List()
    // case y  =>  freq(c, y.filter( x => x == c),((c,count(c,y)) :: list))
    // case y::ys => (y,count  _ y)::freq  (y.filter (_ != y) y, list)


  }
  /*
   * Pattern matching to pull values from a HTree leaf or branch
  * @returns HTree
  */
  def freqNode(tree:HTree): Int = tree match
  {

    case tree:Leaf => tree.freq
    case tree:Branch => tree.value
    case nil => -1

  }

  /*
   * Insert a new HTree node into a list
    *@returns List[HTree]
   */

  def insert(Tree:HTree, HTree:List[HTree]): List[HTree] ={

	val list = scala.collection.mutable.ArrayBuffer(HTree: _*)
	val list2 = scala.collection.mutable.ArrayBuffer(HTree: _*)
    var incre = 0
	var finish = false
	if(list.length > 1){
	
	while(!finish){
	
	if(freqNode(Tree) <= freqNode (HTree(incre))){}
	else {
	
	list2 ++ list.take(incre+1)
	list2 += Tree
	list2 ++ list.drop(incre+1)
	finish = true

	}
	incre = incre + 1
	
	}
	}
	else list2 += Tree
	list2.toList.sortBy(x => freqNode(x))





  }

  /*
   * Make a new link between two HTree nodes 
   * @returns HTree
   */


  def makeLink(t1: HTree, t2:HTree): HTree = {
	
    if(freqNode(t1) < freqNode(t2)) {

	Branch(freqNode (t1) + freqNode (t2),t1,t2)
	
	}

    else  Branch(freqNode (t1) + freqNode (t2),t2,t1)

  }

 

  /*
   * Merges a list of Leaf nodes into a single HTree by creating connecting Branch nodes to hold the Leaf nodes
   * @returns HTree
   */
  def merge(t:List[HTree],t2:List[HTree]): HTree ={
	var incre = 0
	var list = scala.collection.mutable.ArrayBuffer(t: _*)

	var in = List().asInstanceOf[List[HTree]]
	var chunk = List().asInstanceOf[List[HTree]]
	var link = makeLink(list(0), list(1))
	
	while (list.length != 1 ){
	println(list.toString)
	
	
	
	if (list.size == 2) {
	chunk = list.toList.take(2)
	
	in = insert(link,chunk)
	list = list.drop(2) 

	}
	else {
	list = list.drop(2) 
	
	chunk = list.toList.take(2)

	in = insert(link,chunk)
	
	
	}
	
	in.foreach{x => if(!list.contains(x)) list += x }
	
	
	if(list.length != 1) link = makeLink(list(0), list(1))
	}
	
	list.toList.head
	
   

  }

  /*
   * Takes a list if nodes and returns a
   */

  def makeNode(list:List[(Char,Int)],nodeList:List[HTree]):List[HTree] ={

    if(list.isEmpty){
	nodeList
	}
    else makeNode(list.drop(1),Leaf (list.head._1,list.head._2)::nodeList)

  }

  /*
   * Turns an input string into a HTree
   * @returns HTree
   */

  def makeTree(s:String): HTree ={

    if(s != null) merge(makeNode(freq(s,x),x),x)

    else Empty()

  }
  




}
