package test.cuda.runtime

import jcuda.*
import jcuda.runtime.*

object JCudaRuntimeTest:

  def main(args: Array[String]) =
    val pointer = Pointer()
    JCuda.cudaMalloc(pointer, 4)
    println(pointer)
    JCuda.cudaFree(pointer)
    println("ok")