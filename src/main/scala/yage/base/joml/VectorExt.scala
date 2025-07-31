package yage.base.joml

import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import yage.base.FMath

import java.nio.ByteBuffer
import java.nio.FloatBuffer

trait VectorExt:

  extension (v: Vector2f)
    def *(s: Float) = v.mul(s, new Vector2f())
    def /(s: Float) = v.div(s, new Vector2f())    
    def +(w: Vector2f) = v.add(w, new Vector2f())
    def -(w: Vector2f) = v.sub(w, new Vector2f())
    def *=(s: Float) = v.mul(s)
    def /=(s: Float) = v.div(s)
    def +=(w: Vector2f) = v.add(w)
    def -=(w: Vector2f) = v.sub(w)
    //
    def addScaled(w: Vector2f, s: Float) = v.fma(+s, w)
    def subScaled(w: Vector2f, s: Float) = v.fma(-s, w)
    //
    def <<(buf: ByteBuffer) = v.load(buf)
    def <<(buf: FloatBuffer) = v.load(buf)
    def >>(buf: ByteBuffer) = v.store(buf)
    def >>(buf: FloatBuffer) = v.store(buf)
    //
    def load(buf: ByteBuffer) = {v.set(buf); buf.position(buf.position() + 8); v}
    def load(buf: FloatBuffer) = {v.set(buf); buf.position(buf.position() + 2); v}
    def store(buf: ByteBuffer) = {v.get(buf); buf.position(buf.position() + 8)}
    def store(buf: FloatBuffer) = {v.get(buf); buf.position(buf.position() + 2)}
    // 
    def det(w: Vector2f) = v.x * w.y - w.x * v.y
    def apply(i: Int) = v.get(i)
    def update(i: Int, s: Float) = v.setComponent(i, s)
    //
    def str = f"${v.x}% .2f, ${v.y}% .2f"

    // todo: probably not uniform
    def toRandom =
      v.x = FMath.rndF(-1f, 1f)
      v.y = FMath.rndF(-1f, 1f)
      v.normalize()

  extension (v: Vector3f)
    def *(s: Float) = v.mul(s, new Vector3f())
    def /(s: Float) = v.div(s, new Vector3f())
    def +(w: Vector3f) = v.add(w, new Vector3f())
    def -(w: Vector3f) = v.sub(w, new Vector3f())
    def *=(s: Float) = v.mul(s)
    def /=(s: Float) = v.div(s)
    def +=(w: Vector3f) = v.add(w)
    def -=(w: Vector3f) = v.sub(w)
    //
    def addScaled(w: Vector3f, s: Float) = v.fma(+s, w)
    def subScaled(w: Vector3f, s: Float) = v.fma(-s, w)
    //
    def << (buf: ByteBuffer) = v.load(buf)
    def << (buf: FloatBuffer) = v.load(buf)
    def >> (buf: ByteBuffer) = v.store(buf)
    def >> (buf: FloatBuffer) = v.store(buf)
    
    // todo: align to 16 byte boundary for byte buffers?
    def load(buf: ByteBuffer) = {v.set(buf); buf.position(buf.position() + 16); v}
    def load(buf: FloatBuffer) = {v.set(buf); buf.position(buf.position() + 3); v}
    def store(buf: ByteBuffer) = {v.get(buf); buf.position(buf.position() + 12)}
    def store(buf: FloatBuffer) = {v.get(buf); buf.position(buf.position() + 3)}
    //
    def apply(i: Int) = v.get(i)
    def update(i: Int, s: Float) = v.setComponent(i, s)
    //
    def str = f"(${v.x}% .2f,${v.y}% .2f,${v.z}% .2f)"
    //
    def r = v.x
    def r_=(s: Float) = v.x = s
    def g = v.y
    def g_=(s: Float) = v.y = s
    def b = v.z
    def b_=(s: Float) = v.z = s

  extension (v: Vector4f)
    def *(s: Float) = v.mul(s, new Vector4f())
    def /(s: Float) = v.div(s, new Vector4f())
    def +(w: Vector4f) = v.add(w, new Vector4f())
    def -(w: Vector4f) = v.sub(w, new Vector4f())
    def *=(s: Float) = v.mul(s)
    def /=(s: Float) = v.div(s)
    def +=(w: Vector4f) = v.add(w)
    def -=(w: Vector4f) = v.sub(w)
    // 
    def addScaled(w: Vector4f, s: Float) = v.fma(+s, w)
    def subScaled(w: Vector4f, s: Float) = v.fma(-s, w)
    //
    def <<(buf: ByteBuffer) = v.load(buf)
    def <<(buf: FloatBuffer) = v.load(buf)
    def >>(buf: ByteBuffer) = v.store(buf)
    def >>(buf: FloatBuffer) = v.store(buf)
    //
    def load(buf: ByteBuffer) = {v.set(buf); buf.position(buf.position() + 16); v}
    def load(buf: FloatBuffer) = {v.set(buf); buf.position(buf.position() + 4); v}
    def store(buf: ByteBuffer) = {v.get(buf); buf.position(buf.position() + 16)}
    def store(buf: FloatBuffer) = {v.get(buf); buf.position(buf.position() + 4)}
    //
    def apply(i: Int) = v.get(i)
    def update(i: Int, s: Float) = v.setComponent(i, s)
    //
    def str = f"${v.x}% .2f, ${v.y}% .2f, ${v.z}% .2f, ${v.w}% .2f\n"
    //
    def r = v.x
    def r_=(s: Float) = v.x = s
    def g = v.y
    def g_=(s: Float) = v.y = s
    def b = v.z
    def b_=(s: Float) = v.z = s
    def a = v.w
    def a_=(s: Float) = v.w = s
