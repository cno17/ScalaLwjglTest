package demo.base.learn.util

import yage.base.glfw.input.Key
import yage.base.glfw.input.Keyboard
import yage.base.glfw.input.Mouse

abstract class Controller():

  def keyPressed(k: Key): Unit
  def keyRepeated(k: Key): Unit
  
  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float): Unit
  def mouseDragged(x: Float, y: Float, dx: Float, dy: Float): Unit

  def registerListeners() =
    Keyboard.keyPressedListeners += keyPressed
    Keyboard.keyRepeatedListeners += keyRepeated
    Mouse.movedListeners += mouseMoved
    Mouse.draggedListeners += mouseDragged
    
  def unregisterListeners(): Unit
