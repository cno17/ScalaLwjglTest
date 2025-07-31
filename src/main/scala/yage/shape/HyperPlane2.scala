package yage.shape

import org.joml.Intersectionf
import org.joml.Vector2f
import yage.shape.util.IntersectionResult2

/**
 * n = unit normal
 * distance = signedDistance
 *
 */

class HyperPlane2(var a: Float, var b: Float, var c: Float):

  val p1 = Vector2f()
  val p2 = Vector2f()
  val nor = Vector2f()
  val dir = Vector2f()

  def set(a: Float, b: Float, c: Float) =
    this.a = a
    this.b = b
    this.c = c
    update()

  def signedDistance(p: Vector2f) =
    Intersectionf.distancePointLine(p.x, p.y, a, b, c)

  def findIntersection(s: Sphere2, res: IntersectionResult2) =
    val sx = s.center.x
    val sy = s.center.y
    val sr = s.radius
    if !Intersectionf.intersectLineCircle(a, b, c, sx, sy, sr, res.vec3) then
      res.num = 0
    else if res.vec3.z == 0f then
      res.num = 1
      res.p1.set(res.vec3.x, res.vec3.y)
    else
      res.num = 2
      val dx = 0

  def update() = 0
