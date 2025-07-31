package yage.scene.material

import org.joml.Vector4f

import java.nio.ByteBuffer

object LightMaterial:

  val byteCount = 48
  var program = LightProgram()

// color = reflectivity

class LightMaterial extends Material[LightMaterial]:
  
  type P = LightProgram

  var diffuseColor = Vector4f(1f)
  var specularColor = Vector4f(1f)
  var shininess = 64f

  var diffuseColorOffset = 0
  var specularColorOffset = 0
  var shininessOffset = 0

  override def program = LightMaterial.program

  override def load(buf: ByteBuffer) =
    diffuseColor << buf
    specularColor << buf
    shininess = buf.getFloat()
    buf.getFloat()
    buf.getFloat()
    buf.getFloat()
    this

  // align to 16 byte boundary
  override def store(buf: ByteBuffer) =
    diffuseColor >> buf
    specularColor >> buf
    buf.putFloat(shininess)
    buf.putFloat(0f)
    buf.putFloat(0f)
    buf.putFloat(0f)
    buf

  