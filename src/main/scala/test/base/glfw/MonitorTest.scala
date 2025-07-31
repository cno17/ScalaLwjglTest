package test.base.glfw

import org.lwjgl.glfw.GLFW.*
import yage.base.glfw.monitor.Monitor

object MonitorTest:

  def main(args: Array[String]) =
    glfwInit()
    Monitor.primaryMonitor.supportedVideoModes.foreach(println(_))
    glfwTerminate()
