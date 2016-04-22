package my.android.project

import org.scalatest.FlatSpec



class testHandler extends FlatSpec with helpers {

	val h = new handler

 

    it should "check extracting code is functioning" in {
  
    val code = h.extractCode(h.makeTree("hello"),List(),List())
	assert(code.toString ===  "List((l,List(0)), (o,List(1, 0)), (e,List(1, 1, 0)), (h,List(1, 1, 1)))")
		 
    }
	
	it should "look up a find a bit combination to make the char  " in {
	
	val pair1 = ('c', List(0,0,1))
	val pair2 = ('d', List(1,0,0))
	val list = List(pair1,pair2)
	val result = h.lookupBit('d', list)
	assert(result.toString === "Some(List(1, 0, 0))")
	
	}
	
	
	it should "check bit list is functioning" in {
  
 
	val code = h.extractCode(h.makeTree("hello"),x,x)
	val res = h.bitList("hello",code).reverse
	
     assert(res.toString ==="List(1, 1, 1, 1, 1, 0, 0, 0, 1, 0)")
		 
    }
	
	it should "count the number of leaves to check consistant with freqency table" in{
	
	val freq = h.freq("hello",List())
	val code = h.makeTree("hello")
	val leafNum = h.countLeaves(code,x).reduce((x:Int, y:Int) => x + y)
	assert(freq.length === leafNum)
	
	}

	it should "check bitlist builder is functioning" in {
	
    }
	
	

	it should "check encode and decode " in {

	val testString = "Alan Mathison Turing OBE FRS  was a pioneering English computer scientist, mathematician, logician, cryptanalyst and theoretical biologist."
	val encoded = h.encode(testString)
	val decode = h.decode(new StringBuffer(),encoded)
	assert(decode ===testString)
	
	}
	
}