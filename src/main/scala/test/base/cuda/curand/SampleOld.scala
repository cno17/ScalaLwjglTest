package test.base.cuda.curand

import jcuda.Pointer
import jcuda.Sizeof
import jcuda.jcurand.JCurand
import jcuda.jcurand.JCurand.curandCreateGenerator
import jcuda.jcurand.JCurand.curandDestroyGenerator
import jcuda.jcurand.JCurand.curandGenerateUniform
import jcuda.jcurand.JCurand.curandSetPseudoRandomGeneratorSeed
import jcuda.jcurand.curandGenerator
import jcuda.jcurand.curandRngType.CURAND_RNG_PSEUDO_DEFAULT
import jcuda.runtime.JCuda
import jcuda.runtime.JCuda.cudaFree
import jcuda.runtime.JCuda.cudaMalloc
import jcuda.runtime.JCuda.cudaMemcpy
import jcuda.runtime.cudaMemcpyKind.cudaMemcpyDeviceToHost

import java.util.Arrays

/**
 * A small sample showing how to use Curand. This is a direct port
 * of the NVIDIA CURAND documentation example.
 */

object SampleOld:

    def main(args: Array[String]): Unit =
        // Enable exceptions and omit all subsequent error checks
        JCuda.setExceptionsEnabled(true)
        JCurand.setExceptionsEnabled(true)
        val n = 100
        // Allocate n floats on host
        val hostData = new Array[Float](n)
        // Allocate n floats on device
        val deviceData = Pointer()
        cudaMalloc(deviceData, n * Sizeof.FLOAT)
        // Create random number generator and set seed
        val generator = curandGenerator()
        curandCreateGenerator(generator, CURAND_RNG_PSEUDO_DEFAULT)
        curandSetPseudoRandomGeneratorSeed(generator, 1234)
        // Generate n floats on device 
        curandGenerateUniform(generator, deviceData, n)
        // Copy device memory to host 
        cudaMemcpy(Pointer.to(hostData), deviceData, n * Sizeof.FLOAT, cudaMemcpyDeviceToHost)
        // Show result
        println(Arrays.toString(hostData))
        // Cleanup 
        curandDestroyGenerator(generator)
        cudaFree(deviceData)
    
