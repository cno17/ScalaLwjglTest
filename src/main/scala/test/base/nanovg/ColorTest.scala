package test.base.nanovg

import yage.base.glfw.GlApp
import yage.base.nanovg.state.vgColor

object ColorTest extends GlApp:

  override def init() =
    val c1 = vgColor(0.2f, 0.3f, 0.5f)
    val c2 = vgColor(0.8f, 0.9f, 0.1f)
    val c3 = vgColor().interpolate(c1, c2, 0.9f)
    println(c3)