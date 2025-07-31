package test.base.opengl.reflection

import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp

import java.io.File

object UniformTest extends GlApp:

  override def init() =
    val p = Program(srcFile("UniformTest3.vert"))
    p.uniformNames.foreach(println(_))
    close()

