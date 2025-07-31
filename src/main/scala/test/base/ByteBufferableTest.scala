package test.base

import org.joml.Vector3f
import yage.base.ByteBufferable

import java.nio.ByteBuffer

object ByteBufferableTest:

  class Light(val pos: Vector3f) extends ByteBufferable[Light]:
    override def load(buf: ByteBuffer) = this
    override def store(buf: ByteBuffer) = buf


  def main(args: Array[String]) =
    println(2)
