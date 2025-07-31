package yage.base.glfw.window

import org.lwjgl.glfw.GLFW.*
import yage.base.glfw.monitor.Monitor

import scala.collection.mutable.ArrayBuffer

object Window extends Rectangle:

  var handle: Long = 0
  var eventMode: EventMode = null

  val iconifiedListeners = ArrayBuffer[() => Unit]()
  val restoredListeners = ArrayBuffer[() => Unit]()
  val focusGainedListeners = ArrayBuffer[() => Unit]()
  val focusLostListeners = ArrayBuffer[() => Unit]()
  val closeRequestedListeners = ArrayBuffer[() => Unit]()

  def create(info: WindowCreateInfo = WindowCreateInfo()) =
    glfwInit()
    info.applyHints()
    handle = glfwCreateWindow(info.sizeX, info.sizeY, info.title, 0, 0)
    eventMode = info.eventMode
    glfwSetWindowPosCallback(handle, movedCallback)
    glfwSetWindowSizeCallback(handle, resizedCallback)
    glfwSetCursorEnterCallback(handle, mouseEnteredCallback)
    glfwSetWindowIconifyCallback(handle, iconifiedCallback)
    glfwSetWindowFocusCallback(handle, focusCallback)
    glfwSetWindowCloseCallback(handle, closeCallback)
    val vm = Monitor.primaryMonitor.currentVideoMode
    val px = (vm.sizeX - sizeX) / 2
    val py = (vm.sizeY - sizeY) / 2
    setPos(px, py)
  
  def show() = glfwShowWindow(handle)
  def hide() = glfwHideWindow(handle)
  def iconify() = glfwIconifyWindow(handle)
  def restore() = glfwRestoreWindow(handle)
  def focus() = glfwFocusWindow(handle)
  def close() = glfwSetWindowShouldClose(handle, true)

  def setTitle(s: String) = glfwSetWindowTitle(handle, s)
  def processEvents() = eventMode.processor()
  def swapBuffers() = glfwSwapBuffers(handle)
  def shouldClose() = glfwWindowShouldClose(handle)

  def destroy() =
    glfwDestroyWindow(handle)
    glfwTerminate()
  
  private def iconifiedCallback(window: Long, iconified: Boolean) =
    if iconified then iconifiedListeners.foreach(_())
    else restoredListeners.foreach(_())

  private def focusCallback(window: Long, gained: Boolean) =
    if gained then focusGainedListeners.foreach(_())
    else focusLostListeners.foreach(_())

  private def closeCallback(window: Long) =
    closeRequestedListeners.foreach(_())


// useful?

// def setIcon() = glfwSetWindowIcon(handle, null) // needs work
// def setOpacity(v: Double) =
// def getAttribute(k: AttributeKey) = glfwGetWindowAttrib
// def requestAttention()
// text input: glfwCharCallback
// def isFullscreen() = 0
// def toggleFullScreen() = 0

