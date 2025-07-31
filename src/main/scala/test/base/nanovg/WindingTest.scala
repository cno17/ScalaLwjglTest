package test.base.nanovg

import org.lwjgl.opengl.GL11C.*
import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.base.nanovg.vgContext
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.state.vgWinding

object WindingTest extends GlApp:

  var color = vgColor(0.2f, 0.8f, 0.2f)
  

  override def init() =
    glClearColor(0, 0, 0, 1)

  override def draw() =
    val sx = Window.sizeX
    val sy = Window.sizeY
    glClear(GL_COLOR_BUFFER_BIT)
    vgContext.beginFrame(sx, sy)
    vgContext.setBrush(color)
    // vgContext.setFontSize(5)
//     Context.path.begin()
//     Context.path.rect(100, 100, 400, 300)
//     Context.pathWinding(PathWinding.CW) // mark circle as hole
//     Context.path.circle(300, 250, 50)
    // Context.fill()
    vgContext.endFrame()
