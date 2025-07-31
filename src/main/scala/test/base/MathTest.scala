package test.base

object MathTest:

  def main(args: Array[String]) =
//    val rndS = scala.util.Random()
//    for i <- 1 to 100 do
//      println(rndS.nextFloat())
    val rndJ = java.util.Random()
    for i <- 1 to 100 do
      println(rndJ.nextFloat())
