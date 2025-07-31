package test.base.glfw

import yage.base.glfw.input.Mouse
import yage.base.glfw.window.Window


object MouseTest:

  def main(args: Array[String]) =
    Window.create()
    Mouse.create()
    Mouse.buttonPressedListeners += (b => println(s"$b pressed"))
    Mouse.buttonReleasedListeners += (b => println(s"$b released"))
    Mouse.draggedListeners += mouseDragged
    Mouse.wheelRotatedListeners += (x => println(s"wheel rotated by $x"))
    // while not Window.shouldClose
    while !Window.shouldClose() do
      Window.processEvents()
    Window.destroy()

  def mouseDragged(x: Float, y: Float, dx: Float, dy: Float) =
    println(s"x = $x, y = $y, dx = $dx, dy = $dy")
