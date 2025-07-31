package yage.scene.primitive

import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.util.par.ParShapes.par_shapes_create_cube
import org.lwjgl.util.par.ParShapes.par_shapes_create_dodecahedron
import org.lwjgl.util.par.ParShapes.par_shapes_create_icosahedron
import org.lwjgl.util.par.ParShapes.par_shapes_create_octahedron
import org.lwjgl.util.par.ParShapes.par_shapes_create_tetrahedron
import org.lwjgl.util.par.ParShapesMesh
import yage.base.opengl.primitive.Attribute.Descriptor
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.Attribute.Semantics.Normal
import yage.base.opengl.primitive.Attribute.Semantics.Position
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.primitive.Primitive.Mode.Triangles
import yage.base.opengl.primitive.Vertex
import yage.base.opengl.primitive.VertexDescriptor
import yage.scene.primitive.Platonic.PVertex
import yage.scene.primitive.Platonic.Type

import java.nio.ByteBuffer
import scala.collection.mutable.ArrayBuffer

object Platonic:

  enum Type(val creator: () => ParShapesMesh):
    case Tetrahedron extends Type(par_shapes_create_tetrahedron)
    case Hexaahedron extends Type(par_shapes_create_cube)
    case Octaahedron extends Type(par_shapes_create_octahedron)
    case Dodecaahedron extends Type(par_shapes_create_dodecahedron)
    case Icosaahedron extends Type(par_shapes_create_icosahedron)

  class PVertex(val pos: Vector4f, val nor: Vector4f) extends Vertex[PVertex]:

    override def load(buf: ByteBuffer) =
      pos.set(buf)
      nor.set(buf)
      this

    override def store(buf: ByteBuffer) =
      pos.store(buf)
      nor.store(buf)
      buf

class Platonic(val typ: Type) extends Primitive:

  type V = PVertex

  def init() =
    // val mesh = par_shapes_create_dodecahedron()
    val mesh = typ.creator()
    val nv = mesh.npoints()
    val nt = mesh.ntriangles()
    val posB = mesh.points(3 * nv)
    val indB = mesh.triangles(3 * nt)
    val vb = ArrayBuffer[PVertex]()
    val ib = ArrayBuffer[Int]()
    for t <- 0 to nt - 1 do
      val i0 = indB.get(3 * t + 0)
      val i1 = indB.get(3 * t + 1)
      val i2 = indB.get(3 * t + 2)
      val p0 = Vector3f(posB.get(3 * i0 + 0), posB.get(3 * i0 + 1), posB.get(3 * i0 + 2))
      val p1 = Vector3f(posB.get(3 * i1 + 0), posB.get(3 * i1 + 1), posB.get(3 * i1 + 2))
      val p2 = Vector3f(posB.get(3 * i2 + 0), posB.get(3 * i2 + 1), posB.get(3 * i2 + 2))
      val u = Vector3f(p1).sub(p0)
      val v = Vector3f(p2).sub(p0)
      val n = Vector3f(u).cross(v).normalize()
      vb += PVertex(Vector4f(p0, 1f), Vector4f(n, 0f))
      vb += PVertex(Vector4f(p1, 1f), Vector4f(n, 0f))
      vb += PVertex(Vector4f(p2, 1f), Vector4f(n, 0f))
      ib += 3 * t + 0
      ib += 3 * t + 1
      ib += 3 * t + 2
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(1, F32_4, Normal)
    mode = Triangles
    descriptor = VertexDescriptor(d0, d1)
    vertices = vb.toArray
    indices = ib.toArray

