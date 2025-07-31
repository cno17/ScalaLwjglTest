package test.base.nanovg

import yage.base.nanovg.state.vgColor
import yage.base.nanovg.state.vgTrafo

// Which types can be instanciated before init?

object Test:

  def main(args: Array[String]) =
    val c1 = vgColor(0.2f, 0.3f, 0.5f)
    val c2 = vgColor(0.5f, 0.3f, 0.5f)
    val t = vgTrafo()
    // val g = RadialGradient(Vector2f(400, 300), 100, 200, c1, c2)
    println(c1)
    println(t)
