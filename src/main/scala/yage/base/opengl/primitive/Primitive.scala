package yage.base.opengl.primitive

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.GL_UNSIGNED_INT
import org.lwjgl.opengl.GL11C.glDrawElements
import yage.base.opengl.resource.buffer.Buffer
import yage.base.opengl.resource.buffer.Buffer.StorageFlags
import org.lwjgl.opengl.GL11C.*
import org.lwjgl.opengl.GL32C.*
import org.lwjgl.opengl.GL40C.*
import yage.base.opengl.primitive.Primitive.Mode

import java.nio.ByteBuffer

object Primitive:

  // value -> id?
  enum Mode(val value: Int):
    case Points extends Mode(GL_POINTS)
    case Lines extends Mode(GL_LINES)
    case LinesAdjacency extends Mode(GL_LINES_ADJACENCY)
    case LineStrip extends Mode(GL_LINE_STRIP)
    case LineStripAdjacency extends Mode(GL_LINE_STRIP_ADJACENCY)
    case LineLoop extends Mode(GL_LINE_LOOP)
    case Triangles extends Mode(GL_TRIANGLES)
    case TrianglesAdjacency extends Mode(GL_TRIANGLES_ADJACENCY)
    case TriangleStrip extends Mode(GL_TRIANGLE_STRIP)
    case TriangleStripAdjacency extends Mode(GL_TRIANGLE_STRIP_ADJACENCY)
    case TriangleFan extends Mode(GL_TRIANGLE_FAN)
    case Patches extends Mode(GL_PATCHES)

/**
 * For now a primitive creates a single vertex buffer
 */

trait Primitive:

  type V <: Vertex[V]

  var mode: Mode = null
  var descriptor: VertexDescriptor = null
  var vertices: Array[V] = null
  var indices: Array[Int] = null
  var flags = Buffer.StorageFlags()

  var vb: Buffer = null
  var ib: Buffer = null
  var va: VertexArray = null

  create()

  def init(): Unit

  def create() =
    init()
    val vbb = BufferUtils.createByteBuffer(vertices.size * descriptor.vertexByteCount)
    val ibb = BufferUtils.createByteBuffer(indices.size * 4)
    for v <- vertices do vbb.putVertex(v)
    for i <- indices do ibb.putInt(i)
    vb = Buffer(vbb.rewind(), flags)
    ib = Buffer(ibb.rewind(), flags)
    va = VertexArray()
    va.bindVertexBuffer(0, vb, 0, descriptor.vertexByteCount)
    va.bindIndexBuffer(ib)
    for i <- 0 to descriptor.attributeCount - 1 do
      val ad = descriptor.ads(i)
      va.enableAttribute(ad.location)
      va.setAttributeFormat(ad.location, ad.format, descriptor.attributeOffset(i), ad.normalized)
      va.setAttributeBinding(ad.location, 0)

  def draw() =
    va.bind()
    glDrawElements(mode.value, indices.size, GL_UNSIGNED_INT, 0)

  def drawInstanced(n: Int) = 0

  def destroy() =
    va.destroy()
    ib.destroy()
    vb.destroy()

  extension (buf: ByteBuffer)
    def putVertex(v: V) = v >> buf
