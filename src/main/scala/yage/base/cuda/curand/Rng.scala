package yage.base.cuda.curand

import jcuda.jcurand.curandGenerator
import jcuda.jcurand.JCurand.curandCreateGenerator
import jcuda.jcurand.JCurand.curandDestroyGenerator
import jcuda.jcurand.JCurand.curandSetPseudoRandomGeneratorSeed
import yage.base.cuda.runtime.CudaObject

class Rng(val typ: RngType, val seed: Long) extends CudaObject[curandGenerator]:
  
  def this() = this(RngType.PsaudoDefault, System.currentTimeMillis())

  override def create() =
    val res = curandGenerator()
    curandCreateGenerator(res, typ.id)
    res

  def generateUniform() = 0

  // ...

  def setSeed(seed:Long) = curandSetPseudoRandomGeneratorSeed(id, seed)


  override def destroy() = curandDestroyGenerator(id)


