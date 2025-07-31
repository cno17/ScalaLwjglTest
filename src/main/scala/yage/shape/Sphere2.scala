package yage.shape

import org.joml.Intersectionf
import org.joml.Vector2f
import org.joml.Vector3f

case class Sphere2(center: Vector2f, var radius: Float):
  
  def this() = this(Vector2f(), 0f)
  
  def this(s: Sphere2) = this(Vector2f(s.center), s.radius)

  /**
   * Let p1 and p2 the two intersection points (if they exist).
   * res.x = (p1.x + p2.x) / 2
   * res.y = (p1.y + p2.y) / 2
   * res.z = d(p1, p2) / 2
   */

  def findIntersection(s: Sphere2, res: Vector3f) =
    val c1 = center
    val r1 = radius * radius
    val c2 = s.center
    val r2 = s.radius * s.radius
    Intersectionf.intersectCircleCircle(c1, r1, c2, r2, res)
