package yage.base.nanovg.state

import org.lwjgl.nanovg.NanoVG.NVG_HOLE
import org.lwjgl.nanovg.NanoVG.NVG_SOLID

// ?

enum vgSolidity(val id: Int):
  case Solid extends vgSolidity(NVG_SOLID)
  case Hole extends vgSolidity(NVG_HOLE)
