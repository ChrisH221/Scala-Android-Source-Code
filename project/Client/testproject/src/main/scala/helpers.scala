package my.android.project

import scala.language.implicitConversions
import android.view.View

trait helpers {


 val x = List()//Saved me creating multiple empty lists
 
  abstract class HTree
  case class Empty() extends HTree
  case class Branch(value: Int, left: HTree, right: HTree) extends HTree
  case class Leaf(freq: Int, char: Char) extends HTree

  /* 
   * Define an implicit definition for OnClickListener
   * 
   */ 
  
	implicit def onClickListener(f: (View => Unit)): View.OnClickListener = {
		new View.OnClickListener() {
				override def onClick(v: View) {
			f(v)
			}
		}
		}

  /* 
   * Define a single bit as a sealed trait that can be either One, Zero or EmptyBit
   * 
   */
  sealed trait Bit
  case class EmptyBit() extends Bit
  case class Zero() extends Bit
  case class One() extends Bit


  sealed trait HMap
  case class HCodeMap(list:List[(Char,List[Bit])]) extends HMap //TODO implement HCodeMap as input/output
 
  implicit def toRunnable[A](f: => A): Runnable =  new Runnable() { def run() = f } 




}