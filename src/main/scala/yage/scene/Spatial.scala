package yage.scene

import org.joml.Matrix4x3f
import org.joml.Vector3f

class Spatial(var name: String, var parent: Node):

  val matMP = Matrix4x3f()
  val matMW = Matrix4x3f()

  var worldTrafoIsValid = true

  def scaL(s: Float) = matMP.scaleLocal(s, s, s)
  def scaR(s: Float) = matMP.scale(s, s, s)
  def rotL(a: Float, d: Vector3f) = matMP.rotateLocal(a, d.x, d.y, d.z)
  def rotL(a: Float, x: Float, y: Float, z: Float) = matMP.rotateLocal(a, x, y, z)
  def rotLX(a: Float) = matMP.rotateLocalX(a)
  def rotLY(a: Float) = matMP.rotateLocalY(a)
  def rotLZ(a: Float) = matMP.rotateLocalZ(a)
  def rotR(a: Float, d: Vector3f) = matMP.rotate(a, d.x, d.y, d.z)
  def rotR(a: Float, x: Float, y: Float, z: Float) = matMP.rotate(a, x, y, z)
  def rotRX(a: Float) = matMP.rotateX(a)
  def rotRY(a: Float) = matMP.rotateY(a)
  def rotRZ(a: Float) = matMP.rotateZ(a)
  def traL(t: Vector3f) = matMP.translateLocal(t)
  def traL(tx: Float, ty: Float, tz: Float) = matMP.translateLocal(tx, ty, tz)
  def traLX(t: Float) = matMP.translateLocal(t, 0f, 0f)
  def traLY(t: Float) = matMP.translateLocal(0f, t, 0f)
  def traLZ(t: Float) = matMP.translateLocal(0f, 0f, t)
  def traR(t: Vector3f) = matMP.translate(t)
  def traR(tx: Float, ty: Float, tz: Float) = matMP.translate(tx, ty, tz)
  def traRX(t: Float) = matMP.translate(t, 0f, 0f)
  def traRY(t: Float) = matMP.translate(0f, t, 0f)
  def traRZ(t: Float) = matMP.translate(0f, 0f, t)


