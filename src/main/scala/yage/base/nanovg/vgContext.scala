package yage.base.nanovg

import org.joml.Matrix3x2f
import org.lwjgl.nanovg.NanoVG.*
import org.lwjgl.nanovg.NanoVGGL3.nvgCreate
import org.lwjgl.opengl.GL11C.GL_COLOR_BUFFER_BIT
import org.lwjgl.opengl.GL11C.glClear
import org.lwjgl.opengl.GL11C.glClearColor
import yage.base.StackUser
import yage.base.nanovg.state.Font
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.state.vgLineStyle
import yage.base.nanovg.state.vgPaint
import yage.base.nanovg.state.vgTextAlign
import yage.base.nanovg.state.vgTrafo
import yage.base.nanovg.state.vgWinding
import yage.base.nanovg.util.Box

class CreateFlags:
  var antialias = false
  var stencilStrokes = false
  var debug = false

// set pen for drawing and brush for filling

object vgContext extends StackUser:

  var id = 0l

  def init() =
    id = nvgCreate(0)

  def clear() =
    glClear(GL_COLOR_BUFFER_BIT)

  def beginFrame(sx: Float, sy: Float) =
    nvgBeginFrame(id, sx, sy, 1.0f)

  def endFrame() =
    nvgEndFrame(id)

  // context state stuff

  def pushState() =
    nvgSave(vgContext.id)

  def popState() =
    nvgRestore(vgContext.id)

  def resetState() =
    nvgReset(vgContext.id)

  def setClearColor(c: vgColor) =
    glClearColor(c.r, c.g, c.b, c.a)
  
  def setPen(p: vgColor | vgPaint, w: Float) =
    if p.isInstanceOf[vgColor] then nvgStrokeColor(vgContext.id, p.asInstanceOf[vgColor].handle)
    else nvgStrokePaint(vgContext.id, p.asInstanceOf[vgPaint].handle)
    nvgStrokeWidth(vgContext.id, w)
  
  def setPen(p: vgColor | vgPaint) =
    if p.isInstanceOf[vgColor] then nvgStrokeColor(vgContext.id, p.asInstanceOf[vgColor].handle)
    else nvgStrokePaint(vgContext.id, p.asInstanceOf[vgPaint].handle)

  def setPenWidth(w: Float) =
    nvgStrokeWidth(vgContext.id, w)

  def setBrush(p: vgColor | vgPaint) =
    if p.isInstanceOf[vgColor] then nvgFillColor(vgContext.id, p.asInstanceOf[vgColor].handle)
    else nvgFillPaint(vgContext.id, p.asInstanceOf[vgPaint].handle)

  def setLineStyle(s: vgLineStyle) =
    nvgLineCap(vgContext.id, s.lineCap.id)
    nvgLineJoin(vgContext.id, s.lineJoin.id)
    nvgMiterLimit(vgContext.id, s.miterLimit)

  def setTrafo(t: Matrix3x2f) =
    nvgResetTransform(vgContext.id)
    nvgTransform(vgContext.id, t.m00, t.m01, t.m10, t.m11, t.m20, t.m21)


  // combine into one method?

  def setFont(f: Font) =
    nvgFontFaceId(vgContext.id, f.id)

  def setFontSize(s: Float) =
    nvgFontSize(vgContext.id, s)

  // setHorizontalTextAlign()
  // setVerticalTextAlign()
  def setTextAlign(a: vgTextAlign) = nvgTextAlign(vgContext.id, a.id)
  def setTextLetterSpacing(s: Float) = nvgTextLetterSpacing(vgContext.id, s)
  def setTextLineHight(h: Float) = nvgTextLineHeight(vgContext.id, h)

  def setPathWinding(w: vgWinding) = nvgPathWinding(vgContext.id, w.id)

  // todo

  def globalAlpha() = 0
  def scissor(b: Box) = 0
  def shapeAntiAlias(enabled: Boolean) = 0 // set wether to render with anialiasing


