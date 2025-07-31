package test.base.opengl.compute.points

import test.base.opengl.HelloWorld.width
import yage.base.opengl.primitive.Attribute.Format.F32_2

import java.io.File
import java.nio.ByteBuffer
// import org.joml.Vector2f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL15C.*
import org.lwjgl.opengl.GL20C.*
import org.lwjgl.opengl.GL30C.*
import org.lwjgl.opengl.GL40C.GL_PATCHES
import org.lwjgl.opengl.GL42C.*
import org.lwjgl.opengl.GL43C.*
import org.lwjgl.opengl.GL45C.*
import yage.base.opengl.primitive.VertexArray
import yage.base.opengl.resource.buffer.Buffer
import yage.base.opengl.resource.buffer.Buffer.StorageFlags
import yage.base.opengl.resource.buffer.Buffer.Target
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp
import yage.base.glfw.window.WindowCreateInfo

import scala.collection.mutable.ArrayBuffer

/**
 * todo: indices, colors, interleaved, separated, quad
 */

object Points extends GlApp:

  var vb: Buffer = null
  var va: VertexArray = null
  var cp: Program = null
  var dp: Program = null

  var num = 50
  var time = 0.0f

  override def info() =
    val res = WindowCreateInfo()
    res.debugContext = true
    res

  override def init() =
    vb = Buffer(8 * num, StorageFlags())
    vb.bindTo(Target.Storage, 0)
    va = VertexArray()
    va.bind()
    va.bindVertexBuffer(0, vb, 0, 8) // 8 ?
    va.enableAttribute(0)
    va.setAttributeFormat(0, F32_2, 0)
    va.setAttributeBinding(0, 0)
    cp = Program(File(srcDir, "Compute.comp"))
    dp = Program(File(srcDir, "Draw.vert"), File(srcDir, "Draw.frag"))
    glClearColor(0, 0, 0, 1)
    glPointSize(4)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    dp.bind()
    glDrawArrays(GL_LINES, 0, num / 2)

  override def step(t: Int, dt: Int) =
    time += 0.001f * dt.toFloat
    cp.bind()
    cp.setUniform("time", time)
    glDispatchCompute(num / 1, 1, 1)
    glMemoryBarrier(GL_SHADER_STORAGE_BARRIER_BIT);
