package demo.base.learn.util

import yage.base.glfw.input.Key
import yage.base.glfw.input.Keyboard
import yage.base.glfw.input.Mouse

class CameraController(var camera: Camera):

  var traSpeed = 0.2f
  var rotSpeed = 0.02f

  def keyPressed(k: Key) = process(k)
  def keyRepeated(k: Key) = process(k)
  
  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    println("2")
    
  def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    println(3)

  Keyboard.keyPressedListeners += keyPressed
  Keyboard.keyRepeatedListeners += keyRepeated
  Mouse.movedListeners += mouseMoved
  Mouse.draggedListeners += mouseDragged
    
  // def removeListenersFrom(k: Keyboard, m: Mouse) =

  private def process(k: Key) =
    if k == Key.A then camera.traX(-traSpeed)
    if k == Key.D then camera.traX(+traSpeed)
    if k == Key.S then camera.traZ(+traSpeed)
    if k == Key.W then camera.traZ(-traSpeed)
    if k == Key.Left then camera.rotY(-rotSpeed)
    if k == Key.Right then camera.rotY(+rotSpeed)
    if k == Key.Up then camera.rotX(-rotSpeed)
    if k == Key.Down then camera.rotX(+rotSpeed)
    if k == Key.R then camera.matVW.setRotationXYZ(0f, 0f, 0f)
