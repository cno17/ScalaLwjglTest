package yage.scene.primitive

import org.joml.Vector2f
import org.joml.Vector4f
import org.lwjgl.BufferUtils
import org.lwjgl.util.par.ParOctasphere.par_octasphere_get_counts
import org.lwjgl.util.par.ParOctasphere.par_octasphere_populate
import org.lwjgl.util.par.ParOctasphereConfig
import org.lwjgl.util.par.ParOctasphereMesh
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
import yage.scene.primitive.Octasphere.OVertex

import java.nio.ByteBuffer

object Octasphere:

  class OVertex(val pos: Vector4f, val nor: Vector4f, val tec: Vector2f) extends Vertex[OVertex]:

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
 * cr: corner radius
 * ns: num subdivisions
 */

class Octasphere(rx: Float, ry: Float, rz: Float, cr: Float, ns: Int) extends Primitive:

  type V = OVertex

  def this() = this(1, 1, 1, 0.1f, 2)

  override def init() =
    val cfg = ParOctasphereConfig.create()
    cfg.width(rx / 2)
    cfg.height(ry / 2)
    cfg.depth(rz / 2)
    cfg.corner_radius(cr)
    cfg.num_subdivisions(ns)
    val pNumV = BufferUtils.createIntBuffer(1)
    val pNumI = BufferUtils.createIntBuffer(1)
    par_octasphere_get_counts(cfg, pNumI, pNumV)
    val numV = pNumV.get
    val numI = pNumI.get
    val pPos = BufferUtils.createFloatBuffer(3 * numV)
    val pNor = BufferUtils.createFloatBuffer(3 * numV)
    val pTec = BufferUtils.createFloatBuffer(2 * numV)
    val pInd = BufferUtils.createShortBuffer(numI)
    val mesh = ParOctasphereMesh.create()
    mesh.positions(pPos)
    mesh.normals(pNor)
    mesh.texcoords(pTec)
    mesh.indices(pInd)
    par_octasphere_populate(cfg, mesh)
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(1, F32_4, Normal)
    val d2 = Descriptor(4, F32_2, TexCoord)
    mode = Mode.Triangles
    descriptor = VertexDescriptor(d0, d1, d2)
    vertices = new Array[V](numV)
    indices = new Array[Int](numI)
    for i <- 0 to numV - 1 do
      val pos = Vector4f(pPos.get(3 * i + 0), pPos.get(3 * i + 1), pPos.get(3 * i + 2), 1)
      val nor = Vector4f(pNor.get(3 * i + 0), pNor.get(3 * i + 1), pNor.get(3 * i + 2), 0)
      val tec = Vector2f(pTec.get(2 * i + 0), pTec.get(2 * i + 1))
      vertices(i) = OVertex(pos, nor, tec)
    for i <- 0 to numI - 1 do
      indices(i) = pInd.get(i)

