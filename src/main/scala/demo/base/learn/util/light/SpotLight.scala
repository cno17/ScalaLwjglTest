package demo.base.learn.util.light

import org.joml.Vector4f

/**
 * dir must be normalized
 */

class SpotLight(var pos: Vector4f, var dir: Vector4f) extends Light:
  
  var cutOff = 0f
