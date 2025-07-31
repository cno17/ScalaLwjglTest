package yage.base.nanovg.state

import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG.nvgLerpRGBA
import org.lwjgl.nanovg.NanoVG.nvgRGBAf

object vgColor:

  val Black = vgColor(0.0f, 0.0f, 0.0f)
  val Gray = vgColor(0.5f, 0.5f, 0.5f)
  val White = vgColor(1.0f, 1.0f, 1.0f)
  val Red = vgColor(0.8f, 0.2f, 0.2f)
  val Green = vgColor(0.2f, 0.8f, 0.2f)
  val Blue = vgColor(0.2f, 0.2f, 0.8f)
  val Yellow = vgColor(0.8f, 0.8f, 0.2f)
  // ...
  val Cyan = vgColor(0.2f, 0.8f, 0.8f)

class vgColor(rr: Float, gg: Float, bb: Float, aa: Float):

  val handle = NVGColor.create()

  set(rr, gg, bb, aa)

  def this() = this(0f, 0f, 0f, 1f)
  def this(c: vgColor) = this(c.r, c.g, c.b, c.a)
  def this(r: Float, g: Float, b: Float) = this(r, g, b, 1f)

  def r = handle.r()
  def g = handle.g()
  def b = handle.b()
  def a = handle.a()

  def r_=(v: Float) = {handle.r(v); this}
  def g_=(v: Float) = {handle.g(v); this}
  def b_=(v: Float) = {handle.b(v); this}
  def a_=(v: Float) = {handle.a(v); this}

  def set(c: vgColor): Unit = set(c.r, c.g, c.b, c.a)
  def set(r: Float, g: Float, b: Float): Unit = set(r, g, b, 1f)
  def set(r: Float, g: Float, b: Float, a: Float) = {nvgRGBAf(r, g, b, a, handle); this}

  def interpolate(c1: vgColor, c2: vgColor, a: Float) = {nvgLerpRGBA(c1.handle, c2.handle, a, handle); this}

  override def toString() = s"($r, $g, $b, $a)"
