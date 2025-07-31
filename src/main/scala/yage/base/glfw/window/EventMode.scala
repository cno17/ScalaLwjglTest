package yage.base.glfw.window

import org.lwjgl.glfw.GLFW.glfwPollEvents
import org.lwjgl.glfw.GLFW.glfwWaitEvents

/**
 * Games: glfwPollEvents() delegates the events received since the
 * last call to the registered listeners and then returns immediately.
 *
 * Editors: glfwWaitEvents() puts the calling thread to sleep until
 * an event occurs.
 */

enum EventMode(val processor: () => Unit):

  case Poll extends EventMode(glfwPollEvents)
  case Wait extends EventMode(glfwWaitEvents)

  // def processEvents() = processor()