package test.base.nanovg.widget

import org.joml.Vector2f
import yage.base.glfw.GlApp
import yage.base.glfw.window.EventMode
import yage.base.glfw.window.Window
import yage.base.glfw.window.WindowCreateInfo
import yage.base.nanovg.vgContext
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.widget.Button

object ButtonTest extends GlApp:

  var button: Button = null

  override def info() =
    val res = WindowCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res.eventMode = EventMode.Wait
    res

  override def init() =
    button = Button(Vector2f(100, 100), Vector2f(200, 150))
    button.pressedListeners += buttonPressed
    button.releasedListeners += buttonReleased
    button.addListenersToMouse()
    vgContext.setClearColor(vgColor(0, 0, 0))

  override def draw() =
    vgContext.clear()
    vgContext.beginFrame(Window.sizeX, Window.sizeY)
    button.paint()
    vgContext.endFrame()

  def buttonPressed(b: Button) = println(b.state)
  def buttonReleased(b: Button) = println(b.state)