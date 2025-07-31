package demo.book.cookbook.ch11

import org.joml.Vector2f
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import yage.base.opengl.resource.Resource.Access
import yage.base.opengl.resource.Format
import yage.base.opengl.resource.texture.TexelFormat
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.input.Mouse
import yage.base.glfw.window.EventMode
import yage.base.glfw.window.WindowCreateInfo
import yage.scene.primitive.Rectangle

import java.io.File

// todo vec2d

object MandelbrotDemo extends GlApp:

  val si = 1024
  val sj = 1024

  // todo: var min = Vector2f(-2f, 2f)
  // todo: var max = Vector2f(-2f, 2f)

  var minX = -2.0f
  var minY = -2.0f
  var maxX = +2.0f
  var maxY = +2.0f

  var texture: Texture2 = null
  var rectangle: Rectangle = null
  var computeProgram: Program = null
  var drawProgram: Program = null

  override def info() =
    val info = WindowCreateInfo()
    info.eventMode = EventMode.Wait
    info

  override def init() =
    Mouse.draggedListeners += mouseDragged
    Mouse.wheelRotatedListeners += mouseWheelRotated
    texture = Texture2(TexelFormat.FP32_4, si, sj)
    texture.bindToTextureUnit(0)
    texture.bindToImageUnit(0, Access.Write, TexelFormat.FP32_4, 0)
    rectangle = Rectangle(-1, -1, 1, 1)
    computeProgram = Program(srcFile("Mandelbrot.comp"))
    computeProgram.setUniform("image", 0)
    computeProgram.setUniform("si", si)
    computeProgram.setUniform("sj", sj)
    computeProgram.setUniform("maxI", 100)
    drawProgram = Program(srcFile("Mandelbrot.vert"), srcFile("Mandelbrot.frag"))
    drawProgram.setUniform("sampler", 0)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    val t1 = timer.time
    computeProgram.bind()
    computeProgram.setUniform("minZ", minX, minY)
    computeProgram.setUniform("maxZ", maxX, maxY)
    glDispatchCompute(si / 32, sj / 32, 1)
    glMemoryBarrier(GL_SHADER_IMAGE_ACCESS_BARRIER_BIT)
    val t2 = timer.time
    val dt = t2 - t1
    println(s"$dt[micros]")
    glClear(GL_COLOR_BUFFER_BIT)
    drawProgram.bind()
    rectangle.draw()

  def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    val dx2 = (maxX - minX) * dx / 500
    val dy2 = (maxY - minY) * dy / 500
    minX -= dx2
    minY += dy2
    maxX -= dx2
    maxY += dy2

  def mouseWheelRotated(wr: Float) =
    val dx = wr * (maxX - minX) / 20
    val dy = wr * (maxY - minY) / 20
    minX -= dx
    minY -= dy
    maxX += dx
    maxY += dy
