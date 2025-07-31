package test.base.joml

import org.joml.Vector3f
import yage.base.joml.VectorExt

object FrustumRayBuilderTest extends VectorExt:

  def main(args: Array[String]) =
    val v = Vector3f(1f, 2f, 3f)
    v.r = 17
    println(v)
