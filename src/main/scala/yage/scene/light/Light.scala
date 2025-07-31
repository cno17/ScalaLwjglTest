package yage.scene.light

import org.joml.Vector4f
import yage.base.ByteBufferable
import yage.base.joml.VectorExt

import java.nio.ByteBuffer

// color = intensity

class Light[L <: Light[L]] extends ByteBufferable[L], VectorExt:

  var ambientColor = Vector4f(0.4f, 0.4f, 0.4f, 1.0f)
  var diffuseColor = Vector4f(1.0f)
  var specularColor = Vector4f(1.0f)

  var ambientColorOffset = 0
  var diffuseColorOffset = 0
  var specularColorOffset = 0

  override def load(buf: ByteBuffer) =
    ambientColor << buf
    diffuseColor << buf
    specularColor << buf
    this.asInstanceOf[L]

  override def store(buf: ByteBuffer) =
    ambientColor >> buf
    diffuseColor >> buf
    specularColor >> buf
    buf



