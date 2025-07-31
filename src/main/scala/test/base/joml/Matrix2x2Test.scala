package test.base.joml

import org.joml.Matrix2f
import org.joml.Vector2f
import yage.base.joml.MatrixExt
import yage.base.joml.VectorExt

object Matrix2x2Test extends MatrixExt, VectorExt:

  def main(args: Array[String]) =
    val p = Vector2f(1, 0)
    val m = Matrix2f().rotation(90f.toRadians)
    m.transform(p)
    println(p.str)
