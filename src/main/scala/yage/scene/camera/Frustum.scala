package yage.scene.camera

import org.joml.FrustumIntersection
import org.joml.Matrix4f
import org.joml.Vector3f
import yage.shape.ABox3
import yage.shape.Segment3
import yage.shape.Sphere3

/**
 * After the projection matrix matVC has been changed the frustum AND the camera
 * should be updated: => Listener, FxProperty
 * 
 * How to specify a frustum? (l, r, b, t, n, f), ...
 * 
 */


class Frustum:

  val matVC = Matrix4f().perspective(3.1415f / 4, 4f / 3f, 0.1f, 100f)
  val matCV = Matrix4f().identity()

  private val culler = FrustumIntersection(matVC)

  update()
  
  def contains(pos: Vector3f) = culler.testPoint(pos)
  def intersects(seg: Segment3) = culler.testLineSegment(seg.p1, seg.p2)
  def intersects(box: ABox3) = culler.testAab(box.min, box.max)
  def intersects(sph: Sphere3) = culler.testSphere(sph.center, sph.radius)

  // ...

  def update() =
    matCV.set(matVC).invert()
    culler.set(matVC)
    this

