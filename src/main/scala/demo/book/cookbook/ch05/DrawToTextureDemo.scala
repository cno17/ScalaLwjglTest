package demo.book.cookbook.ch05

import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.scene.primitive.Rectangle

import java.io.File

object DrawToTextureDemo extends GlApp:

  var rect: Rectangle = null
  var tex: Texture2 = null
  var prog: Program = null

  override def init() =
    rect = Rectangle(-0.8, -0.8, 0.8, 0.8)
    tex = Texture2(resFile("Images/Bird1.jpg"))
    tex.bindToTextureUnit(0)
    prog = Program(srcFile("SingleTexture.vert"), srcFile("SingleTexture.frag"))
    prog.setUniform("tex", 0)
    prog.bind()
    glContext.setClearColor(0, 0, 0)

  override def draw() =
    glContext.clear()
    rect.draw()
