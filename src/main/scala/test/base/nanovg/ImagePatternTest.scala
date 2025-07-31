package test.base.nanovg

import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.base.nanovg.vgContext
import yage.base.nanovg.vgImage
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.state.vgImagePattern

object ImagePatternTest extends GlApp:

  var img: vgImage = null
  var paint: vgImagePattern = null

  override def init() =
    val flags = vgImage.vgFlags()
    img = vgImage(resFile("Icons/Tree-96.png"), flags)
    paint = vgImagePattern(img)
    vgContext.setClearColor(vgColor(0, 0, 0))

  override def draw() =
    val sx = Window.sizeX
    val sy = Window.sizeY
    paint.px = 100
    paint.py = 100
    paint.sx = img.sizeI.toFloat * 3
    paint.sy = img.sizeJ.toFloat
    vgContext.clear()
    vgContext.beginFrame(sx, sy)
    vgContext.setBrush(paint)
    // Context.path.begin()
    // Context.path.rect(100, 100, img.sizeI.toFloat * 2, img.sizeJ.toFloat)
    // Context.fill()
    // Context.path.begin()
    // Context.path.rect(300, 200, 300, 200)
    // Context.fill()
    vgContext.endFrame()
