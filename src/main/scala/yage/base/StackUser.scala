package yage.base

import org.lwjgl.system.MemoryStack

trait StackUser:

  def useStack[R](f: MemoryStack => R) =
    val s = MemoryStack.stackPush()
    val res = f(s)
    MemoryStack.stackPop()
    res
