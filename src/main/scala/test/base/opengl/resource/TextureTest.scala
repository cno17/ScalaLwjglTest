package test.base.opengl.resource

import yage.base.opengl.resource.texture.Texture2
import yage.base.glfw.GlApp

import java.io.File
import javax.imageio.ImageIO

object TextureTest extends GlApp:

  override def init() =
    val tex = Texture2(resFile("Images/Bird1.jpg"))
    println(tex.typ)
    println(tex.texelFormat)
    println(tex.numLevels)
    println(tex.numLayers)
    println(tex.sizeI)
    println(tex.sizeJ)
    val img = tex.asBufferedImage
    println(img)
    println(ImageIO.write(img, "jpg", File("Texture.jpg")))
    println(ImageIO.write(img, "png", File("Texture.png")))
    close()
