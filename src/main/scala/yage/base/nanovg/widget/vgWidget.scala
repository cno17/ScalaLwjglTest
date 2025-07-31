package yage.base.nanovg.widget

import org.joml.Vector2f
import yage.base.joml.VectorExt

/**
 * 
 */

trait vgWidget extends VectorExt:

  def contains(x: Float, y: Float): Boolean = false
  def contains(p: Vector2f): Boolean = contains(p.x, p.y)

  // def box: ABox2
  
  // activate(), deactivate()
  def addListenersToMouse() = {}
  def removeListenersFromMouse() = {}

  def paint() = {}
  // def update() = {}
