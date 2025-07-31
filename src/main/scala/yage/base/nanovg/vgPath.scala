package yage.base.nanovg

import org.joml.Vector2f
import org.lwjgl.nanovg.NanoVG.nvgArc
import org.lwjgl.nanovg.NanoVG.nvgArcTo
import org.lwjgl.nanovg.NanoVG.nvgBeginPath
import org.lwjgl.nanovg.NanoVG.nvgBezierTo
import org.lwjgl.nanovg.NanoVG.nvgCircle
import org.lwjgl.nanovg.NanoVG.nvgClosePath
import org.lwjgl.nanovg.NanoVG.nvgEllipse
import org.lwjgl.nanovg.NanoVG.nvgFill
import org.lwjgl.nanovg.NanoVG.nvgLineTo
import org.lwjgl.nanovg.NanoVG.nvgMoveTo
import org.lwjgl.nanovg.NanoVG.nvgQuadTo
import org.lwjgl.nanovg.NanoVG.nvgRect
import org.lwjgl.nanovg.NanoVG.nvgRoundedRect
import org.lwjgl.nanovg.NanoVG.nvgStroke
import yage.base.nanovg.state.vgWinding

// moveTo() starts a new subpath

object vgPath:

  def begin() =
    nvgBeginPath(vgContext.id)

  def moveTo(p: Vector2f) =
    nvgMoveTo(vgContext.id, p.x, p.y)

  def moveTo(x: Float, y: Float) =
    nvgMoveTo(vgContext.id, x, y)

  def lineTo(p: Vector2f) =
    nvgLineTo(vgContext.id, p.x, p.y)

  def lineTo(x: Float, y: Float) =
    nvgLineTo(vgContext.id, x, y)

  def quadToTo(p1: Vector2f, p: Vector2f) =
    nvgQuadTo(vgContext.id, p1.x, p1.y, p.x, p.y)

  def quadToTo(x1: Float, y1: Float, x: Float, y: Float) =
    nvgQuadTo(vgContext.id, x1, y1, x, y)

  def cubicTo(p1: Vector2f, p2: Vector2f, p: Vector2f) =
    nvgBezierTo(vgContext.id, p1.x, p1.y, p2.x, p2.y, p.x, p.y)

  def cubicTo(x1: Float, y1: Float, x2: Float, y2: Float, x: Float, y: Float) =
    nvgBezierTo(vgContext.id, x1, y1, x2, y2, x, y)

  def arcTo(p1: Vector2f, p2: Vector2f, r: Float) =
    nvgArcTo(vgContext.id, p1.x, p1.y, p2.x, p2.y, r)

  def arcTo(x1: Float, y1: Float, x2: Float, y2: Float, r: Float) =
    nvgArcTo(vgContext.id, x1, y1, x2, y2, r)

  def close() =
    nvgClosePath(vgContext.id)

  def draw() =
    nvgStroke(vgContext.id)

  def fill() =
    nvgFill(vgContext.id)
    
    
    
  // utils
  // duplicated by shapes // vgPainter

  def arc(c: Vector2f, r: Float, a1: Float, a2: Float, w: vgWinding) =
    nvgArc(vgContext.id, c.x, c.y, r, a1, a2, w.id)

  def arc(x: Float, y: Float, r: Float, a1: Float, a2: Float, w: vgWinding) =
    nvgArc(vgContext.id, x, y, r, a1, a2, w.id)
