package test.base.nanovg

import yage.base.nanovg.state.vgTrafo

object TrafoTest:

  val ra = 3.1415f / 2

  def main(args: Array[String]) =
    val t = vgTrafo()
    t.toRotation(ra).translateLeft(2, 3)
    println(t)
    t.toRotation(ra).translate(2, 3)
    println(t)