package yage.base.glfw.input

import org.lwjgl.glfw.GLFW.*

object Cursor:
  
  enum Shape(val value: Int):
    case Arrow extends Shape(GLFW_ARROW_CURSOR)
    case IBeam extends Shape(GLFW_IBEAM_CURSOR)
    case Crosshair extends Shape(GLFW_CROSSHAIR_CURSOR)
    case PointingHand extends Shape(GLFW_POINTING_HAND_CURSOR)
    case ResizeEW extends Shape(GLFW_RESIZE_EW_CURSOR)
    case ResizeNS extends Shape(GLFW_RESIZE_NS_CURSOR)
    case ResizeNWSE extends Shape(GLFW_RESIZE_NWSE_CURSOR)
    case ResizeNESW extends Shape(GLFW_RESIZE_NESW_CURSOR)
    case ResizeAll extends Shape(GLFW_RESIZE_ALL_CURSOR)
    case NotAllowed extends Shape(GLFW_NOT_ALLOWED_CURSOR)
 
  // todo: use Window.handle!
  def apply(window: Long, shape: Shape) =
    val handle = glfwCreateStandardCursor(shape.value)
    new Cursor(window, handle)

  // todo: custom image  

class Cursor(val window: Long, val handle: Long):

  def show() = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL)
  def hide() = glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED)

  def destroy() = glfwDestroyCursor(handle)

