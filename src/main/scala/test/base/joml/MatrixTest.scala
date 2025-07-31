package test.base.joml

import org.joml.Matrix4f
import org.joml.Vector4f
import org.lwjgl.BufferUtils
import yage.base.joml.MatrixExt
import yage.base.joml.VectorExt

object MatrixTest extends MatrixExt, VectorExt:

  def main(args: Array[String]) =
    val v = Vector4f()
    val m = Matrix4f()
    val b = BufferUtils.createFloatBuffer(32)
    println(2)
