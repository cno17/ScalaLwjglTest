package yage.scene.light

import org.joml.Vector4f

trait PositionalLight:

  var posW = Vector4f()
  var posV = Vector4f()

  var constantAttenuation = 1f
  var linearAttenuation = 1f
  var quadraticAttenuation = 1f

  var posWOffset = 0
  var posVOffset = 0

  var constantAttenuationOffset = 1f
  var linearAttenuationOffset = 1f
  var quadraticAttenuationOffset = 1f
