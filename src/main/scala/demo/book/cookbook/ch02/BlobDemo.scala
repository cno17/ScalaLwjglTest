package demo.book.cookbook.ch02

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.*
import yage.base.opengl.resource.buffer.Buffer
import yage.base.opengl.resource.buffer.Buffer.StorageFlags
import yage.base.opengl.resource.buffer.Buffer.Target.Uniform
import yage.base.opengl.shader.program.Program
import yage.base.opengl.shader.program.resource.Block.Type
import yage.base.glfw.GlApp
import yage.scene.primitive.Rectangle

import java.io.File
import java.nio.ByteBuffer

// todo imgui

object BlobDemo extends GlApp:

  var blob: Blob = null
  var data: ByteBuffer = null
  var buffer: Buffer = null
  var rectangle: Rectangle = null
  var program: Program = null

  override def init() =
    blob = Blob()
    blob.innerColor.set(0.8f, 0.2f, 0.2f, 1.0f)
    blob.outerColor.set(0.2f, 0.8f, 0.2f, 1.0f)
    blob.innerRadius = 0.5f
    blob.innerRadius = 0.9f
    data = BufferUtils.createByteBuffer(Blob.byteCount)
    blob >> data
    buffer = Buffer(data, StorageFlags())
    buffer.bindTo(Uniform, 0)
    rectangle = new Rectangle(-0.8f, -0.8f, 0.8f, 0.8f)
    program = Program(srcFile("Blob.vert"), srcFile("Blob.frag"))
    program.bind()
    for b <- program.blocks(Type.Uniform) do
      println(b)
    glClearColor(0, 0, 0, 1)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT)
    rectangle.draw()
