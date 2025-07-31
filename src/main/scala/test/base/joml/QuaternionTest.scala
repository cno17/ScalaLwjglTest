package test.base.joml

import org.joml.Matrix3f
import org.joml.Quaternionf
import org.joml.Vector3f
import org.joml.Vector4f

object QuaternionTest:

  def main(args: Array[String]) =
    transformTest()

  def transformTest() =
    val q = Quaternionf().rotateZ(45f.toRadians)
    println(q.transform(Vector4f(1f, 0f, 0f, 0f)))
    println(q.transform(Vector4f(1f, 0f, 0f, 1f)))

  def eulerTest() =
    val m = Matrix3f()
    m.rotateX(-11f.toRadians)
    m.rotateY(45f.toRadians)
    m.rotateZ(117f.toRadians)
    val a = Quaternionf().setFromNormalized(m).getEulerAnglesXYZ(Vector3f())
    println(a.x.toDegrees)
    println(a.y.toDegrees)
    println(a.z.toDegrees)
