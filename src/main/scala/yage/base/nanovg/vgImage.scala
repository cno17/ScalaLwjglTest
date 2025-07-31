package yage.base.nanovg

import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_FLIPY
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_GENERATE_MIPMAPS
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_NEAREST
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_PREMULTIPLIED
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_REPEATX
import org.lwjgl.nanovg.NanoVG.NVG_IMAGE_REPEATY
import org.lwjgl.nanovg.NanoVG.nvgCreateImage
import org.lwjgl.nanovg.NanoVG.nvgCreateImageMem
import org.lwjgl.nanovg.NanoVG.nvgDeleteImage
import org.lwjgl.nanovg.NanoVG.nvgImageSize
import org.lwjgl.nanovg.NanoVG.nvgUpdateImage
import yage.base.Caller
import yage.base.Flag
import yage.base.Flags

import java.io.File
import java.nio.ByteBuffer

// join with ImagePattern
// create from Buffered Image?

object vgImage:

  enum vgFlag(val id: Int) extends Flag:
    case FlipY extends vgFlag(NVG_IMAGE_FLIPY)
    case GenerateMipMaps extends vgFlag(NVG_IMAGE_GENERATE_MIPMAPS)
    case Nearest extends vgFlag(NVG_IMAGE_NEAREST)
    case Premultiplied extends vgFlag(NVG_IMAGE_PREMULTIPLIED)
    case RepeatX extends vgFlag(NVG_IMAGE_REPEATY)
    case RepeatY extends vgFlag(NVG_IMAGE_REPEATY)

  class vgFlags(fs: vgFlag*) extends Flags[vgFlag](fs: _*)


  def apply(file: File): vgImage = apply(file, vgFlags())

  def apply(file: File, flags: vgFlags) =
    val id = nvgCreateImage(vgContext.id, file.getAbsolutePath(), flags.value)
    if id == 0 then throw Exception("Could not create image.")
    new vgImage(id)

  def apply(data: ByteBuffer): vgImage = apply(data, vgFlags())

  def apply(data: ByteBuffer, flags: vgFlags) =
    val id = nvgCreateImageMem(vgContext.id, flags.value, data)
    if id == 0 then throw Exception("Could not create image.")
    new vgImage(id)

class vgImage(val id: Int) extends Caller:

  var sizeI = 0
  var sizeJ = 0

  init()

  def init() =
    useStack(s =>
      val bufI = s.mallocInt(1)
      val bufJ = s.mallocInt(1)
      nvgImageSize(vgContext.id, id, bufI, bufJ)
      sizeI = bufI.get(0)
      sizeJ = bufJ.get(0)
    )

  // recompute size?
  def update(data: ByteBuffer) = nvgUpdateImage(vgContext.id, id, data)

  def destroy() = nvgDeleteImage(vgContext.id, id)

