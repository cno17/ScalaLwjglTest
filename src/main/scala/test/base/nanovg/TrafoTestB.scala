package test.base.nanovg

import org.joml.Matrix3x2f
import yage.base.glfw.GlApp
import yage.base.glfw.input.Key
import yage.base.glfw.input.Keyboard
import yage.base.glfw.input.Mouse
import yage.base.glfw.input.Mouse.Button
import yage.base.glfw.window.EventMode
import yage.base.glfw.window.Window
import yage.base.glfw.window.WindowCreateInfo
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.vgContext

object TrafoTestB extends GlApp:

  val color = vgColor(0.2f, 0.8f, 0.2f)
  val trafo = Matrix3x2f()


  override def info() =
    val res = WindowCreateInfo()
    res.sizeX = 1200
    res.sizeY = 800
    res.eventMode = EventMode.Wait
    res

  override def init() =
    // glfwSetInputMode(window.handle, GLFW_STICKY_KEYS, GLFW_TRUE)
    Keyboard.keyPressedListeners += onKeyPressed
    Mouse.draggedListeners += onMouseDragged
    Mouse.wheelRotatedListeners += onWheelRotated
    vgContext.setClearColor(vgColor(0, 0, 0))

  override def draw() =
    val sx = Window.sizeX
    val sy = Window.sizeY
    vgContext.clear()
    vgContext.beginFrame(sx, sy)
    vgContext.setBrush(color)
    vgContext.setTrafo(trafo)
    // Context.path.begin()
    // Context.path.rect(-100, -50, 200, 100)
    // Context.fill()
    vgContext.endFrame()

  def onKeyPressed(k: Key) = {} // println("a")

  def onMouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    if Mouse.isPressed(Mouse.Button.Left) then trafo.translateLocal(dx, dy)
    if Mouse.isPressed(Mouse.Button.Right) then trafo.translate(dx, dy)

  def onWheelRotated(wr: Float) =
    if Keyboard.isShiftKeyPressed then trafo.rotate(wr * 0.1f)
    else trafo.rotateLocal(wr * 0.1f)
