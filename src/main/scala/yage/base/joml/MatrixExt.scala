package yage.base.joml

import org.joml.Matrix2f
import org.joml.Matrix3f
import org.joml.Matrix3x2f
import org.joml.Matrix4f
import org.joml.Matrix4x3f

import java.nio.ByteBuffer
import java.nio.FloatBuffer

trait MatrixExt:

  extension (m: Matrix2f)
    def apply(r: Int, c: Int) = 0
    //
    def load(buf: ByteBuffer) = {m.set(buf); buf.position(buf.position() + 16); m}
    def load(buf: FloatBuffer) = {m.set(buf); buf.position(buf.position() + 4); m}
    def store(buf: ByteBuffer) = {m.get(buf); buf.position(buf.position() + 16)}
    def store(buf: FloatBuffer) = {m.get(buf); buf.position(buf.position() + 4)}

  extension (m: Matrix3x2f)
    // def apply(r: Int, c: Int) = m.getRowColumn(r, c)
    //
    // def load(buf: ByteBuffer) = {m.set(buf); buf.position(buf.position() + 36)}
    // def load(buf: FloatBuffer) = {m.set(buf); buf.position(buf.position() + 9)}
    // def store(buf: ByteBuffer) = {m.get(buf); buf.position(buf.position() + 36)}
    // def store(buf: FloatBuffer) = {m.get(buf); buf.position(buf.position() + 9)}
    def str =
      val s0 = f"${m.m00}% .2f, ${m.m10}% .2f, ${m.m20}% .2f"
      val s1 = f"${m.m01}% .2f, ${m.m11}% .2f, ${m.m21}% .2f"
      s0 + "\n" + s1 + "\n"

  extension (m: Matrix3f)
    def apply(r: Int, c: Int) = m.getRowColumn(r, c)
    //
    def load(buf: ByteBuffer) = {m.set(buf); buf.position(buf.position() + 36); m}
    def load(buf: FloatBuffer) = {m.set(buf); buf.position(buf.position() + 9); m}
    def store(buf: ByteBuffer) = {m.get(buf); buf.position(buf.position() + 36)}
    def store(buf: FloatBuffer) = {m.get(buf); buf.position(buf.position() + 9)}

  extension (m: Matrix4x3f)
    // def apply(r: Int, c: Int) = m.getRowColumn(r, c)
    //
    // def load(buf: ByteBuffer) = {m.set(buf); buf.position(buf.position() + 36)}
    // def load(buf: FloatBuffer) = {m.set(buf); buf.position(buf.position() + 9)}
    // def store(buf: ByteBuffer) = {m.get(buf); buf.position(buf.position() + 36)}
    // def store(buf: FloatBuffer) = {m.get(buf); buf.position(buf.position() + 9)}

    def get(f: Frame3) =
      m.getColumn(0, f.dirX)
      m.getColumn(1, f.dirY)
      m.getColumn(2, f.dirZ)
      m.getColumn(3, f.orig)
      f

    def set(f: Frame3) =
      m.setColumn(0, f.dirX)
      m.setColumn(1, f.dirY)
      m.setColumn(2, f.dirZ)
      m.setColumn(3, f.orig)
      this

    def str =
      val s0 = f"${m.m00}% .2f, ${m.m10}% .2f, ${m.m20}% .2f, ${m.m30}% .2f\n"
      val s1 = f"${m.m01}% .2f, ${m.m11}% .2f, ${m.m21}% .2f, ${m.m31}% .2f\n"
      val s2 = f"${m.m02}% .2f, ${m.m12}% .2f, ${m.m22}% .2f, ${m.m32}% .2f\n"
      s0 + s1 + s2 + "\n"

  extension (m: Matrix4f)
    def apply(r: Int, c: Int) = m.getRowColumn(r, c)
    //
    def load(buf: ByteBuffer) = {m.set(buf); buf.position(buf.position() + 64); m}
    def load(buf: FloatBuffer) = {m.set(buf); buf.position(buf.position() + 16); m}
    def store(buf: ByteBuffer) = {m.get(buf); buf.position(buf.position() + 64)}
    def store(buf: FloatBuffer) = {m.get(buf); buf.position(buf.position() + 16)}

    def str =
      val s0 = f"${m.m00}% .24f, ${m.m10}% .2f, ${m.m20}% .2f, ${m.m30}% .2f\n"
      val s1 = f"${m.m01}% .2f, ${m.m11}% .2f, ${m.m21}% .2f, ${m.m31}% .2f\n"
      val s2 = f"${m.m02}% .2f, ${m.m12}% .2f, ${m.m22}% .2f, ${m.m32}% .2f\n"
      val s3 = f"${m.m03}% .2f, ${m.m13}% .2f, ${m.m23}% .2f, ${m.m33}% .2f\n"
      s0 + s1 + s2 + s3 + "\n"
