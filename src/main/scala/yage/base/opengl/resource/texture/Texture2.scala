package yage.base.opengl.resource.texture

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11C.GL_UNSIGNED_BYTE
import org.lwjgl.opengl.GL11C.glGetTexImage
import org.lwjgl.opengl.GL45C.glCreateTextures
import org.lwjgl.opengl.GL45C.glTextureStorage2D
import org.lwjgl.opengl.GL45C.glTextureSubImage2D
import org.lwjgl.stb.STBImage.stbi_load
import org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load
import org.lwjgl.system.MemoryStack
import yage.base.opengl.resource.texture.Texture.Type.Dim2

import java.awt.Graphics2D
import java.awt.Transparency
import java.awt.color.ColorSpace
import java.awt.image.BufferedImage
import java.awt.image.ComponentColorModel
import java.awt.image.DataBuffer
import java.awt.image.DataBufferByte
import java.awt.image.Raster
import java.io.File
import java.nio.ByteBuffer

/**
 * ToDo write texture from pixel unpack buffer
 * BufferedImage loader from lwjgl/examples/spaceinvaders/TextureLoader.java Thanks!
 */

object Texture2:
  
  def apply(tf: TexelFormat, si: Int, sj: Int) = 
    new Texture2(tf, 1, si, sj)

  def apply(tf: TexelFormat, nl: Int, si: Int, sj: Int) = 
    new Texture2(tf, nl, si, sj)

  def apply(f: File) =
    stbi_set_flip_vertically_on_load(true)
    val stack = MemoryStack.stackPush()
    val si = stack.ints(0)
    val sj = stack.ints(0)
    val nc = stack.ints(0)
    val bb = stbi_load(f.getAbsolutePath(), si, sj, nc, 4)
    val tex = new Texture2(TexelFormat.FP32_4, 1, si.get(0), sj.get(0))
    tex.load(bb, TexelFormat.UI08_4)
    MemoryStack.stackPop()
    tex

  def apply(img: BufferedImage) =
    val si = img.getWidth()
    val sj = img.getHeight()
    val cs = ColorSpace.getInstance(ColorSpace.CS_sRGB)
    val ap = img.isAlphaPremultiplied()
    val cm = ComponentColorModel(cs, Array(8, 8, 8, 8), true, ap, Transparency.TRANSLUCENT, DataBuffer.TYPE_BYTE)
    val wr = Raster.createInterleavedRaster(DataBuffer.TYPE_BYTE, si, sj, 4, null)
    val bi = BufferedImage(cm, wr, ap, null)
    val gc = bi.getGraphics().asInstanceOf[Graphics2D]
    gc.scale(1, -1)
    gc.translate(0, -sj)
    gc.drawImage(img, 0, 0, null)
    val ba = bi.getRaster().getDataBuffer().asInstanceOf[DataBufferByte].getData()
    val bb = BufferUtils.createByteBuffer(ba.length)
    bb.put(ba)
    bb.flip()
    val tex = new Texture2(TexelFormat.FP32_4, 1, si, sj)
    tex.load(bb, TexelFormat.UI08_4)
    tex


class Texture2(tf: TexelFormat, nl: Int, si: Int, sj: Int) extends Texture(Dim2, tf, nl, 1):

  override def create() =
    val id = glCreateTextures(typ.value)
    glTextureStorage2D(id, nl, tf.id, si, sj)
    id

  def load(data: ByteBuffer, format: TexelFormat): Unit =
    load(data, format, 0, 0, 0, si, sj)

  def load(data: ByteBuffer, format: TexelFormat, level: Int): Unit =
    load(data, format, level, 0, 0, si, sj)

  def load(data: ByteBuffer, format: TexelFormat, level: Int, offI: Int, offJ: Int, extI: Int, extJ: Int) =
    glTextureSubImage2D(id, level, offI, offJ, extI, extJ, format.layout, format.componentType, data)

  def asBufferedImage =
    val channels = texelFormat.componentCount
    println(channels)
    val buffer = BufferUtils.createByteBuffer(sizeI * sizeJ * channels)
    val image = BufferedImage(sizeI, sizeJ, BufferedImage.TYPE_INT_ARGB)
    glGetTexImage(typ.value, 0, texelFormat.layout, GL_UNSIGNED_BYTE, buffer)
    for x <- 0 to sizeI - 1 do
      for y <- 0 to sizeJ - 1 do
        val i = (x + y * sizeI) * channels
        val r = buffer.get(i + 0) & 0xFF
        val g = buffer.get(i + 1) & 0xFF
        val b = buffer.get(i + 2) & 0xFF
        val a = if channels == 4 then buffer.get(i + 3) & 0xFF else 255
        image.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b)
        // image.setRGB(x, y, 0)
    image