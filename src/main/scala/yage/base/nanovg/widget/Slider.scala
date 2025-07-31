package yage.base.nanovg.widget

import org.joml.Vector2f
import yage.base.glfw.input.Mouse
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.widget.Slider.Knob
import yage.base.nanovg.widget.Slider.Track
import yage.base.nanovg.widget.Slider.knobRadius
import yage.base.nanovg.vgContext
import yage.base.nanovg.vgPath

import scala.collection.mutable.ArrayBuffer

// todo: box

object Slider:

  val minColor = vgColor(0.4f, 0.8f, 0.4f)
  val maxColor = vgColor(0.4f, 0.4f, 0.8f)
  val normalColor = vgColor(0.8f, 0.8f, 0.8f)
  val focusedColor = vgColor(1.0f, 1.0f, 1.0f)

  val trackRadius = 5.0f
  val knobRadius = 12.0f

  protected class Track(slider: Slider) extends vgWidget:

    // ??? fuck this style!!!
    var n1 = Vector2f()
    var s1 = Vector2f()
    var nc = Vector2f()
    var sc = Vector2f()
    var n2 = Vector2f()
    var s2 = Vector2f()

    def update() =
      // TODO n1.set(slider.minPos).add(slider.nor, -trackRadius)
      // TODO s1.set(slider.minPos).add(slider.nor, +trackRadius)
      // TODO nc.set(slider.curPos).add(slider.nor, -trackRadius)
      // TODO sc.set(slider.curPos).add(slider.nor, +trackRadius)
      // TODO n2.set(slider.maxPos).add(slider.nor, -trackRadius)
      // TODO s2.set(slider.maxPos).add(slider.nor, +trackRadius)
      println(17)

    override def paint() =
      vgPath.begin()
      vgPath.moveTo(n1)
      vgPath.lineTo(s1)
      vgPath.lineTo(sc)
      vgPath.lineTo(nc)
      vgPath.close()
      // vgPath.circle(slider.minPos, trackRadius)
      vgContext.setBrush(minColor)
      vgPath.fill()
      vgPath.begin()
      vgPath.moveTo(nc)
      vgPath.lineTo(sc)
      vgPath.lineTo(s2)
      vgPath.lineTo(n2)
      vgPath.close()
      // vgPath.circle(slider.maxPos, trackRadius)
      vgContext.setBrush(maxColor)
      vgPath.fill()

  protected class Knob(slider: Slider) extends vgWidget:

    override def paint() =
      vgPath.begin()
      // vgPath.circle(slider.curPos, knobRadius)
      if slider.focus then vgContext.setBrush(focusedColor)
      else vgContext.setBrush(normalColor)
      vgPath.fill()

class Slider(val minVal: Float, val maxVal: Float, var curVal: Float) extends vgWidget:

  var minPos = Vector2f()
  var maxPos = Vector2f()
  var curPos = Vector2f()

  var dir = Vector2f()
  var nor = Vector2f()

  var focus = false

  var track = Track(this)
  var knob = Knob(this)

  var valueChangedListeners = ArrayBuffer[(Float) => Unit]()

  setMinMaxPos(100, 100, 600, 100)

  def setMinMaxPos(minX: Float, minY: Float, maxX: Float, maxY: Float) =
    minPos.set(minX, minY)
    maxPos.set(maxX, maxY)
    dir.set(maxPos.x - minPos.x, maxPos.y - minPos.y).normalize()
    nor.set(minPos.y - maxPos.y, maxPos.x - minPos.x).normalize()
    updatePos()
    track.update()

  def setCurVal(v: Float) =
    curVal = v
    if curVal < minVal then curVal = minVal
    if curVal > maxVal then curVal = maxVal
    updatePos()
    track.update()

  // TODO override def box(): Abox2   

  override def paint() =
    track.paint()
    knob.paint()

  override def addListenersToMouse() =
    Mouse.movedListeners += mouseMoved
    Mouse.draggedListeners += mouseDragged

  override def removeListenersFromMouse() =
    Mouse.movedListeners -= mouseMoved
    Mouse.draggedListeners -= mouseDragged

  private def updateVal() =
    val f = curPos.distance(minPos) / maxPos.distance(minPos)
    curVal = minVal + (maxVal - minVal) * f

  private def updatePos() =
    val f = (curVal - minVal) / (maxVal - minVal)
    curPos.x = minPos.x + (maxPos.x - minPos.x) * f
    curPos.y = minPos.y + (maxPos.y - minPos.y) * f

  private def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    focus = curPos.distance(x, y) < knobRadius

  private def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    if focus == true then
      val dot = dir.x * dx + dir.y * dy
      // TODO curPos.add(dir, dot)
      if curPos.distance(minPos) > maxPos.distance(minPos) then
        curPos.set(maxPos)
      // focus = false
      if curPos.distance(maxPos) > minPos.distance(maxPos) then
        curPos.set(minPos)
      // focus = false
      updateVal()
      track.update()
      valueChangedListeners.foreach(_(curVal))

