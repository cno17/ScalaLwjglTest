package test.base.opengl.compute.scroll

import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import yage.base.opengl.resource.Resource.Access
import yage.base.opengl.resource.Format
import yage.base.opengl.resource.texture.TexelFormat
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.resource.texture.Texture.MagFilter
import yage.base.opengl.resource.texture.Texture.MinFilter
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.scene.primitive.Rectangle

import java.io.File

object Scroll extends GlApp:

  var step = 0

  var geometry: Rectangle = null

  var texture0: Texture2 = null
  var texture1: Texture2 = null

  var sizeI = 0
  var sizeJ = 0
  var numGroupsI = 0
  var numGroupsJ = 0

  var drawProgram: Program = null
  var stepProgram: Program = null

  override def init() =

    geometry = Rectangle(-0.8f, -0.8f, 0.8f, 0.8f)

    texture0 = Texture2(resFile("Icons/Frame-16.png"))
    texture0.setMinFilter(MinFilter.Nearest)
    texture0.setMagFilter(MagFilter.Nearest)
    texture0.bindToTextureUnit(0)
    texture0.bindToImageUnit(0, Access.ReadWrite, TexelFormat.FP32_4)

    texture1 = Texture2(resFile("Icons/Frame-16.png"))
    texture1.setMinFilter(MinFilter.Nearest)
    texture1.setMagFilter(MagFilter.Nearest)
    texture1.bindToTextureUnit(1)
    texture1.bindToImageUnit(1, Access.ReadWrite, TexelFormat.FP32_4)

    sizeI = texture0.sizeI
    sizeJ = texture0.sizeJ
    numGroupsI = Math.ceil(sizeI.toFloat / 32).toInt
    numGroupsJ = Math.ceil(sizeJ.toFloat / 32).toInt

    drawProgram = Program(srcFile("Draw.vert"), srcFile("Draw.frag"))
    stepProgram = Program(srcFile("Step.comp"))
    stepProgram.setUniform("si", sizeI)
    stepProgram.setUniform("sj", sizeJ)

    glClearColor(0, 0, 0, 1)

  override def draw() =
    drawProgram.bind()
    drawProgram.setUniform("sampler", step % 2)
    geometry.draw()
    stepProgram.bind()
    stepProgram.setUniform("imageR", (step + 0) % 2)
    stepProgram.setUniform("imageW", (step + 1) % 2)
    glDispatchCompute(numGroupsI, numGroupsJ, 1)
    glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT)
    step += 1
    Thread.sleep(100)
