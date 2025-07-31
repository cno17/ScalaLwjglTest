package yage.scene.light

import org.joml.Vector4f

import java.nio.ByteBuffer

object StarLight:
  
  val byteCount = 48

class StarLight extends Light[StarLight]:
  
  var dirW = Vector4f() 
  var dirV = Vector4f() 

  var dirWOffset = 0
  var dirVOffset = 0

  override def load(buf: ByteBuffer) = this
  override def store(buf: ByteBuffer) = buf
  
  