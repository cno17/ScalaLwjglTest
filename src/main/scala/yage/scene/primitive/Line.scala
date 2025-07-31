package yage.scene.primitive

import org.joml.Vector3f
import yage.base.opengl.primitive.Attribute.Descriptor
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.Attribute.Semantics.Color
import yage.base.opengl.primitive.Attribute.Semantics.Position
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.primitive.Primitive.Mode
import yage.base.opengl.primitive.VertexDescriptor
import org.joml.Vector4f
import yage.base.opengl.primitive.Vertex
import yage.scene.primitive.Line.LVertex

import java.nio.ByteBuffer

object Line:

  class LVertex(val pos: Vector4f, val col: Vector4f) extends Vertex[LVertex]:

    override def load(buf: ByteBuffer) =
      pos.set(buf)
      col.set(buf)
      this

    override def store(buf: ByteBuffer) =
      pos.store(buf)
      col.store(buf)
      buf

class Line(pos1: Vector3f, col1: Vector4f, pos2: Vector3f, col2: Vector4f) 
  extends Primitive:
  
  def this(x1: Float, y1: Float, x2: Float, y2: Float) =
    this(Vector3f(x1, y1, 0), Vector4f(1), Vector3f(x2, y2, 0), Vector4f(1))

  type V = LVertex

  def init() =
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(3, F32_4, Color)
    val v0 = LVertex(Vector4f(pos1.x, pos1.y, 0, 1), Vector4f(1, 1, 1, 1))
    val v1 = LVertex(Vector4f(pos2.x, pos2.y, 0, 1), Vector4f(1, 1, 1, 1))
    mode = Mode.Lines
    descriptor = VertexDescriptor(d0, d1)
    vertices = Array(v0, v1)
    indices = Array(0, 1)
    
