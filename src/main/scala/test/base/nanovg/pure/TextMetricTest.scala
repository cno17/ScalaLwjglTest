package test.base.nanovg.pure

import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL11C.*
import yage.base.StackUser
import yage.base.glfw.GlApp

import java.io.File

object TextMetricTest extends GlApp:

  var ctxp: Long = 0
  val pAsc = BufferUtils.createFloatBuffer(1)
  val pDes = BufferUtils.createFloatBuffer(1)
  val pGap = BufferUtils.createFloatBuffer(1)

  override def init() =
    ctxp = NanoVGGL3.nvgCreate(0)
    nvgCreateFont(ctxp, "font1", File(resDir, "font/Cousine-Regular.ttf").getAbsolutePath())
    nvgCreateFont(ctxp, "font2", File(resDir, "font/Roboto-Bold.ttf").getAbsolutePath())
    nvgFontSize(ctxp, 50.0f)
    nvgFontFace(ctxp, "font1")
    nvgTextMetrics(ctxp, pAsc, pDes, pGap)
    println(pAsc.get(0))
    println(pDes.get(0))
    println(pGap.get(0))
