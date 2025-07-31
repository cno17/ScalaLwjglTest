package yage.shape

import org.joml.Vector2f

class OBox2(
  var org: Vector2f,
  var dirX: Vector2f, var dirY: Vector2f,
  var radX: Float, var radY: Float
) extends Shape2:

  def this() = this(Vector2f(), Vector2f(1, 0), Vector2f(0, 1), 1, 1)
  
  def vertices =
    val res = Array.fill(4)(Vector2f(org))
    res(0).addScaled(dirX, +radX).addScaled(dirY, +radY)
    res(1).addScaled(dirX, -radX).addScaled(dirY, +radY)
    res(2).addScaled(dirX, -radX).addScaled(dirY, -radY)
    res(3).addScaled(dirX, +radX).addScaled(dirY, -radY)
    res
