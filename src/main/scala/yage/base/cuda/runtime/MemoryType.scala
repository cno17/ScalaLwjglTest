package yage.base.cuda.runtime

import jcuda.runtime.cudaMemoryType.cudaMemoryTypeDevice
import jcuda.runtime.cudaMemoryType.cudaMemoryTypeHost
import jcuda.runtime.cudaMemoryType.cudaMemoryTypeManaged
import jcuda.runtime.cudaMemoryType.cudaMemoryTypeUnregistered

enum MemoryType(val id: Int):

  case Device extends MemoryType(cudaMemoryTypeDevice)
  case Host extends MemoryType(cudaMemoryTypeHost)
  case Managed extends MemoryType(cudaMemoryTypeManaged)
  case Unregistered extends MemoryType(cudaMemoryTypeUnregistered)