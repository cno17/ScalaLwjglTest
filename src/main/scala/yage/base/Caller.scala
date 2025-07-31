package yage.base

import java.nio.DoubleBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer

/**
 * Simplify call patterns for some lwjgl functions:
 *
 * glfwGetCursorPos(long window, DoubleBuffer posX, DoubleBuffer posY)
 * glfwGetFramebufferSize(long window, IntBuffer sizeX, IntBuffer sizeY)
 * glfwGetMonitorPhysicalSize(long monitor, IntBuffer sizeX, IntBuffer sizeY)
 *
 * this should be an Image method!
 * nvgImageSize(long ctx, int image, java.nio.IntBuffer w, java.nio.IntBuffer h) // argh!
 */

trait Caller extends StackUser:

  protected def call2i(f: (Long, IntBuffer, IntBuffer) => Unit, handle: Long, i: Int) =
    var res = 0
    useStack(s =>
      val buf = s.mallocInt(1)
      if i == 1 then f(handle, buf, null)
      if i == 2 then f(handle, null, buf)
      res = buf.get(0)
    )
    res

  protected def call4i(f: (Long, IntBuffer, IntBuffer, IntBuffer, IntBuffer) => Unit, handle: Long, i: Int) =
    var res = 0
    useStack(s =>
      val buf = s.mallocInt(1)
      if i == 1 then f(handle, buf, null, null, null)
      if i == 2 then f(handle, null, buf, null, null)
      if i == 3 then f(handle, null, null, buf, null)
      if i == 4 then f(handle, null, null, null, buf)
      res = buf.get(0)
    )
    res

  protected def call2f(f: (Long, FloatBuffer, FloatBuffer) => Unit, handle: Long, i: Int) =
    var res = 0f
    useStack(s =>
      val buf = s.mallocFloat(1)
      if i == 1 then f(handle, buf, null)
      if i == 2 then f(handle, null, buf)
      res = buf.get(0)
    )
    res

  protected def call2d(f: (Long, DoubleBuffer, DoubleBuffer) => Unit, handle: Long, i: Int) =
    var res = 0d
    useStack(s =>
      val buf = s.mallocDouble(1)
      if i == 1 then f(handle, buf, null)
      if i == 2 then f(handle, null, buf)
      res = buf.get(0)
    )
    res.toFloat
