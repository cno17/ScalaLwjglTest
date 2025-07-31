package yage.shape

import org.joml.Vector2f

class ABox2(val min: Vector2f, val max: Vector2f) extends Shape2:

  def this() = this(Vector2f(), Vector2f())

  def this(box: ABox2) = this(Vector2f(box.min), Vector2f(box.max))
  
  def initFrom(ps: Array[Vector2f]) =
    min.set(Float.MaxValue)
    max.set(Float.MinValue)
    for p <- ps do 
      if p.x < min.x then min.x = p.x else if p.x > max.x then max.x = p.x
      if p.y < min.y then min.y = p.y else if p.y > max.y then max.y = p.y
    this

  override def toString = s"(${min.str}, ${max.str})"  