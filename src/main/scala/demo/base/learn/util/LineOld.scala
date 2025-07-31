package demo.base.learn.util

import org.joml.Vector4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.GL_LINES
import org.lwjgl.opengl.GL11C.GL_UNSIGNED_INT
import org.lwjgl.opengl.GL11C.glDrawElements
import yage.base.ByteBufferUtil
import yage.base.opengl.primitive.Attribute.Descriptor
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.Attribute.Semantics.Color
import yage.base.opengl.primitive.Attribute.Semantics.Position
import yage.base.opengl.primitive.VertexArray
import yage.base.opengl.primitive.VertexDescriptor
import yage.base.opengl.resource.buffer.Buffer
import yage.base.opengl.resource.buffer.Buffer.StorageFlags

// todo: drawInstanced

class LineOld(x1: Float, c1: Vector4f, x2: Float, c2: Vector4f) extends ByteBufferUtil:

  private val posA = Array(
    x1, 0f, 0f, 1f,
    x2, 0f, 0f, 1f
  )

  private val colA = Array(
    c1.x, c1.y, c1.z, c1.w,
    c2.x, c2.y, c2.z, c2.w,
  )

  private val indA = Array(
    0, 1
  )

  private val vb = createVB()
  private val ib = createIB()
  private val va = createVA()

  def bind() = 0

  def draw() =
    va.bind()
    glDrawElements(GL_LINES, 2, GL_UNSIGNED_INT, 0)

  def destroy() =
    va.destroy()
    ib.destroy()
    vb.destroy()

  private def createVA() =
    val ad0 = Descriptor(0, F32_4, Position)
    val ad1 = Descriptor(3, F32_4, Color)
    val vd = VertexDescriptor(ad0, ad1)
    val va = VertexArray()
    va.bindVertexBuffer(0, vb, 0, vd.vertexByteCount)
    va.bindIndexBuffer(ib)
    for i <- 0 to vd.attributeCount - 1 do
      val ad = vd.ads(i)
      va.enableAttribute(ad.location)
      va.setAttributeFormat(ad.location, ad.format, vd.attributeOffset(i), ad.normalized)
      va.setAttributeBinding(ad.location, 0)
    va

  private def createVB() =
    val bb = BufferUtils.createByteBuffer(2 * 32)
    for i <- 0 to 1 do
      bb.putFloat(posA(4 * i + 0))
      bb.putFloat(posA(4 * i + 1))
      bb.putFloat(posA(4 * i + 2))
      bb.putFloat(1f)
      bb.putFloat(colA(4 * i + 0))
      bb.putFloat(colA(4 * i + 1))
      bb.putFloat(colA(4 * i + 2))
      bb.putFloat(colA(4 * i + 3))
    Buffer(bb, StorageFlags())

  private def createIB() =
    val bb = toByteBuffer(indA)
    Buffer(bb, StorageFlags())

