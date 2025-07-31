package yage.base.opengl.shader.program.resource

import org.lwjgl.opengl.GL43C.GL_BUFFER_VARIABLE
import org.lwjgl.opengl.GL43C.GL_SHADER_STORAGE_BLOCK
import org.lwjgl.opengl.GL43C.GL_UNIFORM
import org.lwjgl.opengl.GL43C.GL_UNIFORM_BLOCK
import yage.base.opengl.shader.program.resource.Block.Type


object Block:

  enum Type(val blockInterface: Int, val memberInterface: Int):

    case Uniform extends Type(GL_UNIFORM_BLOCK, GL_UNIFORM)
    case Storage extends Type(GL_SHADER_STORAGE_BLOCK, GL_BUFFER_VARIABLE)
    // case Counter extends Type(GL_ATOMIC_COUNTER_BUFFER_INDEX)

/**
 * Either a uniform block or a storage block or a counter block.
 *
 * bufferBinding: the index of the buffer binding point associated with this block
 * bufferSize: the minimum size of the associated buffer in bytes
 */

class Block extends Resource:

  var typ: Type = null
  var bufferBinding = 0
  var bufferSize = 0
  var members: Array[Member] = null
  
  override def toString =
    val sb = StringBuilder()
    sb.append(s"Block(index = $index, name = $name) {\n")
    sb.append(s"  type: $typ\n")
    sb.append(s"  bufferBinding: $bufferBinding\n")
    sb.append(s"  bufferSize: $bufferSize\n")
    sb.append(s"  members {\n")
    for m <- members do 
      sb.append(s"    $m\n")
    sb.append(s"  }\n")  
    sb.append(s"}\n")  
    sb.toString()


  /*	
  override def toString: String = {
  val buf = new StringBuffer
  buf.append(name + " (" + index + ")\n")
  buf.append("bufferBinding = " + bufferBinding + "\n")
  buf.append("bufferSize = " + bufferSize + "\n")
  buf.append("members: ")
  var i = 0
  while ( {
  i < members.size
  }) {
  val u = members.get(i)
  buf.append(u.name + ": " + u.`type`)
  if (i < members.size - 1) buf.append(", ")
  else buf.append("\n")

  i += 1
  }
  buf.toString
  }
  }
  */