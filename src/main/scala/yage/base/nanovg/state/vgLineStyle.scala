package yage.base.nanovg.state

import org.lwjgl.nanovg.NanoVG.NVG_BEVEL
import org.lwjgl.nanovg.NanoVG.NVG_BUTT
import org.lwjgl.nanovg.NanoVG.NVG_MITER
import org.lwjgl.nanovg.NanoVG.NVG_ROUND
import org.lwjgl.nanovg.NanoVG.NVG_SQUARE

enum vgLineCap(val id: Int):

  case Butt extends vgLineCap(NVG_BUTT)
  case Round extends vgLineCap(NVG_ROUND)
  case Square extends vgLineCap(NVG_SQUARE)
  case Bevel extends vgLineCap(NVG_BEVEL)
  case Miter extends vgLineCap(NVG_MITER)

enum vgLineJoin(val id: Int):

  case Bevel extends vgLineJoin(NVG_BEVEL)
  case Miter extends vgLineJoin(NVG_MITER)
  case Round extends vgLineJoin(NVG_ROUND)

class vgLineStyle(var lineCap: vgLineCap, var lineJoin: vgLineJoin, var miterLimit: Float)
