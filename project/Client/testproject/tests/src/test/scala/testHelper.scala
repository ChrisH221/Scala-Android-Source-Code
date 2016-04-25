package my.android.project

import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitSuite
import org.scalatest.prop.Checkers
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalacheck._
import scala.util.Random 

trait testHelpers  {

	val h = new handler
    val n = scala.util.Random
    var listFreq = List(List(('c',1)))
	var listString = List[String]()
	var incre = 10
	var testString = Random.alphanumeric.take(n.nextInt(100)).mkString 
	
	listFreq = listFreq.drop(1)
	while (incre > 0){
	
	testString = Random.alphanumeric.take(n.nextInt(100)).mkString 
	listString = testString::listString
	listFreq = h.freq(testString,List())::listFreq
	incre = incre - 1
			
	}
	
	
	
	
	
	}