package yage.base.nanovg.util

import org.joml.Vector2f

class Box(var min: Vector2f, var max: Vector2f):
  
  def this() = this(Vector2f(), Vector2f())
  
  def set(x1: Float, y1: Float, x2: Float, y2: Float) =
    min.set(x1, y1)
    max.set(x2, y2)
    this
    
  override def toString = s"($min, $max)"