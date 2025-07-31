package yage.base.cuda.driver.memory

import jcuda.driver.CUmemorytype.*

enum CuMemoryType(val id: Int):
  case Array extends CuMemoryType(CU_MEMORYTYPE_ARRAY)
  case Device extends CuMemoryType(CU_MEMORYTYPE_DEVICE)
  case Host extends CuMemoryType(CU_MEMORYTYPE_HOST)
  case Unified extends CuMemoryType(CU_MEMORYTYPE_UNIFIED)