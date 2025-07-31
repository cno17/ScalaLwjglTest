package yage.base.glfw.monitor

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWVidMode
import yage.base.glfw.window.Window

object Monitor:

  def primaryMonitor = Monitor(glfwGetPrimaryMonitor())
  
  def connectedMonitors =
    val buf = glfwGetMonitors()
    val res = for i <- 0 to buf.capacity() - 1 yield Monitor(buf.get(i))
    res.toArray
    
// todo: needs work

class Monitor(val handle: Long):

  def currentVideoMode = VideoMode(glfwGetVideoMode(handle))
  
  def supportedVideoModes = 
    val buf = glfwGetVideoModes(handle)
    val res = for i <- 0 to buf.capacity() - 1 yield VideoMode(buf.get(i))
    res.toArray

//  def centerWindow() =
//    val vm = currentVideoMode
//    val px = (vm.sizeX - Window.sizeX) / 2
//    val py = (vm.sizeY - Window.sizeY) / 2
//    Window.setPos(px, py)
