package yage.shape

import org.joml.Intersectionf
import org.joml.Intersectionf.*
import org.joml.Vector3f
import yage.shape.util.IntersectionResult3

case class Segment3(p1: Vector3f, p2: Vector3f):

  def this() = this(Vector3f(), Vector3f())

  def this(s: Segment3) = this(Vector3f(s.p1), Vector3f(s.p2))

  def findIntersection(t: Trigon3, res: Vector3f, eps: Float = 0.001f) =
    Intersectionf.intersectLineSegmentTriangle(p1, p2, t.p1, t.p2, t.p3, eps, res)

  def findClosestPoints(t: Trigon3, resS: Vector3f, resT: Vector3f) =
    val q1 = t.p1
    val q2 = t.p2
    val q3 = t.p3
    Intersectionf.findClosestPointsLineSegmentTriangle(
      p1.x, p1.y, p1.z, p2.x, p2.y, p2.z,
      q1.x, q1.y, q1.z, q2.x, q2.y, q2.z, q3.x, q3.y, q3.z,
      resS, resT
    )

  def findIntersectionWith(b: ABox3, res: IntersectionResult3) =
    val code = Intersectionf.intersectLineSegmentAab(p1, p2, b.min, b.max, res.vec2)
    if code == INSIDE || code == OUTSIDE then
      res.num = 0
    else if code == ONE_INTERSECTION then
      res.num = 1
      val t = res.vec2.x
      res.p1.x = p1.x + t * (p2.x - p1.x)
      res.p1.y = p1.y + t * (p2.y - p1.y)
      res.p1.z = p1.z + t * (p2.z - p1.z)
    else if code == TWO_INTERSECTION then
      res.num = 2
      val t1 = res.vec2.x
      val t2 = res.vec2.y
      res.p1.x = p1.x + t1 * (p2.x - p1.x)
      res.p1.y = p1.y + t1 * (p2.y - p1.y)
      res.p1.z = p1.z + t1 * (p2.z - p1.z)
      res.p2.x = p1.x + t2 * (p2.x - p1.x)
      res.p2.y = p1.y + t2 * (p2.y - p1.y)
      res.p2.z = p1.z + t2 * (p2.z - p1.z)
    else
      println("strange segment-box intersection")

