package yage.base.nanovg.state

import org.lwjgl.nanovg.NanoVG.NVG_CCW
import org.lwjgl.nanovg.NanoVG.NVG_CW

enum vgWinding(val id: Int):
  case CW extends vgWinding(NVG_CW)
  case CCW extends vgWinding(NVG_CCW)
