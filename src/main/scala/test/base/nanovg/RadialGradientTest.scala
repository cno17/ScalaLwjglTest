package test.base.nanovg

import org.joml.Vector2f
import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.state.vgRadialGradient
import yage.base.nanovg.vgContext
import yage.base.nanovg.vgPainter

object RadialGradientTest extends GlApp:

  var paint: vgRadialGradient = null

  override def init() =
    val c1 = vgColor(0.2f, 0.8f, 0.2f)
    val c2 = vgColor(0.2f, 0.2f, 0.8f)
    paint = vgRadialGradient(Vector2f(400, 300), 50, 300, c1, c2)
    vgContext.setClearColor(vgColor.Black)

  override def draw() =
    val sx = Window.sizeX
    val sy = Window.sizeY
    vgContext.clear()
    vgContext.beginFrame(sx, sy)
    vgContext.setBrush(paint)
    vgPainter.fillRectangle(0, 0, sx, sy)
    vgContext.endFrame()
