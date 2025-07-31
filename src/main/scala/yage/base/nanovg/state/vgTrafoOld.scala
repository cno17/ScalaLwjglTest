package yage.base.nanovg.state

import org.lwjgl.BufferUtils
import org.lwjgl.nanovg.NanoVG.*

// components
// |a c e|
// |b d f|

// cuurent trafo of context

// nvgTransformPoint​(), ...

/**
 * Paths, gradients, patterns and scissors are transformed by the current trafo
 * at the time they are passed to the API. 
 */

class vgTrafoOld:

  val cur = BufferUtils.createFloatBuffer(6)
  val tmp = BufferUtils.createFloatBuffer(6)

  reset()

  def apply(r: Int, c: Int) = 
    cur.get(r + 2 * c)
  
  def update(r: Int, c: Int, v: Float) = 
    cur.put(r + 2 * c, v)

  def reset() = 
    nvgTransformIdentity(cur)
    this
    
  def invert() = 
    nvgTransformInverse(cur, cur)
    this

  def toScale(sx: Float, sy: Float) =
    nvgTransformScale(cur, sx, sy)
    this

  def toRotation(a: Float) = 
    nvgTransformRotate(cur, a)
    this
    
  def toTranslation(tx: Float, ty: Float) = 
    nvgTransformTranslate(cur, tx, ty)
    this

  def scale(sx: Float, sy: Float) =
    nvgTransformScale(tmp, sx, sy)
    nvgTransformMultiply(cur, tmp)
    this


  def scaleLeft(sx: Float, sy: Float) =
    nvgTransformScale(tmp, sx, sy)
    nvgTransformPremultiply(cur, tmp)
    this

  def rotate(a: Float) =
    nvgTransformRotate(tmp, a)
    nvgTransformMultiply(cur, tmp)
    this

  def rotateLeft(a: Float) = 
    nvgTransformRotate(tmp, a)
    nvgTransformPremultiply(cur, tmp)
    this

  def translate(tx: Float, ty: Float) =
    nvgTransformTranslate(tmp, tx, ty)
    nvgTransformMultiply(cur, tmp)
    this

  def translateLeft(tx: Float, ty: Float) =
    nvgTransformTranslate(tmp, tx, ty)
    nvgTransformPremultiply(cur, tmp)
    this
  
  def compose(t: vgTrafoOld) =
    nvgTransformMultiply(cur, t.cur)
    this

  def composeLeft(t: vgTrafoOld) =
    nvgTransformPremultiply(cur, t.cur)
    this

  override def toString() =
    val s0 = f"${apply(0, 0)}%5.2f ${apply(0, 1)}%5.2f ${apply(0, 2)}%5.2f"
    val s1 = f"${apply(1, 0)}%5.2f ${apply(1, 1)}%5.2f ${apply(1, 2)}%5.2f"
    s0 + "\n" + s1 + "\n"
