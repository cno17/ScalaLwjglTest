package yage.base.glfw

import org.lwjgl.glfw.GLFW.glfwMakeContextCurrent
import org.lwjgl.glfw.GLFW.glfwSwapInterval
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11C.glClearColor
import org.lwjgl.opengl.GL11C.glViewport
import yage.base.Flags
import yage.base.opengl.context.BufferFlag
import yage.base.opengl.context.BufferFlag.ColorBuffer
import yage.base.opengl.context.BufferFlag.DepthBuffer
import yage.base.opengl.context.GlContext
import yage.base.opengl.debug.DebugMessenger
import yage.base.glfw.window.Window
import yage.base.glfw.window.Window.handle
import yage.base.nanovg.vgContext

trait GlApp extends App:

  // var clearFlags = Flags[BufferFlag]()

  var glContext: GlContext = null
  // var vgContext: vgContext = null

  var debugMessenger: DebugMessenger = null

  override def create() =
    super.create()
    glfwMakeContextCurrent(handle)
    createCapabilities()
    glfwSwapInterval(1)
    // clearFlags := (ColorBuffer, DepthBuffer)
    glContext = GlContext()
    vgContext.init()
    debugMessenger = DebugMessenger(true)
    glViewport(0, 0, Window.sizeX, Window.sizeY)
    glClearColor(0f, 0f, 0f, 1f)

  override def destroy() =
    // nvgContext.destroy()
    // gui stuff
    super.destroy()

