package test.base.opengl.resource.texture.chess

import org.lwjgl.opengl.GL11C.*
import yage.base.ByteBufferUtil
import yage.base.opengl.resource.Format
import yage.base.opengl.resource.texture.TexelFormat
import yage.base.opengl.resource.texture.Texture2
import yage.base.opengl.resource.texture.Texture.MagFilter
import yage.base.opengl.resource.texture.Texture.MinFilter
import yage.base.opengl.shader.program.Program
import yage.base.glfw.GlApp

import java.io.File

object Chess extends GlApp, ByteBufferUtil:

  var sizeI = 2
  var sizeJ = 2
  var texture: Texture2 = null
  var drawProgram: Program = null

  val dataFP32_1 = toByteBuffer(
    Array(
      1f,
      0f,
      0f,
      1f
    )
  )

  val dataFP32_3 = toByteBuffer(
    Array(
      1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 0.0f
    )
  )

  val dataFP32_4 = toByteBuffer(
    Array(
      1f, 1f, 0f, 1f, 0f, 0f, 1f, 1f, 0f, 0f, 1f, 1f, 1f, 1f, 0f, 1f
    )
  )

  val dataSI08_1 = toByteBuffer(
    Array(
      127,
      0,
      0,
      127
    )
  )

  override def init() =
    texture = Texture2(TexelFormat.FP32_1, sizeI, sizeJ)
    texture.load(dataFP32_1, TexelFormat.FP32_1)
    texture.setMinFilter(MinFilter.Nearest)
    texture.setMagFilter(MagFilter.Nearest)
    texture.bindToTextureUnit(0)
    drawProgram = Program(srcFile("Draw.vert"), srcFile("Draw.frag"))
    glClearColor(0, 0, 0, 1)

  override def draw() =
    drawProgram.bind()
