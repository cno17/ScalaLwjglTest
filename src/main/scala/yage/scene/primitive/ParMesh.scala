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
import yage.base.par.TriMesh
import yage.scene.primitive.ParMesh.PVertex

import java.nio.ByteBuffer

// TODO: all par shapes in one file!

object ParMesh:

  class PVertex(val pos: Vector4f, val nor: Vector4f, val tec: Vector2f) extends Vertex[PVertex]:

    override def load(buf: ByteBuffer) =
      pos << buf
      nor << buf
      tec << buf
      this

    override def store(buf: ByteBuffer) =
      pos >> buf
      nor >> buf
      tec >> buf
      buf

class ParMesh(val mesh: TriMesh) extends Primitive:

  type V = PVertex

  def init() =
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(1, F32_4, Normal)
    val d2 = Descriptor(4, F32_2, TexCoord)
    mode = Mode.Triangles
    descriptor = VertexDescriptor(d0, d1, d2)
    vertices = createVertices()
    indices = createIndices()

  private def createVertices() =
    val nv = mesh.numV
    val pb = mesh.posB
    val nb = mesh.norB
    val tb = mesh.tecB
    val res = new Array[PVertex](nv)
    for i <- 0 to nv - 1 do
      val pos = Vector4f(pb.get(3 * i + 0), pb.get(3 * i + 1), pb.get(3 * i + 2), 1f)
      val nor = Vector4f(nb.get(3 * i + 0), nb.get(3 * i + 1), nb.get(3 * i + 2), 0f)
      val tec = Vector2f(tb.get(2 * i + 0), tb.get(2 * i + 1))
      res(i) = PVertex(pos, nor, tec)
    res

  private def createIndices() =
    val nt = mesh.numT
    val ib = mesh.indB
    val res = new Array[Int](3 * nt)
    // todo: buffer to array
    for i <- 0 to nt - 1 do
      res(3 * i + 0) = ib.get(3 * i + 0)
      res(3 * i + 1) = ib.get(3 * i + 1)
      res(3 * i + 2) = ib.get(3 * i + 2)
    res



