package yage.base.cuda.runtime

import jcuda.runtime.cudaMemcpyKind.cudaMemcpyDefault
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyDeviceToDevice
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyDeviceToHost
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyHostToDevice
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyHostToHost

enum MemCopyKind(val id: Int):

  case Default extends MemCopyKind(cudaMemcpyDefault)
  case DeviceToDevice extends MemCopyKind(cudaMemcpyDeviceToDevice)
  case DeviceToHost extends MemCopyKind(cudaMemcpyDeviceToHost)
  case HostToDevice extends MemCopyKind(cudaMemcpyHostToDevice)
  case HostToHost extends MemCopyKind(cudaMemcpyHostToHost)