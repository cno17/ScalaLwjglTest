package test.base.nanovg

import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.base.nanovg.vgContext
import yage.base.nanovg.vgImage
import yage.base.nanovg.state.vgColor

object ImageTest extends GlApp:

  var img: vgImage = null

  override def init() =
    val flags = vgImage.vgFlags()
    img = vgImage(resFile("Icons/Tree-96.png"), flags)
    println(img.id)
    println(img.sizeI)
    println(img.sizeJ)
    vgContext.setClearColor(vgColor.Black)

  override def draw() =
    vgContext.clear()
    vgContext.beginFrame(Window.sizeX, Window.sizeY)
    // Context.draw(img, 10, 10)
    vgContext.endFrame()
