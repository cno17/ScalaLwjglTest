package yage.shape

import org.joml.Intersectionf
import org.joml.Vector3f

case class Trigon3(p1: Vector3f, p2: Vector3f, p3: Vector3f):
  
  def this() = this(Vector3f(), Vector3f(), Vector3f())
  
  def this(t: Trigon3) = this(Vector3f(t.p1), Vector3f(t.p2), Vector3f(t.p3))
  
  def closestPoint(p: Vector3f, res: Vector3f) =
    Intersectionf.findClosestPointOnTriangle(p1, p2, p3, p, res)
  


// def closestPoint(x: Float, y: Float, z: Float, res: Vector3f) =
