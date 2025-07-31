package yage.base.nanovg.state

import org.joml.Vector2f
import org.lwjgl.nanovg.NVGPaint
import org.lwjgl.nanovg.NanoVG.nvgImagePattern
import org.lwjgl.nanovg.NanoVG.nvgLinearGradient
import org.lwjgl.nanovg.NanoVG.nvgRadialGradient
import yage.base.nanovg.vgContext
import yage.base.nanovg.vgImage

// Paints are value objects: they can not be modified after creation!

class vgPaint:
  
  val handle = NVGPaint.create()


class vgImagePattern(img: vgImage) extends vgPaint:

  // todo args!
  var px = 0f
  var py = 0f
  var sx = 0f
  var sy = 0f
  var angle = 0f
  var alpha = 1f

  nvgImagePattern(vgContext.id, px, py, sx, sy, angle, img.id, alpha, handle)

class vgGradient extends vgPaint

class vgLinearGradient(p1: Vector2f, p2: Vector2f, c1: vgColor, c2: vgColor) extends vgGradient:
  
  nvgLinearGradient(vgContext.id, p1.x, p1.y, p2.x, p2.y, c1.handle, c2.handle, handle)

/**
 * the color at point p is ... 
 * c1, if |p - c| < r1  
 * c2, if |p - c| > r2
 * a blend between c1 and c2, if r1 <= |p - c| <= r2
 */

class vgRadialGradient(c: Vector2f, r1: Float, r2: Float, c1: vgColor, c2: vgColor) extends vgGradient:
  
  nvgRadialGradient(vgContext.id, c.x, c.y, r1, r2, c1.handle, c2.handle, handle)

class BoxVgGradient extends vgGradient
