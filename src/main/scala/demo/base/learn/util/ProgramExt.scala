package demo.base.learn.util

import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import org.lwjgl.opengl.GL20C.glGetUniformLocation
import org.lwjgl.opengl.GL20C.glUniformMatrix4fv
import org.lwjgl.opengl.GL41C.*
import yage.base.StackUser
import yage.base.opengl.shader.program.Program

trait ProgramExt extends StackUser:

  // val buf16
  
  extension (p: Program)

    def setUniform(name: String, v: Vector3f) =
      val loc = glGetUniformLocation(p.id, name)
      // if loc == -1 then throw Exception(s"Unknown Uniform $name")
      if loc == -1 then println(s"Unknown Uniform $name")
      glProgramUniform3f(p.id, loc, v.x, v.y, v.z)

    def setUniform(name: String, v: Vector4f) =
      val loc = glGetUniformLocation(p.id, name)
      // if loc == -1 then throw Exception(s"Unknown Uniform $name")
      if loc == -1 then println(s"Unknown Uniform $name")
      glProgramUniform4f(p.id, loc, v.x, v.y, v.z, v.w)
      
    def setUniform(name: String, matrix: Matrix4f) =
      useStack(s =>
        val loc = glGetUniformLocation(p.id, name)
        // if loc == -1 then throw Exception(s"Unknown Uniform $name")
        if loc == -1 then println(s"Unknown Uniform $name")
        // glProgramUniform
        glUniformMatrix4fv(loc, false, matrix.get(s.mallocFloat(16)))
      )