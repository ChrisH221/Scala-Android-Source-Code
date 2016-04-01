package my.android.project

import org.scalatest.FlatSpec
import scala.collection.mutable.Stack
import my.android.project.builder
import my.android.project.helpers
import scala.collection._




class testStringToMap extends FlatSpec {




 it should "return a list" in {
   val list = mutable.MutableList[(Char,String)]()
   var lString = "@/=/000/n/b/=/100/n/8/=/010/n/EOF"
   var secString = ""
   var section = ""
  
   	
  while (section != "EOF"){
  
  var secString = ""
   

 
	val key = lString.take(1)
	lString = lString.drop(1)
	
	section = lString.take(3)
	
		
   if (section == "/=/"){
    lString = lString.drop(3)   
   while (section != "/n/"){
   
  
  
    section = lString.take(1)
   
    secString  =  secString ++ section
   
   
   lString = lString.drop(1)
   
    section = lString.take(3)
   
 
   
   }
   

   lString.drop(3)
  var tup =(key.toCharArray.head,secString)

   list += tup

   section = lString.take(3)  
   }
   
  
   
   }
   
 
    }
	
	}
 
 
	
	
	
	
