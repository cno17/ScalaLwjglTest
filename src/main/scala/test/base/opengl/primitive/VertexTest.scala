package test.base.opengl.primitive

import org.joml.Vector4f
import org.lwjgl.BufferUtils
import yage.base.opengl.primitive.Vertex

import java.nio.ByteBuffer

object VertexTest:

  class TVertex(val pos: Vector4f, val col: Vector4f) extends Vertex[TVertex]:

    override def load(buf: ByteBuffer) =
      pos.set(buf)
      col.set(buf)
      this

    override def store(buf: ByteBuffer) =
      pos.store(buf)
      col.store(buf)
      buf

    override def toString() = s"($pos, $col)"


  def main(args: Array[String]) =
    val v1 = TVertex(Vector4f(1f, 2f, 3f, 4f), Vector4f(0.1f, 0.2f, 0.3f, 0.4f))
    val v2 = TVertex(Vector4f(5f, 6f, 7f, 8f), Vector4f(0.5f, 0.6f, 0.7f, 0.8f))
    val buf = BufferUtils.createByteBuffer(512)
    println(s"${v1.pos}, ${v1.col}")
    v1 >> (buf, 112)
    v2 >> (buf, 128)
    v1 << (buf, 128)
    println(s"${v1.pos}, ${v1.col}")
