package yage.scene.primitive

import org.joml.Vector4f
import yage.base.FMath.sin
import yage.base.FMath.cos

class Torus(rad1: Float, rad2: Float, numU: Int, numV: Int)
extends Surface(0, PI * 2, 0, PI * 2, numU, numV):

  def this() = this(0.2f, 0.8f, 32, 8)

  init()
  create()

  override def pos(u: Float, v: Float) =
    val x = (rad2 + rad1 * cos(v)) * cos(u)
    val y = (rad2 + rad1 * cos(v)) * sin(u)
    val z = rad1 * sin(v)
    Vector4f(x, y, z, 1)
