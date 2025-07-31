package test.base.cuda.curand

import jcuda.jcurand.JCurand.curandCreateGenerator
import jcuda.jcurand.JCurand.curandDestroyGenerator
import jcuda.jcurand.JCurand.curandGenerateUniform
import jcuda.jcurand.JCurand.curandSetPseudoRandomGeneratorSeed
import jcuda.jcurand.curandRngType.CURAND_RNG_PSEUDO_DEFAULT
import jcuda.runtime.JCuda.cudaFree
import jcuda.runtime.JCuda.cudaMalloc
import jcuda.runtime.JCuda.cudaMemcpy
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyDeviceToHost

import java.util.Arrays
import jcuda.Pointer
import jcuda.Sizeof
import jcuda.jcurand.JCurand
import jcuda.jcurand.curandGenerator
import jcuda.runtime.JCuda
import yage.base.cuda.curand.Rng
import yage.base.cuda.runtime.CudaApp

/**
 * A small sample showing how to use Curand. This is a direct port
 * of the NVIDIA CURAND documentation example.
 */

object Sample extends CudaApp:

    override def execute() =
        val rng = Rng()
        val n = 10
        // allocate n floats on the host
        val hostData = new Array[Float](n)
        // allocate n floats on the device
        val deviceData = Pointer()
        cudaMalloc(deviceData, n * 4)
        // create n floats on device
        curandGenerateUniform(rng.id, deviceData, n)
        // copy device memory to host
        cudaMemcpy(Pointer.to(hostData), deviceData, n * 4, cudaMemcpyDeviceToHost)
        // show result
        println(hostData.mkString("", "\n", ""))
        println(s"${hostData.min} - ${hostData.max}")
        // cleanup
        rng.destroy()
        cudaFree(deviceData)
    
    def executeOld() =
        val n = 10
        // allocate n floats on the host
        val hostData = new Array[Float](n)
        // allocate n floats on the device
        val deviceData = Pointer()
        cudaMalloc(deviceData, n * 4)
        // create random number generator and set seed
        val generator = curandGenerator()
        curandCreateGenerator(generator, CURAND_RNG_PSEUDO_DEFAULT)
        curandSetPseudoRandomGeneratorSeed(generator, System.currentTimeMillis())
        // create n floats on device
        curandGenerateUniform(generator, deviceData, n)
        // copy device memory to host
        cudaMemcpy(Pointer.to(hostData), deviceData, n * 4, cudaMemcpyDeviceToHost)
        // show result
        println(hostData.mkString("", "\n", ""))
        println(s"${hostData.min} - ${hostData.max}")
        // cleanup
        curandDestroyGenerator(generator)
        cudaFree(deviceData)

