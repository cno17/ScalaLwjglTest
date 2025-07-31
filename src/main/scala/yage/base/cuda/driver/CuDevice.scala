package yage.base.cuda.driver

import jcuda.driver.CUdevice
import jcuda.driver.JCudaDriver.cuDeviceGet

// a little bit strange ...

class CuDevice(i: Int) extends CuObject[CUdevice]:

  override def create() =
    val device = CUdevice()
    cuDeviceGet(device, i)
    device

  override def destroy() = {}