package yage.base.cuda.runtime

import jcuda.runtime.cudaChannelFormatKind.*

enum CudaChannelFormatKind(val id: Int):
  case None extends CudaChannelFormatKind(cudaChannelFormatKindNone)
  case F extends CudaChannelFormatKind(cudaChannelFormatKindFloat)
  case S extends CudaChannelFormatKind(cudaChannelFormatKindSigned)
  case SN8x1 extends CudaChannelFormatKind(cudaChannelFormatKindSignedNormalized8X1)
  case SN8x2 extends CudaChannelFormatKind(cudaChannelFormatKindSignedNormalized8X2)
  case SN8x4 extends CudaChannelFormatKind(cudaChannelFormatKindSignedNormalized8X4)
  case SN16x1 extends CudaChannelFormatKind(cudaChannelFormatKindSignedNormalized16X1)
  case SN16x2 extends CudaChannelFormatKind(cudaChannelFormatKindSignedNormalized16X2)
  case SN16x4 extends CudaChannelFormatKind(cudaChannelFormatKindSignedNormalized16X4)
  case U extends CudaChannelFormatKind(cudaChannelFormatKindUnsigned)
  case UN8x1 extends CudaChannelFormatKind(cudaChannelFormatKindUnsignedNormalized8X1)
  case UN8x2 extends CudaChannelFormatKind(cudaChannelFormatKindUnsignedNormalized8X2)
  case UN8x4 extends CudaChannelFormatKind(cudaChannelFormatKindUnsignedNormalized8X4)
  case UN16x1 extends CudaChannelFormatKind(cudaChannelFormatKindUnsignedNormalized16X1)
  case UN16x2 extends CudaChannelFormatKind(cudaChannelFormatKindUnsignedNormalized16X2)
  case UN16x4 extends CudaChannelFormatKind(cudaChannelFormatKindUnsignedNormalized16X4)
  // todo more