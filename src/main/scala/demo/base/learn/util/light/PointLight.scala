package demo.base.learn.util.light

import org.joml.Vector4f

class PointLight(var pos: Vector4f) extends Light:

  var attenuation = CLQ()

  override def toString() = ""
