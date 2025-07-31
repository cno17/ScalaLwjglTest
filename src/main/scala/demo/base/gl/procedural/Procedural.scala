package demo.base.gl.procedural

import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.scene.primitive.Rectangle

import java.io.File

object Procedural extends GlApp:

  var rectangle: Rectangle = null
  var program: Program = null
  var startTime: Long = 0

  override def init() =
    rectangle = Rectangle(-0.8f, -0.8f, 0.8f, 0.8f)
    program = Program(srcFile("Procedural.vert"), srcFile("Procedural.frag"))
    program.bind()
    startTime = System.currentTimeMillis()
    glContext.setClearColor(0, 0, 0)

  override def draw() =
    val dt = 0.001f * (System.currentTimeMillis() - startTime)
    program.setUniform("time", dt)
    glContext.clear()
    rectangle.draw()
