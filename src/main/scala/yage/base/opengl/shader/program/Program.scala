package yage.base.opengl.shader.program

import org.lwjgl.opengl.GL11C.GL_TRUE
import org.lwjgl.opengl.GL20C.GL_LINK_STATUS
import org.lwjgl.opengl.GL20C.glAttachShader
import org.lwjgl.opengl.GL20C.glCreateProgram
import org.lwjgl.opengl.GL20C.glDeleteProgram
import org.lwjgl.opengl.GL20C.glGetProgramInfoLog
import org.lwjgl.opengl.GL20C.glGetProgrami
import org.lwjgl.opengl.GL20C.glLinkProgram
import org.lwjgl.opengl.GL20C.glUseProgram
import yage.base.FileExt
import yage.base.opengl.Object
import yage.base.opengl.shader.Shader

import java.io.File

object Program extends FileExt:

  def apply(fs: File*) =
    val p = new Program()
    for f <- fs do
      p.attach(Shader(f))
    if !p.link() then
      throw Exception(p.infoLog)
    p

class Program extends Object, ProgramInfo, UniformSetter, UniformGetter:

  override def create() = glCreateProgram()

  def attach(s: Shader) = glAttachShader(id, s.id)

  def link() =
    glLinkProgram(id)
    glGetProgrami(id, GL_LINK_STATUS) == GL_TRUE

  def infoLog = glGetProgramInfoLog(id)

  def bind() = glUseProgram(id)

  def unbind() = glUseProgram(0)

  override def destroy() = glDeleteProgram(id)



