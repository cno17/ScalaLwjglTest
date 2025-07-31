package test.base.nanovg

import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.vgContext
import yage.base.nanovg.vgPainter

object PainterTest extends GlApp:

  override def init() =
    vgContext.setClearColor(vgColor.Black)

  override def draw() =
    vgContext.clear()
    vgContext.beginFrame(Window.sizeX, Window.sizeY)
    vgContext.setPen(vgColor.Green)
    vgPainter.drawTrigon(100, 100, 200, 100, 200, 200)
    vgContext.setBrush(vgColor.Blue)
    vgContext.setPen(vgColor.White)
    vgContext.setPenWidth(3)
    vgPainter.fillCircle(300, 300, 50)
    vgPainter.drawCircle(300, 300, 50)
    vgContext.endFrame()
