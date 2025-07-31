package yage.base.glfw.window

import org.lwjgl.glfw.GLFW.GLFW_DONT_CARE
import org.lwjgl.glfw.GLFW.glfwGetFramebufferSize
import org.lwjgl.glfw.GLFW.glfwGetWindowPos
import org.lwjgl.glfw.GLFW.glfwSetWindowPos
import org.lwjgl.glfw.GLFW.glfwSetWindowSize
import org.lwjgl.glfw.GLFW.glfwSetWindowSizeLimits
import yage.base.Caller
import yage.base.glfw.window.Window.handle

import scala.collection.mutable.ArrayBuffer

/**
 * Only the content area of a window, no border decorations.
 * 
 * Inconsistency: setSize(sx, sy) sets the width and height of 
 * the content array in screen coordinates, sizeX and sizeY get
 * the size in pixels. On most platforms the two units should be
 * the same.
 */

trait Rectangle extends Caller:

  val movedListeners = ArrayBuffer[(Int, Int) => Unit]()
  val resizedListeners = ArrayBuffer[(Int, Int) => Unit]()
  val mouseEnteredListeners = ArrayBuffer[() => Unit]()
  val mouseLeftListeners = ArrayBuffer[() => Unit]()
  
  def setPos(px: Int, py: Int) = glfwSetWindowPos(handle, px, py)
  def setSize(sx: Int, sy: Int) = glfwSetWindowSize(handle, sx, sy)
  def setMinSize(sx: Int, sy: Int) = glfwSetWindowSizeLimits(handle, sx, sy, GLFW_DONT_CARE, GLFW_DONT_CARE)
  def setMaxSize(sx: Int, sy: Int) = glfwSetWindowSizeLimits(handle, GLFW_DONT_CARE, GLFW_DONT_CARE, sx, sy)

  def posX = call2i(glfwGetWindowPos, handle, 1)
  def posY = call2i(glfwGetWindowPos, handle, 2)
  def sizeX = call2i(glfwGetFramebufferSize, handle, 1)
  def sizeY = call2i(glfwGetFramebufferSize, handle, 2)

  protected def movedCallback(window: Long, x: Int, y: Int) =
    movedListeners.foreach(_(x, y))

  protected def resizedCallback(window: Long, w: Int, h: Int) =
    resizedListeners.foreach(_(sizeX, sizeY))

  protected def mouseEnteredCallback(window: Long, entered: Boolean) =
    if entered then mouseEnteredListeners.foreach(_())
    else mouseLeftListeners.foreach(_())

// def frameSizeL = call4i(glfwGetWindowFrameSize, wHnd, 1)
// def frameSizeR = call4i(glfwGetWindowFrameSize, wHnd, 3)
// def frameSizeT = call4i(glfwGetWindowFrameSize, wHnd, 2)
// def frameSizeB = call4i(glfwGetWindowFrameSize, wHnd, 4)
