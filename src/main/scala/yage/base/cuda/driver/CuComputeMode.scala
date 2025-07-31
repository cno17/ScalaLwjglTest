package yage.base.cuda.driver

import jcuda.driver.CUcomputemode.*

enum CuComputeMode(val id: Int):
  case Default extends CuComputeMode(CU_COMPUTEMODE_DEFAULT)
  case ExclusiveProcess extends CuComputeMode(CU_COMPUTEMODE_EXCLUSIVE_PROCESS)
  case Prohibited extends CuComputeMode(CU_COMPUTEMODE_PROHIBITED)