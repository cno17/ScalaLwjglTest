package test.base.glfw

import org.lwjgl.opengl.GL11C.*
import yage.base.glfw.*
import yage.base.glfw.GlApp
import yage.base.glfw.input.Key
import yage.base.glfw.input.Keyboard
import yage.base.glfw.input.Mouse
import yage.base.glfw.input.Mouse.Button
import yage.base.glfw.window.Window

// todo: method order!

object EventDemo extends GlApp:

  override def init() =
    Window.movedListeners += windowMoved
    Window.resizedListeners += windowResized
    Window.iconifiedListeners += windowIconified
    Window.restoredListeners += windowRestored
    Window.closeRequestedListeners += windowCloseRequested
    Window.focusGainedListeners += focusGained
    Window.focusLostListeners += focusLost
    Window.mouseEnteredListeners += mouseEntered
    Window.mouseLeftListeners += mouseLeft
    Keyboard.keyPressedListeners += keyPressed
    Keyboard.keyReleasedListeners += keyReleased
    Keyboard.keyRepeatedListeners += keyRepeated
    Mouse.movedListeners += mouseMoved
    Mouse.draggedListeners += mouseDragged
    Mouse.buttonPressedListeners += mouseButtonPressed
    Mouse.buttonReleasedListeners += mouseButtonReleased
    Mouse.wheelRotatedListeners += mouseWheelRotated
    glClearColor(0.0f, 1.0f, 0.0f, 1.0f)

  override def draw() =
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)

  def focusGained() =
    println("focus gained")

  def focusLost() =
    println("focus lost")

  def keyPressed(key: Key) =
    println(s"key pressed: $key")

  def keyRepeated(key: Key) =
    println(s"key repeated: $key")

  def keyReleased(key: Key) =
    println(s"key released: $key")

  def mouseEntered() =
    println("mouse entered")

  def mouseLeft() =
    println("mouse left")

  def mouseMoved(x: Float, y: Float, dx: Float, dy: Float) =
    println(s"mouse moved: x = $x, y = $y, dx = $dx, dy = $dy")

  def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    println(s"mouse dragged: x = $x, y = $y, dx = $dx, dy = $dy")

  def mouseButtonPressed(button: Button) =
    println(s"mouse button pressed: $button")

  def mouseButtonReleased(button: Button) =
    println("mouse button released")

  def mouseWheelRotated(wr: Double) =
    println(s"mouse wheel rotated: wr = $wr")

  def windowMoved(x: Int, y: Int) =
    println(s"window moved: x = $x, y = $y")

  def windowResized(w: Int, h: Int) =
    println(s"window resized: w = $w, h = $h")

  def windowIconified() =
    println("window iconified")

  def windowRestored() =
    println("window restored")

  def windowCloseRequested() =
    println("window close requested")