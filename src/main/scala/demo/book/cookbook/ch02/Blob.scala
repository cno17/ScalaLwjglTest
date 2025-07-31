package demo.book.cookbook.ch02

import org.joml.Vector4f
import yage.base.ByteBufferable
import yage.base.joml.VectorExt

import java.nio.ByteBuffer

object Blob:
  
  val byteCount = 40

class Blob extends ByteBufferable[Blob], VectorExt {

  var innerColor = Vector4f(0.8f, 0.2f, 0.2f, 1.0f)
  var outerColor = Vector4f(0.2f, 0.2f, 0.8f, 1.0f)
  var innerRadius = 0.5f
  var outerRadius = 0.8f
  
  override def load(buf: ByteBuffer) =
    innerColor << buf
    outerColor << buf
    innerRadius = buf.getFloat
    outerRadius = buf.getFloat
    this
    
  override def store(buf: ByteBuffer) =
    innerColor >> buf
    outerColor >> buf
    buf.putFloat(innerRadius)
    buf.putFloat(outerRadius)
    buf
}
