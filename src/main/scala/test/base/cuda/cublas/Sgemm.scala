package test.base.cuda.cublas

import jcuda.Pointer
import jcuda.cudaDataType.CUDA_R_32F
import jcuda.jcublas.JCublas2.cublasCreate
import jcuda.jcublas.JCublas2.cublasDestroy
import jcuda.jcublas.JCublas2.cublasGemmEx
import jcuda.jcublas.JCublas2.cublasGetVector
import jcuda.jcublas.JCublas2.cublasSetVector
import jcuda.jcublas.cublasGemmAlgo.CUBLAS_GEMM_ALGO0
import jcuda.jcublas.cublasHandle
import jcuda.jcublas.cublasOperation.CUBLAS_OP_N
import jcuda.runtime.JCuda.cudaDeviceSynchronize
import jcuda.runtime.JCuda.cudaFree
import jcuda.runtime.JCuda.cudaMalloc
import yage.base.cuda.runtime.CudaApp

/**
 * The BLAS 'sgemm' function computes the matrix C = u * A * B + v * C
 * for scalars u, v and matrices A, B and C
 */

object Sgemm extends CudaApp:

  val n = 10
  val nn = n * n

  override def execute() =

    val u = 0.3f
    val v = 0.7f

    val A = rndFloatArray(nn)
    val B = rndFloatArray(nn)
    val C = rndFloatArray(nn)

    val handle = cublasHandle()
    cublasCreate(handle)

    // allocate device memory
    val d_A: Pointer = Pointer()
    val d_B: Pointer = Pointer()
    val d_C: Pointer = Pointer()
    cudaMalloc(d_A, nn * 4)
    cudaMalloc(d_B, nn * 4)
    cudaMalloc(d_C, nn * 4)

    // copy host memory to device
    cublasSetVector(nn, 4, Pointer.to(A), 1, d_A, 1)
    cublasSetVector(nn, 4, Pointer.to(B), 1, d_B, 1)
    cublasSetVector(nn, 4, Pointer.to(C), 1, d_C, 1)

    // Execute sgemm
    val pU = Pointer.to(Array(u))
    val pV = Pointer.to(Array(v))

    val t1 = System.nanoTime()

    cublasGemmEx(handle,
      CUBLAS_OP_N, CUBLAS_OP_N, n, n, n,
      pU, d_A, CUDA_R_32F, n, d_B, CUDA_R_32F, n, pV, d_C, CUDA_R_32F, n, CUDA_R_32F
      , CUBLAS_GEMM_ALGO0)

    cudaDeviceSynchronize()

    val t2 = System.nanoTime()
    val dt = (t2 - t1) / 1000000
    println(dt)


    // Copy the result from the device to the host
    cublasGetVector(nn, 4, d_C, 1, Pointer.to(C), 1)

    // Clean up
    cudaFree(d_A)
    cudaFree(d_B)
    cudaFree(d_C)
    cublasDestroy(handle)


    println("ok")
