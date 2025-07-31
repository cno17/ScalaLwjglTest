package yage.base.cuda.driver

import jcuda.driver.CUDA_ARRAY3D_DESCRIPTOR

abstract class CuArray(
  width: Int,
  height: Int,
  depth: Int,
  format: CuArrayFormat) extends CuObject[CuArray]:

  val descriptor = CUDA_ARRAY3D_DESCRIPTOR()

  override def create() =
    descriptor.Width = width
    descriptor.Height = height
    descriptor.Depth = depth
    descriptor.Format = format.id
    null

