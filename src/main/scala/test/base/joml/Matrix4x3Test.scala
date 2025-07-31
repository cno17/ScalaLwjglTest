package test.base.joml

import org.joml.Matrix4x3f
import org.joml.Vector3f
import yage.base.joml.MatrixExt
import yage.base.joml.VectorExt

object Matrix4x3Test extends MatrixExt, VectorExt:

  def main(args: Array[String]) = testLookAlong

  def test =
    val m = Matrix4x3f().rotate(0.2f, Vector3f(2, 5, 7).normalize())
    println(m.str)
    m.negateY()
    println(m.str)

  def testRotateTowards =
    val dir = Vector3f(3f, -17f, 7f).normalize()
    val up = Vector3f(0f, 1f, 0f)
    val mat = Matrix4x3f().translate(2, 6, -5).rotateTowards(dir, up)
    println(mat.str)
    println(dir.str)

  def testLookAt =
    val eye = Vector3f(3, 5, 7)
    val center = Vector3f(0, 0, 0)
    val up = Vector3f(0, 1, 0)
    val m = Matrix4x3f().lookAt(eye, center, up)
    val d1 = Vector3f(center).sub(eye).normalize()
    val d2 = m.getColumn(2, Vector3f()).mul(-1).normalize()
    println(m.str)
    println(d1.str)
    println(d2.str)


  def testLookAlong =
    val d = Vector3f(2, 5, 1).normalize()
    val u = Vector3f(0, 1, 0).normalize()
    val m = Matrix4x3f().lookAlong(d, u)
    println(d.str)
    println(u.str)
    println(m.str)

  def testGetUnnormalizedRotation = 0

  def testEulerAngles =
    val m = Matrix4x3f()
    m.rotateX(-0.2f).rotateY(1.5f).rotateZ(0.3f)
    println(m.getEulerAnglesXYZ(Vector3f()).str)

  def testBillboard =
    val p1 = Vector3f(2, 3, 5)
    val p2 = Vector3f(8, 6, 4)
    val m = Matrix4x3f()
    m.billboardSpherical(p1, p2)
    val d1 = Vector3f(p2).sub(p1).normalize()
    val d2 = m.getColumn(2, Vector3f()).normalize()
    println(m.str)
    println(d1.str)
    println(d2.str)

  def testLeftRight =
    val p = Vector3f(1, 2, 3)
    val m = Matrix4x3f()
    // m.rotation(90f.toRadians)
    // m.translate(2f, 3f)
    m.transformPosition(p)
    println(p.str)

  def testRotateTo =
    val d1 = Vector3f(+2, 3, 5).normalize()
    val d2 = Vector3f(-5, 1, 2).normalize()
    val m = Matrix4x3f()
    // m.rotateTo(d1, d2)
    // println(m.str)
    println(d1.str)
    println(d2.str)
    m.transformDirection(d1)
    println(d1.str)

  def testPositiveX =
    val d = Vector3f()
    val m = Matrix4x3f()
    // m.rotate(45f.toRadians)
    // println(m.str)
    m.positiveX(d)
    println(d.str)
    m.transformDirection(d)
    println(d.str)
