package yage.base.cuda.driver.memory

import jcuda.driver.CUdeviceptr
import jcuda.driver.JCudaDriver.cuMemAlloc
import jcuda.driver.JCudaDriver.cuMemFree
import yage.base.cuda.driver.CuObject

class CuDeviceMemory(val size: Long) extends 
  CuMemory[CUdeviceptr](CuMemoryType.Device):

  override def create() =
    val res = CUdeviceptr()
    cuMemAlloc(res, size)
    res

  override def destroy() = cuMemFree(handle)