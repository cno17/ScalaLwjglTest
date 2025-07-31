package test.base.opengl.draw

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import yage.base.opengl.primitive.Attribute.Format.F32_2
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.VertexArray
import yage.base.opengl.resource.buffer.Buffer
import yage.base.opengl.resource.buffer.Buffer.StorageFlags
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.base.glfw.window.WindowCreateInfo

import java.io.File
import scala.collection.mutable.ArrayBuffer

/**
 * todo: indices, colors, interleaved, separated, quad
 */

object Trigon extends GlApp:

  var va: VertexArray = null
  var dp: Program = null

  override def info() =
    val res = WindowCreateInfo()
    res.debugContext = true
    res

  override def init() =
    va = createVertexArray()
    dp = Program(srcFile("Simple.vert"), srcFile("Simple.frag"))
    glClearColor(0, 0, 0, 1)
    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL)
  // close()

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    glViewport(0, 0, Window.sizeX / 2, Window.sizeY / 2)
    va.bind()
    dp.bind()
    glDrawArrays(GL_TRIANGLES, 0, 3)

  def createVertexArray() =
    val ab = ArrayBuffer[Float]()
    ab ++= Array(-0.5f, -0.5f, 1.0f, 0.0f, 0.0f, 1.0f)
    ab ++= Array(+0.5f, -0.5f, 0.0f, 1.0f, 0.0f, 1.0f)
    ab ++= Array(+0.0f, +0.5f, 0.0f, 0.0f, 1.0f, 1.0f)
    val bb = BufferUtils.createByteBuffer(72)
    for f <- ab do bb.putFloat(f)
    bb.rewind()
    val buf = Buffer(0, StorageFlags())
    val res = VertexArray()
    res.bindVertexBuffer(0, buf, 0, 24)
    res.enableAttribute(0)
    res.enableAttribute(1)
    res.setAttributeFormat(0, F32_2, 0)
    res.setAttributeFormat(1, F32_4, 0)
    res.setAttributeBinding(0, 0)
    res.setAttributeBinding(1, 0)
    res
