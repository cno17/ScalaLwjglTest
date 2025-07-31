package test.base.opengl.reflection

import yage.base.opengl.shader.program.Program
import yage.base.opengl.shader.program.resource.Block.Type.Uniform
import yage.base.glfw.GlApp

import java.io.File

object UniformBlockTest extends GlApp:

  override def init() =
    val p = Program(srcFile("UniformBlockTest1.vert"))
    // p.uniformNames.foreach(println(_))
    for b <- p.blocks(Uniform) do
      for m <- b.members do
        println(m)
    close()

