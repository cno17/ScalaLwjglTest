package yage.shape

import org.joml.Intersectionf
import org.joml.Intersectionf.*
import org.joml.Vector2f
import yage.shape.util.IntersectionResult2

case class Segment2(p1: Vector2f, p2: Vector2f):

  def this() = this(Vector2f(), Vector2f())
  
  def this(s: Segment2) = this(Vector2f(s.p1), Vector2f(s.p2))

//  def findIntersectionWith(t: Trigon3, res: Vector2f, eps: Float = 0.001f) =
//    Intersectionf.intersectLineSegmentTriangle(p1, p2, t.p1, t.p2, t.p3, eps, res)

  def findIntersectionWith(b: ABox2, res: IntersectionResult2) =
    val code = Intersectionf.intersectLineSegmentAar(p1, p2, b.min, b.max, res.vec2)
    if code == INSIDE || code == OUTSIDE then
      res.num = 0
    else if code == ONE_INTERSECTION then
      res.num = 1
      val t = res.vec2.x
      res.p1.x = p1.x + t * (p2.x - p1.x)
      res.p1.y = p1.y + t * (p2.y - p1.y)
    else if code == TWO_INTERSECTION then
      res.num = 2
      val t1 = res.vec2.x
      val t2 = res.vec2.y
      res.p1.x = p1.x + t1 * (p2.x - p1.x)
      res.p1.y = p1.y + t1 * (p2.y - p1.y)
      res.p2.x = p1.x + t2 * (p2.x - p1.x)
      res.p2.y = p1.y + t2 * (p2.y - p1.y)
    else
      println("strange segment-box intersection")
