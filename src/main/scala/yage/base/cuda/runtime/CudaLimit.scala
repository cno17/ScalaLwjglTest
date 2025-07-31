package yage.base.cuda.runtime

import jcuda.runtime.cudaLimit.*

enum CudaLimit(val id: Int):

  case DevRuntimePendingLaunchCount extends CudaLimit(cudaLimitDevRuntimePendingLaunchCount)
  case DevRuntimeSyncDepth extends CudaLimit(cudaLimitDevRuntimeSyncDepth)
  case MallocHeapSize extends CudaLimit(cudaLimitMallocHeapSize)
  case MaxL2FetchGranularity extends CudaLimit(cudaLimitMaxL2FetchGranularity)
  case PersistingL2CacheSize extends CudaLimit(cudaLimitPersistingL2CacheSize)
  case PrintfFifoSize extends CudaLimit(cudaLimitPrintfFifoSize)
  case StackSize extends CudaLimit(cudaLimitStackSize)
