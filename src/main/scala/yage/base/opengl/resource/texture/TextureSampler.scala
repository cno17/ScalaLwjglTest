package yage.base.opengl.resource.texture

import org.lwjgl.opengl.GL11C.GL_TEXTURE_MAG_FILTER
import org.lwjgl.opengl.GL11C.GL_TEXTURE_MIN_FILTER
import org.lwjgl.opengl.GL11C.GL_TEXTURE_WRAP_S
import org.lwjgl.opengl.GL11C.GL_TEXTURE_WRAP_T
import org.lwjgl.opengl.GL12C.GL_TEXTURE_WRAP_R
import org.lwjgl.opengl.GL45C.glTextureParameteri
import yage.base.opengl.resource.texture.Texture.MagFilter
import yage.base.opengl.resource.texture.Texture.MinFilter
import yage.base.opengl.resource.texture.Texture.WrapMode

/**
 * The internal sampler of a texture. Don't use it!
 */

trait TextureSampler:

  this: Texture =>

  def setMinFilter(f: MinFilter) = glTextureParameteri(id, GL_TEXTURE_MIN_FILTER, f.id)
  def setMagFilter(f: MagFilter) = glTextureParameteri(id, GL_TEXTURE_MAG_FILTER, f.id)

  def setWrapModeU(m: WrapMode) = glTextureParameteri(id, GL_TEXTURE_WRAP_S, m.id)
  def setWrapModeV(m: WrapMode) = glTextureParameteri(id, GL_TEXTURE_WRAP_T, m.id)
  def setWrapModeW(m: WrapMode) = glTextureParameteri(id, GL_TEXTURE_WRAP_R, m.id)

// more, look at sampler
