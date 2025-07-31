package yage.base.nanovg.state

import org.lwjgl.nanovg.NanoVG.NVG_ATOP
import org.lwjgl.nanovg.NanoVG.NVG_COPY
import org.lwjgl.nanovg.NanoVG.NVG_DESTINATION_ATOP
import org.lwjgl.nanovg.NanoVG.NVG_DESTINATION_IN
import org.lwjgl.nanovg.NanoVG.NVG_DESTINATION_OUT
import org.lwjgl.nanovg.NanoVG.NVG_DESTINATION_OVER
import org.lwjgl.nanovg.NanoVG.NVG_LIGHTER
import org.lwjgl.nanovg.NanoVG.NVG_SOURCE_IN
import org.lwjgl.nanovg.NanoVG.NVG_SOURCE_OUT
import org.lwjgl.nanovg.NanoVG.NVG_SOURCE_OVER
import org.lwjgl.nanovg.NanoVG.NVG_XOR

// BlendOp, BlendMode

enum vgCompositOperation(val id: Int):

  case SrcOver extends vgCompositOperation(NVG_SOURCE_OVER)
  case SrcIn extends vgCompositOperation(NVG_SOURCE_IN)
  case SrcOut extends vgCompositOperation(NVG_SOURCE_OUT)
  case Atop extends vgCompositOperation(NVG_ATOP) // SrcAtop?
  case DstOver extends vgCompositOperation(NVG_DESTINATION_OVER)
  case DstIn extends vgCompositOperation(NVG_DESTINATION_IN)
  case DstOut extends vgCompositOperation(NVG_DESTINATION_OUT)
  case DstAtop extends vgCompositOperation(NVG_DESTINATION_ATOP)
  case Lighter extends vgCompositOperation(NVG_LIGHTER)
  case Copy extends vgCompositOperation(NVG_COPY)
  case Xor extends vgCompositOperation(NVG_XOR)
