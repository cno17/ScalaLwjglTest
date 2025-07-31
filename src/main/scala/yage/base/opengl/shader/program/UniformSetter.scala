package yage.base.opengl.shader.program

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20C.glGetUniformLocation
import org.lwjgl.opengl.GL41C.*
import org.joml.Matrix2f
import org.joml.Matrix3f
import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import yage.base.joml.MatrixExt

import scala.language.postfixOps

// todo: uint versions!

trait UniformSetter extends MatrixExt:

  this: Program =>

  private val buf04 = BufferUtils.createFloatBuffer(4)
  private val buf09 = BufferUtils.createFloatBuffer(9)
  private val buf16 = BufferUtils.createFloatBuffer(16)

  // BY LOCATION

  def setUniform(loc: Int, x: Int) = glProgramUniform1i(id, loc, x)
  def setUniform(loc: Int, x: Int, y: Int) = glProgramUniform2i(id, loc, x, y)
  def setUniform(loc: Int, x: Int, y: Int, z: Int) = glProgramUniform3i(id, loc, x, y, z)
  def setUniform(loc: Int, x: Int, y: Int, z: Int, w: Int) = glProgramUniform4i(id, loc, x, y, z, w)

  def setUniform(loc: Int, x: Float) = glProgramUniform1f(id, loc, x)
  def setUniform(loc: Int, x: Float, y: Float) = glProgramUniform2f(id, loc, x, y)
  def setUniform(loc: Int, x: Float, y: Float, z: Float) = glProgramUniform3f(id, loc, x, y, z)
  def setUniform(loc: Int, x: Float, y: Float, z: Float, w: Float) = glProgramUniform4f(id, loc, x, y, z, w)

  def setUniform(loc: Int, v: Vector2f) = glProgramUniform2f(id, loc, v.x, v.y)
  def setUniform(loc: Int, v: Vector3f) = glProgramUniform3f(id, loc, v.x, v.y, v.z)
  def setUniform(loc: Int, v: Vector4f) = glProgramUniform4f(id, loc, v.x, v.y, v.z, v.w)

  def setUniform(loc: Int, m: Matrix2f) = glProgramUniformMatrix2fv(id, loc, false, m.get(0, buf04))
  def setUniform(loc: Int, m: Matrix3f) = glProgramUniformMatrix3fv(id, loc, false, m.get(0, buf09))
  def setUniform(loc: Int, m: Matrix4f) = glProgramUniformMatrix4fv(id, loc, false, m.get(0, buf16))

  // BY NAME

  def setUniform(name: String, x: Int) = glProgramUniform1i(id, loc(name), x)
  def setUniform(name: String, x: Int, y: Int) = glProgramUniform2i(id, loc(name), x, y)
  def setUniform(name: String, x: Int, y: Int, z: Int) = glProgramUniform3i(id, loc(name), x, y, z)
  def setUniform(name: String, x: Int, y: Int, z: Int, w: Int) = glProgramUniform4i(id, loc(name), x, y, z, w)

  def setUniform(name: String, x: Float) = glProgramUniform1f(id, loc(name), x)
  def setUniform(name: String, x: Float, y: Float) = glProgramUniform2f(id, loc(name), x, y)
  def setUniform(name: String, x: Float, y: Float, z: Float) = glProgramUniform3f(id, loc(name), x, y, z)
  def setUniform(name: String, x: Float, y: Float, z: Float, w: Float) = glProgramUniform4f(id, loc(name), x, y, z, w)

  def setUniform(name: String, v: Vector2f) = glProgramUniform2f(id, loc(name), v.x, v.y)
  def setUniform(name: String, v: Vector3f) = glProgramUniform3f(id, loc(name), v.x, v.y, v.z)
  def setUniform(name: String, v: Vector4f) = glProgramUniform4f(id, loc(name), v.x, v.y, v.z, v.w)

  def setUniform(name: String, m: Matrix2f) = glProgramUniformMatrix2fv(id, loc(name), false, m.get(0, buf04))
  def setUniform(name: String, m: Matrix3f) = glProgramUniformMatrix3fv(id, loc(name), false, m.get(0, buf09))
  def setUniform(name: String, m: Matrix4f) = glProgramUniformMatrix4fv(id, loc(name), false, m.get(0, buf16))

  private def loc(name: String) =
    val res = glGetUniformLocation(id, name)
    // if res == -1 then throw Exception(s"unknown uniform \"$name\"")
    if res == -1 then println(s"unknown uniform \"$name\"")
    res
