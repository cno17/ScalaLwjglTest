package test.base.nanovg.pure

import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NVGPaint
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL11C.*
import yage.base.glfw.GlApp
import yage.base.glfw.window.Window

object LinearGradientTest extends GlApp:

  var ctxp: Long = 0
  var color1: NVGColor = null
  var color2: NVGColor = null
  var paint: NVGPaint = null

  override def init() =
    glClearColor(0, 0, 0, 1)
    ctxp = NanoVGGL3.nvgCreate(0)
    color1 = NVGColor.create()
    color2 = NVGColor.create()
    nvgRGBf(0.8f, 0.2f, 0.2f, color1)
    nvgRGBf(0.2f, 0.8f, 0.2f, color2)
    paint = NVGPaint.create()
    nvgLinearGradient(ctxp, 10, 10, 300, 500, color1, color2, paint) // params?    

  override def draw() =
    val sx = Window.sizeX.toFloat
    val sy = Window.sizeY.toFloat
    glClear(GL_COLOR_BUFFER_BIT)
    nvgBeginFrame(ctxp, sx, sy, 1)
    nvgBeginPath(ctxp)
    nvgRect(ctxp, 10, 10, 300, 300)
    nvgFillPaint(ctxp, paint)
    nvgFill(ctxp)
    nvgClosePath(ctxp)
    nvgEndFrame(ctxp)

