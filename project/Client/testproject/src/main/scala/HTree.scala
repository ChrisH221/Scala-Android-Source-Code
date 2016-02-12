 /*
   * Defines the HTree trait that can be either a branch node or a leaf node
   */
  sealed trait HTree
  case class Empty() extends HTree
  case class Branch(value: Int, left: HTree, right: HTree) extends HTree
  case class Leaf(freq: Int, char: Char) extends HTree
