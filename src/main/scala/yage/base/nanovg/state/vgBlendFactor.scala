package yage.base.nanovg.state

import org.lwjgl.nanovg.NanoVG.NVG_DST_ALPHA
import org.lwjgl.nanovg.NanoVG.NVG_DST_COLOR
import org.lwjgl.nanovg.NanoVG.NVG_ONE_MINUS_DST_ALPHA
import org.lwjgl.nanovg.NanoVG.NVG_ONE_MINUS_DST_COLOR
import org.lwjgl.nanovg.NanoVG.NVG_ONE_MINUS_SRC_ALPHA
import org.lwjgl.nanovg.NanoVG.NVG_ONE_MINUS_SRC_COLOR
import org.lwjgl.nanovg.NanoVG.NVG_SRC_ALPHA
import org.lwjgl.nanovg.NanoVG.NVG_SRC_ALPHA_SATURATE
import org.lwjgl.nanovg.NanoVG.NVG_SRC_COLOR
import org.lwjgl.nanovg.NanoVG.NVG_ZERO

enum vgBlendFactor(val id: Int):
  case Zero extends vgBlendFactor(NVG_ZERO)
  case One extends vgBlendFactor(NVG_ZERO)
  case SrcColor extends vgBlendFactor(NVG_SRC_COLOR)
  case OneMinusSrcColor extends vgBlendFactor(NVG_ONE_MINUS_SRC_COLOR)
  case DstColor extends vgBlendFactor(NVG_DST_COLOR)
  case OneMinusDstColor extends vgBlendFactor(NVG_ONE_MINUS_DST_COLOR)
  case SrcAlpha extends vgBlendFactor(NVG_SRC_ALPHA)
  case OneMinusSrcAlpha extends vgBlendFactor(NVG_ONE_MINUS_SRC_ALPHA)
  case DstAlpha extends vgBlendFactor(NVG_DST_ALPHA)
  case OneMinusDstAlpha extends vgBlendFactor(NVG_ONE_MINUS_DST_ALPHA)
  case SrcAlphaSaturate extends vgBlendFactor(NVG_SRC_ALPHA_SATURATE)
