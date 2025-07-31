package yage.base.opengl.context

import org.joml.Vector4f
import org.lwjgl.opengl.GL11C.glClear
import org.lwjgl.opengl.GL11C.glClearColor
import org.lwjgl.opengl.GL11C.glCullFace
import org.lwjgl.opengl.GL11C.glDisable
import org.lwjgl.opengl.GL11C.glEnable
import org.lwjgl.opengl.GL11C.glPolygonMode
import org.lwjgl.opengl.GL11C.glViewport
import org.lwjgl.opengl.GL20C.glUseProgram
import org.lwjgl.opengl.GL30C.glBindVertexArray
import yage.base.opengl.context.BufferFlag.ColorBuffer
import yage.base.opengl.context.BufferFlag.DepthBuffer
import yage.base.opengl.primitive.VertexArray
import yage.base.opengl.shader.program.Program

class GlContext:

  val clearFlags = BufferFlags(ColorBuffer, DepthBuffer)
  
  // val clearColor = Vector4f(0f, 0f, 0f, 1f)

  def bind(p: Program) = glUseProgram(p.id)
  def bind(a: VertexArray) = glBindVertexArray(a.id)
  def clear() = glClear(clearFlags.value)
  def cullFace(f: Face) = glCullFace(f.id)
  def enable(c: Capability) = glEnable(c.id)
  def disable(c: Capability) = glDisable(c.id)
  def setClearColor(r: Float, g: Float, b: Float) = glClearColor(r, g, b, 1f)
  def setClearColor(r: Float, g: Float, b: Float, a: Float) = glClearColor(r, g, b, a)
  def setPolygonMode(f: Face, m: PolygonMode) = glPolygonMode(f.id, m.id)
  def setViewport(x: Int, y: Int, w: Int, h: Int) = glViewport(x, y, w, h)
