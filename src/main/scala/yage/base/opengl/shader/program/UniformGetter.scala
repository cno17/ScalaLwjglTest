package yage.base.opengl.shader.program

import org.lwjgl.opengl.GL20C.glGetUniformf
import org.lwjgl.opengl.GL20C.glGetUniformi
import org.lwjgl.opengl.GL20C.glGetUniformLocation

// needed?
trait UniformGetter:

  this: Program =>

  def getUniformf(name: String) = glGetUniformf(id, glGetUniformLocation(id, name))
  def getUniformi(name: String) = glGetUniformi(id, glGetUniformLocation(id, name))
