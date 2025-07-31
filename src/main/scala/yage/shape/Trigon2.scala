package yage.shape

import org.joml.Intersectionf
import org.joml.Vector2f

case class Trigon2(p1: Vector2f, p2: Vector2f, p3: Vector2f):
  
  def this() = this(Vector2f(), Vector2f(), Vector2f())
  
  def this(t: Trigon2) = this(Vector2f(t.p1), Vector2f(t.p2), Vector2f(t.p3))

  def contains(x: Float, y: Float) =
    Intersectionf.testPointTriangle(x, y, p1.x, p1.y, p2.x, p2.y, p3.x, p3.y)

  def contains(p: Vector2f): Boolean =
    contains(p.x, p.y)

  def closestPoint(p: Vector2f, res: Vector2f) =
    Intersectionf.findClosestPointOnTriangle(p1, p2, p3, p, res)
  


// def closestPoint(x: Float, y: Float, z: Float, res: Vector2f) =
