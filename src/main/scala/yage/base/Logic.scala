package yage.base

object Logic:

  extension (x: Boolean)
    def not = !x
    def and(y: Boolean) = x && y
    def or(y: Boolean) = x || y
    def xor(y: Boolean) = x ^ y

  // def not(p1: Boolean) = !p1
  // def and(p1: Boolean, p2: Boolean) = p1 && p2
  // def or(p1: Boolean, p2: Boolean) = p1 || p2
  // def xor(p1: Boolean, p2: Boolean) = p1 ^ p2
  
  def main(args: Array[String]) =
    println(false and true)
    println(not(false))
    println(not(false or 1 > 0))

