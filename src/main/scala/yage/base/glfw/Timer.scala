package yage.base.glfw

import System.nanoTime

/**
 * time: time in ms since creation
 * timeStep: time in ms since last call to timeStep
 */

class Timer:
  
  private val startTime = milliTime
  private var oldTime = startTime

  def time = 
    milliTime - startTime
  
  def timeStep = 
    val newTime = milliTime
    val difTime = newTime - oldTime
    oldTime = newTime
    difTime
  
  private def milliTime = (nanoTime() / 1000000).toInt
