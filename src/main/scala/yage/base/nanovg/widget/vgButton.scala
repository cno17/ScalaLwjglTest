package yage.base.nanovg.widget

import scala.collection.mutable.ArrayBuffer
import yage.base.nanovg.vgContext
import org.joml.Vector2f
import org.lwjgl.nanovg.NanoVG.nvgBeginPath
import org.lwjgl.nanovg.NanoVG.nvgFill
import org.lwjgl.nanovg.NanoVG.nvgRect
import org.lwjgl.nanovg.NanoVG.nvgStroke
import yage.base.glfw.input.Key
import yage.base.glfw.input.Keyboard
import yage.base.glfw.input.Mouse
import yage.base.glfw.input.Mouse.Button
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.widget.Button.State
import yage.base.nanovg.widget.Button.State.Down
import yage.base.nanovg.widget.Button.State.Up

object Button:
  
  enum State:
    case Up
    case Down

class Button(val min: Vector2f, val max: Vector2f) extends vgWidget:

  var state = State.Up
  var focused = false
  var pressedListeners = ArrayBuffer[(Button) => Unit]()
  var releasedListeners = ArrayBuffer[(Button) => Unit]()
  val upColor = vgColor(0.8f, 0.8f, 0.8f)
  val downColor = vgColor(0.6f, 0.6f, 0.6f)
  val focusedColor = vgColor(1.0f, 1.0f, 1.0f)

  def setBounds(x1: Float, y1: Float, x2: Float, y2: Float) =
    min.set(x1, y1)
    max.set(x2, y2)

  // TODO override def box(): Abox2

  override def contains(x: Float, y: Float) =
    min.x < x && min.y < y && x < max.x && y < max.y
  
  override def paint() =
    nvgBeginPath(vgContext.id)
    nvgRect(vgContext.id, min.x, min.y, max.x - min.x, max.y - min.y)
    if state == Down then vgContext.setBrush(downColor)
    else if focused then vgContext.setBrush(focusedColor)
    else vgContext.setBrush(upColor)
    nvgFill(vgContext.id)
    // nvgStroke(Context.id)

  override def addListenersToMouse() =
    Mouse.movedListeners += mouseMoved
    Mouse.buttonPressedListeners += buttonPressed
    Mouse.buttonReleasedListeners += buttonReleased
  
  override def removeListenersFromMouse() =
    Mouse.movedListeners -= mouseMoved
    Mouse.buttonPressedListeners -= buttonPressed
    Mouse.buttonReleasedListeners -= buttonReleased

  private def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    focused = contains(x, y)

  private def buttonPressed(b: Mouse.Button) =
    if focused == true then
      state = Down
      pressedListeners.foreach(_(this))

  private def buttonReleased(b: Mouse.Button) =
    if state == Down then
      state = Up
      releasedListeners.foreach(_(this))
