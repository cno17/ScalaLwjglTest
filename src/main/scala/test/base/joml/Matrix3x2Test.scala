package test.base.joml

import org.joml.Matrix3x2f
import org.joml.Vector2f
import yage.base.joml.MatrixExt
import yage.base.joml.VectorExt

object Matrix3x2Test extends MatrixExt, VectorExt:

  def main(args: Array[String]) = testLeftRight

  def testLeftRight =
    val p = Vector2f(1, 2)
    val m = Matrix3x2f()
    m.rotation(90f.toRadians)
    m.translate(2f, 3f)
    m.transformPosition(p)
    println(p.str)

  def testRotateTo =
    val d1 = Vector2f(+2, 3).normalize()
    val d2 = Vector2f(-5, 1).normalize()
    val m = Matrix3x2f()
    m.rotateTo(d1, d2)
    println(m.str)
    println(d1.str)
    println(d2.str)
    m.transformDirection(d1)
    println(d1.str)

  def testPositiveX =
    val d = Vector2f()
    val m = Matrix3x2f()
    m.rotate(45f.toRadians)
    println(m.str)
    m.positiveX(d)
    println(d.str)
    m.transformDirection(d)
    println(d.str)
