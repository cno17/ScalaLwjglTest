package yage.shape

import org.joml.Intersectionf
import org.joml.Vector2f
import org.joml.Vector3f

// Achsenabschnittspunkte

// dir1, dir2 and nor should alway form a right handed orthonormal basis

class HyperPlane3(var a: Float, var b: Float, var c: Float, var d: Float):

  val p1 = Vector3f()
  val p2 = Vector3f()
  val p3 = Vector3f()
  val nor = Vector3f()
  val dir1 = Vector3f()
  val dir2 = Vector3f()

  def set(a: Float, b: Float, c: Float, d: Float) =
    this.a = a
    this.b = b
    this.c = c
    this.d = d
    update()

  def signedDistance(p: Vector3f) =
    Intersectionf.distancePointPlane(p.x, p.y, p.z, a, b, c, d)

  def closestPoint(p: Vector3f, res: Vector3f) =
    Intersectionf.findClosestPointOnPlane(p1.x, p1.y, p1.z,nor.x, nor.y, nor.z, p.x, p.y, p.z, res)

  def update() = 0