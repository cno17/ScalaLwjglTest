package yage.base

import java.nio.ByteBuffer

trait ByteBufferable[T]: // <: ByteBufferable[T]]:

  def load(buf: ByteBuffer): T
  def load(buf: ByteBuffer, off: Int): T = {buf.position(off); load(buf)}

  def store(buf: ByteBuffer): ByteBuffer
  def store(buf: ByteBuffer, off: Int): ByteBuffer = {buf.position(off); store(buf)}

  def <<(buf: ByteBuffer) = load(buf)
  def <<(buf: ByteBuffer, off: Int) = load(buf, off)

  def >>(buf: ByteBuffer) = store(buf)
  def >>(buf: ByteBuffer, off: Int) = store(buf, off)

//  extension (buf: ByteBuffer)
//    def put(t: T) = t.store(buf)
//    def put(off: Int, t: T) = t.store(buf, off)
