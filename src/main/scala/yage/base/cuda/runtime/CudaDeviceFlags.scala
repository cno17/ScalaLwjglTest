package yage.base.cuda.runtime

import jcuda.runtime.JCuda.*

enum CudaDeviceFlags(val id: Int):

  case LmemResizeToMax extends CudaDeviceFlags(cudaDeviceLmemResizeToMax)
  case MapHost extends CudaDeviceFlags(cudaDeviceMapHost)
  case ScheduleAuto extends CudaDeviceFlags(cudaDeviceScheduleAuto)
  case ScheduleYield extends CudaDeviceFlags(cudaDeviceScheduleYield)
  case ScheduleBlockingSync extends CudaDeviceFlags(cudaDeviceScheduleBlockingSync)
  // todo more?
