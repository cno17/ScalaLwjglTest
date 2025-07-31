package yage.base.opengl.context

import org.lwjgl.opengl.GL11C.GL_FILL
import org.lwjgl.opengl.GL11C.GL_LINE
import org.lwjgl.opengl.GL11C.GL_POINT

enum PolygonMode(val id: Int):

  case Point extends PolygonMode(GL_POINT)
  case Line extends PolygonMode(GL_LINE)
  case Fill extends PolygonMode(GL_FILL)
