package yage.scene.primitive

import org.joml.Vector4f

import yage.base.FMath.cos
import yage.base.FMath.sin

// val PI = Math.PI.toFloat
// Spheroid --> Cuboid

class Spheroid(radX: Float, radY: Float, radZ: Float, resU: Int, resV: Int)
extends Surface(0, PI * 2, -PI / 2, PI / 2, resU, resV):

  def this() = this(0.5f, 1.0f, 1.5f, 20, 20)

  def this(rad: Float, resU: Int, resV: Int) =
    this(rad, rad, rad, resU, resV)
  
  init()
  create()

  override def pos(u: Float, v: Float) =
    val x = radX * sin(u) * cos(v)
    val y = radY * sin(v)
    val z = radZ * cos(u) * cos(v)
    Vector4f(x, y, z, 1)
