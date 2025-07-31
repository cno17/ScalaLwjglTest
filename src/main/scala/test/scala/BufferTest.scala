package test.scala

import org.joml.Vector2f
import org.lwjgl.BufferUtils

object BufferTest:

  // class Distance[V <: Vector[V], A <: V | Shape[V], B <: Shape[V]]

  // class PointSphereDistance[V <: Vector[V]] extends Distance[V, V, Sphere[V]]

  def main(args: Array[String]) =
//    val bb = BufferUtils.createByteBuffer(256)
//    println(bb.position())
//    bb.putFloat(2f)
//    println(bb.position())
    val fb = BufferUtils.createFloatBuffer(16)
    fb.put(2f)
    fb.put(3f)
    fb.position(0)
    val v = Vector2f()
    v.set(fb)
    println(v)
    println(fb.position())
