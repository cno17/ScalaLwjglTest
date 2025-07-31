package demo.base.learn.util.light

import org.joml.Vector3f

// light intensity, material reflectivity: ambient, diffuse, specular

class ADS(var a: Vector3f, var d: Vector3f, var s: Vector3f):
  
  def this() = this(Vector3f(), Vector3f(), Vector3f())
  
  override def toString() = s"(a = $a, d = $d, s = $s)"


  

