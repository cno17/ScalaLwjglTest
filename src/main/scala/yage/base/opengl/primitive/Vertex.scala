package yage.base.opengl.primitive

import yage.base.joml.VectorExt

import java.nio.ByteBuffer

trait Vertex[V <: Vertex[V]] extends VectorExt:

  def load(buf: ByteBuffer): V
  def load(buf: ByteBuffer, i: Int): V = {buf.position(i); load(buf)}
  def store(buf: ByteBuffer): ByteBuffer
  def store(buf: ByteBuffer, i: Int): ByteBuffer = {buf.position(i); store(buf)}

  def <<(buf: ByteBuffer) = load(buf)
  def <<(buf: ByteBuffer, i: Int) = load(buf, i)
  def >>(buf: ByteBuffer) = store(buf)
  def >>(buf: ByteBuffer, i: Int) = store(buf, i)
