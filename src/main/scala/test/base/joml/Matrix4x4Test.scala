package test.base.joml

import org.joml.Matrix4f
import org.joml.Vector3f
import yage.base.joml.MatrixExt
import yage.base.joml.VectorExt

object Matrix4x4Test extends MatrixExt, VectorExt:

  def main(args: Array[String]) = testLookAlong

  def testLookAt =
    val eye = Vector3f(3f, 5f, 7f)
    val center = Vector3f(0f, 0f, 0f)
    val up = Vector3f(0f, 1f, 0f)
    val m = Matrix4f().translate(eye).lookAt(eye, center, up)
    val d1 = Vector3f(center).sub(eye).normalize()
    val d2 = m.getColumn(2, Vector3f()).mul(-1).normalize()
    println(m.str)
    println(d1.str)
    println(d2.str)


  def testLookAlong =
    val d = Vector3f(2, 3, 5).normalize()
    val u = Vector3f(0, 1, 0).normalize()
    val m = Matrix4f().setLookAlong(d, u)
    println(d.str)
    println(u.str)
    println(m.str)
