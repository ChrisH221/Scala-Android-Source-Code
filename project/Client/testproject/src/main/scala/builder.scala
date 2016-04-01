package my.android.project

/**
 * @author Chris Howell

 *
 * The builder class contains all the functions required to turn a string into a single HTree
 */
  class builder extends helpers{

  
  /*
  *  A function to count the number of times a character appears in a string
  *  @returns Int
  */
  def count(c:Char, y:String): Int = {

   y.filter{ t => t== c }.length()

  }

  /*
   * Uses pattern matching to create a list containing each frequency for each char in a string
   * that matches the input char
   * @returns List[Pair(Char,Int)]
   */

  // def freq2(y:String):List[(Char,Int)]={
   
//   y.toCharArray.foreach{ x => }
   
//   }
   
  def freq(y:String, list:List[(Char,Int)]): List[(Char,Int)] = {

    if(y == null || y.isEmpty) list.sortBy(_._2).reverse
    else{
      val char = y.head
      val countFreq = count(char,y)
      val t = new Tuple2(char,countFreq)
      freq(y.filter{ x => x != char },list:+t)
    }

    //TODO case instead of if/else
    // case "" => List(('h',1))
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

  def insert(Tree:HTree, HTree:List[HTree], newList:List[HTree]): List[HTree] ={

    //TODO Idiomatic approach
    //val list = Tree::HTree

    //list.sortBy(x => x.freq)
	
	
	 if(HTree.isEmpty) newList:::Tree::HTree

    else{

      if(freqNode(Tree) <= freqNode (HTree.head)){

	  insert(Tree, HTree.drop(1), newList:::HTree.take(1))

	  }
      else  newList:::Tree::HTree


    }

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
   * Calls the insert method to place a new HTree node into a list
   * of HTree nodes 
   * @returns  List[HTree]
   */
  def shrinkList(t1:HTree, t2:List[HTree]): List[HTree]={

    insert(t1,t2,x)

  }

  /*
   * Merges a list of Leaf nodes into a single HTree by creating connecting Branch nodes to hold the Leaf nodes
   * @returns HTree
   */
  def merge(t:List[HTree]): HTree ={


    if(t.length == 1 ) t.head
    else merge(shrinkList(makeLink(t.take(1).head, t.slice(1,2).head), t.drop(2)))


  }

  /*
   * Takes a list if nodes and returns a
   */

  def makeNode(list:List[(Char,Int)],nodeList:List[HTree]):List[HTree] ={

    if(list.isEmpty){
	nodeList
	}
    else makeNode(list.drop(1),Leaf (list.head._2,list.head._1)::nodeList)

  }

  /*
   * Turns an input string into a HTree
   * @returns HTree
   */

  def makeTree(s:String): HTree ={

    if(s != null) merge(makeNode(freq(s,x),x))

    else Empty()

  }



}


