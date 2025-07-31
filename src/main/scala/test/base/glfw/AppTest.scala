package test.base.glfw

import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
import org.lwjgl.opengl.GL11C.glClear
import org.lwjgl.opengl.GL11C.glClearColor
import yage.base.glfw.GlApp
import yage.base.glfw.window.EventMode
import yage.base.glfw.window.WindowCreateInfo

object AppTest extends GlApp:

  override def info() =
    val res = WindowCreateInfo()
    res.eventMode = EventMode.Poll
    res

  override def init() =
    glClearColor(0.5f, 0.8f, 0.5f, 1.0f)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
