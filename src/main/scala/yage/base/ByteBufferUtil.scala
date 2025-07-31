package yage.base

import org.lwjgl.BufferUtils
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import yage.base.joml.VectorExt

trait ByteBufferUtil extends VectorExt:

  def toByteBuffer(as: Array[Byte]) =
    val b = BufferUtils.createByteBuffer(1 * as.size)
    for a <- as do b.put(a)
    b.rewind()

  def toByteBuffer(as: Array[Short]) =
    val b = BufferUtils.createByteBuffer(2 * as.size)
    for a <- as do b.putShort(a)
    b.rewind()

  def toByteBuffer(as: Array[Int]) =
    val b = BufferUtils.createByteBuffer(4 * as.size)
    for a <- as do b.putInt(a)
    b.rewind()

  def toByteBuffer(as: Array[Long]) =
    val b = BufferUtils.createByteBuffer(8 * as.size)
    for a <- as do b.putLong(a)
    b.rewind()

  def toByteBuffer(as: Array[Float]) =
    val b = BufferUtils.createByteBuffer(4 * as.size)
    for a <- as do b.putFloat(a)
    b.rewind()

  def toByteBuffer(as: Array[Double]) =
    val b = BufferUtils.createByteBuffer(8 * as.size)
    for a <- as do b.putDouble(a)
    b.rewind()

  def toByteBuffer(vs: Array[Vector2f]) =
    val b = BufferUtils.createByteBuffer(8 * vs.size)
    for v <- vs do v.get(b)
    b.rewind()

  def toByteBuffer(vs: Array[Vector3f]) =
    val b = BufferUtils.createByteBuffer(12 * vs.size)
    for v <- vs do v.get(b)
    b.rewind()

  def toByteBuffer(vs: Array[Vector4f]) =
    val b = BufferUtils.createByteBuffer(16 * vs.size)
    for v <- vs do v.get(b)
    b.rewind()

