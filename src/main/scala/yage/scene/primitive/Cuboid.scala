package yage.scene.primitive

import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import yage.base.opengl.primitive.Attribute.Descriptor
import yage.base.opengl.primitive.Attribute.Format.F32_2
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.Attribute.Semantics.Normal
import yage.base.opengl.primitive.Attribute.Semantics.Position
import yage.base.opengl.primitive.Attribute.Semantics.TexCoord
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.primitive.Primitive.Mode
import yage.base.opengl.primitive.Vertex
import yage.base.opengl.primitive.VertexDescriptor
import yage.scene.primitive.Cuboid.CVertex

import java.nio.ByteBuffer

/**
 * face order: -x = left, +x = right, -y = bottom, +y = top, -z = far, +z = near
 * vertex order: lb, rb, rt, lt
 */

object Cuboid:

  class CVertex(val pos: Vector4f, val nor: Vector4f, val tec: Vector2f)
    extends Vertex[CVertex]:

    override def load(buf: ByteBuffer) =
      pos.set(buf)
      nor.set(buf)
      tec.set(buf)
      this

    override def store(buf: ByteBuffer) =
      pos.store(buf)
      nor.store(buf)
      tec.store(buf)
      buf


class Cuboid(min: Vector3f, max: Vector3f) extends Primitive:

  type V = CVertex

  def this(rx: Float, ry: Float, rz: Float) = this(Vector3f(-rx, -ry, -rz), Vector3f(rx, ry, rz))

  def this() = this(1f, 1f, 1f)

  override def init() =
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(1, F32_4, Normal)
    val d2 = Descriptor(4, F32_2, TexCoord)
    mode = Mode.Triangles
    descriptor = VertexDescriptor(d0, d1, d2)
    vertices = vs
    indices = is

  private def vs =
    Array(
      CVertex(Vector4f(min.x, min.y, min.z, 1f), Vector4f(-1f, 0f, 0f, 0f), Vector2f(0f, 0f)),
      CVertex(Vector4f(min.x, min.y, max.z, 1f), Vector4f(-1f, 0f, 0f, 0f), Vector2f(1f, 0f)),
      CVertex(Vector4f(min.x, max.y, max.z, 1f), Vector4f(-1f, 0f, 0f, 0f), Vector2f(1f, 1f)),
      CVertex(Vector4f(min.x, max.y, min.z, 1f), Vector4f(-1f, 0f, 0f, 0f), Vector2f(0f, 1f)),
      CVertex(Vector4f(max.x, min.y, max.z, 1f), Vector4f(+1f, 0f, 0f, 0f), Vector2f(0f, 0f)),
      CVertex(Vector4f(max.x, min.y, min.z, 1f), Vector4f(+1f, 0f, 0f, 0f), Vector2f(1f, 0f)),
      CVertex(Vector4f(max.x, max.y, min.z, 1f), Vector4f(+1f, 0f, 0f, 0f), Vector2f(1f, 1f)),
      CVertex(Vector4f(max.x, max.y, max.z, 1f), Vector4f(+1f, 0f, 0f, 0f), Vector2f(0f, 1f)),
      CVertex(Vector4f(min.x, min.y, min.z, 1f), Vector4f(0f, -1f, 0f, 0f), Vector2f(0f, 0f)),
      CVertex(Vector4f(max.x, min.y, min.z, 1f), Vector4f(0f, -1f, 0f, 0f), Vector2f(1f, 0f)),
      CVertex(Vector4f(max.x, min.y, max.z, 1f), Vector4f(0f, -1f, 0f, 0f), Vector2f(1f, 1f)),
      CVertex(Vector4f(min.x, min.y, max.z, 1f), Vector4f(0f, -1f, 0f, 0f), Vector2f(0f, 1f)),
      CVertex(Vector4f(min.x, max.y, max.z, 1f), Vector4f(0f, +1f, 0f, 0f), Vector2f(0f, 0f)),
      CVertex(Vector4f(max.x, max.y, max.z, 1f), Vector4f(0f, +1f, 0f, 0f), Vector2f(1f, 0f)),
      CVertex(Vector4f(max.x, max.y, min.z, 1f), Vector4f(0f, +1f, 0f, 0f), Vector2f(1f, 1f)),
      CVertex(Vector4f(min.x, max.y, min.z, 1f), Vector4f(0f, +1f, 0f, 0f), Vector2f(0f, 1f)),
      CVertex(Vector4f(max.x, min.y, min.z, 1f), Vector4f(0f, 0f, -1f, 0f), Vector2f(0f, 0f)),
      CVertex(Vector4f(min.x, min.y, min.z, 1f), Vector4f(0f, 0f, -1f, 0f), Vector2f(1f, 0f)),
      CVertex(Vector4f(min.x, max.y, min.z, 1f), Vector4f(0f, 0f, -1f, 0f), Vector2f(1f, 1f)),
      CVertex(Vector4f(max.x, max.y, min.z, 1f), Vector4f(0f, 0f, -1f, 0f), Vector2f(0f, 1f)),
      CVertex(Vector4f(min.x, min.y, max.z, 1f), Vector4f(0f, 0f, +1f, 0f), Vector2f(0f, 0f)),
      CVertex(Vector4f(max.x, min.y, max.z, 1f), Vector4f(0f, 0f, +1f, 0f), Vector2f(1f, 0f)),
      CVertex(Vector4f(max.x, max.y, max.z, 1f), Vector4f(0f, 0f, +1f, 0f), Vector2f(1f, 1f)),
      CVertex(Vector4f(min.x, max.y, max.z, 1f), Vector4f(0f, 0f, +1f, 0f), Vector2f(0f, 1f))
    )

  private def is =
    Array(
      0, 1, 2, 2, 3, 0,
      4, 5, 6, 6, 7, 4,
      8, 9, 10, 10, 11, 8,
      12, 13, 14, 14, 15, 12,
      16, 17, 18, 18, 19, 16,
      20, 21, 22, 22, 23, 20
    )
