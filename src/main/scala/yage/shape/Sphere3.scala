package yage.shape

import org.joml.Vector3f

case class Sphere3(center: Vector3f, var radius: Float):
  
  def this() = this(Vector3f(), 0f)
  
  def this(s: Sphere3) = this(Vector3f(s.center), s.radius)
