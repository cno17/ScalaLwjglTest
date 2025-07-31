package yage.base.glfw.monitor

import org.lwjgl.glfw.GLFWVidMode

class VideoMode(m: GLFWVidMode):

  val sizeX = m.width()
  val sizeY = m.height()
  val redBits = m.redBits()
  val greenBits = m.greenBits()
  val blueBits = m.blueBits()
  val refreshRate = m.refreshRate()
  
  override def toString() =
    s"(sizeX = $sizeX, sizeY = $sizeY, refreshRate = $refreshRate)"

  