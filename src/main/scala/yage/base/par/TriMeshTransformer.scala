package yage.base.par

import org.lwjgl.util.par.ParShapes.par_shapes_rotate
import org.lwjgl.util.par.ParShapes.par_shapes_scale
import org.lwjgl.util.par.ParShapes.par_shapes_translate

trait TriMeshTransformer:
  
  this: TriMesh =>

  def scale(s: Float) =
    par_shapes_scale(delegate, s, s, s)
    updateBox()

  def scale(sx: Float, sy: Float, sz: Float) =
    par_shapes_scale(delegate, sx, sy, sz)
    updateBox()
  
  def rotate(a: Float, x: Float, y: Float, z: Float) =
    par_shapes_rotate(delegate, a, Array(x, y, z))
    updateBox()
  
  def translate(tx: Float, ty: Float, tz: Float) =
    par_shapes_translate(delegate, tx, ty, tz)
    updateBox()
