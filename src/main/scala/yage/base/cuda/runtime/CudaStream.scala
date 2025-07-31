package yage.base.cuda.runtime

import jcuda.runtime.JCuda.cudaStreamCreate
import jcuda.runtime.JCuda.cudaStreamDestroy
import jcuda.runtime.JCuda.cudaStreamSynchronize
import jcuda.runtime.cudaStream_t

/**
 * improve CUDA program performance by overlapping the memory copy and kernel executions.
 */

class CudaStream extends CudaObject[cudaStream_t]:

  // TODO with flags, priority

  override def create() =
    id = cudaStream_t()
    cudaStreamCreate(id)
    id

  def addCallback() = 0

  def attachMemAsync() = 0

  def copyAttributes() = 0

  def synchronize() = cudaStreamSynchronize(id)

  override def destroy() = cudaStreamDestroy(id)

