package yage.shape

import org.joml.Vector3f
import org.joml.Intersectionf

// dir should always be normalized!

case class Ray3(org: Vector3f, dir: Vector3f):

  def this() = this(Vector3f(), Vector3f(1f, 0f, 0f))

  def this(r: Ray3) = this(Vector3f(r.org), Vector3f(r.dir))

  def testIntersectionWith(b: ABox3) =
    Intersectionf.testRayAab(org, dir, b.min, b.max)

  def testIntersectionWith(s: Sphere3) =
    Intersectionf.testRaySphere(org, dir, s.center, s.radius * s.radius)

  def findIntersectionWith(p: HyperPlane3, eps: Float = 0.000001f) =
    Intersectionf.intersectRayPlane(org, dir, p.p1, p.nor, eps)