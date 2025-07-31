package test.base.opengl.compute.sine

import org.lwjgl.opengl.GL42C.GL_SHADER_IMAGE_ACCESS_BARRIER_BIT
import org.lwjgl.opengl.GL42C.glMemoryBarrier
import org.lwjgl.opengl.GL43C.glDispatchCompute
import yage.base.opengl.resource.Resource.Access
import yage.base.opengl.resource.Format
import yage.base.opengl.resource.texture.TexelFormat
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.scene.primitive.Rectangle

import java.io.File

object Sine extends GlApp:

  val sizeI = 256
  val sizeJ = 256

  var step = 0
  var texture: Texture2 = null
  var rectangle: Rectangle = null
  var drawProgram: Program = null
  var stepProgram: Program = null

  override def init() =
    texture = Texture2(TexelFormat.FP32_4, sizeI, sizeJ)
    texture.bindToTextureUnit(0)
    texture.bindToImageUnit(0, Access.Write, TexelFormat.FP32_4)
    rectangle = Rectangle(-0.8f, -0.8f, 0.8f, 0.8f)
    drawProgram = Program(srcFile("Draw.vert"), srcFile("Draw.frag"))
    drawProgram.setUniform("sampler", 0)
    stepProgram = Program(srcFile("Step.comp"))
    stepProgram.setUniform("image", 0)
    glContext.setClearColor(0, 0, 0)

  override def draw() =
    glContext.clear();
    drawProgram.bind()
    rectangle.draw()

  override def step(t: Int, dt: Int) =
    step += 1
    stepProgram.bind()
    stepProgram.setUniform("step", step)
    glDispatchCompute(sizeI / 32, sizeJ / 32, 1)
    glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT)
