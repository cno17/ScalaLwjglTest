package yage.scene.material

import org.joml.Vector4f
import yage.base.opengl.shader.program.Program

import java.nio.ByteBuffer

object ColorMaterial:
  
  val byteCount = 16
  val program = ColorProgram()

class ColorMaterial(r: Float, g: Float, b: Float, a: Float) extends Material[ColorMaterial]:

  type P = ColorProgram
  
  def this() = this(1f, 1f, 1f, 1f)
  def this(r: Float, g: Float, b: Float) = this(r, r, b, 1f)

  var color = Vector4f(r, g, b, a)
  var colorOffset = 0

  override def program = ColorMaterial.program 
  
  override def load(buf: ByteBuffer) =
    color << buf
    this
    
  override def store(buf: ByteBuffer) = 
    color >> buf
    buf
  
  
