package yage.base.opengl.resource

import org.lwjgl.opengl.GL15C.GL_READ_ONLY
import org.lwjgl.opengl.GL15C.GL_READ_WRITE
import org.lwjgl.opengl.GL15C.GL_WRITE_ONLY

import yage.base.opengl.Object

import java.nio.ByteBuffer

object Resource:

  // AccessMode?

  enum Access(val id: Int):

    case Read extends Access(GL_READ_ONLY)
    case Write extends Access(GL_WRITE_ONLY)
    case ReadWrite extends Access(GL_READ_WRITE)


trait Resource extends Object

// TODO
trait Resource2[R <: Range] extends Object:
  
  def load(data: ByteBuffer, range: R) = 0
