package yage.shape.info

import org.joml.Intersectionf
import org.joml.Vector3f
import yage.shape.HyperPlane3
import yage.shape.Ray2
import yage.shape.Ray3
import yage.shape.Segment3
import yage.shape.Sphere2
import yage.shape.Sphere3
import yage.shape.Trigon3

// todo: move methods to shapes

object Intersector:

  def find(s: Segment3, t: Trigon3, res: Vector3f) =
    false

  def test(p: HyperPlane3, s: Sphere3) =
    val sx = s.center.x
    val sy = s.center.y
    val sz = s.center.z
    val sr = s.radius
    Intersectionf.testPlaneSphere(p.a, p.b, p.c, p.d, sx, sy, sz, sr)

  def test(r: Ray2, s: Sphere2) =
    Intersectionf.testRayCircle(r.org, r.dir, s.center, s.radius * s.radius)

  def test(r: Ray3, s: Sphere3) =
    Intersectionf.testRaySphere(r.org, r.dir, s.center, s.radius * s.radius)
