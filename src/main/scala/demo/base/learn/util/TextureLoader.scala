package demo.base.learn.util

import org.lwjgl.opengl.GL11.*
import org.lwjgl.opengl.GL30.*
import org.lwjgl.stb.STBImage.stbi_image_free
import org.lwjgl.stb.STBImage.stbi_load
import yage.base.StackUser

import java.io.File

trait TextureLoader extends StackUser:

  def loadTexture(f: File, wrapping: Int, filtering: Int): Int =
    val texture = glGenTextures()
    glBindTexture(GL_TEXTURE_2D, texture)
    // All upcoming GL_TEXTURE_2D operations now have effect on this texture object
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrapping)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrapping)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, filtering)
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, filtering)
    useStack(stack =>
      val width = stack.ints(0)
      val height = stack.ints(0)
      val nrChannels = stack.ints(0)
      // stbi_set_flip_vertically_on_load (flipY) 
      // Tell stb_image.h whether to flip loaded texture's on the y-axis or not.
      val data = stbi_load(f.getAbsolutePath(), width, height, nrChannels, 0)
      //  if (data != null) {
      var format = 0
      if nrChannels.get(0) == 1 then format = GL_RED
      if nrChannels.get(0) == 2 then format = GL_RG
      if nrChannels.get(0) == 3 then format = GL_RGB
      if nrChannels.get(0) == 4 then format = GL_RGBA
      glTexImage2D(GL_TEXTURE_2D, 0, format, width.get(0), height.get(0), 0, format, GL_UNSIGNED_BYTE, data)
      glGenerateMipmap(GL_TEXTURE_2D)
      stbi_image_free(data)
    )
    texture

