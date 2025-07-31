package yage.base.glfw

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import yage.base.StackUser
import yage.base.glfw.input.Key
import yage.base.glfw.input.Keyboard
import yage.base.glfw.input.Mouse
import yage.base.glfw.window.Window
import yage.base.glfw.window.WindowCreateInfo

import java.io.File

trait App extends StackUser:

  var timer: Timer = null
  var frameCounter: FrameCounter = null

  def srcDir =
    val path = getClass.getPackage.getName.replace('.', '\\')
    File(getClass.getClassLoader.getResource(path).toURI)

  def resDir =
    File(getClass.getClassLoader.getResource(".").toURI)

  def srcFile(path: String) = File(srcDir, path)

  def resFile(path: String) = File(resDir, path)

  def info() = WindowCreateInfo()

  def create() =
    val ci = info()
    glfwSetErrorCallback(onError)
    Window.create(ci)
    Keyboard.create()
    Keyboard.keyPressedListeners += onCloseKeyPressed
    Mouse.create()
    Mouse.setPos(ci.sizeX / 2, ci.sizeY / 2)
    timer = Timer()
    frameCounter = FrameCounter()

  def init() = {}

  def draw() = {}

  def step(t: Int, dt: Int) = {}

  def close() = Window.close()

  def exit() = {}

  def destroy() = Window.destroy()

  def main(args: Array[String]) =
    create()
    init()
    while !Window.shouldClose() do
      val t = timer.time
      val dt = timer.timeStep
      draw()
      step(t, dt)
      Window.swapBuffers()
      Window.processEvents()
      frameCounter.step(dt)
    exit()
    destroy()

  protected def onError(code: Int, pDescription: Long) =
    val error = Error(code)
    val description = GLFWErrorCallback.getDescription(pDescription)
    println(s"$error, $description")

  protected def onCloseKeyPressed(key: Key) =
    if key == Key.Escape || key == Key.Q then Window.close()
