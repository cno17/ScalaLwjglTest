package yage.base.joml

import org.joml.Matrix2f
import org.joml.Matrix3f
import org.joml.Matrix3x2f
import org.joml.Matrix4f
import org.joml.Matrix4x3f

import java.nio.ByteBuffer
import java.nio.FloatBuffer

trait Matrix3x2Ext:

  extension (m: Matrix3x2f)
    // def apply(r: Int, c: Int) = m.getRowColumn(r, c)
    def str =
      val s0 = f"${m.m00}% .2f, ${m.m10}% .2f, ${m.m20}% .2f"
      val s1 = f"${m.m01}% .2f, ${m.m11}% .2f, ${m.m21}% .2f"
      s0 + "\n" + s1 + "\n"

    def toIdentity() = 0

    def toScale() = 0
    def toRotation() = 0
    def toTranslation() = 0

    def preScale(sx: Float, sy: Float) = 0
    def preRotate(a: Float) = 0
    def preTranslate(tx: Float, ty: Float) = 0