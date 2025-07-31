package test.base.nanovg.pure

import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3
import yage.base.glfw.GlApp

import java.io.File

object FontTest extends GlApp:

  var ctxp: Long = 0

  override def init() =
    ctxp = NanoVGGL3.nvgCreate(0)
    val h1 = nvgCreateFont(ctxp, "font1", resFile("Fonts/Roboto-Regular.ttf").getAbsolutePath)
    val h2 = nvgCreateFont(ctxp, "font2", resFile("Fonts/Roboto-Bold.ttf").getAbsolutePath)
    println(h1)
    println(h2)
