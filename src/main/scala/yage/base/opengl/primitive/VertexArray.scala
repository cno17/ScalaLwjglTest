package yage.base.opengl.primitive

import org.lwjgl.opengl.GL30C.glBindVertexArray
import org.lwjgl.opengl.GL30C.glDeleteVertexArrays
import org.lwjgl.opengl.GL45C.glCreateVertexArrays
import org.lwjgl.opengl.GL45C.glDisableVertexArrayAttrib
import org.lwjgl.opengl.GL45C.glEnableVertexArrayAttrib
import org.lwjgl.opengl.GL45C.glVertexArrayAttribBinding
import org.lwjgl.opengl.GL45C.glVertexArrayAttribFormat
import org.lwjgl.opengl.GL45C.glVertexArrayBindingDivisor
import org.lwjgl.opengl.GL45C.glVertexArrayElementBuffer
import org.lwjgl.opengl.GL45C.glVertexArrayVertexBuffer
import yage.base.opengl.Object
import yage.base.opengl.primitive.Attribute.Format
import yage.base.opengl.resource.buffer.Buffer

/**
  *
  * ToDo:
  *   - glVertexArrayAttribIFormat
  *   - glVertexArrayAttribLFormat
  */

class VertexArray extends Object:

  override def create() =
    glCreateVertexArrays()

  def bind() =
    glBindVertexArray(id)

  def unbind() =
    glBindVertexArray(0)
  
  // stride = vertex stride (number of bytes occupied by all atributes of a vertex)
  
  def bindVertexBuffer(binding: Int, buffer: Buffer, offset: Long, stride: Int) =
    glVertexArrayVertexBuffer(id, binding, buffer.id, offset, stride)

  def bindIndexBuffer(buffer: Buffer) =
    glVertexArrayElementBuffer(id, buffer.id)

  def enableAttribute(location: Int) =
    glEnableVertexArrayAttrib(id, location)

  def disableAttribute(location: Int) =
    glDisableVertexArrayAttrib(id, location)

  // normalized is ignored for floating point data but can be useful for int data
  // offset?
  
  def setAttributeFormat(location: Int, format: Format, offset: Int, normalized: Boolean = false) =
    val cc = format.componentCount
    val ct = format.componentType
    glVertexArrayAttribFormat(id, location, cc, ct, normalized, offset)

  def setAttributeBinding(location: Int, binding: Int) =
    glVertexArrayAttribBinding(id, location, binding)

  def setBindingDivisor(binding: Int, divisor: Int) =
    glVertexArrayBindingDivisor(id, binding, divisor)

  override def destroy() =
    glDeleteVertexArrays(id)
