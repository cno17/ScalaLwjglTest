package test.base.cuda.runtime

import jcuda.Pointer
import jcuda.runtime.JCuda.cudaFree
import jcuda.runtime.JCuda.cudaMalloc
import yage.base.cuda.runtime.CudaApp

// todo needs work

object AsyncCopies extends CudaApp:

  override def execute() =
    println(2)

  private abstract class Memory(val size: Int):

    type FloatArray = Array[Float]

    var pointer: Pointer = null

    def create(): Unit
    // def loadFrom(data: FloatArray): Unit
    // def storeTo(data: FloatArray): Unit
    def destroy(): Unit

  private class DeviceMemory(size: Int) extends Memory(size):

    override def create() =
      pointer = Pointer()
      cudaMalloc(pointer, size)

    override def destroy() =
      cudaFree(pointer)


  private class PageableHostMemory(size: Int) extends Memory(size):

    override def create() =
      pointer = Pointer()
      cudaMalloc(pointer, size)

    override def destroy() =
      cudaFree(pointer)


  private class PageLockedHostMemory(size: Int) extends Memory(size):

    override def create() =
      pointer = Pointer()
      cudaMalloc(pointer, size)

    override def destroy() =
      cudaFree(pointer)

