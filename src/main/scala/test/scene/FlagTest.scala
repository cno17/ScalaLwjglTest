package test.scene

import yage.scene.Node

object FlagTest:

  val RF_Trafo = 1
  val RF_Bound = 2

  var refreshFlags = 0

  def main(args: Array[String]): Unit =
    println((refreshFlags & RF_Trafo) == 0)
    // set flag
    refreshFlags |= RF_Trafo
    println((refreshFlags & RF_Trafo) == 0)
    // clear flag
    refreshFlags &= ~RF_Trafo
    println((refreshFlags & RF_Trafo) == 0)
