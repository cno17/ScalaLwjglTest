package yage.base.glfw.window

import org.lwjgl.glfw.GLFW.*

// TODO Profile

class WindowCreateInfo(var sizeX: Int, var sizeY: Int, var title: String):
  
  def this() = this(1200, 900, "Glfw Window")

  var resizable = true
  var decorated = true
  var transparent = false
  var debugContext = true
  var eventMode = EventMode.Poll

  def applyHints() =
    // window
    glfwWindowHint(GLFW_RESIZABLE, toInt(resizable))
    glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE)
    glfwWindowHint(GLFW_DECORATED, toInt(decorated))
    glfwWindowHint(GLFW_FOCUSED, GLFW_TRUE)
    glfwWindowHint(GLFW_FLOATING, GLFW_FALSE)
    glfwWindowHint(GLFW_CENTER_CURSOR, GLFW_TRUE)
    glfwWindowHint(GLFW_TRANSPARENT_FRAMEBUFFER, toInt(transparent))
    glfwWindowHint(GLFW_FOCUS_ON_SHOW, GLFW_TRUE)
    glfwWindowHint(GLFW_AUTO_ICONIFY, GLFW_TRUE)
    // context
    glfwWindowHint(GLFW_CLIENT_API, GLFW_OPENGL_API)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 4)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 6)
    glfwWindowHint(GLFW_OPENGL_DEBUG_CONTEXT, toInt(debugContext))
    // glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_COMPAT_PROFILE) // for lwjgl demos
    glfwWindowHint(GLFW_CONTEXT_NO_ERROR, GLFW_FALSE)
    // frame buffer
    glfwWindowHint(GLFW_RED_BITS, 8)
    glfwWindowHint(GLFW_GREEN_BITS, 8)
    glfwWindowHint(GLFW_BLUE_BITS, 8)
    glfwWindowHint(GLFW_ALPHA_BITS, 8)
    glfwWindowHint(GLFW_DEPTH_BITS, 24)
    glfwWindowHint(GLFW_STENCIL_BITS, 8)

  private def toInt(b: Boolean) = if b == false then GLFW_FALSE else GLFW_TRUE


// frame buffer hints
// var redBits = 8
// var greenBits = 8
// var blueBits = 8
// var alphaBits = 8
// var depthBits = 24
// var stencilBits = 8
