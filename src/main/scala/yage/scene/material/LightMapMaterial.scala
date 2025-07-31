package yage.scene.material

import org.joml.Vector3f
import org.lwjgl.BufferUtils
import yage.base.opengl.resource.Format.FP32_4
import yage.base.opengl.resource.texture.Texture2
import yage.base.joml.VectorExt

// color/map = reflectivity

class LightMapMaterial extends VectorExt:

  var ambientColor = Vector3f(0.4f)
  var diffuseMap: Texture2 = null
  var specularMap: Texture2 = null
  var shininess = 64f

  var ambientColorOffset = 0
  var diffuseColorOffset = 0
  var specularColorOffset = 0
  var shininessOffset = 0
