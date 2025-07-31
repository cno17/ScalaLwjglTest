package yage.base.cuda.driver

import jcuda.driver.JCudaDriver.*
import yage.base.Flag
import yage.base.Flags

enum CuArrayCreateFlag(val id: Int) extends Flag:
  
  case ColorAttachment extends CuArrayCreateFlag(CUDA_ARRAY3D_COLOR_ATTACHMENT)
  case DepthTexture extends CuArrayCreateFlag(CUDA_ARRAY3D_DEPTH_TEXTURE)
  case Cubemap extends CuArrayCreateFlag(CUDA_ARRAY3D_CUBEMAP)
  case Layered extends CuArrayCreateFlag(CUDA_ARRAY3D_LAYERED)
  case Sparse extends CuArrayCreateFlag(CUDA_ARRAY3D_SPARSE)
  // more

class CuArrayCreateFlags(fs: CuArrayCreateFlag*) extends Flags[CuArrayCreateFlag](fs: _*)  
  