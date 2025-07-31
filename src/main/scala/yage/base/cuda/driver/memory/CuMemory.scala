package yage.base.cuda.driver.memory

import yage.base.cuda.driver.CuObject

abstract class CuMemory[H](val typ: CuMemoryType) extends CuObject[H]
  
