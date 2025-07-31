package yage.base.nanovg.shape

import org.joml.Vector2f
import yage.base.joml.VectorExt
import yage.base.nanovg.vgPath

// widget?

object vgArrow extends VectorExt:

  var headLength = 80f
  var headWidth = 40f
  var stemWidth = 10f

  val p1 = Vector2f()
  val p2 = Vector2f()
  val pos = Vector2f()
  val dir = Vector2f()
  val nor = Vector2f()
  val pa = Vector2f() // first help point

  def draw(x1: Float, y1: Float, x2: Float, y2: Float) =
    update(x1, y1, x2, y2)
    drawHead(x1, y1, x2, y2)
    drawStem(x1, y1, x2, y2)
  
  // private
  
  def drawHead(x1: Float, y1: Float, x2: Float, y2: Float) =
    val o = headWidth / 2
    vgPath.begin()
    vgPath.moveTo(pa)
    vgPath.lineTo(pos.set(pa).addScaled(nor, +o))
    vgPath.lineTo(p2)
    vgPath.lineTo(pos.set(pa).addScaled(nor, -o))
    vgPath.close()
    vgPath.draw()

  def drawStem(x1: Float, y1: Float, x2: Float, y2: Float) =
    val o = stemWidth / 2
    vgPath.begin()
    vgPath.moveTo(p1)
    vgPath.lineTo(pos.set(p1).addScaled(nor, +o))
    vgPath.lineTo(pos.set(pa).addScaled(nor, +o))
    vgPath.lineTo(pos.set(pa).addScaled(nor, -o))
    vgPath.lineTo(pos.set(p1).addScaled(nor, -o))
    vgPath.close()
    vgPath.draw()

  def update(x1: Float, y1: Float, x2: Float, y2: Float) =
    p1.set(x1, y1)
    p2.set(x2, y2)
    dir.set(p2).sub(p1).normalize()
    nor.set(-dir.y, dir.x)
    pa.set(p2).subScaled(dir, headLength)
