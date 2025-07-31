package yage.base.cuda.driver

import jcuda.driver.CUarray_format

enum CuArrayFormat(val id: Int):

  case I08 extends CuArrayFormat(CUarray_format.CU_AD_FORMAT_SIGNED_INT8)
  case I16 extends CuArrayFormat(CUarray_format.CU_AD_FORMAT_SIGNED_INT16)
  case I32 extends CuArrayFormat(CUarray_format.CU_AD_FORMAT_SIGNED_INT32)
  case U08 extends CuArrayFormat(CUarray_format.CU_AD_FORMAT_UNSIGNED_INT8)
  case U16 extends CuArrayFormat(CUarray_format.CU_AD_FORMAT_UNSIGNED_INT16)
  case U32 extends CuArrayFormat(CUarray_format.CU_AD_FORMAT_UNSIGNED_INT32)
  case F16 extends CuArrayFormat(CUarray_format.CU_AD_FORMAT_HALF)
  case F32 extends CuArrayFormat(CUarray_format.CU_AD_FORMAT_FLOAT)
  // ...