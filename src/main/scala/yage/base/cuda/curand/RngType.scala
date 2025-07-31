package yage.base.cuda.curand

import jcuda.jcurand.curandRngType.CURAND_RNG_PSEUDO_DEFAULT
import jcuda.jcurand.curandRngType.CURAND_RNG_QUASI_DEFAULT

enum RngType(val id: Int):

  case PsaudoDefault extends RngType(CURAND_RNG_PSEUDO_DEFAULT)
  case QuasiDefault extends RngType(CURAND_RNG_QUASI_DEFAULT)
  // ...