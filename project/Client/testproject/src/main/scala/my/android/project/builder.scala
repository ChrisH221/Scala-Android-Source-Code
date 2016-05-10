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
    
    def freq(y:String, list:List[(Char,Int)]): List[(Char,Int)] = {
        
        if(y.isEmpty) list.reverse
        else{
            val char = y.head
            val countFreq = count(char,y)
            val t = new Tuple2(char,countFreq)
            freq(y.filter{ x => x != char }, t::list)
        }   
        
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
        
        
        val list = Tree::HTree
        
        list.sortBy(x => freqNode(x))
        
        
    }
    
    /*
    * Make a new link between two HTree nodes
    * @returns HTree
    */
    
    def makeLink(t1: HTree, t2:HTree): HTree = {
        
        if (freqNode(t1) < freqNode(t2)) Branch(freqNode (t1) + freqNode (t2),t1,t2)
        
        
        else  Branch(freqNode (t1) + freqNode (t2),t2,t1)
        
    }
    
    /*
    * Calls the insert method to place a new HTree node into a list
    * of HTree nodes
    * @returns  List[HTree]
    */
    def shrinkList(t1:HTree, t2:List[HTree]): List[HTree]={insert(t1,t2)}
    
    /*
    * Merges a list of Leaf nodes into a single HTree by creating connecting Branch nodes to hold the Leaf nodes
    * @returns HTree
    */
    def merge(t:List[HTree]): HTree ={
        

		if(t.length < 1) new Empty()
		
        if(t.length == 1 ) t.head
        
        else merge(shrinkList(makeLink(t(0), t(1)), t.drop(2)))
        
    }
    
    /*
    * Takes a list frequency table and generate a list of HTree nodes
    */
    
    def makeNode(list:List[(Char,Int)],nodeList:List[HTree]):List[HTree] ={
        
        val leafList = scala.collection.mutable.MutableList[Leaf]()
        list.foreach{x =>leafList += (new Leaf(x._1,x._2)) }
        leafList.toList.sortBy(x => freqNode(x))
        
    }
    
    /*
    * Turns an input string into a HTree
    * @returns HTree
    */
    
    def makeTree(s:String): HTree ={merge(makeNode(freq(s,x),x))}
    
    
    
}