package test.base.opengl.compute.shadertoy

import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import yage.base.opengl.resource.Resource.Access
import yage.base.opengl.resource.Format
import yage.base.opengl.resource.texture.TexelFormat
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.input.Mouse
import yage.base.glfw.window.Window
import yage.scene.primitive.Rectangle

import java.io.File

object Shadertoy extends GlApp:

  val sizeI = 4096
  val sizeJ = 4096

  var texture: Texture2 = null
  var rectangle: Rectangle = null
  var computeProgram: Program = null
  var drawProgram: Program = null

  override def init() =
    Mouse.movedListeners += mouseMoved
    texture = Texture2(TexelFormat.FP32_4, sizeI, sizeJ)
    texture.bindToTextureUnit(0)
    texture.bindToImageUnit(0, Access.Write, TexelFormat.FP32_4, 0)
    rectangle = Rectangle(-1, -1, 1, 1)
    computeProgram = Program(srcFile("CompCellNoise.comp"))
    computeProgram.setUniform("image", 0)
    drawProgram = Program(srcFile("Draw.vert"), srcFile("Draw.frag"))
    drawProgram.setUniform("sampler", 0)
    glContext.setClearColor(0, 0, 0)

  override def draw() =
    glContext.clear()
    drawProgram.bind()
    rectangle.draw()

  override def step(t: Int, dt: Int) =
    computeProgram.bind()
    computeProgram.setUniform(0, t.toFloat)
    glDispatchCompute(sizeI / 32, sizeJ / 32, 1)
    glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT);
    Thread.sleep(20);

  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    val mouseU = x / Window.sizeX
    val mouseV = y / Window.sizeY
    computeProgram.setUniform(1, mouseU)
    computeProgram.setUniform(2, mouseV)

