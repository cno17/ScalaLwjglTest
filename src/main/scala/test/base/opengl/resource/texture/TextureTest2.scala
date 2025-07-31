package test.base.opengl.resource.texture

import yage.base.opengl.resource.texture.Texture2
import yage.base.glfw.GlApp

object TextureTest2 extends GlApp:

  override def init() =
    val tex = Texture2(resFile("Images/Bird1.jpg"))
    println(tex.sizeI)
    close()

