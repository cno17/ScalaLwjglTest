package demo.book.cookbook.ch05

import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.scene.primitive.Rectangle

import java.io.File

// todo: slider or keyboard or mouse!

object MultiTextureDemo extends GlApp:

  var rect: Rectangle = null
  var tex0: Texture2 = null
  var tex1: Texture2 = null
  var prog: Program = null

  override def init() =
    rect = Rectangle(-0.8, -0.8, 0.8, 0.8)
    tex0 = Texture2(resFile("Images/Woman1.jpg"))
    tex1 = Texture2(resFile("Images/Wood1.jpg"))
    tex0.bindToTextureUnit(0)
    tex1.bindToTextureUnit(1)
    prog = Program(File(srcDir, "MultiTexture.vert"), File(srcDir, "MultiTexture.frag"))
    prog.setUniform("tex0", 0)
    prog.setUniform("tex1", 1)
    prog.bind()
    glContext.setClearColor(0, 0, 0)

  override def draw() =
    glContext.clear()
    rect.draw()
