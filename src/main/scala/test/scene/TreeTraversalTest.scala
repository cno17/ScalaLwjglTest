package test.scene

import yage.scene.Node

object TreeTraversalTest:

/*
a0
  b0
    c0
    c1
      d0
      d1
    c2
  b1
*/

  def main(args: Array[String]): Unit =
    val a0 = Node("a0")
    val b0 = a0.addChild(Node("b0"))
    val b1 = a0.addChild(Node("b1"))
    val c0 = b0.addChild(Node("c0"))
    val c1 = b0.addChild(Node("c1"))
    val c2 = b0.addChild(Node("c2"))
    val d0 = c1.addChild(Node("d0"))
    val d1 = c1.addChild(Node("d1"))
    a0.printTree()
    updateTrafosAndBounds(a0)

  def updateTrafosAndBounds(node: Node): Unit =
    println(s"${node.name}: trafo updated")
    node.foreachChild(n => updateTrafosAndBounds(n))
    println(s"${node.name}: bound updated")
