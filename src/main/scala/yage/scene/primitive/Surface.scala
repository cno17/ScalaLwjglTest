package yage.scene.primitive

import org.joml.Vector2f
import org.joml.Vector4f
import yage.base.opengl.primitive.Attribute.Descriptor
import yage.base.opengl.primitive.Attribute.Format.F32_2
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.Attribute.Semantics.*
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.primitive.Primitive.Mode
import yage.base.opengl.primitive.Vertex
import yage.base.opengl.primitive.VertexDescriptor
import yage.scene.primitive.Surface.SVertex

import java.nio.ByteBuffer
import scala.collection.mutable.ArrayBuffer

val PI = Math.PI.toFloat // use MathF.PI 

/**
 * a parametrized surface: (u, v) => (x, y, z)
 * todo: create indices, do not use array buffers - compute array size
 */

object Surface:

  class SVertex(val pos: Vector4f, val nor: Vector4f, var tan: Vector4f, val col: Vector4f, var tec: Vector2f)
    extends Vertex[SVertex]:

    override def load(buf: ByteBuffer) =
      pos.set(buf)
      nor.set(buf)
      tan.set(buf)
      col.set(buf)
      tec.set(buf)
      this

    override def store(buf: ByteBuffer) =
      pos.store(buf)
      nor.store(buf)
      tan.store(buf)
      col.store(buf)
      tec.store(buf)
      buf


// minU, minV, ...?
abstract class Surface(minU: Float, maxU: Float, minV: Float, maxV: Float, numU: Int, numV: Int)
  extends Primitive:

  type V = SVertex

  private val du = 0.0001f // (maxU - minU) * 0.001f
  private val dv = 0.0001f

  private val incU = (maxU - minU) / (numU - 1)
  private val incV = (maxV - minV) / (numV - 1)

  def vertexCount = numU * numV

  def indexCount = (numU - 1) * (numV - 1) * 6

  def toIndex(iu: Int, iv: Int) = iu + iv * numU

  // Vector3f?
  def pos(u: Float, v: Float): Vector4f

  // use vec ops!
  def nor(u: Float, v: Float) =
    val p0 = pos(u, v)
    val p1 = pos(u + du, v)
    val p2 = pos(u, v + dv)
    val ux = p1.x - p0.x
    val uy = p1.y - p0.y
    val uz = p1.z - p0.z
    val vx = p2.x - p0.x
    val vy = p2.y - p0.y
    val vz = p2.z - p0.z
    val nx = uy * vz - vy * uz
    val ny = vx * uz - ux * vz
    val nz = ux * vy - vx * uy
    Vector4f(nx, ny, nz, 0).normalize() // TODO

  def tan(u: Float, v: Float) =
    val p0 = pos(u, v)
    val p1 = pos(u + du, v)
    val tx = p1.x - p0.x
    val ty = p1.y - p0.y
    val tz = p1.z - p0.z
    Vector4f(tx, ty, tz, 0).normalize()

  def col(u: Float, v: Float) =
    Vector4f(1, 1, 1, 1)

  def tec(u: Float, v: Float) =
    Vector2f(u / numU, v / numV)

  override def init() =
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(1, F32_4, Normal)
    val d2 = Descriptor(2, F32_4, Tangent)
    val d3 = Descriptor(3, F32_4, Color)
    val d4 = Descriptor(4, F32_2, TexCoord)
    mode = Mode.Triangles
    descriptor = VertexDescriptor(d0, d1, d2, d3, d4)
    vertices = new Array[SVertex](vertexCount)
    for iu <- 0 to numU - 1 do
      val u = minU + incU * iu
      for iv <- 0 to numV - 1 do
        val v = minV + incV * iv
        val i = toIndex(iu, iv)
        vertices(i) = SVertex(pos(u, v), nor(u, v), tan(u, v), col(u, v), tec(u, v))
    val indB = new ArrayBuffer[Int]()
    for iu <- 0 to numU - 2 do
      for iv <- 0 to numV - 2 do
        indB += toIndex(iu + 0, iv + 0)
        indB += toIndex(iu + 1, iv + 0)
        indB += toIndex(iu + 1, iv + 1)
        indB += toIndex(iu + 1, iv + 1)
        indB += toIndex(iu + 0, iv + 1)
        indB += toIndex(iu + 0, iv + 0)
    indices = indB.toArray

  def createVertices() =
    val vertices = new Array[SVertex](vertexCount)
    for iu <- 0 to numU - 1 do
      val u = minU + incU * iu
      for iv <- 0 to numV - 1 do
        val v = minV + incV * iv
        val i = toIndex(iu, iv)
        vertices(i) = SVertex(pos(u, v), nor(u, v), tan(u, v), col(u, v), tec(u, v))
    vertices