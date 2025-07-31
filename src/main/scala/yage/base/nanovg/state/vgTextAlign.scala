package yage.base.nanovg.state

import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_BASELINE
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_BOTTOM
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_CENTER
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_LEFT
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_RIGHT
import org.lwjgl.nanovg.NanoVG.NVG_ALIGN_TOP

enum vgTextAlign(val id: Int):

  // H
  case Left extends vgTextAlign(NVG_ALIGN_LEFT)
  case Center extends vgTextAlign(NVG_ALIGN_CENTER)
  case Right extends vgTextAlign(NVG_ALIGN_RIGHT)
  
  // V
  case Top extends vgTextAlign(NVG_ALIGN_TOP)
  case Baseline extends vgTextAlign(NVG_ALIGN_BASELINE)
  // case Middle extends TextAlign(NVG_ALIGN_)
  case Bottom extends vgTextAlign(NVG_ALIGN_BOTTOM)
