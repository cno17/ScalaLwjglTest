package yage.base.opengl.context

import org.lwjgl.opengl.GL11C.GL_BACK
import org.lwjgl.opengl.GL11C.GL_FRONT
import org.lwjgl.opengl.GL11C.GL_FRONT_AND_BACK

enum Face(val id: Int):

  case Front extends Face(GL_FRONT)
  case Back extends Face(GL_BACK)
  case FrontAndBack extends Face(GL_FRONT_AND_BACK)
