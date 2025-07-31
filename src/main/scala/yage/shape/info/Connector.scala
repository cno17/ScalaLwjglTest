package yage.shape.info

import org.joml.Intersectionf
import org.joml.Vector3f
import yage.shape.Segment3
import yage.shape.Trigon3

// todo: move methods to shapes

object Connector:

  def closestPoints(s1: Segment3, s2: Segment3, res1: Vector3f, res2: Vector3f) =
    val a1 = s1.p1
    val a2 = s1.p2
    val b1 = s2.p1
    val b2 = s2.p2
    Intersectionf.findClosestPointsLineSegments(
      a1.x, a1.y, a1.z, a2.x, a2.y, a2.z,
      b1.x, b1.y, b1.z, b2.x, b2.y, b2.z,
      res1, res2
    )

  def closestPoints(s: Segment3, t: Trigon3, resS: Vector3f, resT: Vector3f) =
    val a1 = s.p1
    val a2 = s.p2
    val b1 = t.p1
    val b2 = t.p2
    val b3 = t.p3
    Intersectionf.findClosestPointsLineSegmentTriangle(
      a1.x, a1.y, a1.z, a2.x, a2.y, a2.z,
      b1.x, b1.y, b1.z, b2.x, b2.y, b2.z, b3.x, b3.y, b3.z,
      resS, resT
    )

