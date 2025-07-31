package test.base.nanovg

import org.joml.Matrix3x2f
import test.base.nanovg.ZoomTest.tx as ty
import yage.base.glfw.GlApp
import yage.base.glfw.input.Mouse
import yage.base.glfw.window.EventMode
import yage.base.glfw.window.Window
import yage.base.glfw.window.WindowCreateInfo
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.vgContext
import yage.base.nanovg.vgPainter

// needs work

object ZoomTest2 extends GlApp:

  var sx = 1f
  var sy = 1f
  var tx = 0f
  var ty = 0f
  val trafo = Matrix3x2f()

  override def info() =
    val res = WindowCreateInfo()
    res.eventMode = EventMode.Wait
    res

  override def init() =
    Mouse.movedListeners += mouseMoved
    Mouse.draggedListeners += mouseDragged
    Mouse.wheelRotatedListeners += mouseWheelRotated
    vgContext.setClearColor(vgColor.Black)

  override def draw() =
    vgContext.clear()
    vgContext.beginFrame(Window.sizeX, Window.sizeY)
    vgContext.setTrafo(trafo)
    vgContext.setPen(vgColor.Green)
    vgPainter.drawTrigon(100, 100, 200, 100, 200, 200)
    vgContext.setBrush(vgColor.Blue)
    vgContext.setPen(vgColor.White)
    vgContext.setPenWidth(3)
    vgPainter.fillCircle(300, 300, 50)
    vgPainter.drawCircle(300, 300, 50)
    vgPainter.drawCircle(-200, 300, 20)
    vgPainter.drawCircle(-500, 300, 20)
    vgContext.endFrame()

  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    val px = (x - tx) / sx
    val py = (y - ty) / sy
    println(s"(mx = $x, my = $y), (px = $px, py = $py")

  def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    tx += dx
    ty += dy
    trafo.identity()
    trafo.translate(tx, ty)
    trafo.scale(sx, sy)

  def mouseWheelRotated(r: Float) =
    val px = (Mouse.posX - tx) / sx
    val py = (Mouse.posY - ty) / sy
    val sx1 = sx
    val sy1 = sy
    val tx1 = tx
    val ty1 = tx
    if r > 0 then
      sx *= 1.1f
      sy *= 1.1f
    else
      sx *= 0.9f
      sy *= 0.9f
    tx = (sx1 - sx) * px + tx1
    ty = (sy1 - sy) * py + ty1
    trafo.identity()
    trafo.translate(tx, ty)
    trafo.scale(sx, sy)
