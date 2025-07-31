package yage.base.cuda.driver

import jcuda.driver.CUcontext
import jcuda.driver.JCudaDriver.cuCtxCreate
import jcuda.driver.JCudaDriver.cuCtxDestroy

class CuContext(device: CuDevice) extends CuObject[CUcontext]:

  override def create() =
    val context = CUcontext()
    cuCtxCreate(context, 0, device.handle)
    context

  def apiVersion = 0

  override def destroy() =
    cuCtxDestroy(handle)