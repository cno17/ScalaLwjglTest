package yage.base.cuda.driver.memory

import jcuda.driver.CUDA_ARRAY_DESCRIPTOR
import jcuda.driver.CUarray
import jcuda.driver.JCudaDriver.cuArrayCreate
import yage.base.cuda.driver.CuArrayFormat


class CuArray1D(sizeI: Int, numChannels: Int, format: CuArrayFormat) extends CuArray:

  override def create() =
    val a = CUarray()
    val ad = CUDA_ARRAY_DESCRIPTOR()
    ad.Width = sizeI
    ad.Height = 0
    ad.NumChannels = numChannels
    ad.Format = format.id
    cuArrayCreate(a, ad)
    a

