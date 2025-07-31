package yage.scene.camera

import org.joml.FrustumIntersection
import org.joml.Matrix4f
import org.joml.Matrix4x3f
import org.joml.Vector3f
import yage.base.joml.Frame3
import yage.base.joml.MatrixExt
import yage.shape.ABox3
import yage.shape.Segment3
import yage.shape.Sphere3

/**
 * Terminology: 
 * matWV = view matrix
 * matVC = projection matrix
 *
 * move = translate // moveSpeed/Step
 * turn = rotate // turnSpeed/Step
 *
 * TODO: smarter update: the projection changes seldom!  
 */

class Camera extends MatrixExt:

  val matVW = Matrix4x3f()
  val matWV = Matrix4x3f()
  val matVC = Matrix4f().perspective(3.1415f / 4, 4f / 3f, 0.1f, 10000f)
  val matCV = Matrix4f()
  val matWC = Matrix4f()
  val matCW = Matrix4f()

  private val culler = FrustumIntersection(matVC)

  update()

  // val frustum = Frustum()
  // def matVC = frustum.matVC
  // def matCV = frustum.matCV

  def frustumContains(pos: Vector3f) = culler.testPoint(pos)
  def frustumIntersects(seg: Segment3) = culler.testLineSegment(seg.p1, seg.p2)
  def frustumIntersects(box: ABox3) = culler.testAab(box.min, box.max)
  def frustumIntersects(sph: Sphere3) = culler.testSphere(sph.center, sph.radius)

  def moveTo(px: Float, py: Float, pz: Float) =
    matVW.setTranslation(px, py, pz)
    update()

  def moveTo(p: Vector3f): Camera = moveTo(p.x, p.y, p.z)

  def move(tx: Float, ty: Float, tz: Float) =
    matVW.translate(tx, ty, tz)
    update()

  def move(t: Vector3f): Camera = move(t.x, t.y, t.z)

  def turnX(a: Float) =
    matVW.rotateX(a)
    update()
  
  def turnY(a: Float) =
    matVW.rotateY(a)
    update()
  
  def turnZ(a: Float) =
    matVW.rotateZ(a)
    update()

  def lookAt(px: Float, py: Float, pz: Float) =
    val f = matVW.get(Frame3())
    f.dirY.set(0, 1, 0)
    f.dirZ.set(f.orig).sub(px, py, pz, f.dirZ).normalize()
    f.dirY.cross(f.dirZ, f.dirX).normalize()
    matVW.set(f)
    update()

  def lookAlong(dx: Float, dy: Float, dz: Float) = 0
  
  def alignYUp() =
    val f = matVW.get(Frame3())
    f.dirY.set(0, 1, 0)
    f.dirY.cross(f.dirZ, f.dirX).normalize()
    f.dirZ.cross(f.dirX, f.dirY).normalize()
    matVW.set(f)
    update()

  def update() =
    matWV.set(matVW).invert()
    matCV.set(matVC).invert()
    matWC.set(matVC).mul(matWV)
    matCW.set(matVW).mul(matCV)
    this

  override def toString() = matWV.toString + "\n" + matVW.toString


/*
def lookAt(px: Float, py: Float, pz: Float, ux: Float, uy: Float, uz: Float) =
  val fx = matVW.m30
  val fy = matVW.m31
  val fz = matVW.m32
  matVW.identity().lookAt(fx, fy, fz, px, py, pz, ux, uy, uz)
  update()

def lookAlong(dx: Float, dy: Float, dz: Float, ux: Float, uy: Float, uz: Float) =
  val fromX = matVW.m30
  val fromY = matVW.m31
  val fromZ = matVW.m32
  matVW.lookAlong(dx, dy, dz, ux, uy, uz)
  update()

def lookAt(p: Vector3f, ux: Float = 0f, uy: Float = 1f, uz: Float = 0f): Camera =
  lookAt(p.x, p.y, p.z, ux, uy, uz)

def lookAlong(d: Vector3f, ux: Float = 0f, uy: Float = 1f, uz: Float = 0f): Camera =
  lookAlong(d.x, d.y, d.z, ux, uy, uz)
*/