package yage.scene.light

import org.joml.Vector4f

import java.nio.ByteBuffer

object SpotLight:
  
  val byteCount = 88

/**
 * all directions should be normalized
 * maxAng = max angle
 * angAtt = angular attenuation
 * todo: extend PositionalLight and DirectionalLight
 */

class SpotLight extends Light[SpotLight]:
  
  var posW = Vector4f()
  var dirW = Vector4f(0, 0, -1, 0)
  var posV = Vector4f()
  var dirV = Vector4f(0, 0, -1, 0)
  
  var maxAng = 0.1f
  var angAtt = 256f

  var posWOffset = 0 // needed?
  var dirWOffset = 0
  var posVOffset = 0
  var dirVOffset = 0
  var maxAngOffset = 0
  var angAttOffset = 0

  override def load(buf: ByteBuffer) =
    super.load(buf)
    posV << buf
    dirV << buf
    maxAng = buf.getFloat
    angAtt = buf.getFloat
    this
  
  override def store(buf: ByteBuffer) = 
    super.store(buf)
    posV >> buf
    dirV >> buf
    buf.putFloat(maxAng)
    buf.putFloat(angAtt)
    buf
  
  