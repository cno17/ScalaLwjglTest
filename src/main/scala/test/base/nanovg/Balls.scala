package test.base.nanovg

import org.joml.Vector2f
import yage.base.glfw.GlApp
import yage.base.glfw.window.Window
import yage.base.glfw.window.WindowCreateInfo
import yage.base.joml.VectorExt
import yage.base.nanovg.vgContext
import yage.base.nanovg.state.vgColor
import yage.base.nanovg.vgPainter

import scala.util.Random

object Balls extends GlApp:

  class Ball(val pos: Vector2f, val vel: Vector2f, val rad: Float) extends VectorExt:

    def step(sx: Float, sy: Float, dt: Int) =
      // TODO pos += (vel, 1f)
      pos.addScaled(vel, 1f)
      if pos.x < 0 || pos.x > sx then vel.x *= -1
      if pos.y < 0 || pos.y > sy then vel.y *= -1
  
  val color1 = vgColor(0.2f, 0.8f, 0.2f)
  val color2 = vgColor(0.8f, 0.8f, 0.8f)
  var balls: Array[Ball] = null

  override def info() =
    val res = WindowCreateInfo()
    res.sizeX = 1200
    res.sizeY = 900
    res

  override def init() =
    balls = createBalls(5)
    vgContext.setClearColor(vgColor(0.0f, 0.0f, 0.0f))

  override def draw() =
    vgContext.clear()
    vgContext.beginFrame(Window.sizeX, Window.sizeY)
    vgContext.setBrush(color1)
    vgContext.setPen(color2)
    for b <- balls do
      vgPainter.fillCircle(b.pos, b.rad)
    vgContext.endFrame()

  override def step(t: Int, dt: Int) =
    val sx = Window.sizeX
    val sy = Window.sizeY
    balls.foreach(_.step(sx, sy, dt))

  def createBalls(n: Int) =
    val sx = Window.sizeX
    val sy = Window.sizeY
    val rnd = Random()
    val res = new Array[Ball](n)
    for i <- 0 to n - 1 do
      val px = sx * rnd.nextFloat()
      val py = sy * rnd.nextFloat()
      val vx = -1.0f * 2.0f * rnd.nextFloat()
      val vy = -1.0f * 2.0f * rnd.nextFloat()
      val rad = 5.0f + rnd.nextFloat() * 20.0f
      res(i) = Ball(Vector2f(px, py), Vector2f(vx, vy), rad)
    res

