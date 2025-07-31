package yage.shape

import org.joml.Vector3f
import yage.base.joml.VectorExt

class ABox3(val min: Vector3f, val max: Vector3f) extends VectorExt:

  def this() = this(Vector3f(), Vector3f())

  def this(box: ABox3) = this(Vector3f(box.min), Vector3f(box.max))

  override def toString = s"(${min.str}, ${max.str})"