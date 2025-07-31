package test.base.opengl.primitive

import yage.base.glfw.GlApp
import yage.scene.primitive.Cuboid

object PrimitiveTest extends GlApp:

  override def init() =
    val c = Cuboid()
    println(c)
    close()

