package yage.base.nanovg

import org.lwjgl.nanovg.NanoVG.*
import yage.base.StackUser
import yage.base.nanovg.util.Box

import java.io.File

// Maybe better: Text, TextBox extends Shape and contains its bound

object vgText extends StackUser:

  def fillText(x: Float, y: Float, s: String) =
    nvgText(vgContext.id, x, y, s)
  
  def fillTextBox(x: Float, y: Float, w: Float, s: String) =
    nvgTextBox(vgContext.id, x, y, w, s)
  
  def getTextBounds(x: Float, y: Float, text: String, box: Box) =
    var res = 0f
    useStack(s =>
      val buf = s.mallocFloat(4)
      res = nvgTextBounds(vgContext.id, x, y, text, buf)
      box.set(buf.get(0), buf.get(1), buf. get(2), buf.get(3))
    )
    res

  
  // use ABox2
  /*
  def getTextBounds(x: Float, y: Float, text: String, rect: vgRectangle) =
    var res = 0f
    useStack(s =>
      val buf = s.mallocFloat(4)
      res = nvgTextBounds(vgContext.id, x, y, text, buf)
      rect.min.set(buf.get(0), buf.get(1))
      rect.max.set(buf.get(2), buf.get(3))
    )
    res
   */
  def getTextBoxBounds = 0

  def getTextGlyphPositions(x: Float, y: Float, text: String) = 0

  // based on current state
  def getTextMetric = 0 // asc, desc, lineHight

  def breakText(text: String, width: Float) = 0 // nvgTextBreakLines
