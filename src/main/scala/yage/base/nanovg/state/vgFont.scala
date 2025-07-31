package yage.base.nanovg.state

import org.lwjgl.nanovg.NanoVG.nvgCreateFont
import yage.base.nanovg.vgContext

import java.io.File

object Font:

  def apply(f: File) =
    val id = nvgCreateFont(vgContext.id, "", f.getAbsolutePath())
    if id == -1 then throw Exception(s"Could not load font from file $f")
    new Font(id)

class Font(val id: Int)




