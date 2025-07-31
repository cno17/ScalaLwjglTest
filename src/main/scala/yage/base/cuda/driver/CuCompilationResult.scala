package yage.base.cuda.driver

import jcuda.nvrtc.nvrtcResult.*

object CuCompilationResult:

  // TODO
  def apply(id: Int) = null
    
    

enum CuCompilationResult(val id: Int):
  case CompilationError extends CuCompilationResult(NVRTC_ERROR_COMPILATION)
  case InternalError extends CuCompilationResult(NVRTC_ERROR_INTERNAL_ERROR)
  case Success extends CuCompilationResult(NVRTC_SUCCESS)
  // ...
