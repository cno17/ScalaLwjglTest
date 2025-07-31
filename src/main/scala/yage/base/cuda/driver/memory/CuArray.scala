package yage.base.cuda.driver.memory

import jcuda.driver.CUarray
import jcuda.driver.JCudaDriver.cuArrayDestroy

abstract class CuArray extends CuMemory[CUarray](CuMemoryType.Array):

  def memoryRequirements = 0
  def sparseProperties = 0

  override def destroy() = cuArrayDestroy(handle)
