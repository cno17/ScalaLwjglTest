package yage.base.glfw.input

import org.lwjgl.glfw.GLFW.GLFW_PRESS
import org.lwjgl.glfw.GLFW.GLFW_RELEASE
import org.lwjgl.glfw.GLFW.GLFW_REPEAT
import org.lwjgl.glfw.GLFW.glfwGetKey
import org.lwjgl.glfw.GLFW.glfwSetKeyCallback
import yage.base.glfw.window.Window.handle

import scala.collection.mutable.ArrayBuffer

// TODO charListeners

object Keyboard:

  val keyPressedListeners = ArrayBuffer[Key => Unit]()
  val keyRepeatedListeners = ArrayBuffer[Key => Unit]()
  val keyReleasedListeners = ArrayBuffer[Key => Unit]()

  def create() = glfwSetKeyCallback(handle, keyCallback)

  def isPressed(key: Key) = glfwGetKey(handle, key.id) == GLFW_PRESS
  def isAltKeyPressed = isPressed(Key.LeftAlt) || isPressed(Key.RightAlt)
  def isControlKeyPressed = isPressed(Key.LeftControl) || isPressed(Key.RightControl)
  def isShiftKeyPressed = isPressed(Key.LeftShift) || isPressed(Key.RightShift)

  private def keyCallback(window: Long, key: Int, code: Int, action: Int, mods: Int) =
    val k = Key(key)
    if action == GLFW_PRESS then keyPressedListeners.foreach(_(k))
    else if action == GLFW_REPEAT then keyRepeatedListeners.foreach(_(k))
    else if action == GLFW_RELEASE then keyReleasedListeners.foreach(_(k))
