package yage.base.cuda.driver

import jcuda.driver.CUmodule

abstract class CuModule(ptx: String) extends CuObject[CUmodule]:

  def getFunction(name: String) = 0
