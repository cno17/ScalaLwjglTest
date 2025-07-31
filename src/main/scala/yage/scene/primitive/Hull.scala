package yage.scene.primitive

import com.github.quickhull3d.Point3d
import com.github.quickhull3d.QuickHull3D
import org.joml.Vector3f
import org.joml.Vector4f
import yage.base.opengl.primitive.Attribute.Descriptor
import yage.base.opengl.primitive.Attribute.Format.F32_4
import yage.base.opengl.primitive.Attribute.Semantics.Normal
import yage.base.opengl.primitive.Attribute.Semantics.Position
import yage.base.opengl.primitive.Primitive
import yage.base.opengl.primitive.Primitive.Mode
import yage.base.opengl.primitive.Vertex
import yage.base.opengl.primitive.VertexDescriptor
import yage.base.FMath.rndF
import yage.scene.primitive.Hull.HVertex

import java.nio.ByteBuffer
import scala.collection.mutable.ArrayBuffer

object Hull:

  class HVertex(val pos: Vector4f, val nor: Vector4f) extends Vertex[HVertex]:

    override def load(buf: ByteBuffer) =
      pos << buf
      nor << buf
      this

    override def store(buf: ByteBuffer) =
      pos >> buf
      nor >> buf
      buf
  
  def inCuboid(num: Int, radX: Float, radY: Float, radZ: Float) =
    val points = ArrayBuffer[Vector3f]()
    while points.size < num do
      val x = rndF(-radX, radX)
      val y = rndF(-radY, radY)
      val z = rndF(-radZ, radZ)
      points += Vector3f(x, y, z)
    new Hull(points.toArray)

  def inSpheroid(num: Int, radX: Float, radY: Float, radZ: Float) =
    val points = ArrayBuffer[Vector3f]()
    while points.size < num do
      val x = rndF(-radX, radX)
      val y = rndF(-radY, radY)
      val z = rndF(-radZ, radZ)
      val u = x / radX
      val v = y / radY
      val w = z / radZ
      if u * u + v * v + w * w < 1 then 
        points += Vector3f(x, y, z)
    new Hull(points.toArray)

class Hull(points: Array[Vector3f]) extends Primitive:

  type V = HVertex

  def init() =
    val hull = QuickHull3D()
    hull.build(points.map(toPoint(_)))
    hull.triangulate()
    val vs = hull.getVertices
    val fs = hull.getFaces
    val vb = ArrayBuffer[HVertex]()
    val ib = ArrayBuffer[Int]()
    for i <- 0 to fs.size - 1 do
      val f = fs(i)
      val p0 = toVector(vs(f(0)))
      val p1 = toVector(vs(f(1)))
      val p2 = toVector(vs(f(2)))
      val u = Vector3f(p1).sub(p0)
      val v = Vector3f(p2).sub(p0)
      val n = Vector3f(u).cross(v).normalize()
      vb += HVertex(Vector4f(p0, 1f), Vector4f(n, 0f))
      vb += HVertex(Vector4f(p1, 1f), Vector4f(n, 0f))
      vb += HVertex(Vector4f(p2, 1f), Vector4f(n, 0f))
      ib += 3 * i + 0
      ib += 3 * i + 1
      ib += 3 * i + 2
    val d0 = Descriptor(0, F32_4, Position)
    val d1 = Descriptor(1, F32_4, Normal)
    mode = Mode.Triangles
    descriptor = VertexDescriptor(d0, d1)
    vertices = vb.toArray
    indices = ib.toArray

  private def toPoint(v: Vector3f) = Point3d(v.x, v.y, v.z)
  
  private def toVector(p: Point3d) = Vector3f(p.x.toFloat, p.y.toFloat, p.z.toFloat)
  


