import my.android.project


import org.scalatest.FunSpec

class Tests extends FunSpec{
 
 test("count"){
 
 val b = new builder
 val result = b.count("aaa")
 assert(result == 3)
 
 }
 
 test("Frequency"{
 
 val b = new builder 
 val result = freq("hey")
 
 
 } 
 
 test("Insert"){
  
 val b = new builder
 val result  = b.insert(new Leaf(2,'b'), List(new Leaf(1,'a'), new Leaf(3,'c'))  )
 assert(result == List(Leaf(1,'a'), Leaf(2,'b'), Leaf(3,'c') ) )
 
 }
 
 test("encode/decode"){
 
 val h = new handler
 val s = "hello"
 val resultEncode = h.encode(s)
 val resultDecode = h.decode(resultEncode)
 assert(resultDecode == "hello")
  
 }

 
 
 
 }
