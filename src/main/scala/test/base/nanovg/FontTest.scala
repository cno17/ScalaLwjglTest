package test.base.nanovg

import yage.base.glfw.GlApp
import yage.base.nanovg.state.Font

import java.io.File

object FontTest extends GlApp:

  override def init() =
    // val id1 = ctx.text.loadFont(File(resDir, "font/Roboto-Regular.ttf"), "f1")
    // val id2 = ctx.text.loadFont(File(resDir, "font/Roboto-Bold.ttf"), "f2")
    val font = Font(resFile("Fonts/Roboto-Bold.ttf"))
    println(2)
