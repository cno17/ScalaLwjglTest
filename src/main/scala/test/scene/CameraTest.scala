package test.scene

import yage.scene.camera.Camera

object CameraTest:

  def main(args: Array[String]) =
    val c = Camera()
    c.matWV.translation(2, 3, 5)
    c.update()
    println(c)