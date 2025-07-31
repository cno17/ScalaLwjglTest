package test.base.joml

import org.joml.Vector4f
import org.lwjgl.BufferUtils

object VectorStoreTest:

  def main(args: Array[String]) =
    val v1 = Vector4f(1f, 2f, 3f, 4f)
    val v2 = Vector4f(5f, 6f, 7f, 8f)
    val buf = BufferUtils.createByteBuffer(512)
    println(v1)
    v2.get(64, buf)
    v1.set(64, buf)
    println(v1)
