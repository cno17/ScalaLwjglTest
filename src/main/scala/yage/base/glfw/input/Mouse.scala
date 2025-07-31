package yage.base.glfw.input

import org.lwjgl.glfw.GLFW.*
import yage.base.Caller
import yage.base.glfw.window.Window.handle

import scala.collection.mutable.ArrayBuffer

object Mouse extends Caller:
  
  object Button:
    def apply(i: Int) = Button.values.find(_.id == i).get

  enum Button(val id: Int):
    case Left extends Button(GLFW_MOUSE_BUTTON_LEFT)
    case Middle extends Button(GLFW_MOUSE_BUTTON_MIDDLE)
    case Right extends Button(GLFW_MOUSE_BUTTON_RIGHT)
  
  // var cursor = Cursor(window, CursorShape.Arrow)
  // todo: setCursor
  
  val movedListeners = ArrayBuffer[(Float, Float, Float, Float) => Unit]()
  val draggedListeners = ArrayBuffer[(Float, Float, Float, Float) => Unit]()
  val buttonPressedListeners = ArrayBuffer[Button => Unit]()
  val buttonReleasedListeners = ArrayBuffer[Button => Unit]()
  val wheelRotatedListeners = ArrayBuffer[Float => Unit]()

  private var oldX = 0.0
  private var oldY = 0.0

  def create() =
    glfwSetCursorPosCallback(handle, movedCallback)
    glfwSetMouseButtonCallback(handle, buttonCallback)
    glfwSetScrollCallback(handle, wheelCallback)

  def posX = call2d(glfwGetCursorPos, handle, 1)
  def posY = call2d(glfwGetCursorPos, handle, 2)

  def setPos(x: Float, y: Float) = glfwSetCursorPos(handle, x, y)

  def isPressed(button: Button) = glfwGetMouseButton(handle, button.id) == GLFW_PRESS

  def enableRawMotion = glfwSetInputMode(handle, GLFW_RAW_MOUSE_MOTION, GLFW_TRUE)

  def rawMotionSupported = glfwRawMouseMotionSupported()

  private def movedCallback(window: Long, x: Double, y: Double) =
    val lbp = isPressed(Button.Left)
    val mbp = isPressed(Button.Middle)
    val rbp = isPressed(Button.Right)
    val bp = lbp | mbp | rbp
    val dx = (x - oldX).toFloat
    val dy = (y - oldY).toFloat
    oldX = x
    oldY = y
    if bp then draggedListeners.foreach(_(x.toFloat, y.toFloat, dx, dy))
    else movedListeners.foreach(_(x.toFloat, y.toFloat, dx, dy))

  private def buttonCallback(window: Long, button: Int, action: Int, mods: Int) =
    val b = Button(button)
    if action == GLFW_PRESS then buttonPressedListeners.foreach(_(b))
    else buttonReleasedListeners.foreach(_(b))

  private def wheelCallback(window: Long, xx: Double, wr: Double) =
    wheelRotatedListeners.foreach(_(wr.toFloat))
