package test.base.opengl

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL30.*
import yage.base.glfw.GlApp

object ExtensionTest extends GlApp:

  override def init() =
    // println(capabilities.GL_NV_mesh_shader)
    val n = glGetInteger(GL_NUM_EXTENSIONS)
    for i <- 0 to n - 1 do
      println(glGetStringi(GL_EXTENSIONS, i))
    // for f <- capabilities.getClass.getFields do println(f.getDouble)
    close()
