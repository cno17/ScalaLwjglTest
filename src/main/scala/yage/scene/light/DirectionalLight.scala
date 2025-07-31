package yage.scene.light

import org.joml.Vector4f

// directions must always be normalized!

trait DirectionalLight:
  
  val dirW = Vector4f()
  val dirV = Vector4f()

  var dirWOffset = 0
  var dirVOffset = 0

