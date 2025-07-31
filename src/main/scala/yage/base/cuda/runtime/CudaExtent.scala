package yage.base.cuda.runtime

import jcuda.runtime.cudaPos
import jcuda.runtime.cudaExtent

class CudaExtent {

  val off = cudaPos()
  val ext = cudaExtent()

  // ...

}
