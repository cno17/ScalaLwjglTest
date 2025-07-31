package yage.base.opengl.resource.texture

import org.lwjgl.opengl.GL45C.glCreateTextures
import org.lwjgl.opengl.GL45C.glTextureStorage1D
import org.lwjgl.opengl.GL45C.glTextureSubImage1D
import yage.base.opengl.resource.texture.Texture.Type.Dim1

import java.nio.ByteBuffer

class Texture1(tf: TexelFormat, nl: Int, si: Int) extends Texture(Dim1, tf, nl, 1):

  override def create() =
    val id = glCreateTextures(typ.value)
    glTextureStorage1D(id, nl, tf.id, si)
    id

  def load(data: ByteBuffer, format: TexelFormat): Unit =
    load(data, format, 0, 0, sizeI)

  def load(data: ByteBuffer, format: TexelFormat, level: Int): Unit =
    load(data, format, level, 0, sizeI)

  def load(data: ByteBuffer, format: TexelFormat, level: Int, offI: Int, extI: Int) =
    glTextureSubImage1D(id, level, offI, extI, format.layout, format.componentType, data)
