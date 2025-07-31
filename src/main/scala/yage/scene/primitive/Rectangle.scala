package yage.scene.primitive

import org.joml.Vector2f
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
import yage.scene.primitive.Rectangle.RVertex

import java.nio.ByteBuffer

object Rectangle:

  class RVertex(val pos: Vector4f, val nor: Vector4f, val tec: Vector2f)
    extends Vertex[RVertex]:

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

/**
 * A Rectangle in the xy-plane.
 */

class Rectangle(x1: Float, y1: Float, x2: Float, y2: Float) extends Primitive:

  type V = RVertex

  def init() =
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(1, F32_4, Normal)
    val d2 = Descriptor(4, F32_2, TexCoord)
    val v0 = RVertex(Vector4f(x1, y1, 0, 1), Vector4f(0, 0, 1, 0), Vector2f(0, 0))
    val v1 = RVertex(Vector4f(x2, y1, 0, 1), Vector4f(0, 0, 1, 0), Vector2f(1, 0))
    val v2 = RVertex(Vector4f(x2, y2, 0, 1), Vector4f(0, 0, 1, 0), Vector2f(1, 1))
    val v3 = RVertex(Vector4f(x1, y2, 0, 1), Vector4f(0, 0, 1, 0), Vector2f(0, 1))
    mode = Mode.Triangles
    descriptor = VertexDescriptor(d0, d1, d2)
    vertices = Array(v0, v1, v2, v3)
    indices = Array(0, 1, 2, 2, 3, 0)
    
