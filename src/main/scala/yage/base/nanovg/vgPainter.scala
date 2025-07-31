package yage.base.nanovg

import org.joml.Vector2f
import org.lwjgl.nanovg.NanoVG.nvgBeginPath
import org.lwjgl.nanovg.NanoVG.nvgCircle
import org.lwjgl.nanovg.NanoVG.nvgClosePath
import org.lwjgl.nanovg.NanoVG.nvgEllipse
import org.lwjgl.nanovg.NanoVG.nvgLineTo
import org.lwjgl.nanovg.NanoVG.nvgMoveTo
import org.lwjgl.nanovg.NanoVG.nvgRect
import org.lwjgl.nanovg.NanoVG.nvgRoundedRect

object vgPainter:

  def drawSegment(p1: Vector2f, p2: Vector2f): Unit =
    paintSegment(p1.x, p1.y, p2.x, p2.y, vgPath.draw)

  def drawSegment(x1: Float, y1: Float, x2: Float, y2: Float) =
    paintSegment(x1, y1, x2, y2, vgPath.draw)

  //

  def drawTrigon(p1: Vector2f, p2: Vector2f, p3: Vector2f) =
    paintTrigon(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, vgPath.draw)

  def drawTrigon(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float) =
    paintTrigon(x1, y1, x2, y2, x3, y3, vgPath.draw)

  def fillTrigon(p1: Vector2f, p2: Vector2f, p3: Vector2f) =
    paintTrigon(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, vgPath.fill)

  def fillTrigon(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float) =
    paintTrigon(x1, y1, x2, y2, x3, y3, vgPath.fill)

  //

  def drawRectangle(min: Vector2f, max: Vector2f) =
    paintRectangle(min.x, min.y, max.x, max.y, vgPath.draw)

  def drawRectangle(x1: Float, y1: Float, x2: Float, y2: Float) =
    paintRectangle(x1, y1, x2, y2, vgPath.draw)

  def fillRectangle(min: Vector2f, max: Vector2f) =
    paintRectangle(min.x, min.y, max.x, max.y, vgPath.fill)

  def fillRectangle(x1: Float, y1: Float, x2: Float, y2: Float) =
    paintRectangle(x1, y1, x2, y2, vgPath.fill)

  //

  def drawRoundedRectangle(min: Vector2f, max: Vector2f, r: Float) =
    paintRoundedRectangle(min.x, min.y, max.x, max.y, r, vgPath.draw)

  def drawRoundedRectangle(x1: Float, y1: Float, x2: Float, y2: Float, r: Float) =
    paintRoundedRectangle(x1, y1, x2, y2, r, vgPath.draw)

  def fillRoundedRectangle(min: Vector2f, max: Vector2f, r: Float) =
    paintRoundedRectangle(min.x, min.y, max.x, max.y, r, vgPath.fill)

  def fillRoundedRectangle(x1: Float, y1: Float, x2: Float, y2: Float, r: Float) =
    paintRoundedRectangle(x1, y1, x2, y2, r, vgPath.fill)

  //

  def drawCircle(c: Vector2f, r: Float) =
    paintCircle(c.x, c.y, r, vgPath.draw)

  def drawCircle(x: Float, y: Float, r: Float) =
    paintCircle(x, y, r, vgPath.draw)

  def fillCircle(c: Vector2f, r: Float) =
    paintCircle(c.x, c.y, r, vgPath.fill)

  def fillCircle(x: Float, y: Float, r: Float) =
    paintCircle(x, y, r, vgPath.fill)

  //

  def drawEllipse(c: Vector2f, rx: Float, ry: Float) =
    paintEllipse(c.x, c.y, rx, ry, vgPath.draw)

  def drawEllipse(cx: Float, cy: Float, rx: Float, ry: Float) =
    paintEllipse(cx, cy, rx, ry, vgPath.draw)

  def fillEllipse(c: Vector2f, rx: Float, ry: Float) =
    paintEllipse(c.x, c.y, rx, ry, vgPath.fill)

  def fillEllipse(cx: Float, cy: Float, rx: Float, ry: Float) =
    paintEllipse(cx, cy, rx, ry, vgPath.fill)

  // todo: arc

  def paintSegment(x1: Float, y1: Float, x2: Float, y2: Float, f: () => Unit) =
    nvgBeginPath(vgContext.id)
    nvgMoveTo(vgContext.id, x1, y1)
    nvgLineTo(vgContext.id, x2, y2)
    f()

  private def paintTrigon(x1: Float, y1: Float, x2: Float, y2: Float, x3: Float, y3: Float, f: () => Unit) =
    nvgBeginPath(vgContext.id)
    nvgMoveTo(vgContext.id, x1, y1)
    nvgLineTo(vgContext.id, x2, y2)
    nvgLineTo(vgContext.id, x3, y3)
    nvgClosePath(vgContext.id)
    f()

  private def paintRectangle(x1: Float, y1: Float, x2: Float, y2: Float, f: () => Unit) =
    nvgBeginPath(vgContext.id)
    nvgRect(vgContext.id, x1, y1, x2, y2)
    f()

  private def paintRoundedRectangle(x1: Float, y1: Float, x2: Float, y2: Float, r: Float, f: () => Unit) =
    nvgBeginPath(vgContext.id)
    nvgRoundedRect(vgContext.id, x1, y1, x2, y2, r)
    f()

  private def paintCircle(x: Float, y: Float, r: Float, f: () => Unit) =
    nvgBeginPath(vgContext.id)
    nvgCircle(vgContext.id, x, y, r)
    f()

  private def paintEllipse(cx: Float, cy: Float, rx: Float, ry: Float, f: () => Unit) =
    nvgBeginPath(vgContext.id)
    nvgEllipse(vgContext.id, cx, cy, rx, ry)
    f()
