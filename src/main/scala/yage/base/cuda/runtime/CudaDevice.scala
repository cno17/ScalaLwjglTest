package yage.base.cuda.runtime

import jcuda.runtime.JCuda.cudaGetDeviceProperties
import jcuda.runtime.JCuda.cudaSetDevice
import jcuda.runtime.cudaDeviceProp

class CudaDevice(val index: Int):

  val props = getProps()

  def activate() = cudaSetDevice(index)

  def setFlags() =
    
    0

  private def getProps() =
    val res = cudaDeviceProp()
    cudaGetDeviceProperties(res, index)
    res

