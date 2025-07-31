package yage.shape

import org.joml.Vector2f

// dir should always be normalized!

case class Ray2(org: Vector2f, dir: Vector2f):

  def this() = this(Vector2f(), Vector2f(1f, 0f))

  def this(r: Ray2) = this(Vector2f(r.org), Vector2f(r.dir))
