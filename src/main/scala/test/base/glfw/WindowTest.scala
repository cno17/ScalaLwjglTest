package test.base.glfw

import yage.base.glfw.window.Window

object WindowTest:

  def main(args: Array[String]) =
    Window.create()
    Window.setMinSize(600, 500)
    Window.mouseEnteredListeners += (() => println("a"))
    Window.mouseLeftListeners += (() => println("b"))
    while !Window.shouldClose() do
      Window.processEvents()
    Window.destroy()
