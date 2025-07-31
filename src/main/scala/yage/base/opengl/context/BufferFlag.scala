package yage.base.opengl.context

import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
import org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT
import org.lwjgl.opengl.GL11C.GL_STENCIL_BUFFER_BIT
import yage.base.Flag
import yage.base.Flags

enum BufferFlag(val id: Int) extends Flag:

  case ColorBuffer extends BufferFlag(GL_COLOR_BUFFER_BIT)
  case DepthBuffer extends BufferFlag(GL_DEPTH_BUFFER_BIT)
  case StencilBuffer extends BufferFlag(GL_STENCIL_BUFFER_BIT)

class BufferFlags(fs: BufferFlag*) extends Flags[BufferFlag](fs: _*)