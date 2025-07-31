package test.base.nanovg.widget

import yage.base.glfw.GlApp
import yage.base.glfw.window.EventMode
import yage.base.glfw.window.Window
import yage.base.glfw.window.WindowCreateInfo
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.widget.Slider
import yage.base.nanovg.vgContext

object SliderTest extends GlApp:

  var slider: Slider = null

  override def info() =
    val res = WindowCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res.eventMode = EventMode.Wait
    res

  override def init() =
    slider = Slider(0, 100, 20)
    slider.valueChangedListeners += valueChanged
    slider.addListenersToMouse()
    vgContext.setClearColor(vgColor.Black)

  override def draw() =
    vgContext.clear()
    vgContext.beginFrame(Window.sizeX, Window.sizeY)
    slider.paint()
    vgContext.endFrame()

  def valueChanged(curVal: Float) = println(curVal.toInt)  