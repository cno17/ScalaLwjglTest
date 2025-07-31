package yage.scene

import scala.collection.mutable.ListBuffer

/**
 * Assumptions:
 * - a root node has no siblings
 *
 */

class Node(n: String, p: Node) extends Spatial(n, p):

  def this(name: String) = this(name, null)

  val children = ListBuffer[Node]()

  def isAncestorOf(n: Node) = false

  def isDescendantOf(n: Node) = false

  def addChild(n: Node): Node =
    n.parent = this
    children += n
    n

  def removeChild(n: Node): Boolean =
    if !children.contains(n) then return false
    n.parent = null
    children -= n
    true
  
  def foreachAncestor(f: Node => Unit): Unit =
    var n = parent
    while n != null do
      f(n)
      n = n.parent

  def foreachSibling(f: Node => Unit): Unit =
    if parent == null then return
    for n <- parent.children do
      if n != this then f(n)

  def foreachChild(f: Node => Unit): Unit =
    children.foreach(f)

  def foreachDescendantDF(f: Node => Unit): Unit =
    foreachChild(n =>
      f(n)
      n.foreachDescendantDF(f)
    )

  def foreachDescendantBF(f: Node => Unit): Unit =
    foreachChild(n => f(n))
    foreachChild(n => n.foreachDescendantBF(f))

  def printTree(indent: Int = 4): Unit =
    println(" " * indent + name)
    foreachChild(c => c.printTree(indent + 4))

  override def toString() =
    val p = if parent == null then "null" else parent.name
    s"($name: pn = $p)"


