package yage.scene.camera

import org.lwjgl.opengl.GL41C.glDepthRangeIndexed
import org.lwjgl.opengl.GL41C.glViewportIndexedf

class Viewport(val i: Int = 0):

  var x = 0f
  var y = 0f
  var w = 0f
  var h = 0f
  var n = 0f
  var f = 0f

  def set(x: Float, y: Float, w: Float, h: Float, n: Float = 0f, f: Float = 1f) =
    this.x = x
    this.y = y
    this.w = w
    this.h = h
    this.n = n
    this.f = f
    updateGlState()

  def updateGlState() =
    glViewportIndexedf(i, x, y, w, h)
    glDepthRangeIndexed(i, n, f)
  
