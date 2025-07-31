package yage.base.cuda.runtime

import jcuda.cudaDataType.*

enum CudaDataType(val id: Int):

  case R_08_I extends CudaDataType(CUDA_R_8I)
  case R_16_I extends CudaDataType(CUDA_R_16I)
  case R_32_I extends CudaDataType(CUDA_R_32I)
  case R_64_I extends CudaDataType(CUDA_R_64I)
  case R_32_F extends CudaDataType(CUDA_R_32F)
  case R_64_F extends CudaDataType(CUDA_R_64F)
  case C_32_F extends CudaDataType(CUDA_C_32F)
  case C_64_F extends CudaDataType(CUDA_C_64F)
  // todo more needed?
