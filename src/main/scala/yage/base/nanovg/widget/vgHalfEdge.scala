package yage.base.nanovg.widget

import org.joml.Vector2f
import yage.base.nanovg.vgPath
import yage.base.nanovg.widget.vgWidget

class vgHalfEdge(val p1: Vector2f, val p2: Vector2f) extends vgWidget:
  
  def this(x1: Float, y1: Float, x2: Float, y2: Float) =
    this(Vector2f(x1, y1), Vector2f(x2, y2))
  
  var handleOffset = 0.8f
  var handleLength = 20.0f
  var handleWidth = 8.0f

  val pos = Vector2f()
  val dir = Vector2f()
  val nor = Vector2f()
  val pa = Vector2f()

  private def update() =
    val len = p1.distance(p2)
    dir.set(p2).sub(p1).normalize()
    nor.set(dir.y, -dir.x)
    pa.set(p1).addScaled(dir, len * handleOffset - handleLength)

  override def paint() =
    update()
    vgPath.begin()
    vgPath.moveTo(p1)
    vgPath.lineTo(p2)
    vgPath.draw()
    vgPath.begin()
    vgPath.moveTo(pa)
    vgPath.lineTo(pos.set(pa).addScaled(nor, handleWidth))
    vgPath.lineTo(pos.set(pa).addScaled(dir, handleLength))
    vgPath.close()
    vgPath.fill()

  override def contains(x: Float, y: Float) =
    pa.distance(x, y) <= handleLength
