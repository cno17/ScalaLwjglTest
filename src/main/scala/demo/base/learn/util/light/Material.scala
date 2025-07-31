package demo.base.learn.util.light

class Material:

  var reflectivity = ADS()
  var shininess = 64f
  
  var offsetReflectivityA = 0
  // ...

  init()
  
  def init() =
    reflectivity.a.set(1.0f)
    reflectivity.d.set(1.0f)
    reflectivity.s.set(1.0f)



//  var ambient = Vector3f(1.0f, 1.0f, 1.0f)
//  var diffuse = Vector3f(1.0f, 1.0f, 1.0f)
//  var specular = Vector3f(1.0f, 1.0f, 1.0f)
